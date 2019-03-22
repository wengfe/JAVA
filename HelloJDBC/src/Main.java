import java.sql.*;

public class Main {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String DB_URL = "jdbc:mysql://localhost:3306/test?useSSL=true";
    static final String USER = "root";
    static final String PASSWORD = "admin";

    public static void main(String[] args) throws ClassNotFoundException{
        Connection conn = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
//      1. 装载数据库驱动程序；
        Class.forName(JDBC_DRIVER);
//      2. 通过JDBC建立数据库连接；
        try {
            //在 url 中 开启 游标属性
            DB_URL = DB_URL + "&useCursorFetch=true";
            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
//      3.访问数据库，执行SQL语句；
//            statement = conn.createStatement();
//            rs = statement.executeQuery("select name from test.user ");
//          对 preparedStatement 的 sql 语句进行预编译, ？ 表示参数
            preparedStatement = conn.prepareStatement("select  * from test.ipdata where id=? and startip = ?");
//            通过 preparedStatement 使用游标 进行分批查询语句
            preparedStatement.setFetchSize(1);
//            通过setInt 或者 setString 将，预编译的 sql 语句中的 条件参数传递进去
            preparedStatement.setInt(1,13);
            preparedStatement.setString(2,"16844800");
            rs = preparedStatement.executeQuery();
//      4.获得执行结果
            while(rs.next()) {
                System.out.println("Hello " + rs.getString("country"));
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
}
