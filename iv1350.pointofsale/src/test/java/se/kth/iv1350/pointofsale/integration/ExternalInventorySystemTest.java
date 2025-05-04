/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.pointofsale.integration;


import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointofsale.model.SaleDTO;
import se.kth.iv1350.pointofsale.model.SoldItem;


public class ExternalInventorySystemTest {
    private ExternalInventorySystem inventorySystem;
    private ItemDTO cucumber;
    
    public ExternalInventorySystemTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        inventorySystem = new ExternalInventorySystem();
        cucumber = inventorySystem.getItemDescription(1234);
    }
    
    @AfterEach
    public void tearDown() {
        cucumber = null;
        inventorySystem = null;
    }

    @Test
    public void testGetItemDescriptionValidID() {
        ItemDTO expResult = cucumber;
        ItemDTO result = inventorySystem.getItemDescription(1234);
        
        assertEquals(expResult, result, "Retrieved DTO doesnt match the DTO searched by itemID");
    }

    @Test
    public void testGetItemDescriptionInvalidID() {
        ItemDTO itemRetrieved = inventorySystem.getItemDescription(9999);
        assertNull(itemRetrieved, "Invalid ID should return null");
        
    }
    
    @Test
    public void testUpdateInventoryDecreasesQuantity() {
        int originalQuantity = inventorySystem.findInventoryItemByID(1234).getQuantity();
        
        List<SoldItem> soldItems = new ArrayList<>();
        SoldItem soldItem = new SoldItem(cucumber);
        soldItem.setQuantitySold(1);
        soldItems.add(soldItem);
        SaleDTO saleInformation = new SaleDTO(soldItems, null,0,0,0);
        
        inventorySystem.updateInventory(saleInformation);
        
        int expResult = originalQuantity-2;
        int result = inventorySystem.findInventoryItemByID(1234).getQuantity();
        
        assertEquals(expResult, result, "Updated quantity of item not decrased by quantity sold of item");
        
    }
    
    @Test
    public void testUpdateInventoryMultipleItems() {
        ItemDTO milk = inventorySystem.getItemDescription(1111);
        ItemDTO banana = inventorySystem.getItemDescription(4444);
        
        int milkOriginalQuantity = inventorySystem.findInventoryItemByID(1111).getQuantity();
        int bananaOriginalQuantity = inventorySystem.findInventoryItemByID(4444).getQuantity();
        
        SoldItem soldMilk = new SoldItem(milk);
        soldMilk.setQuantitySold(3);
        SoldItem soldBanana = new SoldItem(banana);
        soldBanana.setQuantitySold(5);
        
        List<SoldItem> soldItems = new ArrayList<>();
        soldItems.add(soldMilk);
        soldItems.add(soldBanana);
        
        SaleDTO saleInfo = new SaleDTO(soldItems, null, 0, 0, 0);
        inventorySystem.updateInventory(saleInfo);
        
        int expResultBanana = bananaOriginalQuantity -6;
        int resultBanana = inventorySystem.findInventoryItemByID(4444).getQuantity();
        
        int expResultMilk = milkOriginalQuantity - 4;
        int resultMilk = inventorySystem.findInventoryItemByID(1111).getQuantity();
        
        assertEquals(expResultMilk, resultMilk, "Updated quantity of item not decrased by quantity sold of item");
        assertEquals(expResultBanana, resultBanana, "Updated quantity of item not decrased by quantity sold of item");
    }
    
    @Test
    public void testUpdateInventoryWithItemNotInInventory() {
        ItemDTO fakeItem = new ItemDTO("Fake item", 9999, 99.0, 10.0);
        SoldItem soldItem = new SoldItem(fakeItem);
        soldItem.setQuantitySold(5);
        List<SoldItem> soldItems = new ArrayList<>();
        soldItems.add(soldItem);
        SaleDTO saleDTO = new SaleDTO(soldItems, null, 0.0, 0.0, 0.0);
        
        inventorySystem.updateInventory(saleDTO);
        
        InventoryItem fakeItemInInventory = inventorySystem.findInventoryItemByID(9999);
        
        assertNull(fakeItemInInventory, "Items not in inventory should not be added");
    }
    
}
