package ghost;

import processing.core.PApplet;
/**
 * All objects which will show on the screen shoud use from this interface.
 */
public interface GameInterface {

    /**
     * The tick method, contain the main logic of the object.
     */
    void tick();

    /**
     * The draw method, how the object should be draw on the screen.
     * @param app the game window object.
     */
    void draw(PApplet app);
}