package tech.jhipster.lite.project.infrastructure.secondary;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.Map;
import tech.jhipster.lite.project.domain.history.ProjectAction;

class PersistedProjectAction {

  private final String module;
  private final Instant date;
  private final Map<String, Object> properties;

  private PersistedProjectAction(
    @JsonProperty("module") String module,
    @JsonProperty("date") Instant date,
    @JsonProperty("properties") Map<String, Object> properties
  ) {
    this.module = module;
    this.date = date;
    this.properties = properties;
  }

  static PersistedProjectAction from(ProjectAction action) {
    return new PersistedProjectAction(action.module().get(), action.date(), action.parameters().get());
  }

  ProjectAction toDomain() {
    return ProjectAction.builder().module(getModule()).date(getDate()).parameters(getProperties());
  }

  public String getModule() {
    return module;
  }

  public Instant getDate() {
    return date;
  }

  public Map<String, Object> getProperties() {
    return properties;
  }
}
