import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class HelloTransaction {
    static BasicDataSource ds;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=true";
    static final String USER = "root";
    static final String PASSWORD = "admin";

    public static void DBPoolInit() {
        ds = new BasicDataSource();
        ds.setDriverClassName(JDBC_DRIVER);
        ds.setUrl(JDBC_URL);
        ds.setUsername(USER);
        ds.setPassword(PASSWORD);
    }

    public void Transaction() {
        Connection conn = null;
        PreparedStatement psta = null;
        ResultSet rs = null;
        Savepoint sp = null;

        try {
            conn = ds.getConnection();
//            开启事务
            conn.setAutoCommit(false);
            psta = conn.prepareStatement("update user set account = ? where name = ?");
            psta.setInt(1, 0);
            psta.setString(2, "Mike");
            psta.execute();
            sp = conn.setSavepoint();

            psta.setString(1, "100");
            psta.setString(2, "Bob");
            psta.execute();

//            提交事务
//            conn.commit();
            throw new SQLException();
        } catch (SQLException e) {
            try {
//                回滚事务到检查点
                conn.rollback(sp);
                psta.setString(1, "100");
                psta.setString(2, "GuoSi");
                psta.execute();
                conn.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
//            e.printStackTrace();
        } finally {

            try {
                if (conn != null)
                    conn.close();
                if (psta != null)
                    psta.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        DBPoolInit();
        new HelloTransaction().Transaction();
    }
}
