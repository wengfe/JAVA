package main;

public class Item {
    protected String title;
//    private String artist;
//    private int numofTracks;
    protected int playingTime;
    private boolean gotIt = false;
    private String comment;

    public Item(String title, int playingTime, boolean gotIt, String comment) {
        this.title = title;
        this.playingTime = playingTime;
        this.gotIt =gotIt;
        this.comment = comment;
    }

    public Item(){

    }
    public void print() {
        System.out.println("Item");
    }
}
