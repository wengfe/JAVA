import java.util.Scanner;



public class Main {
    private int value = 0;
    private int limit = 0;
    private static int steep = 1;

    public Main(int limit) {
        this.limit = limit;
    }

    public void increase(){
        value++;
        if(value == limit){
            value = 0;
        }
    }

    public int getValue(){
        int i;
        return  value;
    }
    public static void main(String[] args)
    {
//        Scanner in = new Scanner(System.in);
//        char c = 'A';
//        char d = 'D';
//        System.out.println("abc\bd");
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println((int)(Math.random()*100));

        Main d1 = new Main(10);
        Main d2 = new Main(20);
        d1.increase();
        System.out.println(d1.getValue());
        System.out.println(d2.getValue());
        System.out.println(d1.steep);
        System.out.println(d2.steep);
        d1.steep =2;
        System.out.println(d1.steep);
        System.out.println(d2.steep);
        Main.steep = 3;
        System.out.println(d1.steep);
        System.out.println(d2.steep);
    }
}
