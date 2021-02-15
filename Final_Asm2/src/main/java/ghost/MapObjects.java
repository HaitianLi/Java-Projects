package ghost;

import processing.core.PImage;

public class MapObjects extends GameObjects{
    private boolean reachable;

    /**
     * MapObject constuctor inherit from GameObject.
     * @param x coordinate x
     * @param y coordinate y
     * @param sprite Image
     * @param reachable boolean true, if the object is reachable.
     */
    public MapObjects(int x, int y, PImage sprite, boolean reachable) {
        super(x, y, sprite);
        this.reachable = reachable;
    }

    /**
     * Check whether the mapObject is reachable.
     * @return Return true if reachable.
     */
    public boolean getReachable() {
        return this.reachable;
    }

    
}