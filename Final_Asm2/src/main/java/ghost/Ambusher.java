package ghost;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PImage;


public class Ambusher extends Ghost {
    
    /**
     * Ambusher constructor inherit from ghost.
     * <br>Creat Ambusher instance with necessary variables.<br>
     * @param x coordinate x.
     * @param y coordinate y.
     * @param sprite Ghost image.
     * @param MapObjects A list conatin all mapObjects(), the objects could not move in the game.
     * @param waka The waka
     * @param Intersections A list of intersections node where ghosts could make a ture.
     * @param ModeTime The different mode time read from json file.
     * @param GameOriData The game original Data read from json(i.e. speed).
     * @param ImgMap All images need to be used in this gmae.
     */
    public Ambusher(int x, int y, PImage sprite, ArrayList<MapObjects> MapObjects, Waka waka, ArrayList<MapObjects> Intersections,ArrayList<Long> ModeTime,HashMap<String,Integer> GameOriData , HashMap<String,PImage> ImgMap) {

        super(x, y, sprite, MapObjects, waka, Intersections, ModeTime, GameOriData, ImgMap);
    }



    /**
     * This method override the super class method checklocations.
     * <br>Ambusher target the right cornor when in scatter mode.<br>
     * Target four grid spaces ahead of Waka in chase mode.<br>
     */
    public void checkLocation() {
        for(MapObjects m: getMapObjects()) {
            if (this.getX() == m.getX() && this.getY() == m.getY()){
                
                //set touchnode boolean let the radar work.
                setTouchnode(true);
                this.setPriviousLocation(m.getX(), m.getY());

                if (!getFrightenedMode()) {

                    if (getScaterMode()) {
                        setTarget(448, 0);
                    } else if (getChaseMode()) {
                        setTarget(getWaka().getX() + (getWaka().getDx()*(16*4)), getWaka().getY() + (getWaka().getDy()*(16*4)));
                    }
                }
                break;
            }
        }
    }
}