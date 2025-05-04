/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.model;
import se.kth.iv1350.pointofsale.integration.DiscountDTO;
import java.time.LocalTime;
import java.util.List;

/**
 * Contains information about a single sale.
 * 
 */

public class SaleDTO {
    private final LocalTime time;
    private final double vat;
    private final double totalPrice;
    private final List<SoldItem> soldItemsList;
    private final double discountAmount;

    /**
     * Creates a new instance representing a sale.
     * @param soldItems         Contains sold items and their quantity
     * @param time              Time of sale
     * @param vat               Total VAT for the sale
     * @param totalPrice        Total cost/price for the sale
     * @param discountAmount    Discount deducted from sale
     */
    public SaleDTO(List<SoldItem> soldItems, LocalTime time, double vat, double totalPrice, double discountAmount) {
        this.soldItemsList = soldItems;
        this.time = time;
        this.vat = vat;
        this.totalPrice = totalPrice;
        this.discountAmount = discountAmount;
    }
    
    public double getTotalPrice(){
        return totalPrice;
    }
    
    public double getVAT(){
        return vat;
    }
    
    public List<SoldItem> getSoldItems(){
        return soldItemsList;
    }
    
    public SoldItem getLastSoldItem(){
        if(soldItemsList == null){
            return null;
        }
        return soldItemsList.get(soldItemsList.size()-1);
    }
    
    public LocalTime getTime(){
        return time;
    }
    
    public double getDiscountAmount(){
        return discountAmount;
    }    
    
}
