package tech.jhipster.lite.module.domain.properties;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.ErrorStatus;

@UnitTest
class InvalidProjectBaseNameExceptionTest {

  @Test
  void shouldGetExceptionInformation() {
    InvalidProjectBaseNameException exception = new InvalidProjectBaseNameException();

    assertThat(exception.getMessage())
      .isEqualTo("Project names can't have special characters, only letters (no accents) and numbers allowed");
    assertThat(exception.key()).isEqualTo(PropertiesErrorKey.INVALID_BASE_NAME);
    assertThat(exception.status()).isEqualTo(ErrorStatus.BAD_REQUEST);
  }
}
