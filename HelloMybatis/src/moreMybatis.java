import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class moreMybatis {
    public static void main(String[] args) {
//        1. 声明配置⽂文件
        String resource = "conf.xml";
//        2. 加载应⽤用配置⽂文件
        InputStream is = moreMybatis.class.getClassLoader().getResourceAsStream(resource);
//        3. 创建SqlSessonFactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

//        关闭sql事务模式
        SqlSession session = sessionFactory.openSession(true);
        try{
//            5. 获取操作类
            GetUserInfo getUserInfo = session.getMapper(GetUserInfo.class);

//            插⼊入⽤用户
            User user = new User(7,"xiaoBai","123456",0,10000);
            getUserInfo.addUser(user);
            System.out.println(user.getId());

//            查询⽤用户
            user = getUserInfo.getUser(user.getId());
            System.out.println(user);

//            更新用户账户信息
            user.setAccount(800);
            getUserInfo.updateUserAccount(user);
//            删除⽤用户
            getUserInfo.deleteUser(user.getId());
        }finally {
            session.close();
        }

    }
}
