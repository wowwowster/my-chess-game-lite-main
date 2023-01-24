package tech.jhipster.lite.module.infrastructure.secondary.npm;

import static org.assertj.core.api.Assertions.*;

import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.npm.NpmPackage;
import tech.jhipster.lite.module.domain.npm.NpmPackageName;
import tech.jhipster.lite.module.domain.npm.NpmPackageVersion;
import tech.jhipster.lite.module.domain.npm.NpmPackagesVersions;
import tech.jhipster.lite.module.domain.npm.NpmVersionSource;
import tech.jhipster.lite.module.domain.npm.UnknownNpmPackageException;

@UnitTest
class JHipsterNpmVersionsTest {

  @Test
  void shouldNotReadVersionWithoutReaders() {
    JHipsterNpmVersions versions = new JHipsterNpmVersions(List.of());

    assertThatThrownBy(() -> versions.get(new NpmPackageName("unknown"), NpmVersionSource.COMMON))
      .isExactlyInstanceOf(UnknownNpmPackageException.class)
      .hasMessageContaining("unknown");
  }

  @Test
  void shouldNotReadUnknownVersion() {
    JHipsterNpmVersions versions = new JHipsterNpmVersions(List.of(emptyReader()));

    assertThatThrownBy(() -> versions.get(new NpmPackageName("unknown"), NpmVersionSource.COMMON))
      .isExactlyInstanceOf(UnknownNpmPackageException.class)
      .hasMessageContaining("unknown");
  }

  private NpmVersionsReader emptyReader() {
    return () -> NpmPackagesVersions.EMPTY;
  }

  @Test
  void shouldReadFirstReadableVersion() {
    NpmVersionsReader firstReader = () ->
      NpmPackagesVersions.builder().put(NpmVersionSource.COMMON, packages(new NpmPackage("vue", "1.2.3"))).build();
    NpmVersionsReader secondReader = () ->
      NpmPackagesVersions
        .builder()
        .put(NpmVersionSource.COMMON, packages(new NpmPackage("vue", "1.2.7")))
        .put(NpmVersionSource.VUE, packages(new NpmPackage("vue", "1.2.7")))
        .build();

    JHipsterNpmVersions versions = new JHipsterNpmVersions(List.of(emptyReader(), firstReader, secondReader));

    assertThat(versions.get("vue", NpmVersionSource.COMMON)).isEqualTo(new NpmPackageVersion("1.2.3"));
    assertThat(versions.get("vue", NpmVersionSource.VUE)).isEqualTo(new NpmPackageVersion("1.2.7"));
  }

  private static Collection<NpmPackage> packages(NpmPackage... packages) {
    return List.of(packages);
  }
}
