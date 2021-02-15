package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;


public class WakaTest {

    @Test
    /**
     * Waka change direction to up(user pressed up tested), if up movable.
     *  */ 
    public void WakaMoveTest() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        ArrayList<MapObjects> temp = waka.getMapObjects();
        for (MapObjects m : temp) {
            if (m.getReachable()) {
                if (waka.targetBlock(m.getX(), m.getY() - 16)) {
                    waka.sendDirect(0, -1);
                    waka.tick();
                }
            }
        }
        assertEquals(-1, waka.getDy());
        
    }

    

    @Test
    /**
     * Waka change direction to down(user pressed down tested), if down movable.
     *  */ 
    public void WakaMoveDownTest() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        ArrayList<MapObjects> temp = waka.getMapObjects();
        for (MapObjects m : temp) {
            if (m.getReachable()) {
                if (waka.targetBlock(m.getX(), m.getY() + 16)) {
                    waka.sendDirect(0, 1);
                    waka.tick();
                }
            }
        }
        assertEquals(1, waka.getDy());
        
    }

    @Test
    /**
     * Waka change direction to left(user pressed left tested), if left movable.
     *  */ 
    public void WakaMoveLeftTest() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        ArrayList<MapObjects> temp = waka.getMapObjects();
        for (MapObjects m : temp) {
            if (m.getReachable()) {
                if (waka.targetBlock(m.getX() - 16, m.getY())) {
                    waka.sendDirect(-1, 0);
                    waka.tick();
                }
            }
        }
        assertEquals(-1, waka.getDx());
        
    }

    @Test
    /**
     * Waka change direction to right(user pressed right tested), if right movable.
     *  */ 
    public void WakaMoveRightTest() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        ArrayList<MapObjects> temp = waka.getMapObjects();
        for (MapObjects m : temp) {
            if (m.getReachable()) {
                if (waka.targetBlock(m.getX() + 16, m.getY())) {
                    waka.sendDirect(1, 0);
                    waka.tick();
                }
            }
        }
        assertEquals(1, waka.getDx());
        
    }

    @Test
    /**
     * Test waka's live statuc  when live == 0.
     *  */ 
    public void WakalivesTest() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();
        
        while (waka.getLives() != 0) {
            waka.setLives();
        }
        waka.checkAlive();
        assertFalse(waka.getAlive());
        
    }

    @Test
    /**
     * Test waka's Frighten mode on/off time triger..
     *  */ 
    public void WakaFrightenTimeTest() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();
        
        int i = waka.getFrightenedLength();
        waka.setFrightenedMode(true);
        assertTrue(waka.getFrightenedMode());
        waka.setFrightTime(i * 60);
        waka.tick();
        assertFalse(waka.getFrightenedMode());

        
    }

    @Test
    /**
     * Test waka's Soda mode on/off time triger..
     *  */ 
    public void WakaSodaTimeTest() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        //app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();
        
        int i = waka.getSodaTime();
        waka.setSodaMode(true);;
        assertTrue(waka.getSodaMode());
        waka.setSodaTime(i * 60);
        waka.tick();
        assertFalse(waka.getSodaMode());

        
    }

    @Test
    /**
     * Test waka's moveable if x coordination change(moved left or right)
     *  */ 
    public void WakaMoveableTest() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();
        
        waka.setX(159);
        waka.tick();
        assertFalse(waka.getUpMoveable());
        assertFalse(waka.getDownMoveable());

        
    }

    @Test
    /**
     * Test waka's moveable if y coordination change(moved up or down)
     *  */ 
    public void WakaMoveableTest2() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();
        
        waka.setY(159);
        waka.tick();
        assertFalse(waka.getLeftMoveable());
        assertFalse(waka.getRightMoveable());

        
    }

    @Test
    /**
     * Waka check if the input direction is moveable.
     *  */ 
    public void WakaCheckMoveableTest() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        int t = 0;
        waka.sendDirect(1, 0);
        waka.tick();
        ArrayList<MapObjects> temp = waka.getMapObjects();
        for (MapObjects m : temp) {
            if (m.getReachable()) {
                if (!waka.targetBlock(m.getX() - 16, m.getY())) {
                    waka.setX(m.getX());
                    waka.setY(m.getY());
                    build.getKeyValue(37, 20);
                    waka.tick();
                    t = waka.getDx();
                    break;
                }
            }
        }
        assertNotEquals(-1, t);
        
    }

}