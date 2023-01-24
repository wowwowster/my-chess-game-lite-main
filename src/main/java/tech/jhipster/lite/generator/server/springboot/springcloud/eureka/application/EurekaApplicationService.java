package tech.jhipster.lite.generator.server.springboot.springcloud.eureka.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.springcloud.eureka.domain.EurekaModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.docker.DockerImages;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class EurekaApplicationService {

  private final EurekaModuleFactory factory;

  public EurekaApplicationService(DockerImages dockerImages) {
    factory = new EurekaModuleFactory(dockerImages);
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
