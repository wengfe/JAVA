package animal;

import cell.Cell;
import field.Location;

import java.awt.*;

public class Rabbit extends Animal implements Cell {

    public Rabbit(){
        super(28,6,0.075,0.4);
    }

    @Override
    public Location eat(Location[] rabbit_locs) {
//        兔子不吃
        return null;
    }

    @Override
    public Animal baby() {
        return new Rabbit();
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        int alpha = 255 - (int)(255.0*age/die_age);
        g.setColor(new Color(33,33,33,alpha));
        g.fillRect(x,y,size,size);
    }
}
