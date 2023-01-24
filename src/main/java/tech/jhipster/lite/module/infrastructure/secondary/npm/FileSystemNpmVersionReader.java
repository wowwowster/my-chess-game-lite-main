package tech.jhipster.lite.module.infrastructure.secondary.npm;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;
import tech.jhipster.lite.common.domain.Enums;
import tech.jhipster.lite.module.domain.ProjectFilesReader;
import tech.jhipster.lite.module.domain.npm.NpmPackage;
import tech.jhipster.lite.module.domain.npm.NpmPackagesVersions;
import tech.jhipster.lite.module.domain.npm.NpmPackagesVersions.NpmPackagesVersionsBuilder;
import tech.jhipster.lite.module.domain.npm.NpmVersionSource;

@Repository
@Order(Ordered.LOWEST_PRECEDENCE)
class FileSystemNpmVersionReader implements NpmVersionsReader {

  private static final Pattern DEV_DEPENDENCIES_PATTERN = Pattern.compile("\"devDependencies\"\\s*:\\s*\\{([^}]*)\\}", Pattern.DOTALL);
  private static final Pattern DEPENDENCIES_PATTERN = Pattern.compile("\"dependencies\"\\s*:\\s*\\{([^}]*)\\}", Pattern.DOTALL);
  private static final Pattern PACKAGES_PATTERN = Pattern.compile("\"([^\"]+)\"\\s*:\\s*\"([^\"]+)\"", Pattern.DOTALL);

  private final ProjectFilesReader projectFiles;

  public FileSystemNpmVersionReader(ProjectFilesReader projectFiles) {
    this.projectFiles = projectFiles;
  }

  @Override
  public NpmPackagesVersions get() {
    NpmPackagesVersionsBuilder builder = NpmPackagesVersions.builder();

    Stream.of(NpmVersionSource.values()).forEach(source -> builder.put(source, sourcePackages(source)));

    return builder.build();
  }

  private Collection<NpmPackage> sourcePackages(NpmVersionSource source) {
    String sourceFile = readVersionsFile(source);

    return Stream.concat(packagesIn(sourceFile, DEV_DEPENDENCIES_PATTERN), packagesIn(sourceFile, DEPENDENCIES_PATTERN)).toList();
  }

  private Stream<NpmPackage> packagesIn(String source, Pattern pattern) {
    Matcher dependenciesMatcher = pattern.matcher(source);

    if (dependenciesMatcher.find()) {
      return readPackages(dependenciesMatcher.group(1));
    }

    return Stream.of();
  }

  private Stream<NpmPackage> readPackages(String content) {
    return PACKAGES_PATTERN.matcher(content).results().map(result -> new NpmPackage(result.group(1), result.group(2)));
  }

  private String readVersionsFile(NpmVersionSource source) {
    return projectFiles.readString("/generator/dependencies/" + sourceFolder(source) + "/package.json");
  }

  private String sourceFolder(NpmVersionSource source) {
    return Enums.map(source, NpmVersionSourceFolder.class).folder();
  }
}
