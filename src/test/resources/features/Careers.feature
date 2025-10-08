@backend
Feature: Validate the Cat Breeds API from CatFact Ninja

  Scenario Outline: Get cat breeds with limit <limit>
    Given I give a careers header with limit <limit>
    When I submit a request GET to Breeds endpoint
    And I successfully receive the breeds information
    Examples:
      | limit |
      | 0     |
      | 1     |
      | 5     |
      | 10    |
      | 1000  |
