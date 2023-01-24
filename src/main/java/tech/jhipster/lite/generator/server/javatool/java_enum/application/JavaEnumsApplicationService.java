package tech.jhipster.lite.generator.server.javatool.java_enum.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.javatool.java_enum.domain.JavaEnumsModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class JavaEnumsApplicationService {

  private final JavaEnumsModuleFactory javaEnums;

  public JavaEnumsApplicationService() {
    javaEnums = new JavaEnumsModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return javaEnums.buildModule(properties);
  }
}
