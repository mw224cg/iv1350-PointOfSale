/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.model;

import se.kth.iv1350.pointofsale.integration.ItemDTO;
import java.util.List;

/**
 * The register at the POS
 * 
 */
public class Register {
    private double totalCash;
    
    /**
     * Creates a new instance of the register.
     * @param amount    The amount of cash in the register.
     */
    public Register(double amount){
        this.totalCash = amount;
    }
    
    public void updateRegister(double amount){
    this.totalCash +=amount;
    }
    
    /**
     * Displays the current sale information.
     * @param currentSaleInfo   Contains information about the current sale
     */
    public void displaySaleInfo(SaleDTO currentSaleInfo){
        SoldItem currentSoldItem = currentSaleInfo.getLastSoldItem();
        ItemDTO currentItem = currentSoldItem.getItemDTO();
        
        System.out.println("Item ID: " + currentItem.getItemID());
        System.out.println("Item name: " + currentItem.getName());
        System.out.println("Item cost: " + currentItem.getPrice() + "kr");
        System.out.println("Item VAT: " + currentItem.getVAT() + "%");
        System.out.println("Quantity of item: " + currentSoldItem.getQuantitySold());
        
        System.out.println();
        System.out.println("Total cost: " + currentSaleInfo.getTotalPrice() + "kr");
        System.out.println("Total VAT: " + currentSaleInfo.getVAT() + "kr\n");
        
    }
    
    /**
     * Displays the total price and VAT for a sale.
     * @param currentSaleInfo       Contains information about the current sale.
     */
    public void displayEndSaleInfo(SaleDTO currentSaleInfo){
        System.out.println("End sale:");
        System.out.println("Total cost: " + currentSaleInfo.getTotalPrice() + "kr");
        System.err.println("Total VAT: " + currentSaleInfo.getVAT() + "kr\n");
    }
    
    
    /**
     * Prints a receipt in the register.
     * @param saleInfo      Contains information about the sale.
     */
    public void printReceipt(SaleDTO saleInfo){
        System.out.println("-----RECEIPT------\n");
        System.out.println("Time of sale: " + saleInfo.getTime());
        
        for(SoldItem item : saleInfo.getSoldItems()){
            String name = item.getItemDTO().getName();
            int quantity = item.getQuantitySold();
            double price = item.getItemDTO().getPrice();
            double vat = item.getItemDTO().getVAT();
            double total = price * quantity;
            
            System.out.printf("%s x%d  %.2f kr (VAT %.0f%%)%n", name, quantity, total, vat);
        }
        System.out.println("-------------------");
        System.out.printf("Total VAT: %.2f kr%n", saleInfo.getVAT());
        System.out.printf("Total cost: %.2f kr%n", saleInfo.getTotalPrice());
        System.out.printf("Total discount for sale: %.2f kr %n", saleInfo.getDiscountAmount());
        System.out.println("-------------------");
    }
    
}
