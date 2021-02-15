package ghost;

import java.util.ArrayList;
import processing.core.PImage;
public class Ignorant extends Ghost{
    private Waka waka;
    public Ignorant(int x, int y, PImage sprite, ArrayList<MapObjects> MapObjects, int speed, Waka waka, ArrayList<MapObjects> Intersections,ArrayList<Long> ModeTime, Long FrightenedModeTime, PImage GhostFrightImg ) {
        super(x, y, sprite, MapObjects, speed, waka, Intersections, ModeTime, FrightenedModeTime, GhostFrightImg);
        this.waka = waka;

    }



    // public void checkNode() {
    //     for (MapObjects m : getIntersections()) {
    //         if (this.getX() == m.getX() && this.getY() == m.getY()) {
    //             //System.out.println(11111111);
    //             //this.touchInter = true;
    //             System.out.printf("%b %b %b %b\n", getLeftMoveable(),getRightMoveable(),getUpMoveable(),getDownMoveable());
    //             PassIntersection();
                
    //             break;
    //         }
    //     }
    // }

    public void checkLocation() {
        for(MapObjects m: getMapObjects()) {
            if (this.getX() == m.getX() && this.getY() == m.getY()){
                //System.out.printf("%b %b %b %b\n",getLeftMoveable(), getRightMoveable(), getUpMoveable(),getDownMoveable());
                // System.out.printf("%d %d\n", getD)
                setTouchnode(true);
                this.setPriviousLocation(m.getX(), m.getY());

                if (!getFrightenedMode()) {

                    if (getScaterMode()) {
                        setTargetX(0);
                        setTargetY(576);
                    } else if (getChaseMode()) {
                        if (culDistence(getX(), getY(), waka.getX(), waka.getY()) > 8 * 16){
                            setTargetX(waka.getX());
                            setTargetY(waka.getY());
                        } else {
                           setTargetX(0);
                           setTargetY(576);
                        }
                    }
                }
                break;
                
            }
        }
    }

    // public void PassIntersection(int x, int y) {
    //     for(MapObjects m: getIntersections()) {
    //         if (this.getX() == m.getX() && this.getY() == m.getY()){
    //             //ghost could not make a turn;
    //             //System.out.printf("%b %b %b %b\n",getLeftMoveable(), getRightMoveable(), getUpMoveable(),getDownMoveable());
    //             if (getDx() == -1) {
    //                 setRightmoveable(false);
    //             } else if (getDx() == 1) {
    //                 setLeftmoveable(false);
    //             } else if (getDy() == -1) {
    //                 setDownmoveable(false);
    //             } else if (getDy() == 1) {
    //                 setUpmoveable(false);
    //             }
    //             if (!getFrightenedMode()) {
    //                 if (getScaterMode()) {
    //                     this.MakeATurn(x,y);
    //                 } else if (getChaseMode()) {
    //                     //public double culDistence(int ghostX, int ghostY, int targetX, int targetY )
    //                     // 8 unit
    //                     if (culDistence(getX(), getY(), waka.getX(), waka.getY()) > 8 * 16){
    //                         this.MakeATurn(waka.getX(), waka.getY());
    //                     } else {
    //                         this.MakeATurn(x,y);
    //                     }
    //                 }
    //             } else {
    //                 moveRandom();
    //             }
    //             //this.setPriviousLocation(m.getX(), m.getY());
    //         }
    //     }
    // }
}