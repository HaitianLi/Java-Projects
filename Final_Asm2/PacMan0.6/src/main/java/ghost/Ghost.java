package ghost;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;


public class Ghost extends MoveObject{
    
    private int timer = 0;
    private int FrightenedModeTimer = 0;
    private Long FrightenedModeTime;
    private double distence;
    private boolean scatterMode = true;
    private boolean chaseMode = false;
    private boolean FrightenedMode = false;
    private int ModeNumber = 0;
    private ArrayList<MapObjects> Intersections;
    private ArrayList<Long> ModeTime;
    private Waka waka;
    private PImage GhostFrightImg;
    private PImage GhostImg;
    private double distenceToWaka;
    private static boolean deBugMode = false;
    

    
    public Ghost(int x, int y, PImage sprite, ArrayList<MapObjects> MapObjects, int speed, Waka waka, ArrayList<MapObjects> Intersections,ArrayList<Long> ModeTime, Long FrightenedModeTime, PImage GhostFrightImg ) {
        super(x, y, sprite, MapObjects, speed);
        this.waka = waka;
        this.Intersections = Intersections;
        this.ModeTime = ModeTime;
        this.FrightenedModeTime = FrightenedModeTime;
        this.GhostFrightImg = GhostFrightImg;
        this.GhostImg = sprite;

    }

    public ArrayList<MapObjects> getIntersections() {
        return this.Intersections;
    }

    public boolean getScaterMode() {
        return this.scatterMode;
    }

    public boolean getChaseMode() {
        return this.chaseMode;
    }

    public boolean getFrightenedMode() {
        return this.FrightenedMode;
    }
    
    public void setTimer() {
        this.timer++;
    }

    public void setWakaDistence() {
        this.distenceToWaka = culDistence(getX(), getY(), waka.getX(), waka.getY());
    }

    public static void sendKeyvalue(int keyAscii) {
        
        if (keyAscii == 32) {
            if (deBugMode) {
                deBugMode = false;
            } else {
                deBugMode = true;
            }
        }
    }

    public void tick() {
        
        if (getAlive()) {
            checkLocation();
            radar();
            PassIntersection();
            move();
            ModeChange();
            checkIfTouchWaka();
            //checkFrightenedMode();
            //System.out.println("aline listening");
            if (!getFrightenedMode()) {
                setTimer();
                
            }
        }

        
        //checkAlive();
        

    }
    
    
    public void draw(PApplet app) {
        app.image(getSprite(), getX() - getOffset(), getY() - getOffset());
        if (deBugMode && !getFrightenedMode()) {
            app.line(getX() + 8, getY() + 8, getTargetX() + 8, getTargetY() + 8);
            app.stroke(200);
        }
    }
    public boolean getDebug() {
        return deBugMode;
    }

    public void ModeChange() {
        FrightenedMode = waka.getFrightenedMode();
        checkFrightenedMode();
        if (timer/60 == this.ModeTime.get(ModeNumber)){
            if (ModeNumber % 2 == 0) {
                this.chaseMode = true;
                this.scatterMode = false;
                System.out.printf("ChaseMode mode on, Scatter off, time:%d\n",timer/60);
            } else {
                this.scatterMode = true;
                this.chaseMode = false;
                System.out.printf("Scatter mode on, chaseModeon off, time:%d\n",timer/60);
            }
            ModeNumber += 1;
            ModeNumber = ModeNumber % ModeTime.size();
            timer = 0;
        }
    }

