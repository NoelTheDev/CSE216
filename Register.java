

/**
 *
 * @author Tom
 */
public class Register {
    Sale sale = new Sale();
    Return ret = new Return();
    DBConnect db = new DBConnect();
    String rentID = ("");
    
    
    
    public Register() { }
    
    Rental rental = new Rental();
    
     public void addItemToRental(String id, String quant){
        System.out.println("select * from SOFTWARE_product where UPC = '" + id + "'");
        if(!db.query("select * from SOFTWARE_product where UPC = " + id + ""))
            javax.swing.JOptionPane.showMessageDialog(null, "Invalid UPC");
        
        boolean match = false;
        
        Item item = new Item(db.getString("upc"),db.getString("ITEM_NAME"),db.getString("description"),db.getString("daily_rate"),quant);
        
        
        for (int i = 0; i < rental.getSize(); i++){
            
           if((rental.get(i).id == item.getId()) && (rental.get(i).cost == item.getCost())){
               
               int newquant = rental.get(i).quantity + Integer.parseInt(quant);
               Item newItem = new Item(db.getString("upc"),db.getString("ITEM_NAME"),db.getString("description"),db.getString("cost"),String.valueOf(newquant));
                
              
                rental.add(newItem);
                rental.remove(rental.get(i));
                match = true;
           }
           
        }
        if(match == false){
        rental.add(item);
        }
        
        
        
        
        
        
    }
     
    public void addItemToSale(String id, String quant){
        System.out.println("select * from SOFTWARE_product where UPC = '" + id + "'");
            if(!db.query("select * from SOFTWARE_product where UPC = " + id + ""))
                javax.swing.JOptionPane.showMessageDialog(null, "Invalid UPC");
            
            boolean match = false;
 
        Item item = new Item(db.getString("upc"),db.getString("ITEM_NAME"),db.getString("description"),db.getString("cost"),quant); 
        
        
        for (int i = 0; i < sale.getSize(); i++){
            
           if((sale.get(i).id == item.getId()) && (sale.get(i).cost == item.getCost())){
               
               int newquant = sale.get(i).quantity + Integer.parseInt(quant);
               Item newItem = new Item(db.getString("upc"),db.getString("ITEM_NAME"),db.getString("description"),db.getString("cost"),String.valueOf(newquant));
                
                
                sale.add(newItem);
                sale.remove(sale.get(i));
                match = true;
           }
           
        }
        if(match == false){
        sale.add(item);
        }
    }
    
    public void removeLastItemSale(){
        sale.remove(sale.get(sale.getSize()-1));
    }
    
    //removes last item in the rental grid
    public void removeLastItemRental(){
        rental.remove(rental.get(rental.getSize()-1));
    }
    
    public void finishRental(){
       Receipt r = new Receipt(rental);
       System.out.println("1");
        rental.calculateTotal();
        System.out.println("2");
        
        for (int i = 0; i < rental.getSize(); i++){
            System.out.println("update software_product set stock = stock - " + rental.get(i).quantity + " where UPC = '" + rental.get(i).id + "'");
            db.update("update software_product set stock = stock - " + rental.get(i).quantity + " where UPC = " + rental.get(i).id + "");
           
        }
        System.out.println("3");
        r.printRentalReceipt();

       rental = new Rental();
        
    }
    public void finishSale(){
        Receipt r = new Receipt(sale);
        sale.calculateTotal();
        
        
        for (int i = 0; i < sale.getSize(); i++){
            System.out.println("update software_product set stock = stock - " + sale.get(i).quantity + " where UPC = '" + sale.get(i).id + "'");
            db.update("update software_product set stock = stock - " + sale.get(i).quantity + " where UPC = " + sale.get(i).id + "");
           
        }
 
        r.printReceipt();
        
        sale = new Sale();
    }
    
    public void cancelSale(){
        sale = new Sale();
    }
    
    public void cancelRental(){
        rental = new Rental();
    }
    
    public Item getSaleItem(int n){
        return sale.get(n);
    }
    public Item getReturnItem(int n){
        return ret.get(n);
    }
    
    
    public float getSaleSubtotal(){
        
        return sale.getSubTotal();
    }
    
    public float getReturnSubtotal(){
        return ret.getSubTotal();
        
    }
    public float getRentalSubtotal(){
        return rental.getSubTotal();
    }
    
    public Item getRentalItem(int n){
        return rental.get(n);
    }
    
    public void addItemToReturn(String id, String quant){
        if (!db.query("select * from SOFTWARE_product where UPC = " + id + ""))
            javax.swing.JOptionPane.showMessageDialog(null, "Invalid UPC");
        
        
        boolean match = false;
        
        Item item = new Item(db.getString("upc"),db.getString("ITEM_NAME"),db.getString("description"),db.getString("cost"),quant);
        System.out.println(item.getName() + "   " + item.getCost());
        
        for (int i = 0; i < ret.getSize(); i++){
            
           if((ret.get(i).id == item.getId()) && (ret.get(i).cost == item.getCost())){
               
               int newquant = ret.get(i).quantity + Integer.parseInt(quant);
               Item newItem = new Item(db.getString("upc"),db.getString("ITEM_NAME"),db.getString("description"),db.getString("cost"),String.valueOf(newquant));
                
               
                ret.add(newItem);
                ret.remove(ret.get(i));
                match = true;
           }
           
        }
        if(match == false){
         ret.add(item);
        }
         
       
    }
    
    public void removeLastItemReturn(){
        ret.remove(ret.get(ret.getSize()-1));
    }
    
    public void finishReturn(){
        ReceiptReturn r = new ReceiptReturn(ret);
        ret.calculateTotal();

         for (int i = 0; i < ret.getSize(); i++){
            System.out.println("update software_product set stock = stock + " + ret.get(i).quantity + " where UPC = '" + ret.get(i).id + "'");
            db.update("update software_product set stock = stock + " + ret.get(i).quantity + " where UPC = " + ret.get(i).id + "");
           
        }
        r.printReceipt();
       
        
         ret = new Return();
    }
    
    public void cancelReturn(){
        ret = new Return();
    }
    
    
}
