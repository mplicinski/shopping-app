Feature: TotalCostCalculation
  Scenario: The customer lives in a non-taxed state & chooses standard shipping
    Given The customer's name is "Daniel"
    And The customer lives in "AZ"
    And The customer buys a "Soap" for 10.00 dollars
    And The customer chooses "Standard" shipping
    Then The total cost is 20.00

  Scenario: The customer lives in a non-taxed state & chooses next day shipping
    Given The customer's name is "Matthew"
    And The customer lives in "AZ"
    And The customer buys a "Soap" for 10.00 dollars
    And The customer chooses "Next Day" shipping
    Then The total cost is 35.00

  Scenario: The customer lives in a taxed state & chooses standard shipping
    Given The customer's name is "Daniel"
    And The customer lives in "IL"
    And The customer buys a "Soap" for 10.00 dollars
    And The customer chooses "Standard" shipping
    Then The total cost is 20.60

  Scenario: The customer lives in a taxed state & chooses next day shipping
    Given The customer's name is "Matthew"
    And The customer lives in "IL"
    And The customer buys a "Soap" for 10.00 dollars
    And The customer chooses "Next Day" shipping
    Then The total cost is 35.60


