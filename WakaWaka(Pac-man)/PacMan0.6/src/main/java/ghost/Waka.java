package ghost;
import processing.core.PImage;
import java.util.ArrayList;


public class Waka extends MoveObject{

    private PImage WakaClose;
    private PImage WakaLeft;
    private PImage WakaRight;
    private PImage WakaUp;
    private PImage WakaDown;
    private int frame = 0;
    private int lives;
    private int goingDirX;
    private int goingDirY;
    private boolean FrightenedMode;
    private int FrightTimeCount = 0;
    private int FrightTime = 0;
    private boolean reSetGame = false;
    
    
    public Waka(int x, int y, PImage sprite, ArrayList<MapObjects> MapObjects, int speed, int lives, int FrightTime) {
        super(x, y, sprite, MapObjects, speed);
        this.WakaLeft = sprite;
        this.lives = lives;
        this.FrightTime = FrightTime;
    }

    public void setWakaCloseImg(PImage img) {
        this.WakaClose = img;
    }

    public void setResetGame(boolean b) {
        this.reSetGame = b;
    }

    public boolean getResetGame() {
        return this.reSetGame;
    }

    public void setWakaUpImg(PImage img) {
        this.WakaUp = img;
    }

    public void setWakaDownImg(PImage img) {
        this.WakaDown = img;
    }

    public void setWakaLeftImg(PImage img) {
        this.WakaLeft = img;
    }

    public void setWakaRightImg(PImage img) {
        this.WakaRight = img;
    }

    public void setLives() {
        this.lives -= 1;
    }

    public void setFrightenedMode(boolean i) {
        this.FrightenedMode = i;
    }

    public boolean getFrightenedMode() {
        return this.FrightenedMode;
    }

    public int getLives() {
        return this.lives;
    }

    public void radar() {
        if (getTouchnode()) {
            setRightmoveable(this.targetBlock(this.getX() + 16, this.getY()));
            setLeftmoveable(this.targetBlock(this.getX() - 16, this.getY()));
            setUpmoveable(this.targetBlock(this.getX() , this.getY() - 16));            setDownmoveable(this.targetBlock(this.getX() , this.getY() + 16));
            setTouchnode(false);
        } else if (getTouchnode() == false && this.getY() != this.getPriY()) {
            setLeftmoveable(false);
            setRightmoveable(false);
        } else if (getTouchnode() == false && this.getX() != this.getPriX()) {
            setUpmoveable(false);
            setDownmoveable(false);
        }
    }


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

    public void move() {
        if (getDx() == -1 && getDy() == 0) {//left
            this.setSprit(WakaLeft);
            if (getLeftMoveable()) {
                this.setX(getSpeed() * getDx());
            }

        } else if (getDx() == 1 && getDy() == 0) {//right
            this.setSprit(WakaRight);
           
            if (getRightMoveable()) {
                this.setX(getSpeed() * getDx());
            }

        } else if (getDx() == 0 && getDy() == -1) {//up
            this.setSprit(WakaUp);
            if (getUpMoveable()) {
                this.setY(getSpeed() * getDy());
            }
           
        } else if (getDx() == 0 && getDy() == 1) {//down
            this.setSprit(WakaDown);
            if (getDownMoveable()) {
                this.setY(getSpeed() * getDy());
            }        
           
        }
    }


    public void tick(){

        checkLocation();
        radar();
        checkMoveable();
        move();
        this.frame++;
        

        if (frame > 8) {
            this.setSprit(WakaClose);
            if (frame > 16) {
                this.frame = 0;
            }
        }

        countFrightTime() ;

        //System.out.println(lives);
        
    }


    public void sendDirect(int dx, int dy) {
        this.goingDirX = dx;
        this.goingDirY = dy;
    }

    public void countFrightTime() {
        if (getFrightenedMode()) {
            this.FrightTimeCount++;
        }

        if (this.FrightTimeCount / 60 == this.FrightTime) {
            this.setFrightenedMode(false);
            System.out.printf("Fright mode end, %d seconds\n", FrightTime);
            this.FrightTimeCount = 0;
        }
    }

}