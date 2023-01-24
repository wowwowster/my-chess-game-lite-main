package tech.jhipster.lite.module.domain.properties;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class JHipsterPropertyExampleTest {

  @Test
  void shouldGetEmptyExampleFromNullExample() {
    assertThat(JHipsterPropertyExample.of(null)).isEmpty();
  }

  @Test
  void shouldGetEmptyExampleFromBlankExample() {
    assertThat(JHipsterPropertyExample.of(" ")).isEmpty();
  }

  @Test
  void shouldGetExampleFromActualExample() {
    assertThat(JHipsterPropertyExample.of("example")).contains(new JHipsterPropertyExample("example"));
  }

  @Test
  void shouldGetValueFromGet() {
    assertThat(JHipsterPropertyExample.of("example").get().get()).isEqualTo(new JHipsterPropertyExample("example").get());
  }
}
