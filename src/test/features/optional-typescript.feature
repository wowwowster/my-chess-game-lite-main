Feature: Optional-typescript module

  Scenario: Add Optional class domain
    When I apply "optional-typescript" module to default project without parameters
    Then I should have files in "/src/main/webapp/app/common/domain/"
      | Optional.ts |
