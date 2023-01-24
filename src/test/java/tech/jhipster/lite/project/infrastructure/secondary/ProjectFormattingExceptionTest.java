package tech.jhipster.lite.project.infrastructure.secondary;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.ErrorStatus;

@UnitTest
class ProjectFormattingExceptionTest {

  @Test
  void shouldGetExceptionWithoutCauseInformation() {
    ProjectFormattingException exception = new ProjectFormattingException("This is an error");

    assertThat(exception.getMessage()).isEqualTo("This is an error");
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
    assertThat(exception.key()).isEqualTo(ProjectErrorKey.FORMATTING_ERROR);
  }

  @Test
  void shouldGetExceptionWithCauseInformation() {
    RuntimeException cause = new RuntimeException();
    ProjectFormattingException exception = new ProjectFormattingException("This is an error", cause);

    assertThat(exception.getMessage()).isEqualTo("This is an error");
    assertThat(exception.getCause()).isEqualTo(cause);
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
    assertThat(exception.key()).isEqualTo(ProjectErrorKey.FORMATTING_ERROR);
  }
}
