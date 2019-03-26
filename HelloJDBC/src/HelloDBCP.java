import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloDBCP {
    public static BasicDataSource ds = null;
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:mysql://localhost/test?useSSL=true";
    public static final String USER = "root";
    public static final String PASSWORD = "admin";

//    初始化 连接池
    public static void dbpoolInit() {
        ds = new BasicDataSource();
        ds.setDriverClassName(JDBC_DRIVER);
        ds.setUrl(JDBC_URL);
        ds.setUsername(USER);
        ds.setPassword(PASSWORD);
    }

    public  void dsPoolTest() {
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery("select * from user");

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (conn != null)
                    conn.close();
                if (stat != null)
                    stat.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        dbpoolInit();
        new HelloDBCP().dsPoolTest();
    }
}
