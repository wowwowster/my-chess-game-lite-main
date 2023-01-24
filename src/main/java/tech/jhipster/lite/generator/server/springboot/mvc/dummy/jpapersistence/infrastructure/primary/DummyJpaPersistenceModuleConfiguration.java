package tech.jhipster.lite.generator.server.springboot.mvc.dummy.jpapersistence.infrastructure.primary;

import static tech.jhipster.lite.generator.JHLiteFeatureSlug.*;
import static tech.jhipster.lite.generator.JHLiteModuleSlug.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.mvc.dummy.jpapersistence.application.DummyJpaPersistenceApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class DummyJpaPersistenceModuleConfiguration {

  @Bean
  JHipsterModuleResource dummyJpaPersistenceModule(DummyJpaPersistenceApplicationService dummyJpaPersistence) {
    return JHipsterModuleResource
      .builder()
      .slug(DUMMY_JPA_PERSISTENCE)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().build())
      .apiDoc("Spring Boot - MVC", "Add JPA persistence for dummy feature")
      .organization(
        JHipsterModuleOrganization
          .builder()
          .feature(DUMMY_PERSISTENCE)
          .addDependency(DUMMY_SCHEMA)
          .addDependency(SPRING_BOOT_CUCUMBER_JPA_RESET)
          .build()
      )
      .tags("server")
      .factory(dummyJpaPersistence::buildModule);
  }
}
