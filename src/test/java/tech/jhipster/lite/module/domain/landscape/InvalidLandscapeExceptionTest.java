package tech.jhipster.lite.module.domain.landscape;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.ErrorStatus;
import tech.jhipster.lite.module.domain.JHipsterModuleSlug;

@UnitTest
class InvalidLandscapeExceptionTest {

  @Test
  void shouldGetDuplicatedSlugExceptionInformation() {
    InvalidLandscapeException exception = InvalidLandscapeException.duplicatedSlug("duplicated-slug");

    assertThat(exception.getMessage())
      .isEqualTo("Can't share a slug between a feature and a module, slug \"duplicated-slug\" is duplicated");
    assertThat(exception.key()).isEqualTo(LandscapeErrorKey.DUPLICATED_SLUG);
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
    assertThat(exception.parameters()).containsOnly(entry("slug", "duplicated-slug"));
  }

  @Test
  void shouldGetUnknownDependencyExceptionInformation() {
    InvalidLandscapeException exception = InvalidLandscapeException.unknownDepdencency(
      Set.of(new JHipsterModuleSlug("known")),
      List.of(new JHipsterModuleSlug("remaining"))
    );

    assertThat(exception.getMessage())
      .isEqualTo(
        "Can't build landscape this happens if you have an unknown dependency or circular dependencies. Known elements: known and trying to find element with all known dependencies in: remaining"
      );
    assertThat(exception.key()).isEqualTo(LandscapeErrorKey.UNKNOWN_DEPENDENCY);
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
  }

  @Test
  void shouldGetMissingRootElementExceptionInformation() {
    InvalidLandscapeException exception = InvalidLandscapeException.missingRootElement();

    assertThat(exception.getMessage()).isEqualTo("Can't build landscape, can't find any root element");
    assertThat(exception.key()).isEqualTo(LandscapeErrorKey.MISSING_ROOT_ELEMENT);
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
  }
}
