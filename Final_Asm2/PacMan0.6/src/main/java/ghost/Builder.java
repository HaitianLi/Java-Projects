package ghost;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PFont;

import org.json.simple.JSONArray;



public class Builder {
 
    private Long lives;
    private Long speed;
    private Long FrightenedModeTime;
    private int WIDTH;
    private int HEIGHT;
    private int keyCode;
    private int SecondCount;
    private String MapFileName;
    private Waka waka;
    private Ghost ghost;
    private PFont Press2;
    private boolean changeScreen = false;
    private boolean playerWin = false;

    private ArrayList<String> MapString;
    private ArrayList<Ghost> ghostList;
    private ArrayList<MapObjects> MapObjects;
    private ArrayList<MapObjects> Intersections;
    private ArrayList<Fruit> FruitsList;
    private ArrayList<Long> ModeTime = new ArrayList<Long>();
    private PImage FruitImg;
    // private PImage GhostImg;
    private PImage GhostAmbusherImg;
    private PImage GhostChaserImg;
    private PImage GhostIgnorantImg;
    private PImage GhostWhimImg;
    private PImage GhostFrightImg;
    private PImage MapHorizontalImg;
    private PImage MapVerticalImg;
    private PImage MapUpLeftImg;
    private PImage MapUpRightImg;
    private PImage MapDownLeftImg;
    private PImage MapDownRightImg;
    private PImage wakaLeft;
    private PImage wakaRight;
    private PImage wakaUp;
    private PImage wakaDown;
    private PImage wakaClose;
    private Chaser chaser;
    private ArrayList<GameObjects> GameObjects;
    private ArrayList<Ghost> GhostList;

    

    public Builder(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        MapString = new ArrayList<String>();
        ghostList = new ArrayList<Ghost>();
        MapObjects = new ArrayList<MapObjects>();
        Intersections = new ArrayList<MapObjects>();
        GameObjects = new ArrayList<GameObjects>();
        GhostList = new ArrayList<Ghost>();
        FruitsList = new ArrayList<Fruit>();
        

    }

    public boolean setFont(PFont f) {
        if (f == null) {
            return false;
        } else {
            this.Press2 = f;
            return true;
        }

    }

    public boolean setFruitImg(PImage i) {
        if (i == null) {
            return false;
        } else {
            this.FruitImg = i;
            return true;
        }
    }
    // public void setGhostImg(PImage i) {
    //     this.GhostImg = i;
    // }
    public boolean setGhostFrightImg(PImage i) {
        if (i == null) {
            return false;
        } else {
            this.GhostFrightImg = i;
            return true;
        }
    }

    public boolean setMapHorizontalImg(PImage i) {
        if (i == null) {
            return false;
        } else {
            this.MapHorizontalImg = i;
            return true;
        }
    }

    public boolean setMapVerticalImg(PImage i) {
        if (i == null) {
            return false;
        } else {
            this.MapVerticalImg = i;
            return true;
        }
    }

    public boolean setMapUpleftImg(PImage i) {
        if (i == null) {
            return false;
        } else {
            this.MapUpLeftImg = i;
            return true;
        }
    }

    public boolean setMapUprightImg(PImage i) {
        if (i == null) {
            return false;
        } else {
            this.MapUpRightImg = i;
            return true;
        }
    }

    public boolean setMapDownleftImg(PImage i) {
        if (i == null) {
            return false;
        } else {
            this.MapDownLeftImg = i;
            return true;
        }
    }

    public boolean setMapDownrightImg(PImage i) {
        if (i == null) {
            return false;
        } else {
            this.MapDownRightImg = i;
            return true;
        }
    }

    public boolean setWakaLeftImg(PImage i) {
        if (i == null) {
            return false;
        } else {
            this.wakaLeft = i;
            return true;
        }
    }

    public boolean setWakaRightImg(PImage i) {
        if (i == null) {
            return false;
        } else {
            this.wakaRight = i;
            return true;
        }
    }

    public boolean setWakaUpImg(PImage i) {
        if (i == null) {
            return false;
        } else {
            this.wakaUp = i;
            return true;
        }
    }

