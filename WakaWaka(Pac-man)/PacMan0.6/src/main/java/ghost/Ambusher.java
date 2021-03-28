package ghost;
import java.util.ArrayList;
import processing.core.PImage;


public class Ambusher extends Ghost {
    private Waka waka;
    public Ambusher(int x, int y, PImage sprite, ArrayList<MapObjects> MapObjects, int speed, Waka waka, ArrayList<MapObjects> Intersections,ArrayList<Long> ModeTime, Long FrightenedModeTime, PImage GhostFrightImg ) {
        super(x, y, sprite, MapObjects, speed, waka, Intersections, ModeTime, FrightenedModeTime, GhostFrightImg);
        this.waka = waka;

    }


    // redo the logic, the pink one will stack

    
    public void checkLocation() {
        for(MapObjects m: getMapObjects()) {
            if (this.getX() == m.getX() && this.getY() == m.getY()){
                
                setTouchnode(true);
                this.setPriviousLocation(m.getX(), m.getY());

                if (!getFrightenedMode()) {

                    if (getScaterMode()) {
                        setTargetX(448);
                        setTargetY(0);
                    } else if (getChaseMode()) {
                        setTargetX(waka.getX() + (waka.getDx()*(16*4)));
                        setTargetY(waka.getY() + (waka.getDy()*(16*4)));
                    }
                }
                break;
                
            }
        }
    }

    // public void checkNode() {
    //     for (MapObjects m : getIntersections()) {
    //         if (this.getX() == m.getX() && this.getY() == m.getY()) {
    //             //System.out.println(11111111);
    //             //this.touchInter = true;
    //             PassIntersection();
                
    //             break;
    //         }
    //     }
    // }
    // public void DebugMode(PApplet app) {
    //     app.line(this.getX() + 8, this.getY() + 8, waka.getX() + 8, waka.getY() + 8);
    //     app.stroke(200);
    // }
    // public void PassIntersection(int x, int y) {
    //     if (true) {
    //         if (getDx() == -1) {
    //             setRightmoveable(false);
    //         } else if (getDx() == 1) {
    //             setLeftmoveable(false);
    //         } else if (getDy() == -1) {
    //             setDownmoveable(false);
    //         } else if (getDy() == 1) {
    //             setUpmoveable(false);
    //         }
    //         if (!getFrightenedMode()) {
    //             if (getScaterMode()) {
    //                 this.MakeATurn(x,y);
    //             } else if (getChaseMode()) {
    //                 //System.out.printf("%d %d %d %d\n", waka.getX(), waka.getX() + (waka.getDx()*(16*4)), waka.getY(), waka.getY() + (waka.getDy()*(16*4)));
    //                 // this.targetX = waka.getX() + (waka.getDx()*(16*4));
    //                 // this.targetY = waka.getY() + (waka.getDy()*(16*4));
    //                 // if (targetX < 0) {
    //                 //     targetX = 0;
    //                 // }
    //                 // if (targetY < 0) {
    //                 //     targetY = 0;
    //                 // }
                    
    //                 this.MakeATurn(getTargetX(), getTargetY());
    //                 //this.MakeATurn(waka.getX(), waka.getY());
    //             }
    //         } else {
    //             moveRandom();
    //         }
    //         this.touchInter = false;
    //     }
        // for(MapObjects m: getIntersections()) {
        //     if (this.getX() == m.getX() && this.getY() == m.getY()){
        //         //ghost could not make a turn;
        //         //System.out.printf("%b %b %b %b\n",getLeftMoveable(), getRightMoveable(), getUpMoveable(),getDownMoveable());
                
        //         break;
        //         //this.setPriviousLocation(m.getX(), m.getY());
        //     }
        // }
    //}
}