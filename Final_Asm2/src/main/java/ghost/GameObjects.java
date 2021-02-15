package ghost;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class GameObjects implements GameInterface{
    private int x;
    private int y;
    private PImage sprite;
    private boolean alive = true;

    /**
     * 
     * @param x coordinate x.
     * @param y coordinate y.
     * @param sprite Image.
     */
    public GameObjects(int x, int y, PImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    /**
     * Return the coordinate x.
     * @return Return the coorinate x.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Return the coordinate y.
     * @return Return the coordinate y.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Check whether the object alive.
     * @return Return true if alive.
     */
    public boolean getAlive() {
        return this.alive;
    }

    /**
     * Retrun the current sprite.
     * @return Return the current using sprite.
     */
    public PImage getSprite() {
        return this.sprite;
    }

    /**
     * Set the coodinate x.
     * <br>Because speed always less than 3, and the map top 3 rows and bottom 2 rows never use.<br>
     * So when i greater 2 means it is a set up coordinate method.<br>
     * if i less and equal than 2, means it is a speed set up method.<br>
     * @param i speed or x coordinate.
     */
    public void setX(int i) {
        if (i > 2) {
            this.x = i;
        } else {
            this.x += i;
        }
    }

    /**
     * Set the coodinate y.
     * <br>Because speed always less than 3, and the map top 3 rows and bottom 2 rows never use.<br>
     * So when i greater 2 means it is a set up coordinate method.<br>
     * if i less and equalt to 2, means it is a speed set up method.
     * @param i speed or y coordinate.
     */
    public void setY(int i) {
        if (i > 2) {
            this.y = i;
        } else {
            this.y += i;
        }
    }

    /**
     * Set the sprite with the parameter image.
     * @param img The image want to set.
     */
    public void setSprit(PImage img) {
        this.sprite = img;
    }
    /**
     * Set the alive with parameter b boolean value.
     * @param b Boolean value, true means object alive.
     */
    public void setAlive(boolean b) {
        this.alive = b;
    }
    
    /**
     * Interface inplements, draw method draw objects on screen.
     */
    public void draw(PApplet app) {
        if (getAlive()) {
            app.image(this.sprite, this.x, this.y);
        }
    }

    /**
     * Interface inplements, the method is empty ready to be override by son classes.
     */
    public  void tick() {
        //ready for son classes to override.
    }

}