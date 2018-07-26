Feature: C4APP API endpoint

  Scenario: Create a new game
    Given I create a game
    Then I receive an identifier for a game
    And I can retrieve an initialised game with that identifier
