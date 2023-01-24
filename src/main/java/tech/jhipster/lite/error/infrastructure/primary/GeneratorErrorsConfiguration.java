package tech.jhipster.lite.error.infrastructure.primary;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
class GeneratorErrorsConfiguration {

  @Bean("generatorErrorMessageSource")
  MessageSource generatorErrorMessageSource() {
    ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();

    source.setBasename("classpath:/messages/errors/generator-errors-messages");
    source.setDefaultEncoding("UTF-8");

    return source;
  }
}
