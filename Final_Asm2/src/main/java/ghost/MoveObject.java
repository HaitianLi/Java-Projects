package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class MoveObject extends GameObjects  {

    private int offset = 5;
    private int dx = -1;
    private int dy = 0;
    private boolean up_moveable;
    private boolean down_moveable;
    private boolean left_moveable;
    private boolean right_moveable;
    private boolean touchNode = false;
    private int originalX;
    private int originalY;
    private int PriviousX;
    private int PriviousY;
    private int targetX;
    private int targetY;
    private ArrayList<MapObjects> MapObjects;
    private HashMap<String,Integer> GameOriData;
    private HashMap<String,PImage> ImgMap;
    
    /**
     * Abstract class MoveObject, constructor set for son classes.
     * @param x coordinate x.
     * @param y coordinate y.
     * @param sprite Image.
     * @param MapObjects A list conatin all mapObjects(),the objects could not move in the game.
     * @param GameOriData The game original Data read from json(i.e. speed).
     * @param ImgMap All images need to be used in this gmae.
     */
    public MoveObject(int x, int y, PImage sprite, ArrayList<MapObjects> MapObjects,HashMap<String,Integer> GameOriData, HashMap<String,PImage> ImgMap) {
        super(x, y, sprite);
        this.MapObjects = MapObjects;
        this.originalX = x;
        this.originalY = y;
        this.GameOriData = GameOriData;
        this.ImgMap = ImgMap;
    }

    /**
     * Return the ImgMap which contain all images used in this game.
     * @return return a hash map of images.
     */
    public HashMap<String,PImage> getImgMap() {
        return ImgMap;
    }

    /**
     * Return the speed.
     * @return Return the speed.
     */
    public int getSpeed(){
        return GameOriData.get("speed");
    }

    /**
     * Return the live numbers, special for waka.
     * @return return the lives.
     */
    public int getLives(){
        return GameOriData.get("lives");
    }

    /**
     * Set lives minus 1.
     * @return  return the updated lives value.
     */
    public int setLives(){
        return GameOriData.put("lives", getLives()-1);
    }

    /**
     * Return the time for Frighten mode
     * @return Return the time for Frighten mode.
     */
    public int getFrightenedLength(){
        return GameOriData.get("FrightenedModeTime");
    }

    /**
     * Return the time for Soda mode.
     * @return Return the time for soda mode.
     */
    public int getSodaTime(){
        return GameOriData.get("SodaTime");
    }

    /**
     * Return the direction of x asix.
     * @return Return the direction of x asix.
     */
    public int getDx() {
        return this.dx;
    }
    /**
     * Return the direction of y axis.
     * @return Return the direction of y axis.
     */
    public int getDy() {
        return this.dy;
    }

    /**
     * Return the original coordinate x, where it marked in map file.
     * @return Return the original coordinate x.
     */
    public int getOriginalX() {
        return this.originalX;
    }
    /**
     * Return the original coordinate y, where it marked in map file.
     * @return Return the original coordinate y.
     */
    public int getOriginalY() {
        return this.originalY;
    }

    /**
     * Check whether left is moveable.
     * @return true if left moveable.
     */
    public boolean getLeftMoveable() {
        return this.left_moveable;
    }

    /**
     * Check whether right is moveable.
     * @return true if right moveable.
     */
    public boolean getRightMoveable() {
        return this.right_moveable;
    }

    /**
     * Check whether up is moveable.
     * @return true if up moveable.
     */
    public boolean getUpMoveable() {
        return this.up_moveable;
    }

    /**
     * Check whether down is moveable.
     * @return true if down moveable.
     */
    public boolean getDownMoveable() {
        return this.down_moveable;
    }

    /**
     * Return the privious node coodinate x.
     * @return return the privious node coodinate x.
     */
    public int getPriX() {
        return this.PriviousX;
    }

    /**
     * Return the privious node coodinate y.
     * @return Return the privious node coodinate y.
     */
    public int getPriY() {
        return this.PriviousY;
    }

    /**
     * Return the offset for moveable objects, normally 8.
     * @return Return the offset for moveable objects, 8.
     */
    public int getOffset() {
        return this.offset;
    }
    
    /**
     * Return the coordinate x of targer point.
     * @return Return the coordinate x of targer post.
     */
    public int getTargetX() {
        return this.targetX;
    }

    /**
     * Return the coordinate y of targer point.
     * @return Return the coordinate y of targer post.
     */
    public int getTargetY() {
        return this.targetY;
    }

    /**
     * Check whether the MOveable object just pass a node which is the point could be devided by 16.
     * @return Return true if just touch a node.
     */
    public boolean getTouchnode() {
        return this.touchNode;
    }

    /**
     * Return the maoObjects which passed from the builder, contain all mapobjects.
     * @return the list of mapObjects(unmoveable objects).
     */
    public ArrayList<MapObjects> getMapObjects() {
        return this.MapObjects;
    }

    /**
     * Set the privious node coordinate.
     * <br>When pass a node, set it to the privious node.
     * @param x passed node coordinate x
     * @param y passed node coordinate y
     */
    public void setPriviousLocation(int x, int y) {
        this.PriviousX = x;
        this.PriviousY = y;
    }

    /**
     * Set the leftmoveable with the parameter i.Used in radar.
     * @param i boolean value, true if moveable.
     */
    public void setLeftmoveable(boolean i) {
        this.left_moveable = i;
    }

    /**
     * Set the Rigntmoveable with the parameter i.Used in radar.
     * @param i boolean value, true if moveable.
     */
    public void setRightmoveable(boolean i) {
        this.right_moveable = i;
    }

    /**
     * Set the Upmoveable with the parameter i.Used in radar.
     * @param i boolean value, true if moveable.
     */
    public void setUpmoveable(boolean i) {
        this.up_moveable = i;
    }

    /**
     * Set the Downmoveable with the parameter i.Used in radar.
     * @param i boolean value, true if moveable.
     */
    public void setDownmoveable(boolean i) {
        this.down_moveable = i;
    }

    
    /**
     * Set the target coordinate x, y with parameter i.
     * <br>If its out of the bound of the window, set it in.<br>
     * @param i target coordinate x.
     * @param j target coordinate y.
     */
    public void setTarget(int i, int j) {
        if (i < 0) {
            i = 0;
        } else if (i > 448){
            i = 448;
        }
        
        if (j < 0) {
            j = 0;
        } else if (j > 576) {
            j = 576;
        }
        this.targetX = i;
        this.targetY = j;

    }

    /**
     * Set the direction with the parameter.
     * <br>Direction left(-1, 0), right(1, 0), up(0, -1), down(0, 1).<br>
     * @param i x direction
     * @param j y direction
     */
    public void setDirect(int i, int j) {
        this.dx = i;
        this.dy = j;
    }

    /**
     * Set the boolean value if the move objects touch a node.
     * @param b true if touched, false not.
     */
    public void setTouchnode(boolean b) {
        this.touchNode = b;
    }

    /**
     * When the moveable object touch a node, set the touchNode to true, set the privious node.
     */
    public void checkLocation() {
        for(MapObjects m: MapObjects) {
            if (this.getX() == m.getX() && this.getY() == m.getY()){
                
                this.touchNode = true;
                this.setPriviousLocation(m.getX(), m.getY());
                break;
                
            }
        }
    }

    /**
     * Check whether the targetBlock reachable.
     * @param x targetBlock coordinate x.
     * @param y targerBlock coordinate y.
     * @return return the boolean value true if moveable, false if not.
     */
    public boolean targetBlock(int x, int y) {
        boolean result = false;
        for (MapObjects m : MapObjects) {
            if (m.getX() == x && m.getY() == y) {
                result = m.getReachable();
                break;
            }
        }
        return result;
    }
    
    /**
     * The draw method, draw if the object alive.
     */
    public void draw(PApplet app) {
        if (getAlive()) {
            app.image(this.getSprite(), this.getX() - offset, this.getY() - offset);
        }
    }

    /**
     * The abstrac method for son class.
     */
    public abstract void move();

}