    public boolean setWakaDownImg(PImage i) {
        if (i == null) {
            return false;
        } else {
            this.wakaDown = i;
            return true;
        }
    }

    public boolean setWakaCloseImg(PImage i) {
        if (i == null) {
            return false;
        } else {
            this.wakaClose = i;
            return true;
        }
    }


    public boolean setGhostWhimImg(PImage i) {
        if (i == null) {
            return false;
        } else {
            this.GhostWhimImg = i;
            return true;
        }
    }

    public boolean setGhostAmbusherImg(PImage i) {
        if (i == null) {
            return false;
        } else {
            this.GhostAmbusherImg = i;
            return true;
        }
    }

    public boolean setGhostChaserImg(PImage i) {
        if (i == null) {
            return false;
        } else {
            this.GhostChaserImg = i;
            return true;
        }
    }

    public boolean setGhostIgnorantImg(PImage i) {
        if (i == null) {
            return false;
        } else {
            this.GhostIgnorantImg = i;
            return true;
        }
    }
    public void setWakaImg() {
        waka.setWakaCloseImg(this.wakaClose);
        waka.setWakaDownImg(this.wakaDown);
        waka.setWakaLeftImg(this.wakaLeft);
        waka.setWakaRightImg(this.wakaRight);
        waka.setWakaUpImg(this.wakaUp);
    }
    
    public void setUp() {
        readJson();
        readMap();
        this.buildWaka();
        this.setMapObjects();
        this.setIntersections();
        this.setGhostList();
        this.setWakaImg();
        System.out.println(this.MapObjects.size());
    }



    //draw part start

    public void drawWaka(PApplet app) {
        waka.draw(app);
    }


    public void drawGameObjects(PApplet app) {

        if (waka.getLives() != 0 && !changeScreen) {
            for (GameObjects obj : GameObjects) {
                if (obj.getSprite() != null) {
                    obj.draw(app);
                }
                obj.tick();
                
            }
    
            waka.tick();
            waka.draw(app);
            
            //reset the position of all ghost when waka hit a ghost
            int ghost_Notalive_count = 0;
            for (Ghost temp_ghost : GhostList) {
                if (!temp_ghost.getAlive()) {
                    ghost_Notalive_count++;
                }

            }

            if (ghost_Notalive_count == GhostList.size()) {
                changeScreen = true;
                playerWin = true;
            }

            boolean fruitCheck = false;
            
            for (Fruit f : FruitsList) {
                if (f.getAlive()) {
                    fruitCheck = true;
                }
            }
            
           
            if (!fruitCheck) {
                changeScreen = true;
                playerWin = true;
            }
            
            if (waka.getResetGame()) {
                for (Ghost temp_ghost : GhostList) {
                    //System.out.println("check list");
                    temp_ghost.setX(temp_ghost.getOriginalX());
                    temp_ghost.setY(temp_ghost.getOriginalY());
                    temp_ghost.setDirect(-1, 0);
                    temp_ghost.setSprit(temp_ghost.getGhostImage());
                    temp_ghost.setAlive(true);
                }
                waka.setResetGame(false);
            }
    
            //System.out.println(this.wakaRight.width);
            //show lives at the bottom of the map
            for (int i = 0; i < waka.getLives(); i++) {
                app.image(this.wakaRight, 8 + 26 * i, 576 -32);
            }

        } else {
            SecondCount++;
            String countDown = "";
            countDown += 10 - SecondCount/60;

            if (SecondCount / 60 == 10) {
                MapString.clear();
                ghostList.clear();
                MapObjects.clear();
                Intersections.clear();
                GameObjects.clear();
                GhostList.clear();
                FruitsList.clear();
                changeScreen = false;
                playerWin = false;
                setUp();
                SecondCount = 0;
            } 
            if (!playerWin) {
                //app.image(GameOverImg, 224 - GameOverImg.width / 2, 200);
                app.textFont(Press2);
                app.text("GAME OVER", 80, 176);
                
                app.text(countDown, 150, 350);
                //app.fill(0, 102, 153, 204);
            } else {
                app.textFont(Press2);
                app.text("YOU WIN", 112, 176);
                app.text(countDown, 160, 350);
            }
        }

    }
    //draw part end

