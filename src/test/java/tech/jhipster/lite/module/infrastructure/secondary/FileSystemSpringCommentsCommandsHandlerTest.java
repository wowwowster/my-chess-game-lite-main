package tech.jhipster.lite.module.infrastructure.secondary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static tech.jhipster.lite.module.domain.JHipsterModule.comment;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyKey;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyValue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.javaproperties.PropertyKey;
import tech.jhipster.lite.module.domain.javaproperties.PropertyValue;
import tech.jhipster.lite.module.domain.javaproperties.SpringComment;
import tech.jhipster.lite.module.domain.javaproperties.SpringComments;
import tech.jhipster.lite.module.domain.javaproperties.SpringPropertiesBlockComment;
import tech.jhipster.lite.module.domain.javaproperties.SpringPropertiesBlockComments;
import tech.jhipster.lite.module.domain.javaproperties.SpringPropertyType;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

@UnitTest
class FileSystemSpringCommentsCommandsHandlerTest {

  private static final FileSystemSpringCommentsCommandsHandler handler = new FileSystemSpringCommentsCommandsHandler();

  @ParameterizedTest
  @ValueSource(strings = { "src/main/resources/config/application.properties", "src/main/resources/application.properties" })
  void shouldCommentMainProperties(String propertiesPath) {
    String path = TestFileUtils.tmpDirForTest();
    Path propetiesFile = Paths.get(path, propertiesPath);
    loadDefaultProperties(propetiesFile);

    handler.handle(folder(path), commentOnMain("spring.application.name"), emptySpringPropertiesBlockComments());

    assertThat(content(propetiesFile)).contains("""
		# This is a comment
		spring.application.name""");
  }

  @ParameterizedTest
  @ValueSource(strings = { "src/main/resources/config/bootstrap.properties", "src/main/resources/bootstrap.properties" })
  void shouldCommentMainBootStrapProperties(String propertiesPath) {
    String path = TestFileUtils.tmpDirForTest();
    Path propetiesFile = Paths.get(path, propertiesPath);
    loadDefaultProperties(propetiesFile);

    handler.handle(folder(path), commentOnMainBootstrap("spring.application.name"), emptySpringPropertiesBlockComments());

    assertThat(content(propetiesFile)).contains("""
		# This is a comment
		spring.application.name""");
  }

  @ParameterizedTest
  @ValueSource(strings = { "src/test/resources/config/application.properties", "src/test/resources/application.properties" })
  void shouldCommentTestProperties(String propertiesPath) {
    String path = TestFileUtils.tmpDirForTest();
    Path propetiesFile = Paths.get(path, propertiesPath);
    loadDefaultProperties(propetiesFile);

    handler.handle(folder(path), commentOnTest("logging.level.tech.jhipster.lite"), emptySpringPropertiesBlockComments());

    assertThat(content(propetiesFile)).contains("""
		# This is a comment
		logging.level.tech.jhipster.lite""");
  }

  @ParameterizedTest
  @ValueSource(strings = { "src/test/resources/config/bootstrap.properties", "src/test/resources/bootstrap.properties" })
  void shouldCommentTestBootStrapProperties(String propertiesPath) {
    String path = TestFileUtils.tmpDirForTest();
    Path propetiesFile = Paths.get(path, propertiesPath);
    loadDefaultProperties(propetiesFile);

    handler.handle(folder(path), commentOnTestBootstrap("logging.level.tech.jhipster.lite"), emptySpringPropertiesBlockComments());

    assertThat(content(propetiesFile)).contains("""
		# This is a comment
		logging.level.tech.jhipster.lite""");
  }

  @Test
  void shouldNotCommentWhenFileNotExists() {
    String path = TestFileUtils.tmpDirForTest();

    handler.handle(folder(path), commentOnMain("spring.application.name"), emptySpringPropertiesBlockComments());

    Throwable thrown = catchThrowable(() -> {
      content(Paths.get(path, "src/main/resources/config/application.properties"));
    });
    assertThat(thrown).hasCauseInstanceOf(NoSuchFileException.class);
  }

  @Test
  void shouldNotCommentWhenPropertieKeyDoesNotExists() {
    String path = TestFileUtils.tmpDirForTest();
    Path propetiesFile = Paths.get(path, "src/main/resources/config/application.properties");
    loadDefaultProperties(propetiesFile);

    handler.handle(folder(path), commentOnMain("springdoc.swagger-ui.operationsSorter"), emptySpringPropertiesBlockComments());

    assertThat(content(propetiesFile)).doesNotContain("""
		# This is a comment
		springdoc.swagger-ui.operationsSorter""");
  }

