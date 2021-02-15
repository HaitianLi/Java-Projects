package ghost;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PFont;
import org.json.simple.JSONArray;



public class Builder {
 
    private int WIDTH;
    private int HEIGHT;
    private int keyCode;
    private String MapFileName;
    private Waka waka;
    private Chaser chaser;
    private PFont Press2;
    private boolean Setuped = false;

    private ArrayList<GameInterface> GameInterface;
    private ArrayList<String> MapString;
    private ArrayList<MapObjects> MapObjects;
    private ArrayList<MapObjects> Intersections;
    private ArrayList<Fruit> FruitsList;
    private ArrayList<Ghost> GhostList;
    private ArrayList<Long> ModeTime = new ArrayList<Long>();

    private HashMap<String, PImage> ImgMap;
    private HashMap<String,Integer> GameOriData;


    
    /**
     * Builder constructor, get WIDTH and HEIGHT from app.
     * <br>Initialize all the variable needed later <br>
     * @param WIDTH WIDTH length pass from app, window width
     * @param HEIGHT HEIGHT length pass from app, window height 
     */
    public Builder(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        GameInterface = new ArrayList<GameInterface>();
        MapString = new ArrayList<String>();
        MapObjects = new ArrayList<MapObjects>();
        Intersections = new ArrayList<MapObjects>();
        GhostList = new ArrayList<Ghost>();
        FruitsList = new ArrayList<Fruit>();
        ImgMap = new HashMap<String,PImage>();
        GameOriData = new  HashMap<String,Integer>();
    }

    /**
     * Load all image which will use in the game to the the HashMap ImgMap.
     * @param app Game window object app.
     */
    public void LoadMaterials(App app) {
        ImgMap.put("FruitImg", app.loadImage("src/main/resources/fruit.png"));
        ImgMap.put("MapHorizontalImg", app.loadImage("src/main/resources/horizontal.png"));
        ImgMap.put("MapVerticalImg", app.loadImage("src/main/resources/vertical.png"));
        ImgMap.put("MapUpLeftImg", app.loadImage("src/main/resources/upLeft.png"));
        ImgMap.put("MapUpRightImg", app.loadImage("src/main/resources/upRight.png"));
        ImgMap.put("MapDownLeftImg", app.loadImage("src/main/resources/downLeft.png"));
        ImgMap.put("MapDownRightImg", app.loadImage("src/main/resources/downRight.png"));
        ImgMap.put("wakaClose", app.loadImage("src/main/resources/playerClosed.png"));
        ImgMap.put("wakaLeft", app.loadImage("src/main/resources/playerLeft.png"));
        ImgMap.put("wakaRight", app.loadImage("src/main/resources/playerRight.png"));
        ImgMap.put("wakaUp", app.loadImage("src/main/resources/playerUp.png"));
        ImgMap.put("wakaDown", app.loadImage("src/main/resources/playerDown.png"));
        ImgMap.put("GhostAmbusherImg", app.loadImage("src/main/resources/ambusher.png"));
        ImgMap.put("GhostChaserImg", app.loadImage("src/main/resources/chaser.png"));
        ImgMap.put("GhostIgnorantImg", app.loadImage("src/main/resources/ignorant.png"));
        ImgMap.put("GhostWhimImg", app.loadImage("src/main/resources/whim.png"));
        ImgMap.put("GhostFrightImg", app.loadImage("src/main/resources/frightened.png"));
        ImgMap.put("GhostGhostImg", app.loadImage("src/main/resources/sodaGhost.png"));
        ImgMap.put("Soda", app.loadImage("src/main/resources/soda.png"));
        Press2 = app.createFont("src/main/resources/PressStart2P-Regular.ttf", 16);
    }

    /**
     * Return the Image hashmap.
     * <br>To check if any img not loaded<br>
     * @return Image hashmap
     */
    public HashMap<String, PImage> getImgMap() {
        return ImgMap;
    }

    /**
     * Build the GameScreen instance which controled what shows on screen.
     * <br>The class inplements Gameinterface.<br>
     * Add it to the GameInterface list.<br>
     */
    public void buildGameScreen() {
        GameInterface Screen = new GameScreen(this, waka, Press2, GameInterface);
        GameInterface.add(Screen);
    }
    

