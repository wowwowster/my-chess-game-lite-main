package tech.jhipster.lite.module.domain.file;

import tech.jhipster.lite.error.domain.Assert;

public record JHipsterModuleFile(JHipsterFileContent content, JHipsterDestination destination, boolean executable) {
  public JHipsterModuleFile {
    Assert.notNull("content", content);
    Assert.notNull("destination", destination);
  }
}
