package TilesM;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import Main.Game;
import Main.Tool;

public class TileManager {
    
    Game gp;
    public Tile[] tile;
    public int mapTileNum[][];
    public int mapTileNum2[][];
    public int currentMapSize;

    public TileManager(Game gp){
        this.gp = gp;

        tile = new Tile[20];
        mapTileNum = new int[100][100];
        mapTileNum2 = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap();
        loadMap2();
    }
    public void getTileImage(){

        try {

            setup(0,"/tiles/0grass", false,"grass");
            setup(1,"/tiles/1water", true,"water");
            setup(2,"/tiles/cavewall",true,"cavewall");
            setup(3,"/tiles/3wood", false,"wood");
            setup(4,"/tiles/4tree", true,"tree");
            setup(5,"/tiles/5log", true,"log");
            setup(6,"/tiles/6darkness", false,"darkness");
            setup(7,"/tiles/7dirt", false,"dirt");
            setup(8,"/tiles/8coarsedirt", false,"coarsedirt");
            setup(9,"/tiles/cave1", false,"cave1");
            setup(10,"/tiles/cave2", false,"cave2");
            setup(11,"/tiles/cave3", false,"cave3");
            setup(12,"/tiles/cave4", false,"cave4");
            setup(13,"/tiles/cave5", false,"cave5");
            setup(14,"/tiles/cave6", false,"cave6");
            setup(15,"/tiles/9campfire", true,"campfire");
            setup(16,"/tiles/doorclosed", true,"doorclosed");
            setup(17,"/tiles/2wall", true,"wall");
            setup(18,"/tiles/fence", true,"fence");
            setup(19,"/tiles/wavewater", true,"wavewater");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setup(int index,String imagePath , boolean collision,  String name){

        Tool tool = new Tool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            tile[index].image = tool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
            tile[index].name = name;
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void loadMap(){
        try {
            
                InputStream is = getClass().getResourceAsStream("/TilesM/newMap01.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                
                int col = 0;
                int row = 0;

                while(col < 100 && row < 100){

                    String line = br.readLine();

                    while(col < 100){
                        String numbers[] = line.split(" ");

                        int num = Integer.parseInt(numbers[col]);

                        mapTileNum[col][row] = num;
                        col++;
                    }
                    if(col == 100){
                        col = 0;
                        row++;
                    }
                }
                br.close();
            
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    public void loadMap2(){
            try { 
                InputStream is2 = getClass().getResourceAsStream("/TilesM/cave01.txt");
                BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
            
                int col2 = 0;
                int row2 = 0;

                while(col2 < gp.maxWorldCol && row2 < gp.maxWorldRow){

                    String line = br2.readLine();

                    while(col2 < gp.maxWorldCol){
                        String numbers2[] = line.split(" ");

                        int num2 = Integer.parseInt(numbers2[col2]);

                        mapTileNum2[col2][row2] = num2;
                        col2++;
                    }
                    if(col2 == gp.maxWorldCol){
                        col2 = 0;
                        row2++;
                    }
                }
                br2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }   
    }
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;
        int tileNum;
        if(gp.mapNumber == 2){
            currentMapSize = 50;
        }
        else 
            currentMapSize = 100;

        while(worldCol < currentMapSize && worldRow < currentMapSize){

            if(gp.mapNumber == 2){             
                tileNum = mapTileNum2[worldCol][worldRow];
            }
            else 
                tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX  + gp.tileSize> gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + screenX 
            && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image,screenX,screenY,null);
    
            }
            
           worldCol++;

            if(worldCol == currentMapSize){
                                
                worldCol = 0;
                worldRow++;
            }        
        }
    }
}
