package tech.jhipster.lite.generator.server.pagination.jpa.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class JpaPaginationModuleFactory {

  private static final JHipsterSource SOURCE = from("server/pagination/jpa");

  private static final String SECONDARY_DESTINATION = "pagination/infrastructure/secondary";

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    String baseName = properties.projectBaseName().capitalized();

    String packagePath = properties.packagePath();

    JHipsterDestination testDestination = toSrcTestJava().append(packagePath).append(SECONDARY_DESTINATION);
    JHipsterDestination mainDestination = toSrcMainJava().append(packagePath).append(SECONDARY_DESTINATION);

    //@formatter:off
    return moduleBuilder(properties)
      .context()
        .put("baseName", baseName)
        .and()
      .documentation(documentationTitle("Jpa pages"), SOURCE.template("jpa-pages.md"))
      .files()
        .add(SOURCE.template("AppPages.java"), mainDestination.append(baseName + "Pages.java"))
        .add(SOURCE.template("AppPagesTest.java"), testDestination.append(baseName + "PagesTest.java"))
        .and()
      .build();
    //@formatter:on
  }
}
