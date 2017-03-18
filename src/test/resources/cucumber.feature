Feature: 4Finance practical task

  Scenario: Check most voted language dependency level for board game
    When Open the game collection of a user "jwhofilm"
    And  Go to the page of one of the games (chosen at random)
    Then Verify that the most voted Language Dependence level is presented on the game's page