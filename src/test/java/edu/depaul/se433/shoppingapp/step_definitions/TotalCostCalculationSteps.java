package edu.depaul.se433.shoppingapp.step_definitions;

import edu.depaul.se433.shoppingapp.ShippingType;
import edu.depaul.se433.shoppingapp.TotalCostCalculator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;

public class TotalCostCalculationSteps {
  String name;
  String state;
  String item;
  double itemCost;
  String shipType;
  String finalCost;

  @Given("The customer's name is {string}")
  public void the_customer_s_name_is(String string) {
    name = string;
    //throw new io.cucumber.java.PendingException();
  }

  @Given("The customer lives in {string}")
  public void the_customer_lives_in(String string) {
    state = string;
    //throw new io.cucumber.java.PendingException();
  }
  @Given("The customer buys a {string} for {double} dollars")
  public void the_customer_buys_a_for_dollars(String string, Double double1) {
    item = string;
    itemCost = double1;
    //throw new io.cucumber.java.PendingException();
  }
  @Given("The customer chooses {string} shipping")
  public void the_customer_chooses_shipping(String string) {
    shipType = string;
    //throw new io.cucumber.java.PendingException();
  }
  @Then("The total cost is {double}")
  public void the_total_cost_is(Double double1) {
    double expectedFinalCost = double1;
    ShippingType shippingType;
    if (shipType.toLowerCase().startsWith("s")) {
      shippingType = ShippingType.STANDARD;
    } else {
      shippingType = ShippingType.NEXT_DAY;
    }
    double actualFinalCost = TotalCostCalculator.calculate(itemCost, state, shippingType);
    assertEquals(expectedFinalCost, actualFinalCost);
    //throw new io.cucumber.java.PendingException();
  }
}
