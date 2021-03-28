package ghost;
import processing.core.PImage;


public class Fruit extends MapObjects{
    private Waka waka;
    
    public Fruit(int x, int y, PImage sprite, boolean reachable, Waka waka) {
        super(x, y, sprite, reachable);
        this.waka = waka;
    }


    public void tick(){
        if (getAlive()) {
            if (this.getX() == this.waka.getX() && this.getY() == this.waka.getY()) {
                this.setSprit(null);
                setAlive(false);
            }
        }
    }
    public int getWakaX() {
        return this.waka.getX();
    }

    public int getWakaY() {
        return this.waka.getY();
    }

    public void setFrightenedMode() {
        waka.setFrightenedMode(true);
    }

}