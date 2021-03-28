package ghost;
import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;


public class GameScreen implements GameInterface{
    private Builder build;
    private ArrayList<Fruit> FruitList;
    private ArrayList<Ghost> GhostList;
    private Waka waka;
    private PFont Press2;
    private boolean ChangeScreen;
    private boolean PlayerWin;
    private int SecondCount;
    private ArrayList<GameInterface> GameInterface;
    private String countDown;
    /**
     * GameScreen constructor, control what to output to screen.
     * <br>Specific for timeing when the game need to rese, show waka's lives.
     * @param build the build object, game manager.
     * @param waka The waka instance.
     * @param Press2 The font for screen text output.
     * @param GameInterface The list contains all objects which implements the Gameinterface.
     */
    public GameScreen(Builder build, Waka waka, PFont Press2, ArrayList<GameInterface> GameInterface) {
        this.build = build;
        FruitList = build.getFruitList();
        GhostList = build.getGhostList();
        this.waka = waka;
        this.Press2 = Press2;
        this.GameInterface = GameInterface;
        SecondCount = 0;
    }

    /**
     * GameScreen main logic.
     * <br> check whether the fruit all dead, or ghost all dead, or the waka have no life.<br>
     * Output the loss or win test to screen and reset the game.
     */
    public void tick() {
        if (waka.getLives() != 0 && !ChangeScreen) {
            //System.out.println(1111);
            //reset the position of all ghost when waka hit a ghost
            int ghost_Notalive_count = 0;
            for (Ghost temp_ghost : GhostList) {
                if (!temp_ghost.getAlive()) {
                    ghost_Notalive_count++;
                }
                
            }
            
            if (GhostList.size() != 0 && ghost_Notalive_count == GhostList.size()) {
                ChangeScreen = true;
                PlayerWin = true;
            }
            
            boolean fruitCheck = false;
            
            for (Fruit f : FruitList) {
                if (f.getAlive()) {
                    fruitCheck = true;
                }
            }
            
            if (!fruitCheck) {

                ChangeScreen = true;
                PlayerWin = true;
            }
            
            if (waka.getResetGame()) {
                for (Ghost temp_ghost : GhostList) {
                    //System.out.println("check list");
                    temp_ghost.setX(temp_ghost.getOriginalX());
                    temp_ghost.setY(temp_ghost.getOriginalY());
                    temp_ghost.setDirect(-1, 0);
                    temp_ghost.setSprit(temp_ghost.getGhostImage());
                    temp_ghost.setAlive(true);
                }
                waka.setResetGame(false);
            }
        } else {
            SecondCount++;
            ChangeScreen = true;

            for (GameInterface g : GameInterface) {
                if (g instanceof GameObjects) {
                    GameObjects temp = (GameObjects) g;
                    temp.setAlive(false);
                }
            }
            
        }
    }

    /**
     * draw method, draw waka's live image, win or loss text on screen.
     * @param app The game window objects.
     */
    public void draw(PApplet app){
       
        if (waka.getAlive()){
            for (int i = 0; i < waka.getLives(); i++) {
                app.image(waka.getImgMap().get("wakaRight"), 8 + 26 * i, 576 -32);
            }
        }

        if (ChangeScreen) {

            //output the 10 sec counting.
            countDown = "";
            countDown += 10 - SecondCount/60;
            if (SecondCount / 60 == 10) {
                build.reSetGame();
                build.setUp();
            }
            
            app.textFont(Press2);

            if (!PlayerWin) {
                app.textAlign(App.CENTER);
                app.text("GAME OVER", app.width/2, 160);
                app.text(countDown, app.width/2, 256);
            } else {
                app.textAlign(App.CENTER);
                app.text("YOU WIN", app.width/2, 160);
                app.text(countDown, app.width/2, 256);
            }
        }
    }

    /**
     * Return the boolean value change screen.
     * @return  Return the change screen value, true the screnn should change.
     */
    public boolean getChangeScreen() {
        return this.ChangeScreen;
    }
    /**
     * Return whether the player win.
     * @return Return true if the player win.
     */
    public boolean getPlayerWin() {
        return this.PlayerWin;
    }
}