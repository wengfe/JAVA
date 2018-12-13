package main;

public class Student extends Person{
    String className;       //班级名
    public Student() {
        name="haydn";    //为父类里的name重新赋值
        className = "菜鸟营一班";    //为className赋值
        System.out.println("I'm Student");
    }
    //这里重写了父类的sayHi方法
    protected void sayHi() {
        System.out.println("stuName=" + name);
        System.out.println("stuClassName=" + className);
    }

    //编译通过，访问权限和父类一致为protected
//    protected void sayHi() {
//        name="rose";
//        System.out.println(title);
//    }
//    //编译通过，访问权限大于父类
//    public void sayHi() {
//        name="rose";
//        System.out.println(title);
//    }
//    //编译不通过，权限小于父类
//    private void sayHi() {
//        name="rose";
//        System.out.println();
//    }
//    //编译不通过，数据类型不一致
//    protected int sayHi() {
//        return 1;
//    }

    public void stuTalk() {
        System.out.println("I like Java");
    }

    public static void main(String[] args) {
        Person pstu = new Student();
        System.out.println("name="+pstu.name);
        pstu.sayHi();
    }
}
