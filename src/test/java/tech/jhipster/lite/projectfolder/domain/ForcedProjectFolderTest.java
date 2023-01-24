package tech.jhipster.lite.projectfolder.domain;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class ForcedProjectFolderTest {

  private static final String ENDING_BY_UUID_REGEX = ".*\\w{8}(-\\w{4}){3}-\\w{12}$";

  private final ForcedProjectFolder forcedProjectFolder = new ForcedProjectFolder("/tmp/jhipster");

  @Test
  void shouldBeInvalidWithNotMatchingPrefix() {
    assertThat(forcedProjectFolder.isInvalid("/other/folder")).isTrue();
  }

  @Test
  void shouldBeInvalidWithDotDot() {
    assertThat(forcedProjectFolder.isInvalid("/tmp/jhipster/../project")).isTrue();
  }

  @Test
  void shouldBeValid() {
    assertThat(forcedProjectFolder.isInvalid("/tmp/jhipster/project")).isFalse();
  }

  @Test
  void shouldGetValidPath() {
    assertThat(forcedProjectFolder.generatePath()).startsWith(Paths.get("/tmp/jhipster") + File.separator).matches(ENDING_BY_UUID_REGEX);
  }
}
