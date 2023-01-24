package tech.jhipster.lite.module.domain.resource;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.lite.module.domain.resource.JHipsterModulesResourceFixture.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.MissingMandatoryValueException;
import tech.jhipster.lite.module.domain.JHipsterModuleSlug;

@UnitTest
class JHipsterModulesResourcesTest {

  @Test
  void shouldNotGetModuleForUnknownSlug() {
    JHipsterModulesResources jHipsterModulesResources = new JHipsterModulesResources(
      List.of(defaultModuleResourceBuilder().slug("dummy").build())
    );

    assertThatThrownBy(() -> jHipsterModulesResources.get(new JHipsterModuleSlug("dummy-2")))
      .isExactlyInstanceOf(UnknownSlugException.class)
      .hasMessageContaining("Module dummy-2 does not exist");
  }

  @Test
  void shouldNotBuildWithoutResources() {
    assertThatThrownBy(() -> new JHipsterModulesResources(List.of()))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("modulesResources");
  }

  @Test
  void shouldNotBuildWithDuplicatedSlug() {
    JHipsterModuleResource resource = defaultModuleResourceBuilder().slug("dummy").build();

    assertThatThrownBy(() -> new JHipsterModulesResources(List.of(resource, resource))).isExactlyInstanceOf(DuplicatedSlugException.class);
  }
}
