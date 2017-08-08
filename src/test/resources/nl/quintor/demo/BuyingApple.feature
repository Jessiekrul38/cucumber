Feature: Buying an apple

  Scenario: Bob buys an apple

    Given Bob has $5.00
    And the shop has 3 apples in stock
    And an apple costs $1.50
    When Bob buys 1 apple(s)
    Then Bob has $3.50 left
    And the shop has 2 apples left