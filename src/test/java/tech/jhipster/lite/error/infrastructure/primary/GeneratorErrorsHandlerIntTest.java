package tech.jhipster.lite.error.infrastructure.primary;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.lite.IntegrationTest;

@IntegrationTest
@AutoConfigureMockMvc
class GeneratorErrorsHandlerIntTest {

  @Autowired
  private MockMvc rest;

  @Test
  void shouldHandleGeneratorErrorWithoutLocale() throws Exception {
    rest
      .perform(get("/api/generator-errors/unknown-docker-image"))
      .andExpect(status().isInternalServerError())
      .andExpect(jsonPath("title").value("Unknown docker image"))
      .andExpect(
        jsonPath("detail")
          .value("Image \"unknown-image\" is unknown, forgot to add it to src/main/resources/generator/dependencies/Dockerfile?")
      )
      .andExpect(jsonPath("key").value("unknown-docker-image"));
  }

  @Test
  void shouldHandleGeneratorErrorForFrenchLocale() throws Exception {
    rest
      .perform(get("/api/generator-errors/unknown-docker-image").locale(Locale.FRANCE))
      .andExpect(status().isInternalServerError())
      .andExpect(jsonPath("title").value("Image docker inconnue"))
      .andExpect(
        jsonPath("detail")
          .value(
            "L'image \"unknown-image\" n'est pas connue, avez-vous oublié de l'ajouter dans src/main/resources/generator/dependencies/Dockerfile ?"
          )
      )
      .andExpect(jsonPath("key").value("unknown-docker-image"));
  }
}
