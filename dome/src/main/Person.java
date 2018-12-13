package main;

public class Person {
    String name;        //例子属性，表示一个人的名字
    public  Person() {
        name = "Person";  //初始化一个值
        System.out.println("I'm Person!");
    }
    //定义sayHi()方法，默认输出HelloWorld
    protected void sayHi() {
        System.out.println("HelloWorld!");
    }
}
