package ghost;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PImage;


public class Ghost extends MoveObject{
    
    private int timer = 0;
    private int ModeNumber = 0;
    private double distence;
    private double distenceToWaka;
    private boolean scatterMode = true;
    private boolean chaseMode;
    private boolean FrightenedMode;
    private boolean SodaMode;
    private boolean RandomMode;
    private static boolean deBugMode;
    private ArrayList<MapObjects> Intersections;
    private ArrayList<Long> ModeTime;
    private Waka waka;
    private PImage GhostImg;

    
    /**
     * Ghost constructor.
     * <br>Creat Ghost instance with necessary variables.<br>
     * @param x coordinate x.
     * @param y coordinate y.
     * @param sprite Ghost image.
     * @param MapObjects A list conatin all mapObjects(),the objects could not move in the game.
     * @param waka The waka
     * @param Intersections A list of intersections node where ghosts could make a ture.
     * @param ModeTime The different mode time read from json file.
     * @param GameOriData The game original Data read from json(i.e. speed).
     * @param ImgMap All images need to be used in this gmae.
     */
    public Ghost(int x, int y, PImage sprite, ArrayList<MapObjects> MapObjects, Waka waka, ArrayList<MapObjects> Intersections,ArrayList<Long> ModeTime,HashMap<String,Integer> GameOriData , HashMap<String,PImage> ImgMap ) {
        super(x, y, sprite, MapObjects, GameOriData, ImgMap);
        this.waka = waka;
        this.Intersections = Intersections;
        this.ModeTime = ModeTime;
        this.GhostImg = sprite;

    }

    /**
     * Return the Intersection list.
     * @return Return the Intersection list.
     */
    public ArrayList<MapObjects> getIntersections() {
        return this.Intersections;
    }

    /**
     *Return the waka instance. 
     * @return Return the waka instance. 
     */
    public Waka getWaka(){
        return this.waka;
    }

    /**
     * Checl whether the mode is Scater 
     * @return Return scatterMode boolean value
     */
    public boolean getScaterMode() {
        return this.scatterMode;
    }

    /**
     * Checl whether the mode is Chase 
     * @return Return chaseMode boolean value
     */
    public boolean getChaseMode() {
        return this.chaseMode;
    }

    /**
     * Checl whether the mode is Frighten.
     * @return Return FrightenedMode boolean value
     */
    public boolean getFrightenedMode() {
        return this.FrightenedMode;
    }
    
    /**
     * Interator the timer by 1.
     * <br> It is the time count for different modes except frighten and soda.<br> 
     */
    public void setTimer() {
        this.timer++;
    }

    /**
     * Set the distence between waka and Ghost.
     */
    public void setWakaDistence() {
        this.distenceToWaka = culDistence(getX(), getY(), waka.getX(), waka.getY());
    }

    /**
     * Check whether to use the debug mode.
     * <br>listening the user's keyboard if it is 32 which means space.<br>
     * If keyAscii == 32, Debug mode on.<br>
     * @param keyAscii the key value pass from builder
     */
    public static void sendKeyvalue(int keyAscii) {
        
        if (keyAscii == 32) {
            if (deBugMode) {
                deBugMode = false;
            } else {
                deBugMode = true;
            }
        }
    }

    /**
     * The interface implements, mean logic of Ghosts.
     * <br>If the Ghost alive the logic keep running else all logic not update but the timecount and modechange.<br>
     * The Modechange method keep the dead ghost counting mode time, so when every thing reset, all ghost have same modetime.<br>
     */
    public void tick() {
        
        if (getAlive()) {
            checkLocation();
            radar();
            PassIntersection();
            move();
            ModeChange();
            checkIfTouchWaka();
            if (!getFrightenedMode()) {
                setTimer();
            }
        } else {
            if (!getFrightenedMode()) {
                setTimer();
            }
            ModeChange();
        }
        //checkAlive();
    }
    
    /**
     * Draw method, drw ghost on screen with offest 8 pixel.
     * @param app The game window instance.
     */
    public void draw(PApplet app) {
        if (getAlive()) {
            app.image(getSprite(), getX() - getOffset(), getY() - getOffset());
            if (deBugMode && !getFrightenedMode()) {
                app.line(getX() + 8, getY() + 8, getTargetX() + 8, getTargetY() + 8);
                app.stroke(200);
            }
        }
    }

    /**
     * Whether the ghost is current under debug mode.
     * @return Return true if debug, false no debug.<br>
     */
    public boolean getDebug() {
        return deBugMode;
    }

    /**
     * Check what mode shoud the ghost use.
     * <br>The timer count the time in milisecond, and following the time list read from json list.<br>
     * The timer of scatter off and chase will not count when in Frighten mode.<br>
     * 
     */
    public void ModeChange() {
        FrightenedMode = waka.getFrightenedMode();
        checkFrightenedMode();
        checkSodaMode();
        if (timer/60 == this.ModeTime.get(ModeNumber)){
            if (ModeNumber % 2 == 0) {
                this.chaseMode = true;
                this.scatterMode = false;
                //System.out.printf("ChaseMode mode on, Scatter off, time:%d\n",timer/60);
            } else {
                this.scatterMode = true;
                this.chaseMode = false;
                //System.out.printf("Scatter mode on, chaseModeon off, time:%d\n",timer/60);
            }
            ModeNumber += 1;
            ModeNumber = ModeNumber % ModeTime.size();
            timer = 0;
        }
    }

