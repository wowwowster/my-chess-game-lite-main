package tech.jhipster.lite.module.infrastructure.secondary;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import tech.jhipster.lite.common.domain.Enums;
import tech.jhipster.lite.common.domain.ExcludeFromGeneratedCodeCoverage;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.module.domain.Indentation;
import tech.jhipster.lite.module.domain.npm.NpmVersionSource;
import tech.jhipster.lite.module.domain.npm.NpmVersions;
import tech.jhipster.lite.module.domain.packagejson.JHipsterModulePackageJson;
import tech.jhipster.lite.module.domain.packagejson.PackageJsonDependencies;
import tech.jhipster.lite.module.domain.packagejson.PackageJsonDependency;
import tech.jhipster.lite.module.domain.packagejson.Scripts;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

class FileSystemPackageJsonHandler {

  private static final String QUOTE = "\"";
  private static final String LINE_END = ",";
  private static final String LINE_SEPARATOR = LINE_END + LINE_BREAK;

  private final NpmVersions npmVersions;

  public FileSystemPackageJsonHandler(NpmVersions npmVersions) {
    Assert.notNull("npmVersions", npmVersions);

    this.npmVersions = npmVersions;
  }

  public void handle(Indentation indentation, JHipsterProjectFolder projectFolder, JHipsterModulePackageJson packageJson) {
    Assert.notNull("indentation", indentation);
    Assert.notNull("projectFolder", projectFolder);
    Assert.notNull("packageJson", packageJson);

    if (packageJson.isEmpty()) {
      return;
    }

    Path file = getPackageJsonFile(projectFolder);

    String content = readContent(file);
    content = replaceScripts(indentation, packageJson.scripts(), content);
    content = replaceDevDependencies(indentation, packageJson.devDependencies(), content);
    content = replaceDependencies(indentation, packageJson.dependencies(), content);

    content = cleanupLineBreaks(indentation, content);
    write(file, content);
  }

  private Path getPackageJsonFile(JHipsterProjectFolder projectFolder) {
    Path file = projectFolder.filePath("package.json");

    if (Files.notExists(file)) {
      throw new MissingPackageJsonException(projectFolder);
    }

    return file;
  }

  private String cleanupLineBreaks(Indentation indentation, String content) {
    return content.replaceAll(",(\\s*|[\r\n]*)}", LINE_BREAK + indentation.spaces() + "}");
  }

  private String replaceScripts(Indentation indentation, Scripts scripts, String content) {
    return JsonReplacer
      .builder()
      .blocName("scripts")
      .jsonContent(content)
      .indentation(indentation)
      .entries(scriptEntries(scripts))
      .replace();
  }

  private List<JsonEntry> scriptEntries(Scripts scripts) {
    return scripts.stream().map(script -> new JsonEntry(script.key().get(), script.command().get())).toList();
  }

  private String replaceDevDependencies(Indentation indentation, PackageJsonDependencies devDependencies, String content) {
    return JsonReplacer
      .builder()
      .blocName("devDependencies")
      .jsonContent(content)
      .indentation(indentation)
      .entries(dependenciesEntries(devDependencies))
      .replace();
  }

  private String replaceDependencies(Indentation indentation, PackageJsonDependencies dependencies, String content) {
    return JsonReplacer
      .builder()
      .blocName("dependencies")
      .jsonContent(content)
      .indentation(indentation)
      .entries(dependenciesEntries(dependencies))
      .replace();
  }

  private List<JsonEntry> dependenciesEntries(PackageJsonDependencies devDependencies) {
    return devDependencies.stream().map(dependency -> new JsonEntry(dependency.packageName().get(), getNpmVersion(dependency))).toList();
  }

  private String getNpmVersion(PackageJsonDependency dependency) {
    return npmVersions.get(dependency.packageName().get(), Enums.map(dependency.versionSource(), NpmVersionSource.class)).get();
  }

  @ExcludeFromGeneratedCodeCoverage(reason = "The error handling is an hard to test implementation detail")
  private String readContent(Path file) {
    try {
      return Files.readString(file);
    } catch (IOException e) {
      throw GeneratorException.technicalError("Error reading " + file.toAbsolutePath().toString() + " content" + e.getMessage(), e);
    }
  }