  @ParameterizedTest
  @ValueSource(strings = { "spring.application.name", "logging.level.tech.jhipster.lite" })
  void shouldOverwritePreviousComment(String propertyKey) {
    String path = TestFileUtils.tmpDirForTest();
    Path propetiesFile = Paths.get(path, "src/main/resources/config/application.properties");
    loadDefaultProperties(propetiesFile);

    handler.handle(folder(path), commentOnMain(propertyKey), emptySpringPropertiesBlockComments());
    handler.handle(folder(path), commentOnMain(propertyKey), emptySpringPropertiesBlockComments());

    assertThat(content(propetiesFile)).doesNotContain("""
      # This is a comment
      # This is a comment
      """ + propertyKey);
    assertThat(content(propetiesFile)).contains("""
		# This is a comment
		""" + propertyKey);
  }

  @ParameterizedTest
  @ValueSource(strings = { "src/main/resources/config/application.properties", "src/main/resources/application.properties" })
  void shouldOrganizePropertiesInBlockWithCommentAtTheBeginnerMainProperties(String propertiesPath) {
    String path = TestFileUtils.tmpDirForTest();
    Path propetiesFile = Paths.get(path, propertiesPath);
    String properties =
      """
			spring.application.name=JHLite
			springdoc.swagger-ui.tryItOutEnabled=alpha
			logging.level.tech.jhipster.lite=INFO
			springdoc.swagger-ui.tagsSorter=alpha
			springdoc.swagger-ui.operationsSorter=alpha""";
    createPropertiesFile(propetiesFile, properties);

    handler.handle(folder(path), emptySpringComments(), springPropertiesBlockCommentsOnPropertyType(SpringPropertyType.MAIN_PROPERTIES));

    assertThat(content(propetiesFile))
      .contains(
        """
		spring.application.name=JHLite
		logging.level.tech.jhipster.lite=INFO
		## Swagger properties
		springdoc.swagger-ui.operationsSorter=alpha
		springdoc.swagger-ui.tagsSorter=alpha
		springdoc.swagger-ui.tryItOutEnabled=alpha"""
      );
  }

  @ParameterizedTest
  @ValueSource(strings = { "src/test/resources/config/application.properties", "src/test/resources/application.properties" })
  void shouldOrganizePropertiesAndCommentsInBlockWithCommentAtTheBeginnerTestProperties(String propertiesPath) {
    String path = TestFileUtils.tmpDirForTest();
    Path propetiesFile = Paths.get(path, propertiesPath);
    String properties =
      """
        spring.application.name=JHLite
        # This is a comment
        springdoc.swagger-ui.tryItOutEnabled=alpha
        logging.level.tech.jhipster.lite=INFO
        # This is a comment
        springdoc.swagger-ui.tagsSorter=alpha
        springdoc.swagger-ui.operationsSorter=alpha""";
    createPropertiesFile(propetiesFile, properties);

    handler.handle(folder(path), emptySpringComments(), springPropertiesBlockCommentsOnPropertyType(SpringPropertyType.TEST_PROPERTIES));

    assertThat(content(propetiesFile))
      .contains(
        """
          spring.application.name=JHLite
          logging.level.tech.jhipster.lite=INFO
          ## Swagger properties
          springdoc.swagger-ui.operationsSorter=alpha
          # This is a comment
          springdoc.swagger-ui.tagsSorter=alpha
          # This is a comment
          springdoc.swagger-ui.tryItOutEnabled=alpha"""
      );
  }

  @ParameterizedTest
  @ValueSource(strings = { "src/test/resources/config/bootstrap.properties", "src/test/resources/bootstrap.properties" })
  void shouldNotDuplicatePropertiesBlockCommentTestBootStrapProperties(String propertiesPath) {
    String path = TestFileUtils.tmpDirForTest();
    Path propetiesFile = Paths.get(path, propertiesPath);
    String properties =
      """
        spring.application.name=JHLite
        logging.level.tech.jhipster.lite=INFO
        ## Swagger properties
        springdoc.swagger-ui.operationsSorter=alpha
        springdoc.swagger-ui.tagsSorter=alpha
        springdoc.swagger-ui.tryItOutEnabled=alpha""";
    createPropertiesFile(propetiesFile, properties);

    handler.handle(
      folder(path),
      emptySpringComments(),
      springPropertiesBlockCommentsOnPropertyType(SpringPropertyType.TEST_BOOTSTRAP_PROPERTIES)
    );

    assertThat(content(propetiesFile))
      .doesNotContain(
        """
          spring.application.name=JHLite
          logging.level.tech.jhipster.lite=INFO
          ## Swagger properties
          ## Swagger properties
          springdoc.swagger-ui.operationsSorter=alpha
          springdoc.swagger-ui.tagsSorter=alpha
          springdoc.swagger-ui.tryItOutEnabled=alpha"""
      );
  }

