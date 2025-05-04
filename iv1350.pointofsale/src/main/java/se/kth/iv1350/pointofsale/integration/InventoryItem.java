/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.integration;

/**
 * This class represents an items status in the inventory.
 * 
 */
public class InventoryItem {
    private ItemDTO item;
    private int quantity;

    /**
    * Creates a new instance of an item in the inventory
    * 
    * @param item       The item in the inventory
    * @param quantity   The quantity of the item in the inventory
    */
    public InventoryItem(ItemDTO item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
    
    public ItemDTO getItem(){
        return item;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    
    
}