    /**
     * Return the ghost current use sprite.
     * @return Return the ghost current use sprite.
     */
    public PImage getGhostImage() {
        return this.GhostImg;
    }

    /**
     * Check the distance between waka and ghost.
     * <br>If touched, set the waka to original node, and let it face left.<br>
     * If the ghost in frighten mode, ghost removed from screen
     */
    public void checkIfTouchWaka() {
        setWakaDistence();
        if (distenceToWaka < 28 && (waka.getX() == getX() || waka.getY() == getY())) {
            if (!getFrightenedMode()) {
                // setX(getOriginalX());
                // setY(getOriginalY());
                waka.setX(waka.getOriginalX());
                waka.setY(waka.getOriginalY());
                waka.setDirect(-1, 0);
                //setDirect(1, 0);
                waka.setLives();
                waka.setResetGame(true);

            } else {
                setAlive(false);
            }
        }
    }
    
    
    /**
     * Check whether the ghost in Frighten mode.
     * <br>If so, change the sprite to the fright image.<br>
     * if not change the sprite back.<br>
     */
    public void checkFrightenedMode() {
        
        if (getFrightenedMode()) {
            this.setSprit(getImgMap().get("GhostFrightImg"));
            
        } else {
            this.setSprit(GhostImg);
        }
        
        
    }

    /**
     * Check whether the ghost in Soda Mode.
     * <br>If so, change the ghost to ghostghost sprit(soda img).<br>
     * If not, change the sprite back.<br>
     * The Frighten mode have the highest preority.<br>
     * If in Frighten mode, always count Frighten mode time first, and then soda time, then scater and chaser.
     */
    public void checkSodaMode() {
        if (!getFrightenedMode()) {

            if (waka.getSodaMode()) {
                this.setSprit(getImgMap().get("GhostGhostImg"));
                SodaMode = true;
                
            } else {
                this.setSprit(GhostImg);
            }
        }
    }

    /**
     * The method keep tracking the grid  around the ghost, whether they are moveable.
     */
    public void radar() {
        if (getTouchnode()) {
            
            setLeftmoveable(targetBlock(getX() - 16, getY()));
            setRightmoveable(targetBlock(getX() + 16, getY()));
            setUpmoveable(targetBlock(getX() , getY() - 16));            
            setDownmoveable(targetBlock(getX() , getY() + 16));
            setTouchnode(false);
            goBack();
        }
    }

    /**
     * The goback method, for the edge situation when the ghost or waka been triped  with 3 wall around, they could go back.
     */
    public void goBack() {
        if (getLeftMoveable() == false && getRightMoveable() == false && getUpMoveable() == false){
            setDirect(0, 1); //go down
        } else if (getLeftMoveable() == false && getRightMoveable() == false && getDownMoveable() == false) {
            setDirect(0, -1);//go up
        } else if (getDownMoveable() == false && getRightMoveable() == false && getUpMoveable() == false) {
            setDirect(-1, 0);//go left
        } else if (getLeftMoveable() == false && getDownMoveable() == false && getUpMoveable() == false) {
            setDirect(1, 0);//go right
        }

    }

    /**
     * This method turnon the radar and set target coordinate in different mode.
     * <br>When the ghost touch a node which is the point have coordinates could be devided by 16.<br>
     * Call radar to check which path around is moveable, set target coordinate in different mode.
     */
    public void checkLocation() {
        for(MapObjects m: getMapObjects()) {
            if (this.getX() == m.getX() && this.getY() == m.getY()){
                
                setTouchnode(true);
                this.setPriviousLocation(m.getX(), m.getY());
                if (!getFrightenedMode()) {

                    if (getScaterMode()) {
                        setTarget(0, 0);
                    } else if (getChaseMode()) {
                        setTarget(waka.getX(), waka.getY());

                    }
                }
                break;
                
            }
        }
    }
    /**
     * Ghost make dissition when touch intersection.
     * <br>When ghost touch a intersection, first check which direction could move.<br>
     * Then, check the moveable direction which one have smaller distence to waka.<br>
     * In different mode, they act in different way.
     * Scater mode will keep target the cornor, chaser mode will chase the waka in specific logic.<br>
     * In Frighten mode, the Ghost go random.
     */
    public void PassIntersection() {
        for(MapObjects m: getIntersections()) {
            if (this.getX() == m.getX() && this.getY() == m.getY()){

                if (getDx() == -1) {
                    setRightmoveable(false);
                } else if (getDx() == 1) {
                    setLeftmoveable(false);
                } else if (getDy() == -1) {
                    setDownmoveable(false);
                } else if (getDy() == 1) {
                    setUpmoveable(false);
                }
                if (!getFrightenedMode()) {
                    MakeATurn(getTargetX(), getTargetY());
                    RandomMode = false;
                } else {
                    moveRandom();
                }
            }
        }
    }

