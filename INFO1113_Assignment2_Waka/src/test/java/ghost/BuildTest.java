package ghost;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import processing.core.PApplet;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;



public class BuildTest {

    
    @Test
    /**
     * Check whether the read json work properly.
     */
    public void BuildReadJsonCheck() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        assertTrue(build.readJson());
    }

    @Test
    /**
     * Check whether the read map work properly.
     */
    public void BuildReadMapCheck() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        build.readJson();
        assertTrue(build.readMap());
    }

    @Test
    /**
     * Check whether the read map work properly, fruit created or not.
     */
    public void BuildCreatedFruits() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        build.readJson();
        build.readMap();        
        assertFalse(build.getFruitList().contains(null));
    }

    @Test
    /**
     * Check whether the waka been created.
     */
    public void BuildCreateWaka() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();       
        assertNotNull(waka);
    }

    @Test
    /**
     * Check whether the waka created at right place.
     */
    public void BuildOriginalWakaCoordinate() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();
        
        assertEquals(320, waka.getY());       
    }

    @Test
    /**
     * Check whether no null data list pass to waka.
     */
    public void BuildCheckNoNullDataListPassed() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        assertNotNull(build.getGameOriData());
    }

    @Test
    /**
     * Check whether the Ghost created at right place.
     * This test assume there is atleast one ghost.
     * The game could run with no ghost.
     */
    public void BuildCheckNullGhost() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<Ghost> ghostlist = build.getGhostList();   
        assertFalse(ghostlist.contains(null));
    }

    @Test
    /**
     * Check whether the Ghost created at right place.
     * even there is no ghost the game could run.
     */
    public void BuildCheckNullGhostList() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<Ghost> ghostlist = build.getGhostList();   
        assertNotNull(ghostlist);
    }

    @Test
    /**
     * Check the method getKeyValue works properly.
     * Waka direction should change to right.
     * This test case base on the map.txt
     * delay 1 sec insure the program load all data
     */
    public void BuildCheckkKeyValuePassRight() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();
        build.getKeyValue(37, 10);
        waka.tick();
        build.getKeyValue(39, 10);
        waka.tick();
        int t = waka.getDx();
        assertEquals(1, t);
    }

    @Test
    /**
     * Check the method getKeyValue works properly.
     * Waka direction should keep left.
     * This test case base on the map.txt
     * delay 1 sec insure the program load all data
     */
    public void BuildCheckkKeyValueLeft() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();
        build.getKeyValue(39, 10);
        waka.tick();
        build.getKeyValue(37, 10);
        waka.tick();
        int t = waka.getDx();
        assertEquals(-1, t);
    }

    @Test
    /**
     * Check the method getKeyValue works properly.
     * Waka direction should not change to up.
     * Waka go right first, and not change direction.
     * This test case base on the map.txt
     * delay 1 sec insure the program load all data
     */
    public void BuildCheckkKeyValuePassUp() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();
        build.getKeyValue(39, 10);
        waka.tick();
        build.getKeyValue(38, 10);
        waka.tick();
        int t = waka.getDy();
        assertEquals(0, t);
    }

    @Test
    /**
     * Check the method getKeyValue works properly.
     * Waka direction should not change to down.
     * Waka go right first, and not change direction.
     * This test case base on the map.txt
     * delay 1 sec insure the program load all data
     */
    public void BuildCheckkKeyValuePassDown() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        //app.setup();
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();
        build.getKeyValue(39, 10);
        waka.tick();
        build.getKeyValue(40, 10);
        build.getKeyValue(50, 10);
        app.delay(250);
        waka.tick();
        int t = waka.getDy();
        assertEquals(0, t);
    }

    @Test
    /**
     * Check the method resetGame work properly.
     * which clear every thing build created.
     */
    public void BuildreSetGame() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        build.reSetGame();

        assertEquals(0, build.getFruitList().size());
        assertEquals(0, build.getGhostList().size());
        assertEquals(0, build.getGameInterface().size());
        assertEquals(0, build.getMapObjects().size());
        assertEquals(0, build.getIntersections().size());
        assertNull(build.getChaser());
    }

}