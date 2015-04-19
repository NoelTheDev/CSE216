import java.awt.Desktop;

import java.io.*;

public class Receipt {
    Sale sale = null;
    Rental rental = null;
    Return ret = null;
    float tendered;
    String ccard = "";
    String brand = "";
    
    public Receipt(Sale sale, float tendered, String ccard, String brand){
        this.sale = sale;
        this.tendered = tendered;
        this.ccard = ccard;
        this.brand = brand;
    }
    public Receipt(Rental rental, float tendered){
        this.rental = rental;
        this.tendered = tendered;
    }
    public Receipt(Return ret){
        this.ret = ret;
    }
     public boolean printRentalReceipt(){
        try {
            FileWriter out = new FileWriter(Integer.toString(rental.getId()) + "receipt.txt");
            out.write("Stuff Hut\r\n Rental #: " + rental.getId() + "\r\n");
            out.write("--------------------------------------------- \r\n\r\n");
            for (int i = 0; i < rental.getSize(); i++){
                Item line = rental.get(i);
                //put in quantity
                //NEWLINES DONT WORK YET
                //cashier name, sale id, smiley faces, all to come
                out.write(line.getName() + " x" + line.getQuantity() + "\t \t$"+String.format("%.02f",line.getCost()) + "\r\n");
            //nicer formatting to come
           }
            out.write("\r\n\r\n\t\t Daily Rate: $" + String.format("%.02f",rental.getSubTotal()) + "\r\n");
            out.write("\t\t Tax: +$"+String.format("%.02f",rental.getTax()) + "\r\n");
            out.write("\t\t____________" + "\r\n");
            out.write("\t\t Total: $"+String.format("%.02f",rental.getTotal()) + "\r\n");
            out.write("\r\n\r\n--------------------------------------------- \r\n\r\n");
           out.write("THANKS FOR SHOPPING AT STUFF HUT\r\nTHE HUT FOR ALL YOUR STUFF \r\n\r\n");
           out.close();
           File pop = new File (Integer.toString(rental.getId()) + ".txt");
           Desktop.getDesktop().edit(pop);
        }
        catch (IOException e){
            System.err.println("Can't write to file HAHAHAHA");
            return false;
        }
        return true;
    }
    public boolean printReceipt(){
        try {
            FileWriter out = new FileWriter(Integer.toString(sale.getId()) + ".txt");
            out.write("Stuff Hut\r\nSale #: " + sale.getId() + "\r\n");
            if (!ccard.equals("")){
                System.out.println("KILL YOUR PARENTS");
                for(int i = 0; i < ccard.length()-4; i++){
                    ccard = ccard.substring(0,i)+'x'+ccard.substring(i+1);
                }
                out.write("Card type: " + brand + "\r\n");
                out.write("Acct #: " + ccard + "\r\n");
            }
            out.write("--------------------------------------------- \r\n\r\n");
            for (int i = 0; i < sale.getSize(); i++){
                Item line = sale.get(i);
                //put in quantity
                //NEWLINES DONT WORK YET
                //cashier name, sale id, smiley faces, all to come
                out.write(line.getName() + " x" + line.getQuantity() + "\t \t$"+String.format("%.02f",line.getCost()) + "\r\n");
            //nicer formatting to come
           }
            out.write("\r\n\r\n\t\t Subtotal: $" +String.format("%.02f",sale.getSubTotal()) + "\r\n");
            out.write("\t\t      Tax: +$" + String.format("%.02f",sale.getTax()) + "\r\n");
            out.write("\t\t    _______________" + "\r\n");
            out.write("\t\t     Total: $" + String.format("%.02f",sale.getTotal()) + "\r\n");
            out.write("\r\n\t\t     Tendered: $" + String.format("%.02f",tendered) + "\r\n");
            out.write("\t\t       Change: $" + String.format("%.02f",tendered - sale.getTotal()));
            out.write("\r\n\r\n--------------------------------------------- \r\n\r\n");
           out.write("THANKS FOR SHOPPING AT STUFF HUT\r\nTHE HUT FOR ALL YOUR STUFF \r\n\r\n");
           out.close();
           File pop = new File (Integer.toString(sale.getId()) + ".txt");
           Desktop.getDesktop().edit(pop);
        }
        catch (IOException e){
            System.err.println("Can't write to file HAHAHAHA");
            return false;
        }
        return true;
    }
    public boolean printReturnReceipt(){
        try {
            FileWriter out = new FileWriter(Integer.toString(ret.getId()) + ".txt");
            out.write("Stuff Hut\r\n\r\n");
            out.write("--------------------------------------------- \r\n\r\n");
            for (int i = 0; i < ret.getSize(); i++){
                Item line = ret.get(i);
                //put in quantity
                //NEWLINES DONT WORK YET
                //cashier name, sale id, smiley faces, all to come
                out.write(line.getName() + " x" + line.getQuantity() + "\t \t$" + String.format("%.02f",line.getCost()) + "\r\n");
            //nicer formatting to come
           }
            out.write("\r\n\r\n\t\t Subtotal: $" + String.format("%.02f",ret.getSubTotal()) + "\r\n");
            out.write("\t\t Tax: +$" + String.format("%.02f",ret.getTax()) + "\r\n");
            out.write("\t\t____________" + "\r\n");
            out.write("\t\t Refund: $" + String.format("%.02f",ret.getTotal()) + "\r\n");
            out.write("\r\n\r\n--------------------------------------------- \r\n\r\n");
           out.write("THANKS FOR SHOPPING AT STUFF HUT\r\nTHE HUT FOR ALL YOUR STUFF \r\n\r\n");
           out.close();
           File pop = new File (Integer.toString(ret.getId()) + ".txt");
           Desktop.getDesktop().edit(pop);
        }
        catch (IOException e){
            System.err.println("Can't write to file HAHAHAHA");
            return false;
        }
        return true;
    }
}
