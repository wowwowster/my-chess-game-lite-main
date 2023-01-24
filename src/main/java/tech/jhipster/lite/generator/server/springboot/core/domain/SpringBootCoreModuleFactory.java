package tech.jhipster.lite.generator.server.springboot.core.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.javabuild.GroupId;
import tech.jhipster.lite.module.domain.javabuildplugin.JavaBuildPlugin;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyScope;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyType;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.module.domain.replacement.TextNeedleBeforeReplacer;

public class SpringBootCoreModuleFactory {

  private static final JHipsterSource SOURCE = from("server/springboot/core");
  private static final JHipsterSource MAIN_SOURCE = SOURCE.append("main");
  private static final JHipsterSource TEST_SOURCE = SOURCE.append("test");

  private static final GroupId SRPING_BOOT_GROUP = groupId("org.springframework.boot");
  private static final String APPLICATION_PROPERTIES = "application.properties";

  private static final String JUNIT_GROUP = "org.junit.jupiter";
  private static final String MOCKITO_GROUP = "org.mockito";

  private static final TextNeedleBeforeReplacer DEFAULT_GOAL_REPLACER = new TextNeedleBeforeReplacer(
    (contentBeforeReplacement, replacement) -> !contentBeforeReplacement.contains("<defaultGoal>"),
    "</build>"
  );

  private static final JHipsterDestination MAIN_RESOURCE_DESTINATION = to("src/main/resources");
  private static final JHipsterDestination MAIN_CONFIG_DESTINATION = MAIN_RESOURCE_DESTINATION.append("config");
  private static final JHipsterDestination TEST_RESOURCES_DESTINATION = to("src/test/resources");
  private static final JHipsterDestination TEST_CONFIG_DESTINATION = TEST_RESOURCES_DESTINATION.append("config");

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    String baseName = properties.projectBaseName().capitalized();
    String packagePath = properties.packagePath();
    JHipsterDestination testDestination = toSrcTestJava().append(packagePath);
    String fullyQualifiedMainClass = properties.basePackage().get() + "." + baseName + "App";

    //@formatter:off
    return moduleBuilder(properties)
      .context()
        .put("baseName", baseName)
        .and()
      .documentation(documentationTitle("Logs spy"), SOURCE.file("logs-spy.md"))
      .javaDependencies()
        .removeDependency(dependencyId(JUNIT_GROUP, "junit-jupiter-engine"))
        .removeDependency(dependencyId(JUNIT_GROUP, "junit-jupiter-params"))
        .removeDependency(dependencyId("org.assertj", "assertj-core"))
        .removeDependency(dependencyId(MOCKITO_GROUP, "mockito-junit-jupiter"))
        .addDependencyManagement(springBootBom())
        .addDependency(SRPING_BOOT_GROUP, artifactId("spring-boot-starter"))
        .addDependency(springBootConfigurationProcessor())
        .addDependency(groupId("org.apache.commons"), artifactId("commons-lang3"))
        .addDependency(springBootTest())
        .and()
      .javaBuildPlugins()
        .pluginManagement(springBootPluginManagement(fullyQualifiedMainClass))
        .plugin(springBootMavenPlugin())
        .and()
      .files()
        .add(MAIN_SOURCE.template("MainApp.java"), toSrcMainJava().append(packagePath).append(baseName + "App.java"))
        .add(MAIN_SOURCE.template("ApplicationStartupTraces.java"), toSrcMainJava().append(packagePath).append("ApplicationStartupTraces.java"))
        .add(TEST_SOURCE.template("IntegrationTest.java"), testDestination.append("IntegrationTest.java"))
        .add(MAIN_SOURCE.template(APPLICATION_PROPERTIES), MAIN_CONFIG_DESTINATION.append(APPLICATION_PROPERTIES))
        .add(MAIN_SOURCE.template("application-local.properties"), MAIN_CONFIG_DESTINATION.append("application-local.properties"))
        .add(TEST_SOURCE.template("application-test.properties"), TEST_CONFIG_DESTINATION.append(APPLICATION_PROPERTIES))
        .add(MAIN_SOURCE.template("logback-spring.xml"), MAIN_RESOURCE_DESTINATION.append("logback-spring.xml"))
        .add(TEST_SOURCE.template("logback.xml"), TEST_RESOURCES_DESTINATION.append("logback.xml"))
        .add(TEST_SOURCE.template("LogsSpy.java"), toSrcTestJava().append(properties.packagePath()).append("LogsSpy.java"))
        .add(TEST_SOURCE.template("ApplicationStartupTracesTest.java"), toSrcTestJava().append(packagePath).append("ApplicationStartupTracesTest.java"))
        .and()
      .optionalReplacements()
        .in(path("pom.xml"))
          .add(DEFAULT_GOAL_REPLACER, properties.indentation().times(2) + "<defaultGoal>spring-boot:run</defaultGoal>")
          .and()
        .and()
      .build();
    //@formatter:on
  }

  private JavaDependency springBootBom() {
    return JavaDependency
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-dependencies")
      .versionSlug("spring-boot")
      .type(JavaDependencyType.POM)
      .scope(JavaDependencyScope.IMPORT)
      .build();
  }

  private JavaDependency springBootConfigurationProcessor() {
    return JavaDependency.builder().groupId(SRPING_BOOT_GROUP).artifactId("spring-boot-configuration-processor").optional().build();
  }

  private JavaDependency springBootTest() {
    return JavaDependency
      .builder()
      .groupId(SRPING_BOOT_GROUP)
      .artifactId("spring-boot-starter-test")
      .scope(JavaDependencyScope.TEST)
      .build();
  }

  private JavaBuildPlugin springBootPluginManagement(String fullyQualifiedMainClass) {
    return JavaBuildPlugin
      .builder()
      .groupId(SRPING_BOOT_GROUP)
      .artifactId("spring-boot-maven-plugin")
      .versionSlug("spring-boot")
      .additionalElements(
        """
        <executions>
          <execution>
            <goals>
              <goal>repackage</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
          <mainClass>%s</mainClass>
        </configuration>
        """.formatted(
            fullyQualifiedMainClass
          )
      )
      .build();
  }

  private JavaBuildPlugin springBootMavenPlugin() {
    return JavaBuildPlugin.builder().groupId(SRPING_BOOT_GROUP).artifactId("spring-boot-maven-plugin").build();
  }
}
