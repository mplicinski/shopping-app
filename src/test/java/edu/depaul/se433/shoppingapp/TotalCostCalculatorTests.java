package edu.depaul.se433.shoppingapp;

import edu.depaul.se433.shoppingapp.ShippingType;
import edu.depaul.se433.shoppingapp.TotalCostCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import static org.junit.jupiter.api.Assertions.*;


public class TotalCostCalculatorTests {

  //Strong Normal Tests

  @Test
  @DisplayName("Under $50 - Tax - Standard Shipping")
  void under50_tax_standard() {
    double finalCost = TotalCostCalculator.calculate(10.00, "IL", ShippingType.STANDARD);
    assertEquals(20.60, finalCost);
  }

  @Test
  @DisplayName("Under $50 - Tax - Next Day Shipping")
  void under50_tax_nextDay() {
    double finalCost = TotalCostCalculator.calculate(10.00, "IL", ShippingType.NEXT_DAY);
    assertEquals(35.60, finalCost);
  }

  @Test
  @DisplayName("Over $50 - Tax - Standard Shipping")
  void over50_tax_standard() {
    double finalCost = TotalCostCalculator.calculate(100.00, "IL", ShippingType.STANDARD);
    assertEquals(106.00, finalCost);
  }

  @Test
  @DisplayName("Over $50 - Tax - Next Day Shipping")
  void over50_tax_nextDay() {
    double finalCost = TotalCostCalculator.calculate(100.00, "IL", ShippingType.NEXT_DAY);
    assertEquals(131.00, finalCost);
  }

  @Test
  @DisplayName("Under $50 - No Tax - Standard Shipping")
  void under50_noTax_standard() {
    double finalCost = TotalCostCalculator.calculate(10.00, "AZ", ShippingType.STANDARD);
    assertEquals(20.00, finalCost);
  }

  @Test
  @DisplayName("Under $50 - No Tax - Next Day Shipping")
  void under50_noTax_nextDay() {
    double finalCost = TotalCostCalculator.calculate(10.00, "AZ", ShippingType.NEXT_DAY);
    assertEquals(35.00, finalCost);
  }

  @Test
  @DisplayName("Over $50 - No Tax - Standard Shipping")
  void over50_noTax_standard() {
    double finalCost = TotalCostCalculator.calculate(100.00, "AZ", ShippingType.STANDARD);
    assertEquals(100.00, finalCost);
  }

  @Test
  @DisplayName("Over $50 - No Tax - Next Day Shipping")
  void over50_noTax_nextDay() {
    double finalCost = TotalCostCalculator.calculate(100.00, "AZ", ShippingType.NEXT_DAY);
    assertEquals(125.00, finalCost);
  }

  //Weak Robust Tests

  @Test
  @DisplayName("Null Tax - Throws exception")
  void null_tax() {
    try {
      double finalCost = TotalCostCalculator.calculate(10.00, null, ShippingType.STANDARD);
      fail();
    } catch (IllegalArgumentException e) {
      assert(true);
    }
  }

  @Test
  @DisplayName("Negative Price - Throws exception")
  void null_price() {
    try {
      double finalCost = TotalCostCalculator.calculate(-5.00, "IL", ShippingType.STANDARD);
      fail();
    } catch (IllegalArgumentException e) {
      assert(true);
    }
  }

  @Test
  @DisplayName("Null Ship Type - Throws exception")
  void null_shipType() {
    try {
      double finalCost = TotalCostCalculator.calculate(10.00, "IL", null);
      fail();
    } catch (IllegalArgumentException e) {
      assert(true);
    }
  }

  //Boundary Tests

  @Test
  @DisplayName("Invalid lower boundary - $0.00")
  void invalid_lower_boundary() {
    try {
      double finalCost = TotalCostCalculator.calculate(0.00, "IL", ShippingType.STANDARD);
      fail();
    } catch (IllegalArgumentException e) {
      assert(true);
    }
  }

  @Test
  @DisplayName("Valid lower boundary - $0.01")
  void valid_lower_boundary() {
    double finalCost = TotalCostCalculator.calculate(0.01, "IL", ShippingType.STANDARD);
    assertEquals(10.01, finalCost);
  }

  @Test
  @DisplayName("Under for free standard shipping boundary - $50")
  void under_free_shipping() {
    double finalCost = TotalCostCalculator.calculate(50.00, "IL", ShippingType.STANDARD);
    assertEquals(60.00, finalCost);
  }

  @Test
  @DisplayName("Over for free standard shipping boundary - $50.01")
  void over_free_shipping() {
    double finalCost = TotalCostCalculator.calculate(50.01, "IL", ShippingType.STANDARD);
    assertEquals(50.01, finalCost);
  }


}
