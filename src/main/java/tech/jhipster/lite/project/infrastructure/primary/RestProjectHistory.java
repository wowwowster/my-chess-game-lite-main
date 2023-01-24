package tech.jhipster.lite.project.infrastructure.primary;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Collection;
import java.util.Map;
import tech.jhipster.lite.project.domain.history.ProjectHistory;

@Schema(name = "ProjectHistory", description = "Information on the JHipster project history")
class RestProjectHistory {

  private final Collection<RestAppliedModule> modules;
  private final Map<String, Object> properties;

  private RestProjectHistory(Collection<RestAppliedModule> modules, Map<String, Object> properties) {
    this.modules = modules;
    this.properties = properties;
  }

  public static RestProjectHistory from(ProjectHistory history) {
    return new RestProjectHistory(history.actions().stream().map(RestAppliedModule::from).toList(), history.latestProperties().get());
  }

  @Schema(description = "Modules applied for the project")
  public Collection<RestAppliedModule> getModules() {
    return modules;
  }

  @Schema(description = "Properties used when applying modules to the project")
  public Map<String, Object> getProperties() {
    return properties;
  }
}
