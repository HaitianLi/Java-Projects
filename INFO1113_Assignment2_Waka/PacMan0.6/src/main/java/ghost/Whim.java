package ghost;

import java.util.ArrayList;
import processing.core.PImage;

public class Whim extends Ghost{
    private Chaser chaser;
    private Waka waka;
    public Whim(int x, int y, PImage sprite, ArrayList<MapObjects> MapObjects, int speed, Waka waka, ArrayList<MapObjects> Intersections,ArrayList<Long> ModeTime, Long FrightenedModeTime, PImage GhostFrightImg, Chaser chaser ) {
        super(x, y, sprite, MapObjects, speed, waka, Intersections, ModeTime, FrightenedModeTime, GhostFrightImg);
        this.chaser = chaser;
        this.waka = waka;

    }

        
    // public void draw(PApplet app) {
    //     app.image(getSprite(), getX() - getOffset(), getY() - getOffset());
    //     if (getDebug() && !getFrightenedMode()) {
    //         app.line(getX() + 8, getY() + 8, getTargetX() + 8, getTargetY() + 8);
    //         app.stroke(200);

    //         app.line(waka.getX() + (waka.getDx()*(16*2)) + 8, waka.getY() + (waka.getDy()*(16*2)) + 8, getTargetX() + 8, getTargetY() + 8);
    //         app.stroke(200);
    //     }
    // }



    public void checkLocation() {
        for(MapObjects m: getMapObjects()) {
            if (this.getX() == m.getX() && this.getY() == m.getY()){
                
                setTouchnode(true);
                this.setPriviousLocation(m.getX(), m.getY());

                if (!getFrightenedMode()) {

                    if (getScaterMode()) {
                        setTargetX(448);
                        setTargetY(576);
                    } else if (getChaseMode()) {
                        if (chaser == null) {
                            System.out.println(111);
                        }
                        int WakaPlusTwoX = waka.getX() + (waka.getDx()*(16*2));
                        int WakaPlusTwoY = waka.getY() + (waka.getDy()*(16*2));
                        int ChaserX = chaser.getX();
                        int ChaserY = chaser.getY();
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
                        
                        // if (numberX < 0) {
                        //     numberX = 0;
                        // } else if (numberX > 448) {
                        //     numberX = 448;
                        // }

                        // if (numberY < 0) {
                        //     numberY = 0;
                        // } else if (numberX > 576) {
                        //     numberY = 576;
                        // }

                        //System.out.printf("%d %d\n",numberX, numberY);

                        setTargetX(numberX);
                        setTargetY(numberY);
                        
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
    //                     //System.out.printf("%d %d %d %d\n", waka.getX(), waka.getX() + (waka.getDx()*(16*4)), waka.getY(), waka.getY() + (waka.getDy()*(16*4)));
    //                     int targetX = waka.getX() + (waka.getDx()*(16*2));
    //                     int targetY = waka.getY() + (waka.getDy()*(16*2));
    //                     // if (targetX < 0) {
    //                     //     targetX = 0;
    //                     // }
    //                     // if (targetY < 0) {
    //                     //     targetY = 0;
    //                     // }
    //                     int targetNodeX = 0;
    //                     int targetNodeY = 0;
    //                     double dis = culDistence(chaser.getX(), chaser.getY(), targetX, targetY);
    //                     System.out.println(dis);
    //                     for (MapObjects i : getMapObjects()) {
    //                         if (culDistence(i.getX(), i.getY(), targetX, targetY) == dis) {
    //                             System.out.printf("%d %d\n",i.getX(), i.getY());
    //                         }
    //                     }
                        
    //                     double chaserTowaka = culDistence(chaser.getX(), chaser.getY(), waka.getX(), waka.getY());
                        

    //                     this.MakeATurn(targetX, targetY);
    //                 }
    //             } else {
    //                 moveRandom();
    //             }
    //             //this.setPriviousLocation(m.getX(), m.getY());
    //         }
    //     }
    //}
}