package ghost;
import processing.core.PImage;


public class Wall extends MapObjects{

    public Wall(int x, int y, PImage sprite, boolean reachable) {
        super(x, y, sprite, reachable);
        setAlive(false);
    }


    public void tick(){
        ;
    }
}