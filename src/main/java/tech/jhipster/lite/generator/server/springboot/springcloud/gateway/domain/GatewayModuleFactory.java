package tech.jhipster.lite.generator.server.springboot.springcloud.gateway.domain;

import static tech.jhipster.lite.generator.server.springboot.springcloud.common.domain.SpringCloudModuleDependencies.*;
import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.javaproperties.PropertyValue;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class GatewayModuleFactory {

  private static final String GATEWAY_PACKAGE = "technical/infrastructure/primary/rest";
  private static final JHipsterSource SOURCE = from("server/springboot/springcloud/gateway/java");
  private static final PropertyValue TRUE_VALUE = propertyValue("true");

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    String packagePath = properties.packagePath();
    JHipsterDestination testDestination = toSrcTestJava().append(packagePath).append(GATEWAY_PACKAGE);
    JHipsterDestination destination = toSrcMainJava().append(packagePath).append(GATEWAY_PACKAGE);

    //@formatter:off
    return moduleBuilder(properties)
      .javaDependencies()
        .addDependencyManagement(springCloudDependenciesManagement())
        .addDependency(SPRING_CLOUD_GROUP, artifactId("spring-cloud-starter-bootstrap"))
        .addDependency(SPRING_CLOUD_GROUP, artifactId("spring-cloud-starter-gateway"))
        .and()
      .springMainBootstrapProperties()
        .set(propertyKey("spring.application.name"), propertyValue(properties.projectBaseName().get()))
        .set(propertyKey("spring.cloud.gateway.discovery.locator.enabled"), TRUE_VALUE)
        .set(propertyKey("spring.cloud.gateway.discovery.locator.lower-case-service-id"), TRUE_VALUE)
        .set(propertyKey("spring.cloud.gateway.discovery.locator.predicates[0].name"), propertyValue("Path"))
        .set(
          propertyKey("spring.cloud.gateway.discovery.locator.predicates[0].args[pattern]"),
          propertyValue("'/services/'+serviceId.toLowerCase()+'/**'")
        )
        .set(propertyKey("spring.cloud.gateway.discovery.locator.filters[0].name"), propertyValue("RewritePath"))
        .set(
          propertyKey("spring.cloud.gateway.discovery.locator.filters[0].args[regexp]"),
          propertyValue("'/services/' + serviceId.toLowerCase() + '/(?<remaining>.*)'")
        )
        .set(propertyKey("spring.cloud.gateway.discovery.locator.filters[0].args[replacement]"), propertyValue("'/${remaining}'"))
        .and()
      .springTestBootstrapProperties()
        .set(propertyKey("spring.application.name"), propertyValue(properties.projectBaseName().get()))
        .set(propertyKey("spring.cloud.gateway.discovery.locator.enabled"), propertyValue("false"))
        .and()
      .files()
        .add(SOURCE.template("GatewayResource.java"), destination.append("GatewayResource.java"))
        .add(SOURCE.template("RouteVM.java"), destination.append("vm/RouteVM.java"))
        .add(SOURCE.template("test/GatewayResourceIT.java"), testDestination.append("GatewayResourceIT.java"))
        .and()
      .build();
    //@formatter:on
  }
}
