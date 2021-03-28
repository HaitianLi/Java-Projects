package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;
import static org.junit.jupiter.api.Assertions.*;


public class FruitText {
    
    @Test
    /**
     * Test the logic(tick) of Fruit, if waka touch a fruit, the fruit gone.  
     */ 
    public void FruitLogic() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        for (Fruit f: build.getFruitList()) {
            if (f.getX() == waka.getX() && f.getY() == waka.getY()) {
                assertFalse(f.getAlive());
            }
        }
    }

    @Test
    /**
     * Test the logic(tick) of super fruit.
     * If the waka touch a superfruit, fruit gone, waka in FrightenMode. 
     */ 
    public void SuperFruitTouch() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        for (Fruit f: build.getFruitList()) {
            if (f instanceof SuperFruit) {
                SuperFruit temp = (SuperFruit) f;
                temp.setX(waka.getX());
                temp.setY(waka.getY());
                temp.tick();
                assertFalse(temp.getAlive());
                break;
            }
        }

        assertTrue(waka.getFrightenedMode());
    }

    @Test
    /**
     * Test the logic(tick) of super fruit.
     * Check whether the method setFrightmode words.
     * If work, waka's status of FrightenMode should change to true.  
     */ 
    public void SuperFruitControlMode() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        for (Fruit f: build.getFruitList()) {
            if (f instanceof SuperFruit) {
                SuperFruit temp = (SuperFruit) f;
                temp.setFrightenedMode();
            }
        }

        assertTrue(waka.getFrightenedMode());
    }

    @Test
    /**
     * Test the logic(tick) of super fruit.
     * Check if the dead superfruit touch waka, the waka status not change.
     */ 
    public void DeadSuperFruitTouch() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        for (Fruit f: build.getFruitList()) {
            if (f instanceof SuperFruit) {
                SuperFruit temp = (SuperFruit) f;
                temp.setAlive(false);
                temp.setX(waka.getX());
                temp.setY(waka.getY());
                temp.tick();
                break;
            }
        }

        assertFalse(waka.getFrightenedMode());
    }

    @Test
    /**
     * Test the logic(tick) of super fruit.
     * Check if the superfruit not touch waka, nothing change.
     */ 
    public void SuperFruitNotTouch() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        for (Fruit f: build.getFruitList()) {
            if (f instanceof SuperFruit) {
                SuperFruit temp = (SuperFruit) f;
                temp.setX(waka.getX()+10);
                temp.setY(waka.getY()+10);
                temp.tick();
                assertTrue(temp.getAlive());
                break;
            }
        }

        assertFalse(waka.getFrightenedMode());
    }


    @Test
    /**
     * Test the logic(tick) of super soda.
     * Check if the super soda not touch waka, nothing change.
     */ 
    public void SuperSodaNotTouch() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        for (Fruit f: build.getFruitList()) {
            if (f instanceof SuperSoda) {
                SuperSoda temp = (SuperSoda) f;
                temp.setX(waka.getX()+10);
                temp.setY(waka.getY()+10);
                temp.tick();
                assertTrue(temp.getAlive());
                break;
            }
        }

        assertFalse(waka.getSodaMode());
    }

    @Test
    /**
     * Test the logic(tick) of super soda.
     * Check if the super soda touch waka, waka status change, soda status change.
     */ 
    public void SuperSodaTouched() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        for (Fruit f: build.getFruitList()) {
            if (f instanceof SuperSoda) {
                SuperSoda temp = (SuperSoda) f;
                temp.setX(waka.getX());
                temp.setY(waka.getY());
                temp.tick();
                assertFalse(temp.getAlive());
                break;
            }
        }
        assertTrue(waka.getSodaMode());
    }

    @Test
    /**
     * Test the logic(tick) of super fruit.
     * Check if the dead superfruit touch waka, nothing change.
     */ 
    public void SuperDeadSodaTouched() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        for (Fruit f: build.getFruitList()) {
            if (f instanceof SuperSoda) {
                SuperSoda temp = (SuperSoda) f;
                temp.setAlive(false);
                temp.setX(waka.getX());
                temp.setY(waka.getY());
                temp.tick();
                break;
            }
        }
        assertFalse(waka.getSodaMode());
    }
}