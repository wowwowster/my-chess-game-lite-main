package tech.jhipster.lite.generator.server.springboot.apidocumentation.springdocokta.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyKey;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyValue;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.javaproperties.PropertyValue;
import tech.jhipster.lite.module.domain.javaproperties.SpringProfile;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class SpringDocOktaModuleFactory {

  private static final SpringProfile OKTA_SPRING_PROFILE = new SpringProfile("okta");

  private static final String OKTA_CLIENT_ID_PROPERTY = "oktaClientId";
  private static final String OKTA_DOMAIN_PROPERTY = "oktaDomain";

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .springMainProperties(OKTA_SPRING_PROFILE)
        .set(propertyKey("springdoc.swagger-ui.oauth.client-id"), clientId(properties))
        .set(propertyKey("springdoc.swagger-ui.oauth.realm"), propertyValue("jhipster"))
        .set(propertyKey("springdoc.swagger-ui.oauth.scopes"), propertyValue("openid","profile","email"))
        .set(propertyKey("springdoc.oauth2.authorization-url"), authorizationUrl(properties))
        .and()
      .build();
    //@formatter:on
  }

  private static PropertyValue clientId(JHipsterModuleProperties properties) {
    return propertyValue(properties.getString(OKTA_CLIENT_ID_PROPERTY));
  }

  private static PropertyValue authorizationUrl(JHipsterModuleProperties properties) {
    return propertyValue(
      new StringBuilder()
        .append("https://")
        .append(properties.getString(OKTA_DOMAIN_PROPERTY))
        .append("/oauth2/default/v1/authorize?nonce=\"jhipster\"")
        .toString()
    );
  }
}
