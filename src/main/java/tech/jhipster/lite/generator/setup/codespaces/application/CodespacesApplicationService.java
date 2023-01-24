package tech.jhipster.lite.generator.setup.codespaces.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.setup.codespaces.domain.CodespacesModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class CodespacesApplicationService {

  private final CodespacesModuleFactory factory;

  public CodespacesApplicationService() {
    factory = new CodespacesModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
