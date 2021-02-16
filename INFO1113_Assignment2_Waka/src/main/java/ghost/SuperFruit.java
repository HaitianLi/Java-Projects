package ghost;

import processing.core.PImage;


import processing.core.PApplet;

public class SuperFruit extends Fruit {

    /**
     * SuperFruit constructor inherit from fruit.
     * <br>Creae the superfruit with the necessary variables.<br>
     * @param x coordinate x
     * @param y coordinate y
     * @param sprite Super fruit img.
     * @param reachable wheather the object is reachable
     * @param waka the waka instance
     */
    public SuperFruit(int x, int y, PImage sprite, boolean reachable, Waka waka) {
        super(x, y, sprite, reachable, waka);
    }

    /**
     * Main logic for Super Fruit class.
     * <br>If waka touch the superfruit, the frightmode on, the superfruit dead.<br>
     */
    public void tick() {
        if (getAlive()) {
            if (getWakaX() == this.getX() && getWakaY() == this.getY()) {
                setFrightenedMode();
                setAlive(false);
            }
        }
    }

    /**
     * The draw method, draw when alive, superfruit have double size of fruit, and offset number 8.
     * @param app The game window instance.
     */
    public void draw(PApplet app) {
        if (getAlive()) {
            app.image(this.getSprite(), this.getX() - 8, this.getY() - 8, this.getSprite().width*2, this.getSprite().height*2);
        }
    }

}