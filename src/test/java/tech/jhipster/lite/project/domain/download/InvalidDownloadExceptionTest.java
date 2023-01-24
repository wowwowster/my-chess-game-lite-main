package tech.jhipster.lite.project.domain.download;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.ErrorStatus;

@UnitTest
class InvalidDownloadExceptionTest {

  @Test
  void shouldGetExceptionInformation() {
    InvalidDownloadException exception = new InvalidDownloadException();

    assertThat(exception.getMessage()).isEqualTo("A user tried to download a project from a protected folder");
    assertThat(exception.status()).isEqualTo(ErrorStatus.BAD_REQUEST);
    assertThat(exception.key()).isEqualTo(DownloadErrorKey.INVALID_DOWNLOAD);
  }
}
