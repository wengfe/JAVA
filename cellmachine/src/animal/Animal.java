package animal;
import field.Location;

public abstract class Animal {
    protected int age;
    protected int die_age;
    protected int mature_age;
    protected double breed_rate;
    protected double move_rate;
    protected boolean alive;
    protected boolean mature;
    protected boolean moved;

    public Animal(int die_age, int mature_age, double breed_rate, double move_rate) {
        this.age = 0;
        this.die_age = die_age;
        this.mature_age = mature_age;
        this.breed_rate = breed_rate;
        this.move_rate = move_rate;
        this.alive = true;
        this.mature = false;
    }

    public void born(){
        this.alive = true;
    }

    public void die(){
        this.alive = false;
    }

    public boolean isAlive(){
        return alive;
    }

    public void growUp(){
        mature = true;
    }

    public boolean isMature() {
        return mature;
    }

    public void setMoved() {
        moved = true;
    }

    public void setNotMoved() {
        moved = false;
    }

    public boolean isMoved() {
        return moved;
    }

    public void grow(){
        this.age++;
        if (age == mature_age)
        {
            this.growUp();
        }
        if ( age ==die_age)
        {
            this.die();
        }
    }

    public void longerLife(int longer){
        this.die_age += longer;
    }

    public abstract  Location eat(Location[] rabbit_locs);

    public Location move(Location[] locs){
        Location ret = null;
        if(0 != locs.length && Math.random() < this.move_rate){
            ret = locs[(int)(Math.random()*locs.length)];
        }
        return ret;
    }

    public abstract Animal baby();

    public Location breed(Location[] locs){
        Location ret = null;
        if(0 != locs.length && Math.random() < this.breed_rate){
            ret = locs[(int)(Math.random()*locs.length)];
        }
        return  ret;
    }
}
