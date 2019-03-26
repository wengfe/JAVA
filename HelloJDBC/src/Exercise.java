import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class Exercise {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=true";
    static final String USER = "root";
    static final String PASSWORD = "admin";
    static BasicDataSource bs = null;

    public static void dbpoolInit(){
        bs = new BasicDataSource();
        bs.setDriverClassName(JDBC_DRIVER);
        bs.setUrl(JDBC_URL);
        bs.setUsername(USER);
        bs.setPassword(PASSWORD);

    }

    public static void main(String[] args) throws ClassNotFoundException {
        dbpoolInit();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Class.forName(JDBC_DRIVER);
        try {
//            conn = DriverManager.getConnection(JDBC_URL,USER, PASSWORD);
            conn = bs.getConnection();
            preparedStatement = conn.prepareStatement("select * from book where id = ?");
            preparedStatement.setInt(1,2);
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(2));
//                resultSet.previous();
                System.out.println(resultSet.getInt(3));
                System.out.println(resultSet.getFloat(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
                try {
                    if (conn != null)
                        conn.close();
                    if (preparedStatement != null)
                        preparedStatement.close();
                    if (resultSet != null)
                        resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
}
