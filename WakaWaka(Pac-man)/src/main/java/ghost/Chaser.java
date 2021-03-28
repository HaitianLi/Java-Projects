package ghost;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PImage;

public class Chaser extends Ghost{
    
    /**
     * chaser constructor inherit from ghost.
     * <br>Creat Chaser instance with necessary variables.<br>
     * Great the Ghost with a special name to make whim easy to create later.<br>
     * The meansing of create this class is to ricognize the special ghost.
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

    public Chaser(int x, int y, PImage sprite, ArrayList<MapObjects> MapObjects, Waka waka, ArrayList<MapObjects> Intersections,ArrayList<Long> ModeTime,HashMap<String,Integer> GameOriData , HashMap<String,PImage> ImgMap) {
        super(x, y, sprite, MapObjects, waka, Intersections, ModeTime, GameOriData, ImgMap);
    }    
}