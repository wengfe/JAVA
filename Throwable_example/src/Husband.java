public class Husband extends Person {
    /*
     * 对属性的封装
     * 一个人的姓名、性别、年龄、妻子都是这个人的私有属性
     */
    private String name ;
    private String sex ;
    private int age ;
    private Wife wife;

    /*
     * setter()、getter()是该对象对外开发的接口
     */

    Husband(){
        super("chenssy");
        System.out.println("Husband Constructor...");
    }

    @Override
    public String toString() {
        return getClass().getName()+"中重写 toString 方法";
    }

    public String getName() {
        return name+"1";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }


    public static void main(String[] args) {
        Person husband = new Husband();
        System.out.println(husband.getClass().getName());
//        System.out.println(husband.getName());
        ((Husband) husband).setAge(18);
        System.out.println(((Husband) husband).age);
        System.out.println(new Husband());

    }
}