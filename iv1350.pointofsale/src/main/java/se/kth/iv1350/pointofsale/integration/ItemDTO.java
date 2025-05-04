/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.integration;

/**
 * Contains information about a single item
 *
 */
public class ItemDTO {
    private final String name;
    private final int itemID;
    private final double price;
    private final double vat;

    
    /**
     * Creates a new instance of the object
     * @param name      The name of the item
     * @param itemID    The items' identifier
     * @param price     The price of the item
     * @param vat       The VAT-rate of the item as a percentage
     */
    public ItemDTO(String name, int itemID, double price, double vat) {
        this.name = name;
        this.itemID = itemID;
        this.price = price;
        this.vat = vat;
    }
    
    public String getName(){
        return name;
    }
    
    public int getItemID(){
        return itemID;
    }
    
    public double getVAT(){
        return vat;
    }
    
    public double getPrice(){
        return price;
    }
    
    /**
     * Checks if a searched item identifier matches the item identifier of the item.
     * @param searchedItemID    The item identifier to compare to the object       
     * @return boolean          true if they are identical, otherwise false
     */
    public boolean matchesItemID(int searchedItemID){
        if(this.itemID == searchedItemID){
            return true;
        }
        return false;
    }
}