    /**
     * Builder's setup function, setup everythng before draw.
     * <br>Read the document first with readJson emthod.<br>
     * Than read the map file which read from json.<br>
     * Use the map file to initialize all objects(MapObjects, fruit, superfruit, soda,waka, ghosts).<br>
     * If also creat a Mapobjects list and intersection point list.<br>
     * Add the waka and Ghost after the Mapobject already in the GameInterface list.
     * Build the gameScreen instance.
     * The order is build waka then build MapObjects then build Intersection then build ghost then build others.
     * Each step depends on the privious step.
     */
    public void setUp() {
        readJson();
        readMap();
        this.buildWaka();
        this.setMapObjects();
        this.setIntersections();
        this.setGhostList();
        GameInterface.add(waka);
        this.buildGameScreen();
    }

    /**
     * This is the mean output method. Use the interface which every object have in ths project except build and app.<br>
     * <br>Draw all game objects on the screen.<br>
     * Update the objects status by the frameRate(60)<br>
     * @param app The game Windows instance.
     */
    public void runGameInterface(PApplet app) {
        if (!Setuped) {
            setUp();
            Setuped = true;
        }
        for (GameInterface obj : GameInterface) {
            obj.tick();
            obj.draw(app);
        }
    }


    /**
     * Reset the game, clear all objects in the list than call builder setUp to resetup everything.
     * <br>chaser is not in the list since the create of whim depends on the chaser exist.<br>
     */
    public void reSetGame() {
        //MapString.clear();
        MapObjects.clear();
        Intersections.clear();
        GameInterface.clear();
        GhostList.clear();
        FruitsList.clear();
        chaser = null;
        //setup will creat a new waka as will.
    }

    /**
     * Return waka instance.
     * @return the Waka instance
     */
    public Waka getWaka() {
        return waka;
    }


    /**
     * Get keycode from app, process the value, than pass to waka.
     * <br>(-1, 0, left), (1, 0, right),(0, -1, up) , (0, 1, down).<br>
     * Pass the value to the Ghost, to check if need use Debug Mode.<br>
     * @param keycode   keyCode from app, the value when user press keyboard(up,down,left,right).
     * @param keyvalue  keyValue from app, the value not up,down,left,right
     */
    public void getKeyValue(int keycode, int keyvalue) {
        this.keyCode = keycode;
        //System.out.println(keycode);
        if (keyCode == 37) {
            waka.sendDirect(-1, 0);
        } else if (keyCode == 39) {
            waka.sendDirect(1, 0);
        } else if (keyCode == 38) {
            waka.sendDirect(0, -1);
        } else if (keyCode == 40) {
            waka.sendDirect(0, 1);
        }
        Ghost.sendKeyvalue(keyvalue);
    }

    /**
     * Set up the Ghost instance for the game.
     * <br>Build different kind Ghost read from map file with their necessary variables at the original coordinate.<br>
     * Special logic for whim, the whim only created when chaser exist.<br>
     * This method should run after Mapobject and intersation already built.<br>
     * Add all ghosts to the Gameinterface list.<br>
     */
    public void setGhostList() {
        boolean CreatWhim = false;
        int whimX = 0;
        int whimY = 0;
        //could replace by a function which return (x,y)
        for (int i = 0; i < this.HEIGHT; i += 16) {
            String Map_row = this.MapString.get(i / 16);
            for (int j = 0; j< this.WIDTH; j += 16) {
                char temp_char = Map_row.charAt(j / 16);
                if (temp_char == 'a') {
                    Ghost temp_ghost = new Ambusher(j, i, ImgMap.get("GhostAmbusherImg"), this.MapObjects, waka, this.Intersections, this.ModeTime, GameOriData, ImgMap);
                    GameInterface.add(temp_ghost);
                    GhostList.add(temp_ghost);

                } else if (temp_char == 'i') {
                    Ghost temp_ghost = new Ignorant(j, i, ImgMap.get("GhostIgnorantImg"), this.MapObjects, waka, this.Intersections, this.ModeTime,GameOriData, ImgMap);
                    GameInterface.add(temp_ghost);
                    GhostList.add(temp_ghost);
                    
                } else if (temp_char == 'c') {
                    
                    this.chaser = new Chaser(j, i, ImgMap.get("GhostChaserImg"), this.MapObjects, waka, this.Intersections, this.ModeTime,GameOriData,ImgMap);

                    GameInterface.add(chaser);
                    GhostList.add(chaser);
                } else if (temp_char == 'w') {
                    if (this.chaser != null) {
                        Ghost temp_ghost = new Whim(j, i, ImgMap.get("GhostWhimImg"), this.MapObjects, waka, this.Intersections, this.ModeTime, GameOriData, ImgMap, this.chaser);
                        
                        GameInterface.add(temp_ghost);
                        GhostList.add(temp_ghost);
                    } else {
                        CreatWhim = true;
                        whimX = j;
                        whimY = i;
                    }
                }
            }
        }
        //there will always a chaser in the map;
        if (CreatWhim) {
            
            Ghost temp_ghost = new Whim(whimX, whimY, ImgMap.get("GhostWhimImg"), this.MapObjects, waka, this.Intersections, this.ModeTime, GameOriData, ImgMap, this.chaser);
            GameInterface.add(temp_ghost);
            GhostList.add(temp_ghost);
            CreatWhim = false;
        }
    }

