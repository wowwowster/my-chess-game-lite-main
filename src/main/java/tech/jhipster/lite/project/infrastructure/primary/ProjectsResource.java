package tech.jhipster.lite.project.infrastructure.primary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.project.application.ProjectsApplicationService;
import tech.jhipster.lite.project.domain.ProjectPath;
import tech.jhipster.lite.project.domain.download.Project;

@RestController
@Tag(name = "Project")
@RequestMapping("/api")
class ProjectsResource {

  private final ProjectsApplicationService projects;

  public ProjectsResource(ProjectsApplicationService projects) {
    this.projects = projects;
  }

  @PostMapping(path = "/format-project")
  @Operation(summary = "Format the project")
  void formatProject(@Schema(description = "Path of the project to format") @RequestParam("path") String path) {
    projects.format(new ProjectPath(path));
  }

  @Operation(summary = "Download the project")
  @GetMapping(path = "/projects", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  ResponseEntity<Resource> downloadProject(@Schema(description = "Path of the project to download") @RequestParam("path") String path) {
    Project project = projects.get(new ProjectPath(path));
    String filename = project.name().filename();

    return ResponseEntity
      .ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
      .header("X-Suggested-Filename", filename)
      .contentType(MediaType.APPLICATION_OCTET_STREAM)
      .contentLength(project.contentLength())
      .body(new ByteArrayResource(project.content()));
  }

  @Operation(summary = "Get project information")
  @GetMapping(path = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<RestProjectHistory> getprojectHistory(
    @Schema(description = "Path of the project to get information for") @RequestParam("path") String path
  ) {
    return ResponseEntity.ok(RestProjectHistory.from(projects.getHistory(new ProjectPath(path))));
  }
}
