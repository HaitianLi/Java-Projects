package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class GhostTest {
    //by testing the ghost, every time test a ghost should set its coordinate on a node.
    
    @Test
    /**
     *Check whether the waka passed to all ghost. 
    */ 
    public void GhostsHaveWakaIn() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        
        for(Ghost g: Ghostlist) {
            assertNotNull(g.getWaka());
        }
    }

    @Test
    /**
     *ChangeMode test.
     *The ghost change the mode to chaser, when scaser mode time end.
    */ 
    public void GhostsCheckChaseMode() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            ArrayList<Long> ModeTime = g.getModeTime();
            g.setTime(ModeTime.get(g.getModeNumber()).intValue() * 60);
            g.ModeChange();

            assertTrue(g.getChaseMode()); 
            break;
        }
    }

    @Test
    /**
     *Check Ignorant chaser mode.
     *Check the target of ignorant ghost in chaser mode.
     *when distence larger than 8 unit.
    */ 
    public void GhostsCheckIgnorantGreater8ChaseMode() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            if (g instanceof Ignorant) {
                Ignorant temp_g = (Ignorant) g;
                ArrayList<Long> ModeTime = temp_g.getModeTime();
                temp_g.setTime(ModeTime.get(temp_g.getModeNumber()).intValue() * 60);
                temp_g.ModeChange();
                Waka waka = temp_g.getWaka();
                waka.setX(16);
                waka.setY(16);
                temp_g.setX(160);
                temp_g.setY(160);
                temp_g.tick();
                assertEquals(waka.getX(),temp_g.getTargetX());
                assertEquals(temp_g.getTargetY(), waka.getY());
                break;
            }
        }
    }

    @Test
    /**
     *Check Ignorant chaser mode.
     *Check the target of ignorant ghost in chaser mode.
     *when distence less than 8 unit.
    */ 
    public void GhostsCheckIgnorantless8ChaseMode() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            if (g instanceof Ignorant) {
                Ignorant temp_g = (Ignorant) g;
                ArrayList<Long> ModeTime = temp_g.getModeTime();
                temp_g.setTime(ModeTime.get(temp_g.getModeNumber()).intValue() * 60);
                temp_g.ModeChange();
                Waka waka = temp_g.getWaka();
                waka.setX(16);
                waka.setY(16);
                temp_g.setX(48);
                temp_g.setY(48);
                temp_g.tick();
                assertEquals(0,temp_g.getTargetX());
                assertEquals(576,temp_g.getTargetY());
                break;
            }
        }
    }

    @Test
    /**
     *Check Ignorant scater mode.
     *Check the target of ignorant ghost in scater mode.
     *Bottom left corner
    */ 
    public void GhostsCheckIgnorantInScaterMode() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            if (g instanceof Ignorant) {
                Ignorant temp_g = (Ignorant) g;
                ArrayList<Long> ModeTime = temp_g.getModeTime();
                temp_g.setModeNumber(1);
                temp_g.setTime(ModeTime.get(temp_g.getModeNumber()).intValue() * 60);
                temp_g.ModeChange();
                assertEquals(0,temp_g.getTargetX());
                assertEquals(576,temp_g.getTargetY());
                break;
            }
        }
    }

    @Test
    /**
     *Check Ambusher chaser mode.
     *Check the target of Ambusher ghost in chaser mode.
     *Four grid spaces ahead of Waka.
    */ 
    public void GhostsCheckAmbusherChaseMode() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            if (g instanceof Ambusher) {
                Ambusher temp_g = (Ambusher) g;
                ArrayList<Long> ModeTime = temp_g.getModeTime();
                temp_g.setTime(ModeTime.get(temp_g.getModeNumber()).intValue() * 60);
                temp_g.ModeChange();
                Waka waka = temp_g.getWaka();
                waka.setX(160);
                waka.setY(160);
                temp_g.setX(80);
                temp_g.setY(80);
                temp_g.tick();
                assertTrue(temp_g.getChaseMode());
                assertEquals(waka.getX() + (waka.getDx()*(16*4)),temp_g.getTargetX());
                assertEquals(waka.getY() + (waka.getDy()*(16*4)),temp_g.getTargetY());
                break;
            }
        }
    }

    @Test
    /**
     *Check Ambusher scater mode.
     *Check the target of Ambusher ghost in scater mode.
     *Top right corner
    */ 
    public void GhostsCheckAmbusherScaterMode() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            if (g instanceof Ambusher) {
                Ambusher temp_g = (Ambusher) g;
                ArrayList<Long> ModeTime = temp_g.getModeTime();
                temp_g.setModeNumber(1);
                temp_g.setTime(ModeTime.get(temp_g.getModeNumber()).intValue() * 60);
                temp_g.ModeChange();
                temp_g.tick();
                assertEquals(448,temp_g.getTargetX());
                assertEquals(0,temp_g.getTargetY());
                break;
            }
        }
    }

    @Test
    /**
     *Check Whim chaser mode.
     *Check the target of Whim ghost in chaser mode.
     *Double the vector from Chaser to 2 grid spaces ahead of Waka.
    */ 
    public void GhostsCheckWhimChaseMode() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();
        Chaser chaser = build.getChaser();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            if (g instanceof Whim) {
                Whim temp_g = (Whim) g;
                ArrayList<Long> ModeTime = temp_g.getModeTime();
                temp_g.setTime(ModeTime.get(temp_g.getModeNumber()).intValue() * 60);
                temp_g.ModeChange();
                
                chaser.setX(96);
                chaser.setY(96);
                temp_g.setX(80);
                temp_g.setY(80);
                temp_g.tick();
                int WakaPlusTwoX = waka.getX() + (waka.getDx()*(16*2));
                int WakaPlusTwoY = waka.getY() + (waka.getDy()*(16*2));
                int ChaserX = 96;
                int ChaserY = 96;
                int numberX = 0;
                int numberY = 0;
                
                if (WakaPlusTwoX > ChaserX) {
                    numberX = WakaPlusTwoX + (WakaPlusTwoX - ChaserX);
                } else if (WakaPlusTwoX < ChaserX) {
                    numberX = WakaPlusTwoX - (ChaserX - WakaPlusTwoX);
                } else if (WakaPlusTwoX == ChaserX) {
                    numberX = WakaPlusTwoX;
                }
                if (WakaPlusTwoY > ChaserY) {
                    numberY = WakaPlusTwoY + (WakaPlusTwoY - ChaserY);
                } else if (WakaPlusTwoY < ChaserY) {  
                    numberY = WakaPlusTwoY - (ChaserY - WakaPlusTwoY);
                } else if (WakaPlusTwoY == ChaserY) {
                    numberY = WakaPlusTwoY;
                } 
                assertTrue(temp_g.getChaseMode());
                assertEquals(numberX,temp_g.getTargetX());
                assertEquals(numberY,temp_g.getTargetY());
                break;
            }
        }
    }

    @Test
    /**
     *Check Whim chaser mode. different target X smaller
     *Check the target of Whim ghost in chaser mode.
     *Double the vector from Chaser to 2 grid spaces ahead of Waka.
    */ 
    public void GhostsCheckWhimChaseModeDifferentTarget() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();
        Chaser chaser = build.getChaser();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            if (g instanceof Whim) {
                Whim temp_g = (Whim) g;
                ArrayList<Long> ModeTime = temp_g.getModeTime();
                temp_g.setTime(ModeTime.get(temp_g.getModeNumber()).intValue() * 60);
                temp_g.ModeChange();
                
                chaser.setX(48);
                chaser.setY(96);
                temp_g.setX(80);
                temp_g.setY(80);
                temp_g.tick();
                int WakaPlusTwoX = waka.getX() + (waka.getDx()*(16*2));
                int WakaPlusTwoY = waka.getY() + (waka.getDy()*(16*2));
                int ChaserX = 48;
                int ChaserY = 96;
                int numberX = 0;
                int numberY = 0;
                
                if (WakaPlusTwoX > ChaserX) {
                    numberX = WakaPlusTwoX + (WakaPlusTwoX - ChaserX);
                } else if (WakaPlusTwoX < ChaserX) {
                    numberX = WakaPlusTwoX - (ChaserX - WakaPlusTwoX);
                } else if (WakaPlusTwoX == ChaserX) {
                    numberX = WakaPlusTwoX;
                }
                if (WakaPlusTwoY > ChaserY) {
                    numberY = WakaPlusTwoY + (WakaPlusTwoY - ChaserY);
                } else if (WakaPlusTwoY < ChaserY) {  
                    numberY = WakaPlusTwoY - (ChaserY - WakaPlusTwoY);
                } else if (WakaPlusTwoY == ChaserY) {
                    numberY = WakaPlusTwoY;
                } 
                assertTrue(temp_g.getChaseMode());
                assertEquals(numberX,temp_g.getTargetX());
                assertEquals(numberY,temp_g.getTargetY());
                break;
            }
        }
    }

    @Test
    /**
     *Check Whim chaser mode. different target x equal
     *Check the target of Whim ghost in chaser mode.
     *Double the vector from Chaser to 2 grid spaces ahead of Waka.
    */ 
    public void GhostsCheckWhimChaseModeDifferentTargetXEqual() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();
        Chaser chaser = build.getChaser();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            if (g instanceof Whim) {
                Whim temp_g = (Whim) g;
                ArrayList<Long> ModeTime = temp_g.getModeTime();
                temp_g.setTime(ModeTime.get(temp_g.getModeNumber()).intValue() * 60);
                temp_g.ModeChange();
                
                chaser.setX(96);
                chaser.setY(96);
                temp_g.setX(48);
                temp_g.setY(48);
                temp_g.tick();
                int WakaPlusTwoX = waka.getX() + (waka.getDx()*(16*2));
                int WakaPlusTwoY = waka.getY() + (waka.getDy()*(16*2));
                int ChaserX = 96;
                int ChaserY = 96;
                int numberX = 0;
                int numberY = 0;
                
                if (WakaPlusTwoX > ChaserX) {
                    numberX = WakaPlusTwoX + (WakaPlusTwoX - ChaserX);
                } else if (WakaPlusTwoX < ChaserX) {
                    numberX = WakaPlusTwoX - (ChaserX - WakaPlusTwoX);
                } else if (WakaPlusTwoX == ChaserX) {
                    numberX = WakaPlusTwoX;
                }
                if (WakaPlusTwoY > ChaserY) {
                    numberY = WakaPlusTwoY + (WakaPlusTwoY - ChaserY);
                } else if (WakaPlusTwoY < ChaserY) {  
                    numberY = WakaPlusTwoY - (ChaserY - WakaPlusTwoY);
                } else if (WakaPlusTwoY == ChaserY) {
                    numberY = WakaPlusTwoY;
                } 
                assertTrue(temp_g.getChaseMode());
                assertEquals(numberX,temp_g.getTargetX());
                assertEquals(numberY,temp_g.getTargetY());
                break;
            }
        }
    }

    @Test
    /**
     *Check Whim chaser mode. different target All equal
     *Check the target of Whim ghost in chaser mode.
     *Double the vector from Chaser to 2 grid spaces ahead of Waka.
    */ 
    public void GhostsCheckWhimChaseModeDifferentTargetEqual() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();
        Chaser chaser = build.getChaser();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            if (g instanceof Whim) {
                Whim temp_g = (Whim) g;
                ArrayList<Long> ModeTime = temp_g.getModeTime();
                temp_g.setTime(ModeTime.get(temp_g.getModeNumber()).intValue() * 60);
                temp_g.ModeChange();
                
                chaser.setX(96);
                chaser.setY(96);
                temp_g.setX(96);
                temp_g.setY(96);
                temp_g.tick();
                int WakaPlusTwoX = waka.getX() + (waka.getDx()*(16*2));
                int WakaPlusTwoY = waka.getY() + (waka.getDy()*(16*2));
                int ChaserX = 96;
                int ChaserY = 96;
                int numberX = 0;
                int numberY = 0;
                
                if (WakaPlusTwoX > ChaserX) {
                    numberX = WakaPlusTwoX + (WakaPlusTwoX - ChaserX);
                } else if (WakaPlusTwoX < ChaserX) {
                    numberX = WakaPlusTwoX - (ChaserX - WakaPlusTwoX);
                } else if (WakaPlusTwoX == ChaserX) {
                    numberX = WakaPlusTwoX;
                }
                if (WakaPlusTwoY > ChaserY) {
                    numberY = WakaPlusTwoY + (WakaPlusTwoY - ChaserY);
                } else if (WakaPlusTwoY < ChaserY) {  
                    numberY = WakaPlusTwoY - (ChaserY - WakaPlusTwoY);
                } else if (WakaPlusTwoY == ChaserY) {
                    numberY = WakaPlusTwoY;
                } 
                assertTrue(temp_g.getChaseMode());
                assertEquals(numberX,temp_g.getTargetX());
                assertEquals(numberY,temp_g.getTargetY());
                break;
            }
        }
    }

    @Test
    /**
     *Check Whim scater mode.
     *Check the target of Whim ghost in scater mode.
     *Bottom right corner
    */  
    public void GhostsCheckWhimScaterMode() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            if (g instanceof Whim) {
                Whim temp_g = (Whim) g;
                ArrayList<Long> ModeTime = temp_g.getModeTime();
                temp_g.setModeNumber(1);
                temp_g.setTime(ModeTime.get(temp_g.getModeNumber()).intValue() * 60);
                temp_g.ModeChange();
                temp_g.tick();
                assertEquals(448,temp_g.getTargetX());
                assertEquals(576,temp_g.getTargetY());
                break;
            }
        }
    }

    @Test
    /**
     *Check Whim cater mode.
     *Check the target of Whim ghost in chater mode when chaser gone.
     *Bottom right corner
    */  
    public void GhostsCheckWhimWhenChaseGone() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Chaser chaser = build.getChaser();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            if (g instanceof Whim) {
                Whim temp_g = (Whim) g;
                ArrayList<Long> ModeTime = temp_g.getModeTime();
                temp_g.setTime(ModeTime.get(temp_g.getModeNumber()).intValue() * 60);
                chaser.setAlive(false);
                temp_g.ModeChange();
                temp_g.tick();
                assertEquals(448,temp_g.getTargetX());
                assertEquals(576,temp_g.getTargetY());
                break;
            }
        }
    }

    @Test
    /**
     *Check Chaser chaser mode.
     *Check the target of Chaser ghost in chaser mode.
     *Waka current coordinate.
    */ 
    public void GhostsCheckChaserChaseMode() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            if (g instanceof Chaser) {
                Chaser temp_g = (Chaser) g;
                ArrayList<Long> ModeTime = temp_g.getModeTime();
                temp_g.setTime(ModeTime.get(temp_g.getModeNumber()).intValue() * 60);
                temp_g.ModeChange();
                Waka waka = temp_g.getWaka();
                waka.setX(160);
                waka.setY(160);
                temp_g.setX(80);
                temp_g.setY(80);
                temp_g.tick();
                assertTrue(temp_g.getChaseMode());
                assertEquals(waka.getX(),temp_g.getTargetX());
                assertEquals(waka.getY(),temp_g.getTargetY());
                break;
            }
        }
    }

    @Test
    /**
     *Check Chaser scater mode.
     *Check the target of Chaser ghost in scater mode.
     *Top left corner
    */ 
    public void GhostsCheckChaserScaterMode() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            if (g instanceof Chaser) {
                Chaser temp_g = (Chaser) g;
                ArrayList<Long> ModeTime = temp_g.getModeTime();
                temp_g.setModeNumber(1);
                temp_g.setTime(ModeTime.get(temp_g.getModeNumber()).intValue() * 60);
                temp_g.ModeChange();
                temp_g.tick();
                assertEquals(0,temp_g.getTargetX());
                assertEquals(0,temp_g.getTargetY());
                break;
            }
        }
    }

    

    @Test
    /**
     *ChangeMode test.
     *The ghost change the mode to scater, when chaser mode time end.
    */ 
    public void GhostsCheckSchaserMode() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            ArrayList<Long> ModeTime = g.getModeTime();
            g.setModeNumber(1);
            g.setTime(ModeTime.get(g.getModeNumber()).intValue() * 60);

            g.ModeChange();
            assertTrue(g.getScaterMode()); 
            break;
        }
    }

    @Test
    /**
     *The ghost should change to frigntend mode when waka touch a superfruit.
     *THe logic of waka touch superfruit is already tested in FruitTest.
     *So, just set the waka to frighten mode.
     *All ghost should in frighten mode
    */ 
    public void GhostsCheckFrigntenMode() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();

        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            Waka waka = g.getWaka();
            waka.setFrightenedMode(true);
            g.tick();
            assertTrue(g.getFrightenedMode());
        }
    }

    @Test
    /**
     *The ghost should change to soda mode when waka touch a supersoda.
     *THe logic of waka touch supersod is already tested in FruitTest.
     *So, just set the waka to soda mode.
     *All ghost should in soda mode
    */ 
    public void GhostsCheckSodaMode() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();

        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            Waka waka = g.getWaka();
            waka.setSodaMode(true);
            g.tick();
            assertTrue(g.getSodaMode());
        }
    }

    @Test
    /**
     *The ghost should change to to when user pressed space.
     *The Ascii code for space is 32, when press again, debug mode close.
    */ 
    public void GhostsCheckDebugMode() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        build.getKeyValue(39, 32);
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            assertTrue(g.getDebug());
        }
        build.getKeyValue(39, 32);
        for(Ghost g: Ghostlist) {
            assertFalse(g.getDebug());
        }
    }

    @Test
    /**
     * Ghost should change mode even dead, so every ghost will have same time role.
     */
    public void GhostsCheckDeadChangeMode() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            ArrayList<Long> ModeTime = g.getModeTime();
            assertTrue(g.getScaterMode());
            g.setAlive(false);

            g.setModeNumber(1);
            g.setTime(ModeTime.get(g.getModeNumber()).intValue() * 60);

            g.tick();
            assertTrue(g.getScaterMode()); 
            break;
        }
    }

    @Test
    /**
     * Check when Ghost touch the waka in normal mode(not frighten).
     */
    public void GhostsTouchWaka() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            
            Waka waka = g.getWaka();
            int i = waka.getLives();
            waka.setX(16);
            waka.setY(16);
            g.setX(16);
            g.setY(16);
            g.checkIfTouchWaka();
            assertEquals(i-1, waka.getLives()); 
            break;
        }
    }

    @Test
    /**
     * Check when Ghost touch the waka frighten mode.
     */
    public void GhostsTouchWakaFrighten() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            
            Waka waka = g.getWaka();
            waka.setFrightenedMode(true);
            int i = waka.getLives();
            g.setX(16);
            g.setY(16);
            waka.setX(16);
            waka.setY(16);
            g.tick();
            assertEquals(i, waka.getLives());
            assertFalse(g.getAlive());
            break;
        }
    }

    @Test
    /**
     * Test random move method when mode in frighten.
     */
    public void GhostsRandom() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            ArrayList<MapObjects> temp = g.getIntersections();
            Waka waka = g.getWaka();
            waka.setFrightenedMode(true);
            g.ModeChange();
            for (MapObjects m: temp) {
                g.setX(m.getX());
                g.setY(m.getY());
                g.tick();
                assertTrue(g.getRandomMode());
            }
        }
    }

    @Test
    /**
     * Test random move method when mode in frighten.
     */
    public void GhostsMoveright() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            ArrayList<MapObjects> temp = g.getIntersections();
            g.setAlive(false);
            for (MapObjects m: temp) {
                if (waka.targetBlock(m.getX() + 16, m.getY())) {
                    g.setX(m.getX());
                    g.setY(m.getY());
                    
                    g.setDirect(1, 0);
                    g.setDirect(10, 20);
                    g.setRightmoveable(true);
                    //System.out.printf("%d %d\n", g.getX(), g.getY());
                    g.move();
                    //System.out.printf("%d %d\n", g.getX(), g.getY());
                    int i = g.getX();
                    assertNotEquals(i, m.getX()+g.getSpeed()*g.getDx());
                    break;
                }
            }
            break;
        }
    }

    @Test
    /**
     * Test random move method when mode in frighten.
     */
    public void GhostsMoveleft() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            ArrayList<MapObjects> temp = g.getIntersections();
            g.setAlive(false);
            for (MapObjects m: temp) {
                if (waka.targetBlock(m.getX() - 16, m.getY())) {
                    g.setX(m.getX());
                    g.setY(m.getY());
                    g.setDirect(-1, 0);
                    g.setLeftmoveable(true);
                    //System.out.printf("%d %d\n", g.getX(), g.getY());
                    g.move();
                    //System.out.printf("%d %d\n", g.getX(), g.getY());
                    int i = g.getX();
                    assertEquals(i, m.getX()+g.getSpeed()*g.getDx());
                    break;
                }
            }
            break;
        }
    }

    @Test
    /**
     * Test random move method when mode in frighten.
     */
    public void GhostsMoveup() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            ArrayList<MapObjects> temp = g.getIntersections();
            g.setAlive(false);
            for (MapObjects m: temp) {
                if (waka.targetBlock(m.getX(), m.getY() - 16)) {
                    g.setX(m.getX());
                    g.setY(m.getY());
                    g.setDirect(0, -1);
                    g.setUpmoveable(true);
                    System.out.printf("%d %d\n", g.getX(), g.getY());
                    g.move();
                    System.out.printf("%d %d\n", g.getX(), g.getY());
                    int i = g.getY();
                    assertEquals(i, m.getY() + g.getSpeed()*g.getDy());
                    break;
                }
            }
            break;
        }
    }
    @Test
    /**
     * Test random move method when mode in frighten.
     */
    public void GhostsMoveDown() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        Waka waka = build.getWaka();

        ArrayList<Ghost> Ghostlist = build.getGhostList();
        for(Ghost g: Ghostlist) {
            ArrayList<MapObjects> temp = g.getIntersections();
            g.setAlive(false);
            for (MapObjects m: temp) {
                if (waka.targetBlock(m.getX(), m.getY() + 16)) {
                    g.setX(m.getX());
                    g.setY(m.getY());
                    g.setDirect(0, 1);
                    g.setDownmoveable(true);
                    System.out.printf("%d %d\n", g.getX(), g.getY());
                    g.move();
                    System.out.printf("%d %d\n", g.getX(), g.getY());
                    int i = g.getY();
                    assertEquals(i, m.getY() + g.getSpeed()*g.getDy());
                    break;
                }
            }
            break;
        }
    }
    
    @Test
    /**
     * Test ghost's moveable if node changeing.
     *  */ 
    public void GhostMoveableTest() {
        App app = new App();
        PApplet.runSketch(new String[] { "Test Sketch" }, app);
        app.delay(1000);
        app.noLoop();
        
        Builder build = app.getBuilder();
        ArrayList<MapObjects> map = build.getIntersections();
        ArrayList<Ghost> ghost = build.getGhostList();
        //Waka waka = build.getWaka();
        
       
        for (Ghost g: ghost) {
            for (MapObjects m : map) {
                ArrayList<Long> ModeTime = g.getModeTime();
                g.setTime(ModeTime.get(g.getModeNumber()).intValue() * 60);
                g.setX(m.getX());
                g.setY(m.getY());
                Waka waka = g.getWaka();
                waka.setX(224);
                waka.setY(288);
                g.setDirect(100, 20);
                g.tick();
            }
        }
    }

}