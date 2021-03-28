package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public abstract class MoveObject extends GameObjects  {

    private int offset = 5;
    private int dx = -1;
    private int dy = 0;
    private int speed;
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


    public MoveObject(int x, int y, PImage sprite, ArrayList<MapObjects> MapObjects, int speed) {
        super(x, y, sprite);
        this.MapObjects = MapObjects;
        this.speed = speed;
        this.originalX = x;
        this.originalY = y;
    }

    public int getDx() {
        return this.dx;
    }

    public int getDy() {
        return this.dy;
    }

    public int getOriginalX() {
        return this.originalX;
    }

    public int getOriginalY() {
        return this.originalY;
    }

    public boolean getLeftMoveable() {
        return this.left_moveable;
    }

    public boolean getRightMoveable() {
        return this.right_moveable;
    }

    public boolean getUpMoveable() {
        return this.up_moveable;
    }

    public boolean getDownMoveable() {
        return this.down_moveable;
    }

    public int getPriX() {
        return this.PriviousX;
    }

    public int getPriY() {
        return this.PriviousY;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getOffset() {
        return this.offset;
    }
    
    public int getTargetX() {
        return this.targetX;
    }

    public int getTargetY() {
        return this.targetY;
    }

    public boolean getTouchnode() {
        return this.touchNode;
    }

    public ArrayList<MapObjects> getMapObjects() {
        return this.MapObjects;
    }

    public void setMapObjects(ArrayList<MapObjects> map) {
        this.MapObjects = map;
    } 

    public void setPriviousLocation(int x, int y) {
        this.PriviousX = x;
        this.PriviousY = y;
    }

    public void setLeftmoveable(boolean i) {
        this.left_moveable = i;
    }

    public void setRightmoveable(boolean i) {
        this.right_moveable = i;
    }

    public void setUpmoveable(boolean i) {
        this.up_moveable = i;
    }

    public void setDownmoveable(boolean i) {
        this.down_moveable = i;
    }

    public void setTargetX(int i) {
        if (i < 0) {
            i = 0;
        }
        this.targetX = i;
    }

    public void setTargetY(int i) {
        if (i < 0) {
            i = 0;
        }
        this.targetY = i;
    }

    public void setTarget(int i, int j) {
        if (i < 0) {
            i = 0;
        }
        
        if (j < 0) {
            j = 0;
        }
        this.targetX = i;
        this.targetY = i;
        

    }

    public void setDirect(int i, int j) {
        this.dx = i;
        this.dy = j;
    }

    
    public void setTouchnode(boolean b) {
        this.touchNode = b;
    }

    public void checkLocation() {
        for(MapObjects m: MapObjects) {
            if (this.getX() == m.getX() && this.getY() == m.getY()){
                
                this.touchNode = true;
                this.setPriviousLocation(m.getX(), m.getY());
                break;
                
            }
        }
    }


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
    
    public void draw(PApplet app) {
        app.image(this.getSprite(), this.getX() - offset, this.getY() - offset);
    }

    public abstract void move();

}