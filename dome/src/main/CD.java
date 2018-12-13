package main;

public class CD extends Item{
    private String artist;
    private int numofTracks;
//    private int playingTime;
//    private boolean gotIt = false;
//    private String comment;

    public CD(String title, String artist, int numofTracks, int playingTime, String comment) {
        super(title,playingTime,false,comment);
        this.artist = artist;
        this.numofTracks = numofTracks;
    }

    public void print() {
        System.out.println(title+": " +artist);
    }

    public static void main(String[] args) {
        CD cd = new CD("abc","art",24,6,"...");
        cd.print();
    }
}
