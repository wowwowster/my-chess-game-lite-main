package tech.jhipster.lite.module.domain.javadependency;

import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import tech.jhipster.lite.error.domain.Assert;

public record JavaDependencyClassifier(String classifier) {
  public JavaDependencyClassifier {
    Assert.notBlank("classifier", classifier);
  }

  public static Optional<JavaDependencyClassifier> of(String classifier) {
    return Optional.ofNullable(classifier).filter(StringUtils::isNotBlank).map(JavaDependencyClassifier::new);
  }

  public String get() {
    return classifier();
  }
}
