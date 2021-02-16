package ghost;
import processing.core.PImage;


public class Fruit extends MapObjects{

    private Waka waka;
    /**
     * Fruit constructor inherit from Mapobjects
     * @param x coordinate x
     * @param y coordinate y
     * @param sprite Image use.
     * @param reachable whether its reachable
     * @param waka The waka instance
     */
    public Fruit(int x, int y, PImage sprite, boolean reachable, Waka waka) {
        super(x, y, sprite, reachable);
        this.waka = waka;
    }


    /**
     * main logic of fruit ckass.
     * <br> If fruit touch waka, set it to not alive.<br>
     * The logic only work when its alive.<br>
     */
    public void tick(){
        if (getAlive()) {
            if (this.getX() == this.waka.getX() && this.getY() == this.waka.getY()) {
                this.setSprit(null);
                setAlive(false);
            }
        }
    }

    /**
     * Return waka current cooridnate x.
     * @return Return waka current coordinate x.
     */
    public int getWakaX() {
        return this.waka.getX();
    }

    /**
     * Return waka current cooridnate y.
     * @return Return waka current coordinate y.
     */
    public int getWakaY() {
        return this.waka.getY();
    }

    /**
     * This is the method to set the waka to frightmode.
     * <br>This method creat for the son class superfruit to set the waka to frighten mode.<br>
     */
    public void setFrightenedMode() {
        waka.setFrightenedMode(true);
    }
}