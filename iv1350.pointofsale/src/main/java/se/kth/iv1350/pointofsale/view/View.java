/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.pointofsale.view;

import se.kth.iv1350.pointofsale.controller.Controller;
import se.kth.iv1350.pointofsale.model.SaleDTO;
/**
 * Placeholder for the real view. Contains hardcoded calls to the system
 * operations in the controller.
 * 
 */
public class View {
    private Controller controller;
    
    /**
     * Creates a new instance of the view
     * @param controller    The controller that is used for all operations
     */
    public View(Controller controller){
        this.controller = controller;
    }
    
    /**
     * Performs a fake sale by calling all system operations in the controller.
     */
    public void runFakeExecution(){
        controller.startSale();
        System.out.println("A new sale has started\n");
        
        controller.scanItem(1111);
        System.out.println("Scanned item with item ID \"1111\" \n");
        controller.scanItem(1234);
        System.out.println("Scanned item with item ID \"1234\" \n");
        controller.scanItem(2222);
        System.out.println("Scanned item with item ID \"2222\" \n");
        controller.scanItem(3333);
        System.out.println("Scanned item with item ID \"3333\" \n");
        
        controller.enterQuantity(3);
        System.out.println("Adjusted quantity  of last item \"Apple\"\n");
        
        controller.endSale();
        System.out.println("Cashier ended sale\n");
        
        controller.requestDiscount(123456);
        System.out.println("Discount requested by customer\n");
        
        controller.pay(2000);
        System.out.println("Customer pays for goods\n");
        
        
        controller.startSale();
        System.out.println("A new sale is started and a non-existing item is scanned \n");
        
        controller.scanItem(3456);
        System.out.println("Scanned item \"3456\" ");
        
        controller.endSale();
    }
    
}
