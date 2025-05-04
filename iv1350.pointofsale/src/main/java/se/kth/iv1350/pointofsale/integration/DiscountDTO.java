/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.integration;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Marcus
 */
public class DiscountDTO {
    private final int ItemID;
    private final double discountPercentage;
    private final boolean isOnlyForMembers;
    
    /**
     * Creates a new instance of a discount
     * @param itemID    
     * @param discountPercentage
     * @param isOnlyformembers 
     */
    public DiscountDTO(int itemID, double discountPercentage, boolean isOnlyformembers) {
        this.ItemID = itemID;
        this.discountPercentage = discountPercentage;
        this.isOnlyForMembers = isOnlyformembers;
    }
    
    public int getItemID(){
    return ItemID;
    }
    
    public double getDiscountPercentage(){
        return discountPercentage;
    }
    
    public boolean isOnlyForMembers(){
        return isOnlyForMembers;
    }
   
}
