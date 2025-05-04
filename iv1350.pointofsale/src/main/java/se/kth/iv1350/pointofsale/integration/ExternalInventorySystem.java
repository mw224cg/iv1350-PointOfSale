/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.integration;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.pointofsale.model.SaleDTO;
import se.kth.iv1350.pointofsale.model.SoldItem;

/**
 * Contains information about the items and their quantity in the inventory.
 * 
 */
public class ExternalInventorySystem {
    private List<InventoryItem> inventory = new ArrayList<>();
    
    /**
    * Creates an instance of the Inventory system and fills inventory with items 
    * and quantity of each item.
    */
    public ExternalInventorySystem(){
        addItems();
    }
    
    private void addItems(){
        inventory.add(new InventoryItem(new ItemDTO("Cucumber", 1234, 12.50, 6.0),50));
        inventory.add(new InventoryItem(new ItemDTO("Milk 1L", 1111, 15.95, 6.0),100));
        inventory.add(new InventoryItem(new ItemDTO("Butter 500g", 2222, 49.95, 15.0),120));
        inventory.add(new InventoryItem(new ItemDTO("Apple, red", 3333, 5.95, 6.0),100));
        inventory.add(new InventoryItem(new ItemDTO("Banana", 4444, 7.0, 6.0),400));
        inventory.add(new InventoryItem(new ItemDTO("Coca Cola, 33cl", 5555, 15.95, 20.0),100));
    }
    
    /**
    * Searches for an item based on the items' identifier
    *@param itemID              The items unique identifier
    *@return itemDescription    if an itemDTO with a matching itemID is found in
    *                           the inventory it is returned. If no matches the 
    *                           function returns null.
    */
    public ItemDTO getItemDescription(int itemID){
        for(InventoryItem invItem : inventory){
            if(invItem.getItem().matchesItemID(itemID)){
                ItemDTO itemDescription = invItem.getItem();
                return  itemDescription;
            }
        }
        return null;
    }
    
    /**
     * Updates the inventory based on a sale.
     * @param saleInformation       contains information about the sale.
     */
    public void updateInventory(SaleDTO saleInformation){
        if(saleInformation == null){
            return;
        }
        for(SoldItem soldItem : saleInformation.getSoldItems()){
            updateInventoryForItem(soldItem);
        }
    }
    
    private void updateInventoryForItem(SoldItem soldItem){
        ItemDTO soldItemDTO = soldItem.getItemDTO();
        int quantitySold = soldItem.getQuantitySold();

        InventoryItem inventoryItem = findInventoryItemByID(soldItemDTO.getItemID());
        if (inventoryItem != null) {
            reduceInventoryQuantity(inventoryItem, quantitySold);
        }
    }
    
    InventoryItem findInventoryItemByID(int itemID) {
        for (InventoryItem item : inventory) {
            if (item.getItem().matchesItemID(itemID)) {
                return item;
            }
        }
        return null;
    }
    
    private void reduceInventoryQuantity(InventoryItem item, int quantitySold) {
        int newQuantity = item.getQuantity() - quantitySold;
        item.setQuantity(newQuantity);
    }
}
