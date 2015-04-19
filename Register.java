

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
        
        Item item = new Item(db.getString("upc"),db.getString("ITEM_NAME"),db.getString("description"),db.getString("daily_rate"),quant);
        rental.add(item);
    }
     
    public void addItemToSale(String id, String quant){
        System.out.println("select * from SOFTWARE_product where UPC = '" + id + "'");
            if(!db.query("select * from SOFTWARE_product where UPC = " + id + ""))
                javax.swing.JOptionPane.showMessageDialog(null, "Invalid UPC");
        
        Item item = new Item(db.getString("upc"),db.getString("ITEM_NAME"),db.getString("description"),db.getString("cost"),quant);
        sale.add(item);
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

    }
    public void finishSale(){
        Receipt r = new Receipt(sale);
        sale.calculateTotal();
        
        
        for (int i = 0; i < sale.getSize(); i++){
            System.out.println("update software_product set stock = stock - " + sale.get(i).quantity + " where UPC = '" + sale.get(i).id + "'");
            db.update("update software_product set stock = stock - " + sale.get(i).quantity + " where UPC = " + sale.get(i).id + "");
           
        }
 
        r.printReceipt();

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
        
        Item item = new Item(db.getString("upc"),db.getString("ITEM_NAME"),db.getString("description"),db.getString("cost"),quant);
        System.out.println(item.getName() + "   " + item.getCost());
        ret.add(item);
    }
    
    public void removeLastItemReturn(){
        ret.remove(ret.get(ret.getSize()-1));
    }
    
    public void finishReturn(){
        ReceiptReturn r = new ReceiptReturn(ret);
        ret.calculateTotal();
        
        
        try{
        for(int i = 0; i < sale.getSize(); i++){
            db.query("select * from software_product where UPC = " + sale.get(i).id);
            int quant = Integer.parseInt(db.getString("Stock")) + sale.get(i).quantity;
            db.query("update software_product set stock = " + quant +  " where UPC = " + sale.get(i).id);
        }
        
        db.query("insert into SOFTWARE_invoice values (" + sale.getId() + "0,0)");}
        catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, "Error updating inventory");
        }
        r.printReceipt();
        //send it to the DB!!!!!!
    }
    
    public void cancelReturn(){
        ret = new Return();
    }
    
    
}
