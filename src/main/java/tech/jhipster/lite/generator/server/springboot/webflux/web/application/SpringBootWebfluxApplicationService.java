package tech.jhipster.lite.generator.server.springboot.webflux.web.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.webflux.web.domain.SpringBootWebfluxModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class SpringBootWebfluxApplicationService {

  private final SpringBootWebfluxModuleFactory factory;

  public SpringBootWebfluxApplicationService() {
    factory = new SpringBootWebfluxModuleFactory();
  }

  public JHipsterModule buildNettyModule(JHipsterModuleProperties properties) {
    return factory.buildNettyModule(properties);
  }

  public JHipsterModule buildEmptyModule(JHipsterModuleProperties properties) {
    return factory.buildEmptyModule(properties);
  }
}
