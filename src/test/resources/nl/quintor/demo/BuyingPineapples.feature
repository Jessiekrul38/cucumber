Feature: Buying a pineapple

  Scenario: Bob buys a pineapple

    Given Bob has $5.00
    And the shop has 3 pineapples in stock
    And a pineapple costs $1.50
    When Bob buys 1 apple(s)
    Then Bob has $3.50 left
    And the shop has 2 pineapples left