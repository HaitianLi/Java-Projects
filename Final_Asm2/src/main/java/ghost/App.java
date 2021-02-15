package ghost;

import processing.core.PApplet;

public class App extends PApplet {
    public static final int WIDTH = 448;
    public static final int HEIGHT = 576;
    private Builder build;
    public App() {
        build = new Builder(WIDTH, HEIGHT);
    }
    
    /**
     * Set the game fram, load images.
     */
    public void setup() {
        // Load images
        frameRate(60);
        build.LoadMaterials(this);
    }

    /**
     * Returns the build object when app been initialized.
     * @return The build object
     */
    public Builder getBuilder() {
        return this.build;
    }
    
    /**
     * Set the size of the game window.
     */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Game listening the key press by user.
     */
    public void keyPressed() {
        build.getKeyValue(keyCode, key); 
    }

    /**
     * Draw all game objects into the game window with background color(black).
     * <br>Depend on the framRate, if framRate(60).<br>
     * The app call this method every second 60 times.
     */
    public void draw() { 
        background(0, 0, 0);
        build.runGameInterface(this);   
    }
    
    public static void main(String[] args) {
        PApplet.main("ghost.App");
    }
}
