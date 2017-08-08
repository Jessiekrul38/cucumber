Feature: Buying fruit

  Scenario Outline: Bob buys fruit

    Given Bob has <Balance>
    And the shop has the following items in stock

      | Name       | Amount | Price |
      | Apple      | 2      | 1.50  |
      | Banana     | 4      | 1.00  |
      | Watermelon | 10293  | 20.00 |

    When Bob buys <Amount> <Fruit>(s)
    Then Bob has <RemainingBalance> left
    And the shop has <RemainingStock> <Fruit> left

    Examples:
      | Balance | Amount | Fruit      | RemainingBalance | RemainingStock |
      | $5.00   | 1      | Banana     | $4.00            | 3              |
      | $128.00 | 4      | Watermelon | $48.00           | 10289          |
      | $17.00  | 2      | Apple      | $14.00           | 0              |