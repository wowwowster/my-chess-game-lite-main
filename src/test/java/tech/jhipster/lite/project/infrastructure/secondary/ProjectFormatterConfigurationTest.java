package tech.jhipster.lite.project.infrastructure.secondary;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import ch.qos.logback.classic.Level;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.LogsSpy;
import tech.jhipster.lite.UnitTest;

@UnitTest
@ExtendWith({ MockitoExtension.class, LogsSpy.class })
class ProjectFormatterConfigurationTest {

  private static final ProjectFormatterConfiguration configuration = new ProjectFormatterConfiguration();

  @Mock
  private NpmInstallationReader npmInstallation;

  private final LogsSpy logs;

  public ProjectFormatterConfigurationTest(LogsSpy logs) {
    this.logs = logs;
  }

  @Test
  void shouldGetTraceFormatterWithoutInstalledNpm() {
    when(npmInstallation.get()).thenReturn(NpmInstallationType.NONE);

    assertThat(configuration.projectFormatter(npmInstallation)).isExactlyInstanceOf(TraceProjectFormatter.class);
    logs.shouldHave(Level.INFO, "Using trace formatter");
  }

  @Test
  void shouldGetUnixFormatterForUnixInstallation() {
    when(npmInstallation.get()).thenReturn(NpmInstallationType.UNIX);

    NpmProjectFormatter formatter = (NpmProjectFormatter) configuration.projectFormatter(npmInstallation);

    assertThat(formatter.command()).isEqualTo("npm");
    logs.shouldHave(Level.INFO, "Using unix formatter");
  }

  @Test
  void shouldGetWindowsFormatterForWindowsInstallation() {
    when(npmInstallation.get()).thenReturn(NpmInstallationType.WINDOWS);

    NpmProjectFormatter formatter = (NpmProjectFormatter) configuration.projectFormatter(npmInstallation);

    assertThat(formatter.command()).isEqualTo("npm.cmd");
    logs.shouldHave(Level.INFO, "Using windows formatter");
  }
}
