/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tom
 */

public class Item {
    int id;
    String name = new String();
    String description = new String();
    int quantity; //SHOULD BE how much we're buying, IS how much in inventory
    float cost;
    
    public Item(String id, String name, String description, String cost, String quantity){
        this.id = Integer.parseInt(id);
        this.name = name;
        this.description = description;
        this.cost = Float.parseFloat(cost);
        this.quantity = Integer.parseInt(quantity);
    }
    
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
        
    public String getDescription(){
        return description;
    }
            
    public float getCost(){
        return cost;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
     public void setId(int id){
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
        
    public void setDescription(String description){
        this.description = description;
    }
            
    public void setCost(float cost){
        this.cost = cost;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
