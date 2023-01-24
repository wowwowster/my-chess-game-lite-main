package tech.jhipster.lite.statistic.domain;

import tech.jhipster.lite.error.domain.Assert;

public record Module(String slug) {
  public Module {
    Assert.notBlank("slug", slug);
  }
}
