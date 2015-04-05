/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tom
 */
import java.util.*;

public class Return {
    
    ArrayList items = new ArrayList();
    int min = 100000;
    int max = 999999;
    int result = (max - min) +1;
    int id = (int)(Math.random()*result) + min;
    float subtotal = 0;
    float total = 0;
    float tax = 0;
    TaxCalculator tCalc = new TaxCalculator();
    
    public Return(){
    
}
    
    
    public void add(Item itemIn){
        //You can use 'contains' to check if the arraylist has a specific object in it
        
        items.add(itemIn);
        subtotal += itemIn.getCost() * itemIn.getQuantity();
        
    }
    
    public void remove(Item itemOut){
        items.remove(itemOut);
        subtotal -= itemOut.getCost() * itemOut.getQuantity();
    }
    
    public Item get(int m){
        return (Item)items.get(m);
        
    }
    public int getSize(){
        return items.size();
        
    }
    
    public int getId(){        
        return id;
    }
    
    public float getSubTotal(){
    return subtotal;
}
    
    public float getTax(){
        
        return tax;
    }
    public void calculateTotal(){
        tax = tCalc.taxCalc(subtotal);
        total = subtotal + tax;
        
    }
    
    public float getTotal(){
        
        return total;
    }
}
