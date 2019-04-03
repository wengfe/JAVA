import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionExercise {
    static BasicDataSource bs;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=true";
    static final String USER = "root";
    static final String PASSWORD = "admin";

    public static void DBPoolInit() {
        bs = new BasicDataSource();
        bs.setDriverClassName(JDBC_DRIVER);
        bs.setUrl(JDBC_URL);
        bs.setUsername(USER);
        bs.setPassword(PASSWORD);
    }

    public static int CanBuy(String productName) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        int inventory = 0;

        try {
            conn = bs.getConnection();
            conn.setAutoCommit(false);
            pstat = conn.prepareStatement("select * from Inventory where ProductName = ?");
//            pstat.setString(1, "Inventory");
            pstat.setString(1, productName);


            rs = pstat.executeQuery();
            conn.commit();
            rs.next();
            inventory = rs.getInt("Inventory");
            System.out.println(inventory);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != conn)
                    conn.close();
                if (null != pstat)
                    pstat.close();
                if (null != rs)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return inventory;
    }

    public static void PayOrder(String buyer, String produceName, int num) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = bs.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("UPDATE Inventory set Inventory = Inventory -? where ProductName = ?");
            pstmt.setInt(1,num);
            pstmt.setString(2,produceName);
            pstmt.execute();

            pstmt = conn.prepareStatement("select ID from pay_order where ID is not null order by ID desc limit 1 ");
            rs = pstmt.executeQuery();
            rs.next();
            int id = rs.getInt("ID") + 1 ;
            System.out.println(buyer+ produceName + num);
            pstmt = conn.prepareStatement("insert into pay_order VALUES(?,?,?,?)");
            pstmt.setInt(1,id);
            pstmt.setString(2,buyer);
            pstmt.setString(3,produceName);
            pstmt.setInt(4,num);
            pstmt.execute();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != conn)
                    conn.close();
                if (null != pstmt)
                    pstmt.close();
                if (null != rs)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String name = "xiaoming";
        String product = "bag";
        int num = 1;
        DBPoolInit();

        if((CanBuy("bag") - num) > 0 ){
            PayOrder(name,product,num);
        }
    }
}
