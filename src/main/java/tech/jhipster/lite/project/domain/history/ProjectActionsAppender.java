package tech.jhipster.lite.project.domain.history;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.project.domain.ProjectsRepository;

public class ProjectActionsAppender {

  private ProjectsRepository projects;

  public ProjectActionsAppender(ProjectsRepository projects) {
    Assert.notNull("projects", projects);

    this.projects = projects;
  }

  public void append(ProjectActionToAppend actionToAppend) {
    ProjectHistory history = projects.getHistory(actionToAppend.path());

    history.append(actionToAppend.action());

    projects.save(history);
  }
}
