package tech.jhipster.lite.generator.init.infrastructure.primary;

import static tech.jhipster.lite.generator.JHLiteModuleSlug.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class InitModuleConfiguration {

  @Bean
  JHipsterModuleResource initModule(InitApplicationService inits) {
    return JHipsterModuleResource
      .builder()
      .slug(INIT)
      .propertiesDefinition(initPropertiesDefinition())
      .apiDoc("Init", "Init project")
      .standalone()
      .tags("server", "init")
      .factory(inits::buildModule);
  }

  private JHipsterModulePropertiesDefinition initPropertiesDefinition() {
    return JHipsterModulePropertiesDefinition.builder().addProjectBaseName().addProjectName().addEndOfLine().addIndentation().build();
  }
}
