package tech.jhipster.lite.module.infrastructure.secondary;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.ErrorStatus;

@UnitTest
class UnknownFileToMoveExceptionTest {

  @Test
  void shouldGetExceptionInformation() {
    UnknownFileToMoveException exception = new UnknownFileToMoveException("path");

    assertThat(exception.getMessage()).isEqualTo("Can't move path, can't find it in project");
    assertThat(exception.status()).isEqualTo(ErrorStatus.BAD_REQUEST);
    assertThat(exception.key()).isEqualTo(ModuleSecondaryErrorKey.UNKNOWN_FILE_TO_MOVE);
    assertThat(exception.parameters()).containsOnly(entry("file", "path"));
  }
}
