package tech.jhipster.lite.statistic.domain;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public final class AppliedModuleFixture {

  private AppliedModuleFixture() {}

  public static AppliedModule appliedModule() {
    return AppliedModule
      .builder()
      .id(appliedModuleId())
      .path(folder())
      .module(moduleSlug())
      .date(Instant.parse("2021-12-03T10:15:30.00Z"))
      .properties(moduleProperties());
  }

  private static AppliedModuleId appliedModuleId() {
    return new AppliedModuleId(UUID.fromString("065b2280-d0bd-4bea-b685-1a899f49fba7"));
  }

  private static ProjectPath folder() {
    return new ProjectPath("path");
  }

  private static Module moduleSlug() {
    return new Module("module");
  }

  private static ModuleProperties moduleProperties() {
    return new ModuleProperties(Map.of("key", "value"));
  }
}
