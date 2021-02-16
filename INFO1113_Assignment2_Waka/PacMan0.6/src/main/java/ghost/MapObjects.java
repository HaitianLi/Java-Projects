package ghost;

import processing.core.PImage;

public class MapObjects extends GameObjects{
    private boolean reachable;


    public MapObjects(int x, int y, PImage sprite, boolean reachable) {
        super(x, y, sprite);
        this.reachable = reachable;
    }

    
    
    public boolean getReachable() {
        return this.reachable;
    }

    
}