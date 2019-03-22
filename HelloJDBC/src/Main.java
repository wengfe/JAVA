import java.sql.*;

public class Main {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String DB_URL = "jdbc:mysql://localhost:3306/test?useSSL=true";
    static final String USER = "root";
    static final String PASSWORD = "admin";

    public static void main(String[] args) throws ClassNotFoundException{
        Connection conn = null;
        Statement statemente = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
//      1. 装载数据库驱动程序；
        Class.forName(JDBC_DRIVER);
//      2. 通过JDBC建立数据库连接；
        try {
            //在 url 中 开启 游标功能
            DB_URL = DB_URL + "&useCursorFetch=true";
            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
//      3.访问数据库，执行SQL语句；
//            statemente = conn.createStatement();
//            rs = statemente.executeQuery("select name from test.user ");
            preparedStatement = conn.prepareStatement("select  name from test.user");
            preparedStatement.setFetchSize(1);
            rs = preparedStatement.executeQuery();
//      4.获得执行结果
            while(rs.next()) {
                System.out.println("Hello " + rs.getString("name"));
            }



        } catch (SQLException e) {
//            异常处理
            e.printStackTrace();
        } finally {
            // 5. 断开数据库连接，清理环境
            try {
                if(conn != null) {
                    conn.close();
                }
                if(statemente != null) {
                    statemente.close();
                }
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                // pass
            }

        }
    }
}