    //tick part start
    // public void wakaTick(PApplet app) {
    //     //check null
    //     this.waka.tick();
    //     this.ambusher.tick();
    //     this.ignorant.tick();
    //     this.chaser.tick();
    //     //this.whim.tick();

    //     for (MapObjects f : MapObjects) {
    //         if (f.getClass() == Fruit.class) {
    //             f = (Fruit)f;
    //             f.tick();
    //         }

    //         if (f.getClass() == SuperFruit.class) {
    //             f = (SuperFruit)f;
    //             f.tick();
    //         }
    //     }

    //     //ghost.tick();
    //     //ghost.run(app);
    // }
    //tick part end

    public void getKeyValue(int keycode, int keyvalue) {
        this.keyCode = keycode;
        if (keyCode == 37) {
            waka.sendDirect(-1, 0);
        } else if (keyCode == 39) {
            waka.sendDirect(1, 0);
        } else if (keyCode == 38) {
            waka.sendDirect(0, -1);
        } else if (keyCode == 40) {
            waka.sendDirect(0, 1);
        }
        ghost.sendKeyvalue(keyvalue);
    }

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
                    Ghost temp_ghost = new Ambusher(j, i, this.GhostAmbusherImg, this.MapObjects, this.speed.intValue(), waka, this.Intersections, this.ModeTime, this.FrightenedModeTime, this.GhostFrightImg);
                    GameObjects.add(temp_ghost);
                    GhostList.add(temp_ghost);

                } else if (temp_char == 'i') {
                    Ghost temp_ghost = new Ignorant(j, i, this.GhostIgnorantImg, this.MapObjects, this.speed.intValue(), waka, this.Intersections, this.ModeTime, this.FrightenedModeTime, this.GhostFrightImg);
                    GameObjects.add(temp_ghost);
                    GhostList.add(temp_ghost);
                    
                } else if (temp_char == 'c') {
                    
                    this.chaser = new Chaser(j, i, this.GhostChaserImg, this.MapObjects, this.speed.intValue(), waka, this.Intersections, this.ModeTime, this.FrightenedModeTime, this.GhostFrightImg);

                    GameObjects.add(chaser);
                    GhostList.add(chaser);
                } else if (temp_char == 'w') {
                    if (this.chaser != null) {
                        Ghost temp_ghost = new Whim(j, i, this.GhostWhimImg, this.MapObjects, this.speed.intValue(), waka, this.Intersections, this.ModeTime, this.FrightenedModeTime, this.GhostFrightImg, this.chaser);

                        GameObjects.add(temp_ghost);
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
            Ghost temp_ghost = new Whim(whimX, whimY, this.GhostWhimImg, this.MapObjects, this.speed.intValue(), waka, this.Intersections, this.ModeTime, this.FrightenedModeTime, this.GhostFrightImg, this.chaser);

            GameObjects.add(temp_ghost);
            GhostList.add(temp_ghost);
        }
    }
    
    public void setIntersections() {
        for (MapObjects m : this.MapObjects) {
            if (m.getReachable()) {
                if (waka.targetBlock(m.getX() + 16, m.getY()) || waka.targetBlock(m.getX() - 16, m.getY())) {
                    if (waka.targetBlock(m.getX(), m.getY() + 16) || waka.targetBlock(m.getX(), m.getY() - 16)) {
                        Intersections.add(m);
                    }
                }
            }
        }
    }

    public void buildWaka() {

        //could replace by a function which return (x,y)
        for (int i = 0; i < this.HEIGHT; i += 16) {
            String Map_row = this.MapString.get(i / 16);
            for (int j = 0; j< this.WIDTH; j += 16) {
                char temp_char = Map_row.charAt(j / 16);
                if (temp_char == 'p') {
                    this.waka = new Waka(j, i, wakaLeft, MapObjects, speed.intValue(), lives.intValue(), FrightenedModeTime.intValue());
                    //GameObjects.add(new Waka(j, i, wakaLeft, MapObjects, speed.intValue(), lives.intValue()));

                }
            }
        }
    }

    public void setMapObjects() {
        for (int i = 0; i < this.HEIGHT; i += 16) {
            String Map_row = this.MapString.get(i / 16);
            for (int j = 0; j< this.WIDTH; j += 16) {
                char temp_char = Map_row.charAt(j / 16);
            
                if (temp_char == '1') {
                    
                    Wall temp = new Wall(j,i,this.MapHorizontalImg,false);
                    this.MapObjects.add(temp);
                    GameObjects.add(temp);
                } else if (temp_char == '2') {
                    
                    Wall temp = new Wall(j,i,this.MapVerticalImg,false);
                    this.MapObjects.add(temp);
                    GameObjects.add(temp);
                } else if (temp_char == '3') {
                 
                    Wall temp = new Wall(j,i,this.MapUpLeftImg,false);
                    this.MapObjects.add(temp);
                    GameObjects.add(temp);
                } else if (temp_char == '4') {
                    
                    Wall temp = new Wall(j,i,this.MapUpRightImg,false);
                    this.MapObjects.add(temp);
                    GameObjects.add(temp);
                } else if (temp_char == '5') {
                    
                    Wall temp = new Wall(j,i,this.MapDownLeftImg,false);
                    this.MapObjects.add(temp);
                    GameObjects.add(temp);
                } else if (temp_char == '6') {
                    Wall temp = new Wall(j,i,this.MapDownRightImg,false);
                    this.MapObjects.add(temp);
                    GameObjects.add(temp);
                } else if (temp_char == '7') {
                    Fruit temp = new Fruit(j, i, this.FruitImg, true, this.waka);
                    this.MapObjects.add(temp);
                    GameObjects.add(temp);
                    FruitsList.add(temp);
                } else if (temp_char == '8') {
                    SuperFruit temp = new SuperFruit(j, i, this.FruitImg, true, this.waka);
                    this.MapObjects.add(temp);
                    GameObjects.add(temp);
                    FruitsList.add(temp);
                } else if (temp_char != '0') {
                    this.MapObjects.add(new MapObjects(j, i, null, true));
                }
            }
        }
    }

    public void readJson() {
        
        JSONParser jsonparse = new JSONParser();
        try {
            FileReader reader = new FileReader("config.json");
            Object obj = jsonparse.parse(reader);
            JSONObject empjsonobj = (JSONObject)obj;
            //System.out.println((String)empjsonobj.get("map"));
            this.MapFileName = (String)empjsonobj.get("map");
            //System.out.println(MapFileName);
            this.lives = (Long)empjsonobj.get("lives");

            this.speed = (Long)empjsonobj.get("speed");

            this.FrightenedModeTime = (Long)empjsonobj.get("frightenedLength");

            JSONArray array1 = (JSONArray)empjsonobj.get("modeLengths");
            for (Object i : array1) {
                this.ModeTime.add((Long)i);
            }
            
        } catch(Exception e) {
            System.out.println("No such json file.");
        }
        
    }

    public void readMap() {
        
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
        }


        this.MapString = result;
    }

    
    // public ArrayList<int[]> locateXY(char ch) {
    //     ArrayList<int[]> temp = new ArrayList<int[]>();

        
    //     for (int i = 0; i < this.HEIGHT; i += 16) {
    //         String Map_row = this.MapString.get(i / 16);

    //         for (int j = 0; j< this.WIDTH; j += 16) {
    //             int [] result = new int[2];
    //             char temp_char = Map_row.charAt(j / 16);
    //             if (temp_char == ch) {
    //                 result[0] = j;
    //                 result[1] = i;
    //                 temp.add(result);
    //             }
    //         }
    //     }
    //     return temp;
    // }


    // //  7,
    //     20,
    //     7,
    //     20,
    //     5,
    //     20,
    //     5,
    //     1000
}