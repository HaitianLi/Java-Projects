package ghost;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PImage;

public class Whim extends Ghost{
    private Chaser chaser;
    /**
     * Whim constructor inherit from ghost.
     * <br>Creat Whim instance with necessary variables.<br>
     * @param x coordinate x.
     * @param y coordinate y.
     * @param sprite Ghost image.
     * @param MapObjects A list conatin all mapObjects(),the objects could not move in the game.
     * @param waka The waka
     * @param Intersections A list of intersections node where ghosts could make a ture.
     * @param ModeTime The different mode time read from json file.
     * @param GameOriData The game original Data read from json(i.e. speed).
     * @param ImgMap All images need to be used in this gmae.
     * @param chaser The chaset instance, whim target depend on chaser.
     */
    public Whim(int x, int y, PImage sprite, ArrayList<MapObjects> MapObjects, Waka waka, ArrayList<MapObjects> Intersections,ArrayList<Long> ModeTime,HashMap<String,Integer> GameOriData , HashMap<String,PImage> ImgMap, Chaser chaser) {
        //super(x, y, sprite, MapObjects, waka, Intersections, ModeTime, SodaGhost, GameOriData);
        super(x, y, sprite, MapObjects, waka, Intersections, ModeTime, GameOriData, ImgMap);
        this.chaser = chaser;
    }

    /**
     * Double the vector from Chaser to 2 grid spaces ahead of Waka.
     */
    public void checkLocation() {
        for(MapObjects m: getMapObjects()) {
            if (this.getX() == m.getX() && this.getY() == m.getY()){
                
                setTouchnode(true);
                this.setPriviousLocation(m.getX(), m.getY());

                if (!getFrightenedMode()) {
                    if (chaser.getAlive()) {

                        if (getScaterMode()) {
                            setTarget(448, 576);
                        } else if (getChaseMode()) {
                
                            int WakaPlusTwoX = getWaka().getX() + (getWaka().getDx()*(16*2));
                            int WakaPlusTwoY = getWaka().getY() + (getWaka().getDy()*(16*2));
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
                            
                            setTarget(numberX, numberY);
                        }
                    } else {
                        setTarget(448, 576);
                    }
                }
                break;
                
            }
        }
    }
}