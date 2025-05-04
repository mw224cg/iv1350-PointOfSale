/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.model;
import se.kth.iv1350.pointofsale.integration.ItemDTO;

/**
 *Contains information about the quantity of a sold item in a sale.
 * 
 */
public class SoldItem {
    private ItemDTO itemDTO;
    private int quantitySold;
    private  int STARTING_QUANTITY = 1;

    /**
    *Creates a new instance representing one (1) sold item.
    *@param itemDTO         The DTO representing the item  
    */
    public SoldItem(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
        this.quantitySold = STARTING_QUANTITY;
    }
    
    public ItemDTO getItemDTO(){
        return itemDTO;
    }
    
    public int getQuantitySold(){
        return quantitySold;
    }
    /**
     * Increases the quantity sold by 1.
     */
    void increaseQuantitySold(){
        quantitySold +=1;
    }
    /**
     * After scanning an item adds a specified amount to the quantity sold.  
     * @param amount        The amount of the item during a scan operation.
     */
    public void setQuantitySold(int amount){
        quantitySold = quantitySold + amount;
    }
}

