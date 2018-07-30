Feature: C4APP API endpoint

  Scenario: Create a new game
    Given I create a game
    Then I receive an identifier for a game
    And I can retrieve an initialised game with that identifier


    Scenario: Make a move
      Given I have an empty game
      When I make a move in column 2
      And I retrieve that game
      Then My piece is in the bottom row of column 2