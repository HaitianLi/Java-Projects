package ghost;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class GameObjects {
    private int x;
    private int y;
    private PImage sprite;
    private boolean alive = true;

    public GameObjects(int x, int y, PImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getAlive() {
        return this.alive;
    }

    public PImage getSprite() {
        return this.sprite;
    }

    
    public void setX(int i) {
        if (i > 2) {
            this.x = i;
        } else {
            this.x += i;
        }
    }

    public void setY(int i) {
        if (i > 2) {
            this.y = i;
        } else {
            this.y += i;
        }
    }

    public void setSprit(PImage img) {
        this.sprite = img;
    }

    public void setAlive(boolean b) {
        this.alive = b;
    }
    
    public void draw(PApplet app) {
        app.image(this.sprite, this.x, this.y);
    }

    public  void tick() {
        //ready for son classes to override.
    }

}