package tech.jhipster.lite.statistic.domain;

import tech.jhipster.lite.error.domain.Assert;

public record Statistics(long appliedModules) {
  public Statistics {
    Assert.field("appliedModules", appliedModules).min(0);
  }
}
