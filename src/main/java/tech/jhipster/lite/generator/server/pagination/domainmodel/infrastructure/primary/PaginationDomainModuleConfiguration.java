package tech.jhipster.lite.generator.server.pagination.domainmodel.infrastructure.primary;

import static tech.jhipster.lite.generator.JHLiteModuleSlug.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.pagination.domainmodel.application.PaginationDomainApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class PaginationDomainModuleConfiguration {

  @Bean
  public JHipsterModuleResource paginationDomainModule(PaginationDomainApplicationService paginationDomain) {
    return JHipsterModuleResource
      .builder()
      .slug(PAGINATION_DOMAIN)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().addProjectBaseName().build())
      .apiDoc("Pagination", "Add domain model for pagination management")
      .organization(JHipsterModuleOrganization.builder().addDependency(JAVA_BASE).build())
      .tags("server")
      .factory(paginationDomain::buildModule);
  }
}