  @Test
  void shouldNotOrganizePropertiesWhenFileNotExists() {
    String path = TestFileUtils.tmpDirForTest();

    handler.handle(folder(path), emptySpringComments(), springPropertiesBlockCommentsOnPropertyType(SpringPropertyType.MAIN_PROPERTIES));

    Throwable thrown = catchThrowable(() -> {
      content(Paths.get(path, "src/main/resources/config/application.properties"));
    });
    assertThat(thrown).hasCauseInstanceOf(NoSuchFileException.class);
  }

  private JHipsterProjectFolder folder(String path) {
    return new JHipsterProjectFolder(path);
  }

  private SpringComments commentOnMain(String propertyKey) {
    return new SpringComments(
      List.of(
        SpringComment
          .builder(SpringPropertyType.MAIN_PROPERTIES)
          .key(propertyKey(propertyKey))
          .comment(comment("This is a comment"))
          .build()
      )
    );
  }

  private SpringComments commentOnMainBootstrap(String propertyKey) {
    return new SpringComments(
      List.of(
        SpringComment
          .builder(SpringPropertyType.MAIN_BOOTSTRAP_PROPERTIES)
          .key(propertyKey(propertyKey))
          .comment(comment("This is a comment"))
          .build()
      )
    );
  }

  private SpringComments commentOnTest(String propertyKey) {
    return new SpringComments(
      List.of(
        SpringComment
          .builder(SpringPropertyType.TEST_PROPERTIES)
          .key(propertyKey(propertyKey))
          .comment(comment("This is a comment"))
          .build()
      )
    );
  }

  private SpringComments commentOnTestBootstrap(String propertyKey) {
    return new SpringComments(
      List.of(
        SpringComment
          .builder(SpringPropertyType.TEST_BOOTSTRAP_PROPERTIES)
          .key(propertyKey(propertyKey))
          .comment(comment("This is a comment"))
          .build()
      )
    );
  }

  private static String content(Path file) {
    try {
      return Files.readString(file);
    } catch (IOException e) {
      throw new AssertionError(e.getMessage(), e);
    }
  }

  private void loadDefaultProperties(Path propertiesFile) {
    try {
      Files.createDirectories(propertiesFile.getParent());

      Files.copy(Paths.get("src/test/resources/projects/project-with-spring-properties/application.properties"), propertiesFile);
    } catch (IOException e) {
      throw new AssertionError(e.getMessage(), e);
    }
  }

  private SpringPropertiesBlockComments emptySpringPropertiesBlockComments() {
    return new SpringPropertiesBlockComments(List.of());
  }

  private SpringComments emptySpringComments() {
    return new SpringComments(List.of());
  }

  private void createPropertiesFile(Path propertiesFile, String properties) {
    try {
      Files.createDirectories(propertiesFile.getParent());
      Files.createFile(propertiesFile);

      Files.write(propertiesFile, properties.getBytes(StandardCharsets.UTF_8));
    } catch (IOException e) {
      throw new AssertionError(e.getMessage(), e);
    }
  }

  private SpringPropertiesBlockComments springPropertiesBlockCommentsOnPropertyType(SpringPropertyType springPropertyType) {
    Map<PropertyKey, PropertyValue> properties = new LinkedHashMap<>();
    properties.put(propertyKey("springdoc.swagger-ui.operationsSorter"), propertyValue("alpha"));
    properties.put(propertyKey("springdoc.swagger-ui.tagsSorter"), propertyValue("alpha"));
    properties.put(propertyKey("springdoc.swagger-ui.tryItOutEnabled"), propertyValue("alpha"));
    return new SpringPropertiesBlockComments(
      List.of(
        SpringPropertiesBlockComment.builder(springPropertyType).comment(comment("Swagger properties")).properties(properties).build()
      )
    );
  }
}
