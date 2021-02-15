package ghost;

import processing.core.PImage;


import processing.core.PApplet;

public class SuperFruit extends Fruit {

    public SuperFruit(int x, int y, PImage sprite, boolean reachable, Waka waka) {
        super(x, y, sprite, reachable, waka);
    }

    public void tick() {
        if (getAlive()) {
            if (getWakaX() == this.getX() && getWakaY() == this.getY()) {
                setFrightenedMode();
                setSprit(null);
                setAlive(false);
            }
        }
    }


    public void draw(PApplet app) {
        app.image(this.getSprite(), this.getX() - 8, this.getY() - 8, this.getSprite().width*2, this.getSprite().height*2);
    }
}