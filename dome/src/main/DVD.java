package main;

public class DVD extends Item{
    private String director;

    public DVD(String title, String dictor, int playingTime, String comment) {
        super(title,playingTime,false,comment);
        this.director = dictor;
    }

    public void print() {
        System.out.println(title+": " +director+" "+ playingTime);
    }

    public static void main(String[] args) {
        DVD dvd = new DVD("123","abc",160,"...");
        dvd.print();
    }
}
