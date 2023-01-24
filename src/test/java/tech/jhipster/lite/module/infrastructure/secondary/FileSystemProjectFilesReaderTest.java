package tech.jhipster.lite.module.infrastructure.secondary;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.module.domain.ProjectFilesReader;

@UnitTest
class FileSystemProjectFilesReaderTest {

  private static final ProjectFilesReader files = new FileSystemProjectFilesReader();

  @Nested
  @DisplayName("String")
  class FileSystemProjectFilesReaderStringTest {

    @Test
    void shouldNotReadUnknownFileContent() {
      assertThatThrownBy(() -> files.readString("unknown")).isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldReadFileContent() {
      assertThat(files.readString("/generator/dependencies/Dockerfile")).isNotEmpty();
    }
  }

  @Nested
  @DisplayName("Bytes")
  class FileSystemProjectFilesReaderBytesTest {

    @Test
    void shouldNotReadUnknownFileContent() {
      assertThatThrownBy(() -> files.readBytes("unknown")).isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldReadFileContent() {
      assertThat(files.readBytes("/generator/dependencies/Dockerfile")).isNotEmpty();
    }
  }
}
