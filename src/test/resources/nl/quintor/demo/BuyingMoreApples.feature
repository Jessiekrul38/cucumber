Feature: Buying more apples

  Scenario Outline: Bob buys more apples

    Given Bob has <Balance>
    And the shop has <Stock> apples in stock
    And an apple costs <Price>
    When Bob buys <Apples> apple(s)
    Then Bob has <RemainingBalance> left
    And the shop has <RemainingStock> apples left

    Examples:
      | Balance | Stock | Apples | Price | RemainingBalance | RemainingStock |
      | $5.00   | 3     | 1      | $1.50 | $3.50            | 2              |
      | $10.00  | 13    | 1      | $10   | $0.00            | 12             |
      | $7.00   | 5     | 4      | $1.50 | $1.00            | 1              |