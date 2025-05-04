/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.integration;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import se.kth.iv1350.pointofsale.model.SaleDTO;
import se.kth.iv1350.pointofsale.model.SoldItem;

/**
 *  Represents a discount database. Responsible for calculating discounts.
 * 
 */
public class DiscountDatabase {
    private Map<Integer, DiscountDTO> discountMap;
    private Set<Integer> EligbleCustomerIDs;
    
    /**
     * Creates a new instance of the database.
     */
    public DiscountDatabase(){
        this.discountMap = new HashMap<>();
        this.EligbleCustomerIDs = new HashSet<>();
        addDiscounts();
        addEligbleCustomerIDs();
    }
    
    private void addDiscounts(){
        discountMap.put(1234, new DiscountDTO(1234, 0.10, true));
        discountMap.put(2222, new DiscountDTO(2222, 0.15, false));
        discountMap.put(5555,  new DiscountDTO(5555, 0.25, true));
    }
    
    private void addEligbleCustomerIDs(){
        EligbleCustomerIDs.add(123456);
        EligbleCustomerIDs.add(000001);
        EligbleCustomerIDs.add(000002);
        EligbleCustomerIDs.add(000003);
        EligbleCustomerIDs.add(000004);
    }
    
    /**
     * Checks if a given customer is a member.
     * @param customerID    The customers identification
     * @return              true if the customer is a member.
     */
    private boolean isMember(int customerID){
    return EligbleCustomerIDs.contains(customerID);
    }
    
    /**
     * Returns the total discount as an amount to be deducted for the sale 
     * @param customerID
     * @param saleDTOBeforeDiscount
     * @return 
     */
    public double getDiscount(int customerID, SaleDTO saleDTOBeforeDiscount){
        boolean member = isMember(customerID);
        double totalDiscountAmount = 0;
        
        for(SoldItem item : saleDTOBeforeDiscount.getSoldItems()){
            DiscountDTO discount = discountMap.get(item.getItemDTO().getItemID());
            if(discount !=null && (member || !discount.isOnlyForMembers())){
                double itemDiscount = calculateItemDiscount(item, discount);
                totalDiscountAmount += itemDiscount;
            }
        }
        return totalDiscountAmount;
    }
    
    private double calculateItemDiscount(SoldItem item, DiscountDTO discount){
        return item.getItemDTO().getPrice() * item.getQuantitySold() * discount.getDiscountPercentage();
    }
    
    
}