    /**
     * The method let the ghost go random using math.random method.
     */
    public void moveRandom() {
        ArrayList<int[]> directList = new ArrayList<>();
        if (getLeftMoveable()) {
            int[] temp = new int[2];
            temp[0] = -1;
            temp[1] = 0;
            directList.add(temp);
        }

        if (getRightMoveable()) {
            int[] temp = new int[2];
            temp[0] = 1;
            temp[1] = 0;
            directList.add(temp);
        }

        if (getUpMoveable()) {
            int[] temp = new int[2];
            temp[0] = 0;
            temp[1] = -1;
            directList.add(temp);
        }

        if (getDownMoveable()) {
            int[] temp = new int[2];
            temp[0] = 0;
            temp[1] = 1;
            directList.add(temp);
        }

        int [] randomTarget = directList.get((int)(Math.random() * directList.size()));
        setDirect(randomTarget[0], randomTarget[1]);
        RandomMode = true;

    }

    /**
     * Move method let the ghost move by changing the current coordinates.
     * <br>Only work if the direction is moveable.<br>
     */
    public void move() {
        if (getDx() == -1 && getDy() == 0) {//left
            if (getLeftMoveable()) {
                this.setX(getSpeed() * getDx());
            }

        } else if (getDx() == 1 && getDy() == 0) {//right
            if (getRightMoveable()) {
                this.setX(getSpeed() * getDx());
            }

        } else if (getDx() == 0 && getDy() == -1) {//up
            if (getUpMoveable()) {
                this.setY(getSpeed() * getDy());
            }
           
        } else if (getDx() == 0 && getDy() == 1) {//down
            if (getDownMoveable()) {
                this.setY(getSpeed() * getDy());
            }        
        }
    }

    /**
     * Calculate the distence between waka and ghost
     * @param ghostX ghost coordinate x
     * @param ghostY ghost coordinate y
     * @param targetX waka coordinate x
     * @param targetY waka coordinate y
     * @return Return the distence in double.
     */
    public double culDistence(int ghostX, int ghostY, int targetX, int targetY ) {
        this.distence = Math.sqrt(Math.pow((double)ghostX - (double)targetX, 2.0) + Math.pow((double)ghostY - (double)targetY, 2.0));
        return distence;
    }



    /**
     * The ghost make a trun, choose the grid which have the lowest distence value to target.
     * <br>But all 4 direction  + 16 grid distence to waka in a list, if the direction moveable.<br>
     * The priority is up, down, left, right.<br>
     * If the value in the list is equals to 999 means the value never change.<br>
     * @param x The target coordinate x
     * @param y The target coordinate y
     */
    public void MakeATurn(int x, int y) {
           
        double [] temp = {999.00,999.00,999.00,999.00};
        int tempX = 0;
        int tempY = 0;
                                            
        if (getLeftMoveable()) {    //left
            tempX = this.getX() - 16;
            tempY = this.getY();
            temp[1] = culDistence(tempX, tempY, x, y);
        } 

        if (getRightMoveable()){ // right
            tempX = this.getX() + 16;
            tempY = this.getY();
            temp[3] = culDistence(tempX, tempY, x, y);
        }

        if (getUpMoveable()) { //up
            tempX = this.getX() ;
            tempY = this.getY() - 16;
            temp[0] = culDistence(tempX, tempY, x, y);
        }

        if (getDownMoveable()) {//down
            tempX = this.getX() ;
            tempY = this.getY() + 16;
            temp[2] = culDistence(tempX, tempY, x, y);
        }

        double min = 15000.0f;
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] < min && temp[i] != 999) {
                min = temp[i];
                index = i;
            }
        }
        
        for (int i = 0; i< temp.length; i++) {
            for (int j = i + 1; j < temp.length; j++) {
                if (temp[i] == temp[j] && temp[i] != 999) {
                }
            }
        }

        if (index == 1) {
            this.setDirect(-1, 0);
        } else if (index == 3) {
            this.setDirect(1, 0);
        } else if (index == 0) {
            this.setDirect(0, -1);
        } else if (index == 2) {
            this.setDirect(0, 1);
        }         
    }

    /**
     * Set the mode index number, specific use for test
     * @param modeNumber The index of different mode.
     */
    public void setModeNumber(int modeNumber) {
        ModeNumber = modeNumber;
    }
    
    /**
     * Return the current index of the mode.
     * @return  Return the current index of the mode.
     */
    public int getModeNumber() {
        return ModeNumber;
    }

    /**
     * Return the mode time list read from json file.
     * @return Return the mode time list read from json file.
     */
    public ArrayList<Long> getModeTime() {
        return ModeTime;
    }

    /**
     * Set the time which count the mode time with the param
     * @param i The time want to set.
     */
    public void setTime(int i) {
        this.timer = i;
    }
    
    /**
     * Return whether the game is in Soda mode.
     * @return  True means in soda mode.
     */
    public boolean getSodaMode() {
        return this.SodaMode;
    }
    
    /**
     * Create for test, whether ghost move in random way.
     * @return true if the ghost move randomly.
     */
    public boolean getRandomMode() {
        return this.RandomMode;
    }
}
