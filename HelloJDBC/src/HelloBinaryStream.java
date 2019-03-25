import java.io.*;
import java.sql.*;

public class HelloBinaryStream {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=true";
    static final String USER = "root";
    static final String PASSWORD = "admin";
    static final String FILE_URL = "src/../resource/temp_test.txt";

    public static void main(String[] args) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Class.forName(JDBC_DRIVER);
        try {
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
//            创建 preparedStatement 对象
            preparedStatement = conn.prepareStatement("select * from blog where id = ?");
            preparedStatement.setInt(1, 1);
//            执行 sql
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                获取对象流
                InputStream in = resultSet.getBinaryStream("context");
//                将对象流写入文件
                File f = new File(FILE_URL);
                OutputStream out = new FileOutputStream(f);
                int temp = 0;
                while ((temp = in.read()) != -1) {//边读边写
                    out.write(temp);
                    System.out.println(temp);
                }
                in.close();
                out.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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

