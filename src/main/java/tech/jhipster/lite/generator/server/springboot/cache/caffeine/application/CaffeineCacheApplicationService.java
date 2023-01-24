package tech.jhipster.lite.generator.server.springboot.cache.caffeine.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.cache.caffeine.domain.CaffeineCacheModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class CaffeineCacheApplicationService {

  private final CaffeineCacheModuleFactory factory;

  public CaffeineCacheApplicationService() {
    factory = new CaffeineCacheModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
