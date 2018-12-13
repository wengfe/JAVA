package main;

public class VideoGame extends Item {
    private int numberOfPlayer;

    public VideoGame(String title, int playingTime, boolean gotIt, String comment, int numberOfPlayer) {
        super(title, playingTime, gotIt, comment);
        this.numberOfPlayer = numberOfPlayer;
    }

    @Override
    public void print() {
        System.out.println("VideoGame");
        super.print();
    }
}
