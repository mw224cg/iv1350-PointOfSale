/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.model;
import java.time.LocalTime;
import java.util.List;

/**
 *Represents a receipt for a particular sale.
 * 
 */
public class Receipt {
    private double totalPrice;
    private double totalVAT;
    private LocalTime time;
    private List<SoldItem> itemsList;
    private double amountPaid;
    private double change;
    private double discount;
    
    /**
     * Creates a new instance of a reciept containing information about a sale.
     * @param saleDTO       Contains information about the transpired sale
     * @param payment       The payment done by the customer
     */
    Receipt(SaleDTO saleDTO, Payment payment) {
        this.totalPrice = saleDTO.getTotalPrice();
        this.amountPaid = payment.getAmount();
        this.totalVAT = saleDTO.getVAT();
        this.itemsList = saleDTO.getSoldItems();
        this.time = saleDTO.getTime();
        this.discount = saleDTO.getDiscountAmount();
        calculateChange();
        
    }
    
    private void calculateChange(){
    double change = totalPrice - amountPaid;
    this.change = change;
    }
    
    public double getTotalPrice(){
        return totalPrice; 
    }
    
    public double getTotalVAT(){
        return totalVAT;
    }
    
    public LocalTime getTime(){
        return time;
    }
    
    public double getChange(){
        return change;
    }
    
    public double getAmountPaid(){
    return amountPaid;
    }
    
    public List<SoldItem> getSoldItems(){
    return itemsList;
    }
    
    public double getDiscountAmount(){
        return discount;
    }
    
    
}
