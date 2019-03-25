import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class HelloBatch {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String DB_URL = "jdbc:mysql://localhost:3306/test?useSSL=true";
    static final String USER = "root";
    static final String PASSWORD = "admin";

    static void insertUsers(Set<String> users) throws ClassNotFoundException{
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
//      1. 装载数据库驱动程序；
        Class.forName(JDBC_DRIVER);
//      2. 通过JDBC建立数据库连接；
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
//      3.访问数据库，执行SQL语句；
            statement = conn.createStatement();
//            添加 sql 语句到 Batch
            for (String user : users) {
                statement.addBatch("insert into user(name) values ('" + user + "')");
                System.out.println("insert into user(name) values (" + user + ")");
            }
//            批执行 sql 语句
            statement.executeBatch();
//            清空已执行语句
            statement.clearBatch();
        } catch (SQLException e) {
//            异常处理
            e.printStackTrace();
        } finally {
            // 5. 断开数据库连接，清理环境
            try {
                if(conn != null) {
                    conn.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                // pass
            }

        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Set<String> users = new HashSet<>();
        users.add("GuoYi");
        users.add("GuoEi");
        users.add("GuoSi");

        insertUsers(users);
    }

}
