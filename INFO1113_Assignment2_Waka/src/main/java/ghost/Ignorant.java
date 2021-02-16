package ghost;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PImage;
public class Ignorant extends Ghost{

    /**
     * Ignorant constructor inherit from ghost.
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
    public Ignorant(int x, int y, PImage sprite, ArrayList<MapObjects> MapObjects, Waka waka, ArrayList<MapObjects> Intersections,ArrayList<Long> ModeTime,HashMap<String,Integer> GameOriData , HashMap<String,PImage> ImgMap) {
        //super(x, y, sprite, MapObjects, waka, Intersections, ModeTime, SodaGhost, GameOriData);
        super(x, y, sprite, MapObjects, waka, Intersections, ModeTime, GameOriData, ImgMap);
    }

    /**
     * Override method, In scatter mode Ignorant target left cornor.
     * <br>If more than 8 units away from Waka (straight line distance) target location is Waka.<br>
     * Otherwise, target location is bottom left corner.<br>
     */
    public void checkLocation() {
        for(MapObjects m: getMapObjects()) {
            if (this.getX() == m.getX() && this.getY() == m.getY()){
            
                setTouchnode(true);
                this.setPriviousLocation(m.getX(), m.getY());

                if (!getFrightenedMode()) {

                    if (getScaterMode()) {
                        setTarget(0, 576);
                    } else if (getChaseMode()) {
                        if (culDistence(getX(), getY(), getWaka().getX(), getWaka().getY()) > 8 * 16){
                            setTarget(getWaka().getX(), getWaka().getY());
                        } else {
                            setTarget(0, 576);
                        }
                    }
                }
                break;
                
            }
        }
    }
}