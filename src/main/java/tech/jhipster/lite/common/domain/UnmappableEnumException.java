package tech.jhipster.lite.common.domain;

import tech.jhipster.lite.error.domain.GeneratorException;

class UnmappableEnumException extends GeneratorException {

  public <T extends Enum<T>, U extends Enum<U>> UnmappableEnumException(Class<T> from, Class<U> to) {
    super(internalServerError(EnumsErrorKey.UNMAPPABLE_ENUM).message("Can't map " + from + " to " + to));
  }
}
