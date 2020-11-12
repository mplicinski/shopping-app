package edu.depaul.se433.shoppingapp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class DatabaseTests {

  @Test
  @DisplayName("PurchaseDBO saving to database")
  void test_save_transactions() {
    PurchaseDBO mockPurchase = mock(PurchaseDBO.class);
    PurchaseAgent mockAgent = new PurchaseAgent(mockPurchase);
    ArrayList<Purchase> purchasesList = new ArrayList<>();

    Purchase purchase = Purchase.make("Matt", LocalDate.of(
            2020, 11, 11), 100.00, "AZ", "STANDARD");

    when(mockPurchase.getPurchases()).thenReturn(purchasesList);
    mockAgent.save(purchase);
    verify(mockPurchase, times(1)).savePurchase(purchase);
  }

  @Test
  @DisplayName("Average purchase calculation no tax or shipping.")
  void average_purchase_no_tax() {
    PurchaseDBO mockPurchase = mock(PurchaseDBO.class);
    PurchaseAgent mockAgent = new PurchaseAgent(mockPurchase);
    ArrayList<Purchase> purchasesList = new ArrayList<>();

    Purchase purchase1 = Purchase.make("Matthew", LocalDate.of(
            2020, 11, 11), 100.00, "AZ", "STANDARD");

    Purchase purchase2 = Purchase.make("Daniel", LocalDate.of(
            2020, 11, 11), 200.00, "AZ", "STANDARD");

    purchasesList.add(purchase1);
    purchasesList.add(purchase2);

    when(mockPurchase.getPurchases()).thenReturn(purchasesList);
    Double average = (purchase1.getCost() + purchase2.getCost()) / 2;
    assertEquals(average, mockAgent.averagePurchase());
  }

  @Test
  @DisplayName("Average purchase calculation with tax and shipping.")
  void average_purchase_with_tax() {
    PurchaseDBO mockPurchase = mock(PurchaseDBO.class);
    PurchaseAgent mockAgent = new PurchaseAgent(mockPurchase);
    ArrayList<Purchase> purchasesList = new ArrayList<>();

    Purchase purchase1 = Purchase.make("Matthew", LocalDate.of(
            2020, 11, 11), 25.00, "IL", "STANDARD");

    Purchase purchase2 = Purchase.make("Daniel", LocalDate.of(
            2020, 11, 11), 50.00, "IL", "NEXT_DAY");

    purchasesList.add(purchase1);
    purchasesList.add(purchase2);

    when(mockPurchase.getPurchases()).thenReturn(purchasesList);
    Double average = (purchase1.getCost() + purchase2.getCost()) / 2;
    assertEquals(average, mockAgent.averagePurchase());
  }
}
