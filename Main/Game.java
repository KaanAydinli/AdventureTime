package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import Collision.CollisionChecker;
import EntitySpawner.Spawner;
import Environment.Lighting;
import Objects.objects;
import TilesM.TileManager;
import entity.Entity;
import entity.Player;

public class Game extends JPanel implements Runnable{

    final int scale = 3;
    final int originaltileSize = 16;
    public final int tileSize = scale * originaltileSize;
    public final int screenWidth = 16 * tileSize;
    public final int screenHeight = 12 * tileSize;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;


    public int titleState = 0;
    public int playState = 1;
    public int deathState = 2;
    public int dialogueState= 3;
    public int inventoryState = 4;
    public int settingsState = 5;
    public int controlsState = 6;
    public int statsState = 7;
    public int tradeState = 8;
    public int sureState = 9;
    public int mapState = 10;
    public int gameState;

    public boolean screenChange = false;
    public int mapNumber = 1;

    public TileManager tileM = new TileManager(this);
    public KeyHandler kh = new KeyHandler(this);
    Thread gameThread;
    public Player player = new Player(this, kh);
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[10];
    public objects obj[] = new objects[50];
    
    public AssetSetter aSetter = new AssetSetter(this);
    public Spawner spawner = new Spawner(this);
    public UI ui = new UI(this);
    public Lighting light = new Lighting(this, tileSize * 10);
    public CollisionChecker cChecker = new CollisionChecker(this);
    

    public Game(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
        
        aSetter.setEverything();
        
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
        
    }
    
    @Override
    public void run() {
        
        while(gameThread != null){

            //1. Update
            update();

            //2. Draw
            repaint();

            try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}         
        }
    }
    public void update(){
        if(gameState == playState){

            if(gameState == deathState){

            }
            else{
                              
                for(int i = 0; i < npc.length; i++){
                    if(npc[i] != null && npc[i].mapNumberPlace == mapNumber){

                        if(npc[i].alive){
                            npc[i].update(); 
                        }
                        else 
                            npc[i] = null;                  
                    }
                    if(monster[i] != null && monster[i].mapNumberPlace == mapNumber){
                        if(monster[i].alive){
                            monster[i].update(); 
                        }
                        else 
                            monster[i] = null;
                                          
                    }
                    
                }   
                for(int k = 0; k < obj.length; k++){
                    if(obj[k] != null && obj[k].mapNumberPlace == mapNumber){
                        obj[k].update();
                    }
                }
                player.update(); 
            }
        }      
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        long start = System.nanoTime();


        if(gameState == titleState){

            //Titles
            ui.draw(g2);
        }
        else{     
            //Tiles
            tileM.draw(g2); 

          
            //NPCs
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null && npc[i].mapNumberPlace == mapNumber){
                    npc[i].draw(g2);
                }
                if(monster[i] != null && monster[i].mapNumberPlace == mapNumber){
                    monster[i].draw(g2);
                }
            }
            for(int k = 0; k < obj.length; k++){
                if(obj[k] != null && obj[k].mapNumberPlace == mapNumber){
                    obj[k].draw(g2);
                }
            }


            //Player
            player.draw(g2);
           

            if(mapNumber == 2){
                light.draw(g2);   
                            
            }
            
            spawner.generateZombie();
          
            //UI
            ui.draw(g2);
        }       
        long finish = System.nanoTime(); 


        //System.out.println(player.worldX / 48 + "      " + player.worldY/48);
        //System.out.println(player.collisionOn);
        //System.out.println(finish - start);
        g2.dispose();

    }  
}