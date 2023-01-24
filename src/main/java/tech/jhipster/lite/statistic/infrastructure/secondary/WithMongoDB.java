package tech.jhipster.lite.statistic.infrastructure.secondary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@ConditionalOnExpression("'${spring.data.mongodb.uri:}' != '' && '${mongock.migration-scan-package:}' != ''")
@interface WithMongoDB {
}
