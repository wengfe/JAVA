import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

//使用线程来同时启用多个连接
public class JDBCvsDBCP extends Thread{
    public static BasicDataSource ds = null;
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:mysql://localhost/test?useSSL=true";
    public static final String USER = "root";
    public static final String PASSWORD = "admin";

    public static void dbpoolInit() {
        ds = new BasicDataSource();
        ds.setDriverClassName(JDBC_DRIVER);
        ds.setUrl(JDBC_URL);
        ds.setUsername(USER);
        ds.setPassword(PASSWORD);
//        设置连接池最大连接数量
        ds.setMaxTotal(2);
    }

    public void dsPoolTest() {
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

    public void jdbcTest(){
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL,USER,PASSWORD);
            stat = conn.createStatement();
            rs = stat.executeQuery("select * from user");
            while (rs.next()){
                System.out.println(rs.getString("name"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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

    public void run(){
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start < 10000){
            dsPoolTest();
//            jdbcTest();
        }
    }

    public static void main(String[] args) {
        dbpoolInit();
        for (int i = 0; i<10;i++){
//            start 方法来启用新的线程
            new JDBCvsDBCP().start();
        }
    }
}