  @ExcludeFromGeneratedCodeCoverage(reason = "The error handling is an hard to test implementation detail")
  private void write(Path file, String content) {
    try {
      Files.write(file, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);
    } catch (IOException e) {
      throw GeneratorException.technicalError("Error writing " + file.toAbsolutePath().toString() + ": " + e.getMessage(), e);
    }
  }

  private static class JsonReplacer {

    private final String blocName;
    private final String jsonContent;
    private final Indentation indentation;
    private final Collection<JsonEntry> entries;

    private JsonReplacer(JsonReplacerBuilder builder) {
      blocName = builder.blocName;
      jsonContent = builder.jsonContent;
      indentation = builder.indentation;
      entries = builder.entries;
    }

    public static JsonReplacerBuilder builder() {
      return new JsonReplacerBuilder();
    }

    public String handle() {
      if (entries.isEmpty()) {
        return jsonContent;
      }

      String result = removeExistingEntries();

      Matcher blocMatcher = buildBlocMatcher(result);

      if (blocMatcher.find()) {
        result = appendEntries(blocMatcher);
      } else {
        result = appendNewBlock(result);
      }

      return result;
    }

    private String appendEntries(Matcher blocMatcher) {
      return blocMatcher.replaceFirst(match -> {
        StringBuilder result = new StringBuilder().append(match.group(1)).append(LINE_BREAK);

        result.append(entriesBloc(indentation, entries));

        result.append(LINE_END);
        return result.toString();
      });
    }

    private String appendNewBlock(String result) {
      String jsonBloc = new StringBuilder()
        .append(LINE_SEPARATOR)
        .append(indentation.spaces())
        .append(QUOTE)
        .append(blocName)
        .append(QUOTE)
        .append(": {")
        .append(LINE_BREAK)
        .append(entriesBloc(indentation, entries))
        .append(LINE_BREAK)
        .append(indentation.spaces())
        .append("}")
        .toString();

      return result.replaceFirst("(\\s{1,10})\\}(\\s{1,10})$", jsonBloc + "$1}$2");
    }

    private Matcher buildBlocMatcher(String result) {
      return Pattern.compile("(\"" + blocName + "\"\\s*:\\s*\\{)").matcher(result);
    }

    @ExcludeFromGeneratedCodeCoverage(reason = "Combiner can't be tested and an implementation detail")
    private String removeExistingEntries() {
      return entries
        .stream()
        .map(toEntryPattern(blocName, indentation))
        .reduce(jsonContent, (json, entryPattern) -> entryPattern.matcher(json).replaceAll("$1$2"), (first, second) -> first);
    }

    private Function<JsonEntry, Pattern> toEntryPattern(String blocName, Indentation indentation) {
      return entry -> {
        String pattern = new StringBuilder()
          .append("(\"")
          .append(blocName)
          .append("\"\\s*:\\s*\\{)([^}]*)(\"")
          .append(entry.key())
          .append("\"\\s*:\\s*\"[^\\r\\n]+[\\r\\n]{1,2}(\\s{")
          .append(indentation.spacesCount() * 2)
          .append("})?)")
          .toString();

        return Pattern.compile(pattern, Pattern.DOTALL);
      };
    }

    private String entriesBloc(Indentation indentation, Collection<JsonEntry> entries) {
      return entries.stream().map(entry -> entry.toJson(indentation)).collect(Collectors.joining(LINE_SEPARATOR));
    }

    private static class JsonReplacerBuilder {

      private String blocName;
      private String jsonContent;
      private Indentation indentation;
      private Collection<JsonEntry> entries;

      private JsonReplacerBuilder blocName(String blocName) {
        this.blocName = blocName;

        return this;
      }

      private JsonReplacerBuilder jsonContent(String jsonContent) {
        this.jsonContent = jsonContent;

        return this;
      }

      private JsonReplacerBuilder indentation(Indentation indentation) {
        this.indentation = indentation;

        return this;
      }

      private JsonReplacerBuilder entries(Collection<JsonEntry> entries) {
        this.entries = entries;

        return this;
      }

      private String replace() {
        return new JsonReplacer(this).handle();
      }
    }
  }

  private static record JsonEntry(String key, String value) {
    String toJson(Indentation indentation) {
      return new StringBuilder()
        .append(indentation.times(2))
        .append(QUOTE)
        .append(key())
        .append(QUOTE)
        .append(": ")
        .append(QUOTE)
        .append(value())
        .append(QUOTE)
        .toString();
    }
  }
}
