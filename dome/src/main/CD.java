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


    @Override
    public String toString() {
        return "CD{" +
                "artist='" + artist + '\'' +
                ", numofTracks=" + numofTracks +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        CD cc = (CD)obj;
        return artist.equals(cc.artist);
    }

    public void print() {
        System.out.println(title+": " +artist);
    }

    public static void main(String[] args) {
        CD cd = new CD("abc","art",24,6,"...");
        cd.print();
        System.out.println(cd);

        CD cd1 = new CD("abc","art",24,6,"...");
        System.out.println(cd.equals(cd1));
    }
}
