package tech.jhipster.lite.project.infrastructure.secondary;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.ErrorStatus;

@UnitTest
class ProjectZippingExceptionTest {

  @Test
  void shouldGetExceptionWithMessageInformation() {
    ProjectZippingException exception = new ProjectZippingException("This is an error");

    assertThat(exception.getMessage()).isEqualTo("This is an error");
    assertThat(exception.key()).isEqualTo(ProjectErrorKey.ZIPPING_ERROR);
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
  }

  @Test
  void shouldGetExceptionWithCauseInformation() {
    RuntimeException cause = new RuntimeException();
    ProjectZippingException exception = new ProjectZippingException(cause);

    assertThat(exception.getMessage()).isEqualTo("Error creating zip file");
    assertThat(exception.getCause()).isEqualTo(cause);
    assertThat(exception.key()).isEqualTo(ProjectErrorKey.ZIPPING_ERROR);
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
  }
}
