package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;


import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class GameScreenText {

    @Test
    /**
     * Test when all friut been eaten by waka.
     */
    public void TestGameScreenWhenPlayerWin() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        GameScreen screen = null;
        ArrayList<GameInterface> temp = build.getGameInterface();
        for (GameInterface g: temp) {
            if (g instanceof Fruit) {
                Fruit fru = (Fruit) g;
                fru.setAlive(false);
            }
            if (g instanceof GameScreen) {
                screen  = (GameScreen) g;
            }
        }
        screen.tick();
        assertTrue(screen.getChangeScreen());
        assertTrue(screen.getPlayerWin());
    }

    @Test
    /**
     * Test when waka lost all lives.
     */
    public void TestGameScreenWhenGameloss() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        GameScreen screen = null;
        ArrayList<GameInterface> temp = build.getGameInterface();
        for (GameInterface g: temp) {
            if (g instanceof GameScreen) {
                screen  = (GameScreen) g;
            }
        }

        while (waka.getLives() != 0) {
            waka.setLives();
        }
        screen.tick();
        assertTrue(screen.getChangeScreen());
        assertFalse(screen.getPlayerWin());
    }

    @Test
    /**
     * Test when all ghost removed by waka.
     */
    public void TestWhenAllGhostKilledByWaka() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        GameScreen screen = null;
        ArrayList<GameInterface> temp = build.getGameInterface();
        ArrayList<Ghost> temp_ghost = build.getGhostList();
        if (temp_ghost.size() != 0) {
            for (GameInterface g: temp) {
                if (g instanceof Ghost) {
                    Ghost ghos = (Ghost) g;
                    ghos.setAlive(false);
                }
                if (g instanceof GameScreen) {
                    screen  = (GameScreen) g;
                }
            }
            screen.tick();
            assertTrue(screen.getChangeScreen());
            assertTrue(screen.getPlayerWin());
        }
        
    }

    @Test
    /**
     * Test waka's touch a ghost and reset game to origion..
     *  */ 
    public void WakaReSetTest() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();
        
        waka.setResetGame(true);
        GameScreen screen = null;
        ArrayList<GameInterface> temp = build.getGameInterface();
        ArrayList<Ghost> temp_ghost = build.getGhostList();
    
        
        for (GameInterface g: temp) {
            if (g instanceof GameScreen) {
                screen  = (GameScreen) g;
            }
        }
        screen.tick();

        for (Ghost g: temp_ghost) {
            assertEquals(g.getOriginalX(), g.getX());
            assertEquals(g.getOriginalY(), g.getY());
        }
        
    }
}