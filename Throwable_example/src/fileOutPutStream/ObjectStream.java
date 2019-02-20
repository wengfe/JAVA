package fileOutPutStream;

import java.io.*;

class Student implements Serializable {
    private String name;
    private int age;
    private int grade;

    public Student(String name, int age, int grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                '}';
    }
}
public class ObjectStream {
    public static void main(String[] args) {
        try {
            Student s1 = new Student("John",18,8);
            System.out.println(s1);
//            obj stream 写到文件
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("obj.dat"));
            out.writeObject(s1);
            out.close();
//          obj stream 读
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream("src/../obj.dat"));
            Student s2 = (Student) in.readObject();
            System.out.println(s2);
            in.close();
            System.out.println(s1==s2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
