import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class HelloMyBatis {
    public static void main(String[] args) {
//        1.声明配置文件的目录读值
//        String resource = "conf.xml";
        String resource = "confAnnotation.xml";//注解方式
//        2. 加载应⽤用配置⽂文件
        InputStream is = HelloMyBatis.class.getClassLoader()
                .getResourceAsStream(resource);
//        3. 创建SqlSessionFactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
//        3.1 添加映射关系到类对象    注解方式
        Configuration conf = sessionFactory.getConfiguration();
        conf.addMapper(GetUserInfoAnnotation.class);
//        4. 获取Session
        SqlSession session = sessionFactory.openSession();
        try {
//            5. 获取操作类
//            GetUserInfo getUserInfo = session.getMapper(GetUserInfo.class);
            GetUserInfoAnnotation getUserInfoAnnotation = session.getMapper(GetUserInfoAnnotation.class); //注解方式
//            6. 完成查询操作
//            User user = getUserInfo.getUser(2);
            User user = getUserInfoAnnotation.getUser(2);
            System.out.println(user.getId() + " " + user.getName() + " " + user.getAccount());
        } finally {
//            7.关闭Session
            session.close();
        }
    }
}
