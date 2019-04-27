import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MoreMybatis {

    public static void main(String[] args) {
        // 1. 声明配置⽂文件的⺫⽬目录渎职
	    String resource = "conf.xml";
        // 2. 加载应⽤用配置⽂文件
	    InputStream is = MoreMybatis.class.getClassLoader().getResourceAsStream(resource);
        // 3. 创建SqlSessonFactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 4. 获取Session
        SqlSession session = sessionFactory.openSession();
        try{
            // 5. 获取操作类
            StudentOp studentOp = session.getMapper(StudentOp.class);

            // 6. 完成查询操作
            Student student = studentOp.getStudent(25);
            System.out.println(student.getId() + " " + student.getUserName()+ " ");
            System.out.println(student.getCourses().get(0).getCourseName() + " ");
            System.out.println(student.getCourses().get(0).getTeacher().getTeacherName());
        }finally {
            session.close();
        }

    }
}
