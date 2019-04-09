import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class sqlMapping {
    static String resource = "conf.xml";
    static InputStream is = sqlMapping.class.getClassLoader().getResourceAsStream(resource);
    static SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);


    public static User selectOne(int id, String name) {
        SqlSession session = sqlSessionFactory.openSession();
        User user = null;
        try {
            user = session.selectOne("getUserOne", new User(id, name, null, 0, 0));
        } catch(Exception exception){
            System.out.println(exception);
        }
        finally{
            session.close();
        }
        return user;
    }

    public static List<User> selectList(int id){
        SqlSession session = sqlSessionFactory.openSession();
        List<User>  users = null;
        try{
            users = session.selectList("getUserList", id);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            session.close();
        }
        return users;
    }

    public static void insertUser(){
        SqlSession session = sqlSessionFactory.openSession();
        try{
            session.insert("insertUser",new User(0,"GuoWu","123456",0,10000));
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }

    public static void updateUser(){
        SqlSession session = sqlSessionFactory.openSession(true);
        try{
            session.update("updateUserNameById",new User(12,"GuoGau","23456",1,800));
//            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }

    public static void deleteUserById(int id){
        SqlSession session = sqlSessionFactory.openSession(true);
        try{
            session.delete("deleteUserById",id);
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }

    public static void main(String[] args) {
//        System.out.println(selectOne(10,"GuoYi"));
//        System.out.println(selectList(2));
//        insertUser();
//        updateUser();
        deleteUserById(14);
    }
}
