package ghost;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.HashMap;



public class Waka extends MoveObject{

    private int frame = 0;
    private int goingDirX;
    private int goingDirY;
    private boolean FrightenedMode;
    private boolean SodaMode;
    private int FrightTimeCount = 0;

    private int SodaTimeCount = 0;
    private boolean reSetGame = false;
    
    /**
     * Waka constructor.
     * @param x coordinate x
     * @param y coordinate y
     * @param sprite sprite Waka image (original face left)
     * @param MapObjects MapObjects Objects which could not move in the game.
     * @param GameOriData GameOriData the game original data read from json file.
     * @param ImgMap ImgMap the list with all image use in the game in it.
     */
    public Waka(int x, int y, PImage sprite, ArrayList<MapObjects> MapObjects, HashMap<String,Integer> GameOriData, HashMap<String,PImage> ImgMap) {
        super(x, y, sprite, MapObjects, GameOriData, ImgMap);
        setSprit(ImgMap.get("wakaLeft"));
    }

    /**
     * The triger to reset the game.
     * @param b true to reset the game
     */
    public void setResetGame(boolean b) {
        this.reSetGame = b;
    }

    /**
     * Return the boolean value of resetGame.
     * <br>if true, the game should be reset to original coordinate.
     * @return Return the boolean value of reset Game.
     */
    public boolean getResetGame() {
        return this.reSetGame;
    }

    /**
     * The triger of Frightend mode.
     * @param i true if the frightend mode on, false off.
     */
    public void setFrightenedMode(boolean i) {
        this.FrightenedMode = i;
    }

    /**
     * The triger of Soda mode.
     * @param i true if the Soda mode on, false off.
     */
    public void setSodaMode(boolean i) {
        this.SodaMode = i;
    }

    /**
     * Return the triger value of frightend mode
     * @return Return true if Frighend mod should on.
     */
    public boolean getFrightenedMode() {
        return this.FrightenedMode;
    }

    /**
     * Return the triger value of Soda mode
     * @return Return true if Soda mod should on.
     */
    public boolean getSodaMode() {
        return this.SodaMode;
    }

    
    // public int getLives() {
    //     return super.getLives();
    // }
    
    /**
     * Radar keep tracking the aound block set wheather the block is reachable.
     * <br>The radar for waka is a bit different than ghost.<br>
     * Cause if controled by user, so when user change it direction up or down, lefe right cannot move untill next node check.
     * when user change it direction left or rignt, up down cannot move untill next node check.
     */
    public void radar() {

        if (getTouchnode()) {
            setRightmoveable(this.targetBlock(this.getX() + 16, this.getY()));
            setLeftmoveable(this.targetBlock(this.getX() - 16, this.getY()));
            setUpmoveable(this.targetBlock(this.getX() , this.getY() - 16));            
            setDownmoveable(this.targetBlock(this.getX() , this.getY() + 16));
            setTouchnode(false);
        } else if (getTouchnode() == false && this.getY() != this.getPriY()) {
            setLeftmoveable(false);
            setRightmoveable(false);
        } else if (getTouchnode() == false && this.getX() != this.getPriX()) {
            setUpmoveable(false);
            setDownmoveable(false);
        }
    }


    /**
     * Hode the user input until the input direction is moveable.
     */
    public void checkMoveable() {
        if (this.goingDirX == 0 && this.goingDirY == -1) {
            if (getUpMoveable()) {
                this.setDirect(0, -1);
                
            }
        } else if (this.goingDirX == 0 &&this.goingDirY == 1) {
            if (getDownMoveable()) {
                this.setDirect(0, 1);
            }
        } else if (this.goingDirX == -1 &&this.goingDirY == 0) {
            if (getLeftMoveable()) {
                this.setDirect(-1, 0);
            }
        } else if (this.goingDirX == 1 &&this.goingDirY == 0) {
            if (getRightMoveable()) {
                this.setDirect(1, 0);
            }
        }
    }

    /**
     * Simple move method wich image change for waka.
     * <br>Waka show different picture with different direction.<br>
     */
    public void move() {
        if (getDx() == -1 && getDy() == 0) {//left
            this.setSprit(getImgMap().get("wakaLeft"));
            if (getLeftMoveable()) {
                this.setX(getSpeed() * getDx());
            }

        }
        if (getDx() == 1 && getDy() == 0) {//right
            this.setSprit(getImgMap().get("wakaRight"));
           
            if (getRightMoveable()) {
                this.setX(getSpeed() * getDx());
            }

        }
        if (getDx() == 0 && getDy() == -1) {//up
            this.setSprit(getImgMap().get("wakaUp"));
            if (getUpMoveable()) {
                this.setY(getSpeed() * getDy());
            }
           
        }
        if (getDx() == 0 && getDy() == 1) {//down
            this.setSprit(getImgMap().get("wakaDown"));
            if (getDownMoveable()) {
                this.setY(getSpeed() * getDy());
            }        
           
        }
    }

    /**
     * Main logic of waka, run when waka alive.
     * <br> Change to img wakaclose every 8 frame.<br>
     * Check if just pass a node, if so, radar on.<br>
     * The radar set up which direction is moveable.<br>
     * The checMOveable check the user input direction whether moveable.<br>
     * If in Frighten mode or Soda mode, count time.<br>
     */
    public void tick(){
        if (getAlive()) {

            checkLocation();
            radar();
            checkMoveable();
            move();
            this.frame++;
        
            if (frame > 8) {
                this.setSprit(getImgMap().get("wakaClose"));
                if (frame > 16) {
                    this.frame = 0;
                }
            }
            countFrightTime();
            countSodaTime();
            checkAlive();
        }        
    }

    /**
     * If waka have no more lives, set it as dead.
     */
    public void checkAlive() {
        if (getLives() <= 0) {
            this.setAlive(false);
        }
    }

    /**
     * Get the direction input from builder.
     * @param dx input x direction
     * @param dy input y direction
     */
    public void sendDirect(int dx, int dy) {
        this.goingDirX = dx;
        this.goingDirY = dy;
    }

    /**
     * If in Frighten mode, count the time.
     */
    public void countFrightTime() {
        if (getFrightenedMode()) {
            this.FrightTimeCount++;
        }

        if (this.FrightTimeCount / 60 == getFrightenedLength()) {
            this.setFrightenedMode(false);
            //System.out.printf("Fright mode end, %d seconds\n", getFrightenedLength());
            this.FrightTimeCount = 0;
        }
    }

    /**
     * If in Soda mode, count the time.
     */
    public void countSodaTime() {
        if (!getFrightenedMode()) {
            if (getSodaMode()) {
                this.SodaTimeCount++;
            }
    
            if (this.SodaTimeCount / 60 == getSodaTime()) {
                this.setSodaMode(false);
                //System.out.printf("Soda mode end, %d seconds\n", getSodaTime());
                this.SodaTimeCount = 0;
            }
        }
    }

    public void setFrightTime(int i) {
        this.FrightTimeCount = i;
    }

    public void setSodaTime(int i) {
        this.SodaTimeCount = i;
    }
}