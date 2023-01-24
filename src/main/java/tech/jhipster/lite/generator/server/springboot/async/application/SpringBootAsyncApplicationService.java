package tech.jhipster.lite.generator.server.springboot.async.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.async.domain.SpringBootAsyncModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class SpringBootAsyncApplicationService {

  private final SpringBootAsyncModuleFactory factory;

  public SpringBootAsyncApplicationService() {
    factory = new SpringBootAsyncModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
