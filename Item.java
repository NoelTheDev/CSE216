/**
 *
 * @author Tom
 */
public class Item {
    String id = new String();
    String name = new String();
    String description = new String();
    String cost = new String();
    public Item(String id, String name, String description, String cost){
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }
    
    public String getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
        
    public String getDescription(){
        return description;
    }
            
    public String getCost(){
        return cost;
    }
     public void setId(String id){
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
        
    public void setDescription(String description){
        this.description = description;
    }
            
    public void setCost(String cost){
        this.cost = cost;
    }
    
}
