/**
 *
 * @author Tom
 */
public class Register {
    Sale sale = new Sale();
    Return ret = new Return();
    //Rental rental = new Rental();
    DBConnect db = new DBConnect();
    
    public Register() { }
    
    public void addItemToSale(String id, String quant){
        db.query("select * from SOFTWARE_product where UPC = " + id + "");
        
        Item item = new Item(db.getString("upc"),db.getString("ITEM_NAME"),db.getString("description"),db.getString("cost"),quant);
        sale.add(item);
    }
    
    public void removeLastItemSale(){
        sale.remove(sale.get(sale.getSize()-1));
    }
    
    public void finishSale(){
        Receipt r = new Receipt(sale);
        sale.calculateTotal();
        
        for(int i = 0; i < sale.getSize(); i++){
            db.query("select * from inventory where UPC = " + sale.get(i).id);
            int quant = Integer.parseInt(db.getString("Stock")) - sale.get(i).quantity;
            db.query("update software_product set stock = " + quant +  " where UPC = " + sale.get(i).id);
        }
        
        db.query("insert into SOFTWARE_invoice values (" + sale.getId() + "0,0)");
        r.printReceipt();
    }
    
    public void cancelSale(){
        sale = new Sale();
    }
    
    public void addItemToReturn(String id, String quant){
        db.query("select * from SOFTWARE_product where SSID = " + id + "");
        
        Item item = new Item(db.getString("upc"),db.getString("ITEM_NAME"),db.getString("description"),db.getString("cost"),quant);
        ret.add(item);
    }
    
    public void removeLastItemReturn(){
        ret.remove(ret.get(ret.getSize()-1));
    }
    
    public void finishReturn(){
        ReceiptReturn r = new ReceiptReturn(ret);
        ret.calculateTotal();
        
        for(int i = 0; i < sale.getSize(); i++){
            db.query("select * from inventory where UPC = " + ret.get(i).id);
            int quant = Integer.parseInt(db.getString("Stock")) + ret.get(i).quantity;
            db.query("update software_product set stock = " + quant +  " where UPC = " + ret.get(i).id);
        }
        
        r.printReceipt();
        //send it to the DB!!!!!!
    }
    
    public void cancelReturn(){
        ret = new Return();
    }
}
