import java.awt.Desktop;

/**
 *
 * @author Tom
 */
import java.io.*;

public class ReceiptReturn {
    Return ret = null;
    public ReceiptReturn(Return ret){
        this.ret = ret;
    }
    
    public boolean printReceipt(){
        try {
            FileWriter out = new FileWriter(Integer.toString(ret.getId()) + ".txt");
            out.write("Stuff Hut\n\n");
            out.write("--------------------------------------------- \n\n");
            for (int i = 0; i < ret.getSize(); i++){
                Item line = ret.get(i);
                //put in quantity
                //NEWLINES DONT WORK YET
                //cashier name, sale id, smiley faces, all to come
                out.write(line.getName() + " x" + line.getQuantity() + "\t \t" + line.getCost() + "\n");
            //nicer formatting to come
           }
            out.write("\n\n\t\t Subtotal: $" + ret.getSubTotal() + "\n");
            out.write("\t\t Tax: +$" + ret.getTax() + "\n");
            out.write("\t\t____________" + "\n");
            out.write("\t\t Refund: $" + ret.getTotal() + "\n");
            out.write("\n\n--------------------------------------------- \n\n");
           out.write("THANKS FOR SHOPPING AT STUFF HUT\nTHE HUT FOR ALL YOUR STUFF \n\n");
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