    /**
     * Retuen a list which contain all fruit instance of the game.
     * @return A list of Fruits
     */
    public ArrayList<Fruit> getFruitList(){
        return this.FruitsList;
    }
    /**
     * Return a list which contain all Ghosts instance of the game.
     * @return A list of Ghosts.
     */
    public ArrayList<Ghost> getGhostList() {
        return this.GhostList;
    }
    
    /**
     * Set the list of node that the ghost should make a dicesion.
     * <br>When a node could make a turn in left or right, and it could make a turn in up or down, means it is a intersection.<br>
     * Return true if success, false if Map.object is null.<br>
     * This method should run after MapObjects already built.<br>
     * @return true if set intersections sucess, false if map.object is null.
     */
    public boolean setIntersections() {
        if (this.MapObjects == null) {
            return false;
        }
        for (MapObjects m : this.MapObjects) {
            if (m.getReachable()) {
                if (waka.targetBlock(m.getX() + 16, m.getY()) || waka.targetBlock(m.getX() - 16, m.getY())) {
                    if (waka.targetBlock(m.getX(), m.getY() + 16) || waka.targetBlock(m.getX(), m.getY() - 16)) {
                        Intersections.add(m);
                    }
                }
            }
        }
        return true;
    }

    /**
     * Read the map file and build the waka at original coordinate with necessary variables.
     * <br>This method should run after Mapobjects already built.<br>
     */
    public void buildWaka() {

        for (int i = 0; i < this.HEIGHT; i += 16) {
            String Map_row = this.MapString.get(i / 16);
            for (int j = 0; j< this.WIDTH; j += 16) {
                char temp_char = Map_row.charAt(j / 16);
                if (temp_char == 'p') {
                    this.waka = new Waka(j, i, ImgMap.get("wakaLeft"), MapObjects, this.GameOriData, ImgMap);
                }
            }
        }
    }

