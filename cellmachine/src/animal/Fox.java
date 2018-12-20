package animal;
import cell.Cell;
import field.Location;

import java.awt.*;

public class Fox extends Animal implements Cell{

    public Fox(){
        super(32,12,0.0285,0.4);
    }


    @Override
    public Location eat(Location[] rabbit_locs) {
        Location ret = null;
        if( 0 != rabbit_locs.length && Math.random() < 0.3)
        {
            int index = (int)(Math.random()* rabbit_locs.length);
            ret = rabbit_locs[index];
            this.longerLife(1);
        }
        return ret;
    }

    @Override
    public Animal baby() {
        return new Fox();
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        int alpha = 255 - (int)(255.0 * age /die_age);
        g.setColor(new Color(255,102,114,alpha));
        g.fillRect(x,y,size,size);
    }
}
