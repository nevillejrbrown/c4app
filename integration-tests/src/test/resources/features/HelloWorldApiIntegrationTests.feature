Feature: C4APP API endpoint

  Scenario: Create a new game
    Given I create a game
    Then I receive an identifier for a game
    And I can retrieve an initialised game with that identifier


    Scenario: Make a move
      Given I have an empty game
      When I make a move in column 2 with mark X
      And I retrieve that game
      Then Position 0 of column 2 has the mark X

  Scenario: Making a move twice in a row with same player causes error
    Given I have an empty game
    When I make a move in column 2 with mark X
    And I make a move in column 2 with mark X
    Then I get an error message returned
    And Position 2 of column 2 has the mark EMPTY