    /**
     * Read the mapfile and set all the game object which could not move in the gmae (fruit, superfruit, soda, MapObjects).
     * <br> The objects could not move should be created by this method and add to the MapObject list.<br>
     * <br> This method should run first in the setup method after read map.<br>
     */
    public void setMapObjects() {
        for (int i = 0; i < this.HEIGHT; i += 16) {
            String Map_row = this.MapString.get(i / 16);
            for (int j = 0; j< this.WIDTH; j += 16) {
                char temp_char = Map_row.charAt(j / 16);
            
                if (temp_char == '1') {
                    
                    MapObjects temp = new MapObjects(j,i,ImgMap.get("MapHorizontalImg"),false);
                    this.MapObjects.add(temp);
                    GameInterface.add(temp);
                } else if (temp_char == '2') {
                    
                    MapObjects temp = new MapObjects(j,i,ImgMap.get("MapVerticalImg"),false);
                    this.MapObjects.add(temp);
                    GameInterface.add(temp);
                } else if (temp_char == '3') {
                 
                    MapObjects temp = new MapObjects(j,i,ImgMap.get("MapUpLeftImg"),false);
                    this.MapObjects.add(temp);
                    GameInterface.add(temp);
                } else if (temp_char == '4') {
                    
                    MapObjects temp = new MapObjects(j,i,ImgMap.get("MapUpRightImg"),false);
                    this.MapObjects.add(temp);
                    GameInterface.add(temp);
                } else if (temp_char == '5') {
                    
                    MapObjects temp = new MapObjects(j,i,ImgMap.get("MapDownLeftImg"),false);
                    this.MapObjects.add(temp);
                    GameInterface.add(temp);
                } else if (temp_char == '6') {
                    MapObjects temp = new MapObjects(j,i,ImgMap.get("MapDownRightImg"),false);
                    this.MapObjects.add(temp);
                    GameInterface.add(temp);
                } else if (temp_char == '7') {
                    Fruit temp = new Fruit(j, i, ImgMap.get("FruitImg"), true, this.waka);
                    this.MapObjects.add(temp);
                    GameInterface.add(temp);
                    FruitsList.add(temp);
                } else if (temp_char == '8') {
                    SuperFruit temp = new SuperFruit(j, i, ImgMap.get("FruitImg"), true, this.waka);
                    this.MapObjects.add(temp);
                    GameInterface.add(temp);
                    FruitsList.add(temp);
                }  else if (temp_char == '9') {
                    SuperSoda temp = new SuperSoda(j, i, ImgMap.get("Soda"), true, this.waka);
                    this.MapObjects.add(temp);
                    GameInterface.add(temp);
                    FruitsList.add(temp);
                }  else if (temp_char != '0') {
                    MapObjects temp = new MapObjects(j, i, null, true);
                    temp.setAlive(false);
                    this.MapObjects.add(temp);
                }
            }
        }
    }

    /**
     * Read the json file get the game original data.
     * <br>Return true if success false if jsonfile not exist.<br>
     * Read all the data from json and set as hashmap.
     * This method should run first when build setup().<br>
     * @return True is success, false if json file not exist.
     */
    public boolean readJson() {
        
        JSONParser jsonparse = new JSONParser();
        try {
            FileReader reader = new FileReader("config.json");
            Object obj = jsonparse.parse(reader);
            JSONObject empjsonobj = (JSONObject)obj;
            this.MapFileName = (String)empjsonobj.get("map");
            

            Long temp = (Long)empjsonobj.get("lives");
            GameOriData.put("lives",temp.intValue());

            temp = (Long)empjsonobj.get("speed");
            GameOriData.put("speed",temp.intValue());

            temp = (Long)empjsonobj.get("frightenedLength");
            GameOriData.put("FrightenedModeTime",temp.intValue());

            temp = (Long)empjsonobj.get("soda-can");
            GameOriData.put("SodaTime",temp.intValue());
            
            
            JSONArray array1 = (JSONArray)empjsonobj.get("modeLengths");
            for (Object i : array1) {
                this.ModeTime.add((Long)i);
            }
            
        } catch(Exception e) {
            System.out.println("No such json file.");
            return false;
        }
        return true;
    }

    /**
     * Read mapfile which read from the json file.
     * <br>Set a map string which could been process by all other methods.<br>
     * Return true if success, false if mapfile not exist.<br>
     * This method should run just after readJson() called in setup().<br>
     * @return true if success, false if file not exist.
     */
    public boolean readMap() {
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            File fname = new File(this.MapFileName);
            Scanner scan = new Scanner(fname);
            while(scan.hasNextLine()){
                result.add(scan.nextLine());
            }
            scan.close();
        } catch(FileNotFoundException e) {
            System.out.println("map File not exist.");
            return false;
        }

        this.MapString = result;
        return true;
    }

    /**
     * Return the game original data read from json.
     * @return  Return hashmap which contain original data read from json.
     */
    public HashMap<String,Integer> getGameOriData() {
        return this.GameOriData;
    }

    /**
     * Return the list of intersections
     * @return Return the list of intersections
     */
    public ArrayList<MapObjects> getIntersections() {
        return Intersections;
    }

    /**
     * Return the list of mapobjects
     * @return Return the list of mapObjects
     */
    public ArrayList<MapObjects> getMapObjects() {
        return MapObjects;
    }
    
    /**
     * Return the chaser instance
     * @return Return the chaser instance.
     */
    public Chaser getChaser() {
        return chaser;
    }
    
    /**
     * Return the game interface list, which contain all game objects in, include gamescreen.
     * @return  return the game interface list.
     */
    public ArrayList<GameInterface> getGameInterface() {
        return GameInterface;
    }

    
}