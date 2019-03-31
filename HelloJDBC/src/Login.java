import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class Login {
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

    public static User Login(String userName, String password) {
        Connection conn = null;
        PreparedStatement pstat = null;
//        Statement stat = null;
        ResultSet rs = null;
        User user = null;

        try {
            conn = ds.getConnection();
            pstat = conn.prepareStatement("select * from user where name = ? and password = ?");
            pstat.setString(1,userName);
            pstat.setString(2,password);
            rs = pstat.executeQuery();
//            stat = conn.createStatement();
//            rs = stat.executeQuery("select * from user where name = '" + userName + "' and password = '" + password + "'");
            while (rs.next()) {
                user = new User();
                user.setUserName(rs.getString("name"));
                user.setSex(rs.getInt("sex"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (pstat != null)
                    pstat.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public static void main(String[] args) {
        DBPoolInit();
        System.out.println("登录场景 " + (Login("GuoYi","123456") != null));
        System.out.println("密码错误场景 " + (Login("GuoYi","12345") != null));
        System.out.println("SQL 注入场景 " + (Login("GuoYi';-- ","12345") != null));
    }
}
