package parent;

public class Subroutine extends SubParent {
    Subroutine(){
        System.out.println("调用子类的 Subroutine 方法");
    }

    public static void main(String[] args) {
        Subroutine s = new Subroutine();
    }
}
