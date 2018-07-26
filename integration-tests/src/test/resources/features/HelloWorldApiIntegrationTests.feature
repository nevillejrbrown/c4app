Feature: C4APP API endpoint

#  Scenario: Create new game
#    Given I POST to endpoint "/apis/v1/api/games/"
#    """
#      """
#    Then the api returns a 201 code
#    And response has game id "1"
#
#  Scenario: Create second game
#    Given I POST to endpoint "/apis/v1/api/games/"
#    """
#      """
#    Then the api returns a 201 code
#    And response has game id "2"
#
#  Scenario: Create second game
#    Given I POST to endpoint "/apis/v1/api/games/"
#    """
#       {
#          "username": "Kotlin"
#        }
#      """
#    Then the api returns a 201 code
#    And response has game id "3"


  Scenario: Create a new game
    Given I create a game
    Then I receive an identifier for a game
    And I can retrieve an initialised game with that identifier
#
#Scenario: Get hello message
#    Given I POST to endpoint "/apis/v1/api/games/"
#    """
#    {
#      "username": "Kotlin"
#    }
#    """
#    And the api returns a 201 code
#    When a GET request is made to the URI: "/apis/v1/hello"
#    Then the api returns a 200 code
#    And reponse for JsonSlurper "find{it.username}.username" returns "Kotlin"
