/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.model;

/**
 * Represents a payment for a sale.
 * 
 */
class Payment {
    private double amount;

    /**
     * Creates an instance of a payment.
     * @param amount        The amount paid.
     */
    Payment(double amount) {
        this.amount = amount;
    }
    
    double getAmount(){
        return amount;
    }
 
    
}
