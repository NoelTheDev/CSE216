/**
 *
 * @author Noel
 */
import java.util.*;

public class Sale {
    
    ArrayList items = new ArrayList();
    int min = 100000;
    int max = 999999;
    int result = (max - min) +1;
    int id = (int)(Math.random()*result) + min;
    float subtotal = 0;
    
    public Sale(){
    
}
    
    
    public void add(Item itemIn){
        //You can use 'contains' to check if the arraylist has a specific object in it
        
        items.add(itemIn);
        subtotal += itemIn.getQuantity() * itemIn.getCost();
        
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
    
    
}

