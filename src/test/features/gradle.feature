Feature: Gradle module

  Scenario: Should apply gradle init module
    When I apply "gradle-java" module to default project
      | packageName | tech.jhipster.chips |
      | baseName    | jhipster            |
      | projectName | Jhipster            |
    Then I should have files in "."
      | build.gradle.kts |
      | settings.gradle.kts |
      | gradlew |
      | gradlew.bat |
    Then I should have files in "gradle/wrapper"
      | gradle-wrapper.jar |
      | gradle-wrapper.properties |

  Scenario: Should apply gradle wrapper module
    When I apply "gradle-wrapper" module to default project without parameters
    Then I should have files in "."
      | gradlew |
      | gradlew.bat |
    Then I should have files in "gradle/wrapper"
      | gradle-wrapper.jar |
      | gradle-wrapper.properties |
