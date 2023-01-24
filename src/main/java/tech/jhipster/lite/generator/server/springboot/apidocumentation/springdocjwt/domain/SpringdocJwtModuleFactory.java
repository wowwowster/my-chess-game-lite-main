package tech.jhipster.lite.generator.server.springboot.apidocumentation.springdocjwt.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class SpringdocJwtModuleFactory {

  private static final JHipsterSource SOURCE = from("server/springboot/apidocumentation/springdocjwt");

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .files()
        .add(
          SOURCE.template("SpringdocJWTConfiguration.java"),
          toSrcMainJava()
            .append(properties.packagePath())
            .append("technical/infrastructure/primary/springdoc")
            .append("SpringdocJWTConfiguration.java")
        )
        .and()
      .build();
    //@formatter:on
  }
}
