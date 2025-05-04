/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.model;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.pointofsale.integration.ItemDTO;

/**
 * Represents a single sale made by a customer.
 * 
 */
public class Sale {
    private LocalTime timeOfSale;
    private double currentVAT;
    private double currentPrice;
    private List<SoldItem> soldItemsList;
    private double discountAmount;
    
    /**
     * Creates a new instance of a sale.
     */
    public Sale(){
        setTimeOfSale();
        this.soldItemsList = new ArrayList<>();
    }
    
    private void setTimeOfSale(){
        this.timeOfSale = LocalTime.now();
    }
    
    /**
     * Updates the current price for the sale based on the items cost.
     * @param itemDTO       Represents the bought item
     */
    private void updateCurrentPrice(ItemDTO itemDTO){
        currentPrice += itemDTO.getPrice();
    }
    
    /**
    *Updates the current VAT based on the VAT of the item.
    *@param ItemDTO         The bought item
    */
    private void updateCurrentVAT(ItemDTO itemDTO){
        currentVAT += itemDTO.getPrice() * (itemDTO.getVAT() * 0.01);
    }
    
    /**
    *Adds an item to the list of sold items, if it has already been added:
    *increases quantity by 1
    *@param itemDTO     Item to be added to list of sold items.
    */
    public void addItem(ItemDTO itemDTO){
        for(SoldItem item : soldItemsList){
            if(item.getItemDTO().getItemID()==itemDTO.getItemID()){
                item.increaseQuantitySold();
                updateCurrentPrice(itemDTO);
                updateCurrentVAT(itemDTO);
                return;
            }
        }
        soldItemsList.add(new SoldItem(itemDTO));
        updateCurrentPrice(itemDTO);
        updateCurrentVAT(itemDTO);
    }
    
    /**
     * Increases the quantity of last scanned item by an amount. 
     * @param quantity      The quantity of the item during a scan operation
     */
    public void adjustQuantityOfLastItem(int quantity){
        SoldItem lastItem = getLastSoldItem();
        int alreadyScanned = 1;
        lastItem.setQuantitySold(quantity-alreadyScanned);
    }
    
    /**
     * Creates and returns a new instance of a SaleDTO describing the sale.
     * @return  saleDTO        A DTO describing the sale.
     */
    public SaleDTO getSaleDTO(){
        SaleDTO saleDTO = new SaleDTO(soldItemsList, timeOfSale, currentVAT, currentPrice, discountAmount);
        return saleDTO;
    }
    
    /**
     * Gets the last scanned item in the sale.
     * @return      The last scanned item in the sale.
     */
    private SoldItem getLastSoldItem(){
        if(soldItemsList.isEmpty()){
            return null;
        }
        return soldItemsList.get(soldItemsList.size()-1);
    }
    
    /**
     * Creates a receipt for the sale after being paid for.
     * @param amount        The amount paid.
     * @return receipt      A receipt containg information about the finished sale.
     */
    public Receipt pay(double amount){
    Payment payment = new Payment(amount);
    SaleDTO saleDTO = getSaleDTO();
    Receipt receipt = new Receipt(saleDTO, payment);
    return receipt;
    }
    
    /**
     * Applies discount to the cost of the sale.
     * @param discountAmount        The amount to be deducted.
     */
    public void applyDiscount(double discountAmount){
    currentPrice = currentPrice - discountAmount;
    this.discountAmount = discountAmount;
    }
    
}
