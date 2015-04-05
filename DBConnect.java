
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tom
 */
public class DBConnect {

    static Connection con = null;
    static Statement s = null;
    static ResultSet result;

    public DBConnect() {
        String username = "amm216";
        String pass = "Archie1994";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", "" + username + "",
                    "" + pass + "");
            s = con.createStatement();

        } catch (java.lang.ClassNotFoundException e2) {
            System.out.println("Missing necessary driver..");
        } catch (SQLException e) {
            System.out.println("Invalid username and password..");
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public void query(String q) throws Exception{
        try {
            result = s.executeQuery(q);
            ResultSetMetaData rsmd = result.getMetaData();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public String getString(String s) {
        try {
            return result.getString(s);
        } catch (Exception e) {
            System.out.println("error");
            return "seqmentation fault";
        }
    }
}
