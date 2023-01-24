package tech.jhipster.lite.module.domain.replacement;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.ErrorStatus;

@UnitTest
class MandatoryReplacementExceptionTest {

  @Test
  void shouldGetExceptionInformation() {
    RuntimeException cause = new RuntimeException();
    MandatoryReplacementException exception = new MandatoryReplacementException(cause);

    assertThat(exception.getMessage()).isEqualTo("Error applying mandatory replacement");
    assertThat(exception.getCause()).isEqualTo(cause);
    assertThat(exception.key()).isEqualTo(ReplacementErrorKey.MANDATORY_REPLACEMENT_ERROR);
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
  }
}
