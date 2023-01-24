package tech.jhipster.lite.module.domain.properties;

import tech.jhipster.lite.error.domain.GeneratorException;

class InvalidPropertyTypeException extends GeneratorException {

  private InvalidPropertyTypeException(InvalidPropertyTypeExceptionBuilder builder) {
    super(
      badRequest(PropertiesErrorKey.INVALID_PROPERTY_TYPE)
        .message(buildMessage(builder))
        .addParameter("propertyKey", builder.key)
        .addParameter("expectedType", builder.expectedType.getName())
        .addParameter("actualType", builder.actualType.getName())
    );
  }

  private static String buildMessage(InvalidPropertyTypeExceptionBuilder builder) {
    return new StringBuilder()
      .append("Can't get property ")
      .append(builder.key)
      .append(", expecting ")
      .append(builder.expectedType)
      .append(" but is a ")
      .append(builder.actualType)
      .toString();
  }

  static InvalidPropertyTypeExceptionKeyBuilder builder() {
    return new InvalidPropertyTypeExceptionBuilder();
  }

  static class InvalidPropertyTypeExceptionBuilder
    implements
      InvalidPropertyTypeExceptionKeyBuilder,
      InvalidPropertyTypeExceptionExpectedTypeBuilder,
      InvalidPropertyTypeExceptionActualTypeBuilder {

    private String key;
    private Class<?> expectedType;
    private Class<?> actualType;

    public InvalidPropertyTypeExceptionExpectedTypeBuilder key(String key) {
      this.key = key;

      return this;
    }

    @Override
    public InvalidPropertyTypeExceptionBuilder expectedType(Class<?> expectedType) {
      this.expectedType = expectedType;

      return this;
    }

    @Override
    public InvalidPropertyTypeException actualType(Class<?> actualType) {
      this.actualType = actualType;

      return new InvalidPropertyTypeException(this);
    }
  }

  interface InvalidPropertyTypeExceptionKeyBuilder {
    InvalidPropertyTypeExceptionExpectedTypeBuilder key(String key);
  }

  interface InvalidPropertyTypeExceptionExpectedTypeBuilder {
    InvalidPropertyTypeExceptionBuilder expectedType(Class<?> expectedType);
  }

  interface InvalidPropertyTypeExceptionActualTypeBuilder {
    InvalidPropertyTypeException actualType(Class<?> actualType);
  }
}
