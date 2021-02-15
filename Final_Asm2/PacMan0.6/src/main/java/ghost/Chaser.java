package ghost;
import java.util.ArrayList;
import processing.core.PImage;

public class Chaser extends Ghost{
    
    public Chaser(int x, int y, PImage sprite, ArrayList<MapObjects> MapObjects, int speed, Waka waka, ArrayList<MapObjects> Intersections,ArrayList<Long> ModeTime, Long FrightenedModeTime, PImage GhostFrightImg ) {
        super(x, y, sprite, MapObjects, speed, waka, Intersections, ModeTime, FrightenedModeTime, GhostFrightImg);

    }

    
}