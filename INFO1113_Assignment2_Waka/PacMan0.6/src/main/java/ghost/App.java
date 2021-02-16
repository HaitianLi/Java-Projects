package ghost;

import processing.core.PApplet;

public class App extends PApplet {
    public static final int WIDTH = 448;
    public static final int HEIGHT = 576;
    private Builder build;
    //gradle jacocoTestReport
    public App() {
        //Set up your objects
        build = new Builder(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(60);
        // Load images
        build.setFruitImg(this.loadImage("src/main/resources/fruit.png"));
        //build.setGhostImg(this.loadImage("src/main/resources/ghost.png"));
        build.setMapHorizontalImg(this.loadImage("src/main/resources/horizontal.png"));
        build.setMapVerticalImg(this.loadImage("src/main/resources/vertical.png"));
        build.setMapUpleftImg(this.loadImage("src/main/resources/upLeft.png"));
        build.setMapUprightImg(this.loadImage("src/main/resources/upRight.png"));
        build.setMapDownleftImg(this.loadImage("src/main/resources/downLeft.png"));
        build.setMapDownrightImg(this.loadImage("src/main/resources/downRight.png"));
        build.setWakaCloseImg(this.loadImage("src/main/resources/playerClosed.png"));
        build.setWakaLeftImg(this.loadImage("src/main/resources/playerLeft.png"));
        build.setWakaRightImg(this.loadImage("src/main/resources/playerRight.png"));
        build.setWakaUpImg(this.loadImage("src/main/resources/playerUp.png"));
        build.setWakaDownImg(this.loadImage("src/main/resources/playerDown.png"));
        build.setGhostAmbusherImg(this.loadImage("src/main/resources/ambusher.png"));
        build.setGhostChaserImg(this.loadImage("src/main/resources/chaser.png"));
        build.setGhostIgnorantImg(this.loadImage("src/main/resources/ignorant.png"));
        build.setGhostWhimImg(this.loadImage("src/main/resources/whim.png"));
        build.setGhostFrightImg(this.loadImage("src/main/resources/frightened.png"));
        build.setFont(this.createFont("src/main/resources/PressStart2P-Regular.ttf", 28));
        build.setUp();
    }
    public Builder getBuilder() {
        return this.build;
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void keyPressed() {
        build.getKeyValue(keyCode, key); 
        
    }

    public void draw() { 
        background(0, 0, 0);
        build.drawGameObjects(this);   
    }
    public static void main(String[] args) {
        PApplet.main("ghost.App");
    }

}
