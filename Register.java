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
        db.query("select * from SOFTWARE_product where SSID = " + id + "");
        
        Item item = new Item(db.getString("ssid"),db.getString("ITEMNAME"),db.getString("description"),db.getString("cost"),quant);
        sale.add(item);
    }
    
    public void removeLastItemSale(){
        sale.remove(sale.get(sale.getSize()-1));
    }
    
    public void finishSale(){
        Receipt r = new Receipt(sale);
        sale.calculateTotal();
        r.printReceipt();
        //send it to the DB!!!!!!
    }
    
    public void cancelSale(){
        sale = new Sale();
    }
    
    public void addItemToReturn(String id, String quant){
        db.query("select * from SOFTWARE_product where SSID = " + id + "");
        
        Item item = new Item(db.getString("ssid"),db.getString("ITEMNAME"),db.getString("description"),db.getString("cost"),quant);
        ret.add(item);
    }
    
    public void removeLastItemReturn(){
        ret.remove(ret.get(ret.getSize()-1));
    }
    
    public void finishReturn(){
        ReceiptReturn r = new ReceiptReturn(ret);
        ret.calculateTotal();
        r.printReceipt();
        //send it to the DB!!!!!!
    }
    
    public void cancelReturn(){
        ret = new Return();
    }
}
