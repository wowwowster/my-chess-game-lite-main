Feature: Vue.js modules

  Scenario: Should apply vue core module
    When I apply modules to default project
      | init     |
      | prettier |
      | vue-core |
    Then I should have files in "src/main/webapp/app/http"
      | AxiosHttp.ts |

  Scenario: Should apply pinia module
    Given I apply "vue-core" module to default project with package json without parameters
    When I apply "vue-pinia" module without parameters to last project
    Then I should have "import piniaPersist from 'pinia-plugin-persist'" in "src/main/webapp/app/main.ts"
