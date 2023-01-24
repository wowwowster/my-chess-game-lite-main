package tech.jhipster.lite.module.domain.properties;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.Indentation;

@UnitTest
class JHipsterModulePropertiesTest {

  @Nested
  @DisplayName("Mandatory String")
  class JHipsterModulePropertiesMandatoryStringTest {

    @Test
    void shouldNotGetUnknownProperty() {
      assertThatThrownBy(() -> properties().getString("unknown")).isExactlyInstanceOf(UnknownPropertyException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = { "boolean", "integer" })
    void shouldNotGetInvalidType(String key) {
      assertThatThrownBy(() -> properties().getString(key)).isExactlyInstanceOf(InvalidPropertyTypeException.class);
    }

    @Test
    void shouldGetProperty() {
      assertThat(properties().getString("string")).isEqualTo("value");
    }
  }

  @Nested
  @DisplayName("Optional String")
  class JHipsterModulePropertiesOptionalStringTest {

    @Test
    void shouldNotGetDefaultValueForUnknownProperty() {
      assertThat(properties().getOrDefaultString("unknown", "default")).isEqualTo("default");
    }

    @ParameterizedTest
    @ValueSource(strings = { "boolean", "integer" })
    void shouldNotGetInvalidType(String key) {
      assertThatThrownBy(() -> properties().getOrDefaultString(key, "default")).isExactlyInstanceOf(InvalidPropertyTypeException.class);
    }

    @Test
    void shouldGetDefaultForBlankValue() {
      assertThat(properties().getOrDefaultString("blank", "default")).isEqualTo("default");
    }

    @Test
    void shouldGetProperty() {
      assertThat(properties().getOrDefaultString("string", "default")).isEqualTo("value");
    }
  }

  @Nested
  @DisplayName("Mandatory Boolean")
  class JHipsterModulePropertiesMandatoryBooleanTest {

    @Test
    void shouldNotGetUnknownProperty() {
      assertThatThrownBy(() -> properties().getBoolean("unknown")).isExactlyInstanceOf(UnknownPropertyException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = { "string", "integer" })
    void shouldNotGetInvalidType(String key) {
      assertThatThrownBy(() -> properties().getBoolean(key)).isExactlyInstanceOf(InvalidPropertyTypeException.class);
    }

    @Test
    void shouldGetProperty() {
      assertThat(properties().getBoolean("boolean")).isTrue();
    }
  }

  @Nested
  @DisplayName("Optional Boolean")
  class JHipsterModulePropertiesOptionalBooleanTest {

    @Test
    void shouldNotGetUnknownProperty() {
      assertThat(properties().getOrDefaultBoolean("unknown", true)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = { "string", "integer" })
    void shouldNotGetInvalidType(String key) {
      assertThatThrownBy(() -> properties().getOrDefaultBoolean(key, true)).isExactlyInstanceOf(InvalidPropertyTypeException.class);
    }

    @Test
    void shouldGetProperty() {
      assertThat(properties().getOrDefaultBoolean("boolean", false)).isTrue();
    }
  }

  @Nested
  @DisplayName("Mandatory Integer")
  class JHipsterModulePropertiesMandatoryIntegerTest {

    @Test
    void shouldNotGetUnknownProperty() {
      assertThatThrownBy(() -> properties().getInteger("unknown")).isExactlyInstanceOf(UnknownPropertyException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = { "string", "boolean" })
    void shouldNotGetInvalidType(String key) {
      assertThatThrownBy(() -> properties().getInteger(key)).isExactlyInstanceOf(InvalidPropertyTypeException.class);
    }

    @Test
    void shouldGetProperty() {
      assertThat(properties().getInteger("integer")).isEqualTo(42);
    }
  }

  @Nested
  @DisplayName("Optional Integer")
  class JHipsterModulePropertiesOptionalIntegerTest {

    @Test
    void shouldNotGetUnknownProperty() {
      assertThat(properties().getOrDefaultInteger("unknown", 42)).isEqualTo(42);
    }

    @ParameterizedTest
    @ValueSource(strings = { "string", "boolean" })
    void shouldNotGetInvalidType(String key) {
      assertThatThrownBy(() -> properties().getOrDefaultInteger(key, 42)).isExactlyInstanceOf(InvalidPropertyTypeException.class);
    }

    @Test
    void shouldGetProperty() {
      assertThat(properties().getOrDefaultInteger("integer", 42)).isEqualTo(42);
    }
  }

  @Test
  void shouldGetDefaultProjectProperties() {
    JHipsterModuleProperties properties = properties();

    assertThat(properties.indentation()).isEqualTo(Indentation.DEFAULT);
    assertThat(properties.basePackage()).isEqualTo(JHipsterBasePackage.DEFAULT);
    assertThat(properties.projectName()).isEqualTo(JHipsterProjectName.DEFAULT);
    assertThat(properties.projectBaseName()).isEqualTo(JHipsterProjectBaseName.DEFAULT);
  }

  private JHipsterModuleProperties properties() {
    return new JHipsterModuleProperties("/tmp/folder", false, Map.of("string", "value", "boolean", true, "integer", 42, "blank", " "));
  }
}
