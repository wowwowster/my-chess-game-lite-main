Feature: Webflux module

  Scenario: Should apply webflux module
    When I apply modules to default project
      | maven-java                 |
      | spring-boot                |
      | spring-boot-webflux        |
      | spring-boot-webflux-netty  |
    Then I should have "<artifactId>spring-boot-starter-webflux</artifactId>" in "pom.xml"
    Then I should have files in "src/main/java/tech/jhipster/chips/technical/infrastructure/primary/exception/"
      | FieldErrorDTO.java |