    public PImage getGhostImage() {
        return this.GhostImg;
    }

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
                setSprit(null);
            }
        }
       
        //System.out.printf("%d %d %d %d\n",getOriginalX(),getOriginalY(),waka.getOriginalX(),waka.getOriginalY() );
    }
    
    public void checkAlive() {
        if (getAlive()) {
            setSprit(getSprite());
        } else {
            setSprit(null);
        }
    }

    public void checkFrightenedMode() {
        
        if (getFrightenedMode()) {
            this.setSprit(this.GhostFrightImg);
            
        } else {
            this.setSprit(GhostImg);
        }
        
        
    }

    public void resetFrightModeTimer() {
        this.FrightenedModeTimer = 0;
    }


    public int getFrightTimeer() {
        return this.FrightenedModeTimer;
    }

    public Long getFrightModeTime() {
        return this.FrightenedModeTime;
    }

    public void setFrightModeTimer() {
        this.FrightenedModeTimer++;
    }

    public void radar() {
        if (getTouchnode()) {
            
            setLeftmoveable(targetBlock(getX() - 16, getY()));
            setRightmoveable(targetBlock(getX() + 16, getY()));
            setUpmoveable(targetBlock(getX() , getY() - 16));            
            setDownmoveable(targetBlock(getX() , getY() + 16));
            //System.out.printf("%b %b %b %b\n", getLeftMoveable(), getRightMoveable(), getUpMoveable(), getDownMoveable());
            setTouchnode(false);
            goBack();
        }
    }

    public void goBack() {
        if (getLeftMoveable() == false && getRightMoveable() == false && getUpMoveable() == false){
            setDirect(0, 1);//go down
        } else if (getLeftMoveable() == false && getRightMoveable() == false && getDownMoveable() == false) {
            setDirect(0, -1);//go up
        } else if (getDownMoveable() == false && getRightMoveable() == false && getUpMoveable() == false) {
            setDirect(-1, 0);//go left
        } else if (getLeftMoveable() == false && getDownMoveable() == false && getUpMoveable() == false) {
            setDirect(1, 0);//go right
        }

    }

    public void checkLocation() {
        for(MapObjects m: getMapObjects()) {
            if (this.getX() == m.getX() && this.getY() == m.getY()){
                
                setTouchnode(true);
                this.setPriviousLocation(m.getX(), m.getY());

                if (!getFrightenedMode()) {

                    if (getScaterMode()) {
                        setTargetX(0);
                        setTargetY(0);
                    } else if (getChaseMode()) {
                        setTargetX(waka.getX());
                        setTargetY(waka.getY());
                    }
                }
                break;
                
            }
        }
    }
    public void PassIntersection() {
        for(MapObjects m: getIntersections()) {
            if (this.getX() == m.getX() && this.getY() == m.getY()){
                //ghost could not make a turn;
                //System.out.printf("%b %b %b %b\n",getLeftMoveable(), getRightMoveable(), getUpMoveable(),getDownMoveable());
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
                    // if (getScaterMode()) {
                    //     this.MakeATurn(x,y);
                    // } else if (getChaseMode()) {
                    //     //System.out.printf("%d %d %d %d\n", waka.getX(), waka.getX() + (waka.getDx()*(16*4)), waka.getY(), waka.getY() + (waka.getDy()*(16*4)));
                    //     this.MakeATurn(waka.getX(), waka.getY());
                    // }
                } else {
                    moveRandom();
                }
                //this.setPriviousLocation(m.getX(), m.getY());
            }
        }
    }

    
    public void moveRandom() {
        ArrayList<int[]> directList = new ArrayList<>();
        //System.out.printf("%b %b %b %b\n", getLeftMoveable(),getRightMoveable(),getUpMoveable(),getDownMoveable());
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
        //System.out.println((int)Math.random() * 3);
        int [] randomTarget = directList.get((int)(Math.random() * directList.size()));
        setDirect(randomTarget[0], randomTarget[1]);
        
        
        // if (randomTarget[0] == -1) {
        //     setTarget(getX() - 16, getY());
        // } else if (randomTarget[0] == 1) {
        //     setTarget(getX() + 16, getY());
        // } else if (randomTarget[1] == -1) {
        //     setTarget(getX() , getY() - 16);
        // } else if (randomTarget[1] == 1) {
        //     setTarget(getX() , getY() + 16);
        // }

    }


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

    public double culDistence(int ghostX, int ghostY, int targetX, int targetY ) {
        this.distence = Math.sqrt(Math.pow((double)ghostX - (double)targetX, 2.0) + Math.pow((double)ghostY - (double)targetY, 2.0));
        return distence;
    }




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
                    //System.out.printf("%f %f\n", temp[i], temp[j]);
                    //System.out.printf("%f %f %f %f %d\n", temp[0], temp[1], temp[2], temp[3],index);
                }
            }
        }

        // System.out.printf("%b %b %b %b\n", getLeftMoveable(), getRightMoveable(), getUpMoveable(), getDownMoveable());
        // //

        //proriority order up>left>down
        if (index == 1) {
            this.setDirect(-1, 0);
            //System.out.println("move left");
        } else if (index == 3) {
            this.setDirect(1, 0);
            //System.out.println("move right");
        } else if (index == 0) {
            this.setDirect(0, -1);
            //System.out.println("move up");
        } else if (index == 2) {
            this.setDirect(0, 1);
            //System.out.println("move down");
        }         
        
    }
}
