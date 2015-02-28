/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tom
 */
import java.io.*;

public class Receipt {
    Sale sale = null;
    public Receipt(Sale sale){
        this.sale = sale;
    }
    
    public boolean printReceipt(){
        try {
            FileWriter out = new FileWriter(Integer.toString(sale.getId()) + ".txt");
            out.write("Stuff Hut\n\n");
            out.write("---------------------------------------------");
            for (int i = 0; i < sale.getSize(); i++){
                Item line = sale.get(i);
                //put in quantity
                //NEWLINES DONT WORK YET
                //cashier name, sale id, smiley faces, all to come
                out.write(line.getName() + "\t" + line.getCost());
            //nicer formatting to come
           }
           out.write("THANKS FOR SHOPPING AT STUFF HUT\nTHE HUT FOR ALL YOUR STUFF");
           out.close();
        }
        catch (IOException e){
            System.err.println("Can't write to file HAHAHAHA");
            return false;
        }
        return true;
    }
}
