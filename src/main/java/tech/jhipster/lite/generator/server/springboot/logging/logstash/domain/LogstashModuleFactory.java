package tech.jhipster.lite.generator.server.springboot.logging.logstash.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.LogLevel;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class LogstashModuleFactory {

  private static final JHipsterSource SOURCE = from("server/springboot/logging/logstash");

  private static final String LOGSTASH_SECONDARY = "technical/infrastructure/secondary/logstash";

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    String packagePath = properties.packagePath();

    //@formatter:off
    return moduleBuilder(properties)
      .javaDependencies()
        .addDependency(groupId("net.logstash.logback"), artifactId("logstash-logback-encoder"), versionSlug("logstash-logback-encoder"))
        .and()
      .files()
        .batch(SOURCE.append("main"), toSrcMainJava().append(packagePath).append(LOGSTASH_SECONDARY))
          .addTemplate("LogstashTcpConfiguration.java")
          .addTemplate("LogstashTcpLifeCycle.java")
          .addTemplate("LogstashTcpProperties.java")
        .and()
        .batch(SOURCE.append("test"), toSrcTestJava().append(packagePath).append(LOGSTASH_SECONDARY))
          .addTemplate("LogstashTcpConfigurationIT.java")
          .addTemplate("LogstashTcpConfigurationTest.java")
          .addTemplate("LogstashTcpLifeCycleTest.java")
          .addTemplate("LogstashTcpPropertiesTest.java")
          .and()
        .and()
      .springMainProperties()
        .set(propertyKey("application.logging.logstash.tcp.enabled"), propertyValue("false"))
        .set(propertyKey("application.logging.logstash.tcp.host"), propertyValue("localhost"))
        .set(propertyKey("application.logging.logstash.tcp.port"), propertyValue("5000"))
        .set(propertyKey("application.logging.logstash.tcp.ring-buffer-size"), propertyValue("8192"))
        .set(propertyKey("application.logging.logstash.tcp.shutdown_grace_period"), propertyValue("PT1M"))
        .and()
      .springTestLogger("net.logstash.logback", LogLevel.ERROR)
      .springTestLogger("org.jboss.logging", LogLevel.WARN)
      .build();
    //@formatter:on
  }
}
