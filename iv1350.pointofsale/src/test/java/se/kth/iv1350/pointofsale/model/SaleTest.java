/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.pointofsale.model;

import se.kth.iv1350.pointofsale.integration.ItemDTO;
import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Marcus
 */
public class SaleTest {
    private Sale saleInstanceToTest;
    private ItemDTO item;
    private ItemDTO anotherItem;
    
    public SaleTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        saleInstanceToTest = new Sale();
        item = new ItemDTO("Flour", 1001, 10, 6);
        anotherItem = new ItemDTO("Milk", 1002, 10, 6);
        
    }
    
    @AfterEach
    public void tearDown() {
        saleInstanceToTest = null;
        item = null;
    }

    @Test
    public void testAddItemFirstTime() {
        saleInstanceToTest.addItem(item);
        SaleDTO saleDTO = saleInstanceToTest.getSaleDTO();
        
        int expResult = 1;
        int result = saleDTO.getSoldItems().size();
        
        assertEquals(expResult, result, "List of sold items is not one item");
    }
    
    @Test
    public void testAddItemStoresPrice() {
        saleInstanceToTest.addItem(item);
        saleInstanceToTest.addItem(anotherItem);
        SaleDTO saleDTO = saleInstanceToTest.getSaleDTO();
        
        double expResult = 20;
        double result = saleDTO.getTotalPrice();
        
        assertEquals(expResult, result, "Price differs from stored price");
    }
    
    @Test
    public void testAddItemStoresVAT() {
        saleInstanceToTest.addItem(item);
        saleInstanceToTest.addItem(anotherItem);
        SaleDTO saleDTO = saleInstanceToTest.getSaleDTO();
        
        double expResult = 1.2;
        double result = saleDTO.getVAT();
        
        assertEquals(expResult, result, "VAT differs from stored VAT");
    }
    
    @Test
    public void testAddMultipleItems() {
        saleInstanceToTest.addItem(item);
        saleInstanceToTest.addItem(anotherItem);
        SaleDTO saleDTO = saleInstanceToTest.getSaleDTO();
        
        double expResult = 2;
        double result = saleDTO.getSoldItems().size();
        
        assertEquals(expResult, result, "Quantity of scanned items differ from scanned items recorded");
    }
    
    @Test
    public void testAddSameItemMultipleTimesStoresOneItem() {
        saleInstanceToTest.addItem(item);
        saleInstanceToTest.addItem(item);
        SaleDTO saleDTO = saleInstanceToTest.getSaleDTO();
        
        int expResult = 1;
        int result = saleDTO.getSoldItems().size();
        
        assertEquals(expResult, result, "List of sold items is not one item");
        
    }
    
    @Test
    public void testAddSameItemMultipleTimesHasCorrectQuantity() {
        saleInstanceToTest.addItem(item);
        saleInstanceToTest.addItem(item);
        SaleDTO saleDTO = saleInstanceToTest.getSaleDTO();
        
        int expResult = 2;
        int result = saleDTO.getSoldItems().get(0).getQuantitySold();
        
        assertEquals(expResult, result, "Wrong quantity of scanned items recorded");
    }
    
    @Test
    public void testApplyDiscountAdjustsPrice() {
        saleInstanceToTest.addItem(item);
        saleInstanceToTest.applyDiscount(5);
        SaleDTO saleDTO = saleInstanceToTest.getSaleDTO();
        
        double expResult = 5.0;
        double result = saleDTO.getTotalPrice();
        
        assertEquals(expResult, result, "Price differs from expected price after discount");
    }
    
    @Test
    public void testApplyDiscountStoresDiscountedAmount() {
        saleInstanceToTest.addItem(item);
        saleInstanceToTest.applyDiscount(5);
        SaleDTO saleDTO = saleInstanceToTest.getSaleDTO();
        
        double expResult = 5;
        double result = saleDTO.getDiscountAmount();
        
        assertEquals(expResult, result, "Discounted amount differs from stored discount");
    }
    
    @Test
    public void testPayCreatesReceipt() {
        saleInstanceToTest.addItem(item);
        Receipt receipt = saleInstanceToTest.pay(25);
        
        assertNotEquals(null, receipt, "No receipt created by pay method");
    }
    
    @Test
    public void testPayRegistersAmounPaid() {
        saleInstanceToTest.addItem(item);
        Receipt receipt = saleInstanceToTest.pay(25);
        
        double expResult = 25;
        double result = receipt.getAmountPaid();
        
        assertEquals(expResult, result, "Paid amount differs from created receipt");
    }
    
    @Test
    public void testAdjustQuantityOfLastItem() {
        saleInstanceToTest.addItem(item);
        saleInstanceToTest.adjustQuantityOfLastItem(3);
        SaleDTO saleDTO = saleInstanceToTest.getSaleDTO();
        int expResult = 3;
        int result = saleDTO.getLastSoldItem().getQuantitySold();
        
        assertEquals(expResult, result, "Adjusted quantity does not match actual quantity");
    }
    
    @Test
    public void testGetSaleDTOIsNotNull() {
        saleInstanceToTest.addItem(item);
        saleInstanceToTest.addItem(anotherItem);
        
        SaleDTO saleDTO = saleInstanceToTest.getSaleDTO();
        
        assertNotNull(saleDTO, "SaleDTO created is null");
    }
    
    @Test
    public void testGetSaleDTOContainsCorrectNumberOfItems() {
        saleInstanceToTest.addItem(item);
        saleInstanceToTest.addItem(anotherItem);
        
        SaleDTO saleDTO = saleInstanceToTest.getSaleDTO();
        
        int expResult = 2;
        int result = saleDTO.getSoldItems().size();
        
        assertEquals(expResult, result, "SaleDTO cantains more items than added");   
    }
    
    @Test
    public void testGetSaleDTOHasCorrectPrice() {
        saleInstanceToTest.addItem(item);
        saleInstanceToTest.addItem(anotherItem);
        
        SaleDTO saleDTO = saleInstanceToTest.getSaleDTO();
        
        double expResult = 20.0;
        double result = saleDTO.getTotalPrice();
        
        assertEquals(expResult, result, "SaleDTO has wrong price");
    }
}
