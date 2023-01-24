package tech.jhipster.lite.generator.server.springboot.database.mongodb.infrastructure.primary;

import static tech.jhipster.lite.generator.JHLiteModuleSlug.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.database.mongodb.application.MongodbApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class MongoDbModuleConfiguration {

  @Bean
  JHipsterModuleResource mongoDbModule(MongodbApplicationService mongoDb) {
    return JHipsterModuleResource
      .builder()
      .slug(MONGODB)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().addIndentation().build())
      .apiDoc("Spring Boot - Database", "Add MongoDB drivers and dependencies, with testcontainers")
      .organization(JHipsterModuleOrganization.SPRINGBOOT_DEPENDENCY)
      .tags("server", "spring", "spring-boot", "database")
      .factory(mongoDb::buildModule);
  }
}
