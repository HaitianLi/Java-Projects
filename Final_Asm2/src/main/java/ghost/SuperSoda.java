package ghost;
import processing.core.PImage;
import processing.core.PApplet;

public class SuperSoda extends SuperFruit {
    private Waka waka;

    /**
     * Super Soda constructor inherit from super class fruit.
     * @param x coordinate x
     * @param y coordinate y
     * @param sprite Super soda image.
     * @param reachable whether its reachable.
     * @param waka The waka instance
     */
    public SuperSoda(int x, int y, PImage sprite, boolean reachable, Waka waka) {
        super(x, y, sprite, reachable, waka);
        this.waka = waka;
    }

    /**
     * Main logic for Super soda, if waka touched, the ghost get in Sodamode.
     */
    public void tick() {
        if (getAlive()) {
            if (getWakaX() == this.getX() && getWakaY() == this.getY()) {
                waka.setSodaMode(true);
                setAlive(false);
            }
        }
    }

    public void draw(PApplet app) {
        if (getAlive()) {
            app.image(this.getSprite(), this.getX(), this.getY());
        }
    }
}