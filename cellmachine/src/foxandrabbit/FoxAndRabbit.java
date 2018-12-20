package foxandrabbit;

import animal.Animal;
import animal.Fox;
import cell.Cell;
import field.Field;
import field.Location;
import field.View;

import javax.swing.*;
import java.awt.*;

public class FoxAndRabbit {
    private static Field field = new Field(60, 60);
    private static JFrame frame = new JFrame();
    private static View view = new View(field);

    public static void main(String[] args) {
        field.init();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Fox and Rabbit");
        frame.setResizable(false);
        frame.add(view);
        frame.pack();
        frame.setVisible(true);

        while (true){
            initMoved();
            change();
            frame.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static void initMoved(){
        int h =field.getHeight();
        int w = field.getWidth();
        for(int r = 0; r < field.getHeight(); r++)
        {
            for (int c = 0; c < field.getWidth(); c++)
            {
                Cell cell = field.get(r,c);
                if (null != cell)
                {
                    ((Animal)cell).setNotMoved();
                }
            }
        }
    }

    private static void change(){
        for(int r = 0; r < field.getHeight()-1; r++)
        {
            for (int c = 0; c < field.getWidth()-1; c++)
            {
                Cell cell = field.get(r,c);
                if (null != cell)
                {
                    Animal animal = (Animal)cell;
                    animal.grow();
                    if(animal.isAlive())
                    {
                        if( !animal.isMoved())
                        {
                            animalMove(animal,r,c);
                        }
                        animalEat(animal,r,c);
                        animalBreed(animal,r,c);
                    }else{
                        field.remove(r,c);
                    }
                }
            }
        }
    }

    public static void animalMove(Animal animal, int row, int col) {
        animal.setMoved();
        if (animal instanceof Fox) {
            if (Math.random() < 0.75/*移动率*/) {
                field.animalMove(row, col, field.BFSRabbit(row, col));
            }
        } else {
            if (Math.random() < 0.3/*移动率*/) {
                field.animalMove(row, col, field.BFSFox(row, col));
            }
        }
    }

    private static void animalEat(Animal animal, int row, int col) {
        Location rabbit_loc = animal.eat(field.getRabbitNeighbourLoc(row, col));
        if ( null != rabbit_loc ) {
            field.remove(rabbit_loc.row, rabbit_loc.col);
        }
    }

    private static void animalBreed(Animal animal, int row, int col) {
        Location breed_loc = animal.breed(field.getFreeNeighbourLoc(row, col));
        if ( null != breed_loc ) {
            field.place(breed_loc.row, breed_loc.col, (Cell)animal.baby());
        }
    }

}
