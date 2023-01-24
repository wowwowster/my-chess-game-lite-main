package tech.jhipster.lite.module.infrastructure.secondary.git;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.ErrorStatus;

@UnitTest
class GitCommitExceptionTest {

  @Test
  void shouldGetExceptionInformation() {
    RuntimeException cause = new RuntimeException();
    GitCommitException exception = new GitCommitException("This is an error", cause);

    assertThat(exception.getMessage()).isEqualTo("This is an error");
    assertThat(exception.getCause()).isEqualTo(cause);
    assertThat(exception.key()).isEqualTo(GitErrorKey.COMMIT_ERROR);
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
  }
}
