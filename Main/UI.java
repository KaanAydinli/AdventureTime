package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import entity.Entity;


public class UI {
    Game gp;
    Graphics2D g2;
    public int choice = 0;
    public int choiceSettings = 0;
    public int darkColor = 0;
    public int inventoryX, inventoryY;
    public int tradeBuyX, tradeBuyY;
    public int tradeSellX, tradeSellY;
    public int sureX, sureY;
    public int subStateY;
    public int time = 4096;
    public boolean buying = false;
    public boolean selling = false;
    public boolean subTrade = true;
    public int buy = 1;
    public int sell = 2;
    public int current;
    boolean goBack = false;
    
    public String currentDiyalog = " ";

    public UI(Game gp){
        this.gp = gp;
        
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;

        if(gp.gameState == gp.playState){
            drawHealth();
            drawCoin();
            drawHand();
            if(gp.screenChange){
                screenDarkening();
            }
        }
        if(gp.gameState == gp.dialogueState){
            drawDialogue();
            
        }
        if(gp.titleState == gp.gameState){
            drawTitle();
        }
        if(gp.deathState == gp.gameState){
            drawDeathScreen();
        }
        if(gp.inventoryState == gp.gameState){
            drawInventory(gp.player,gp.tileSize * 5,gp.tileSize * 2,gp.tileSize * 6,gp.tileSize*4,false);
        }
        if(gp.gameState == gp.settingsState){
            drawSettings();
        }
        if(gp.gameState == gp.controlsState){
            drawControls();
        }
        if(gp.gameState == gp.statsState){    
        
           drawStats();
        }
        if(gp.gameState == gp.tradeState){
            drawCoin();

            if(subTrade){
                drawSubTrade();
            }
            else{

                if(current == buy){
                    drawBuy();
                }
                else if(current == sell){
                    drawSell();
                }
            }
            
            
        }
        if(gp.gameState == gp.sureState){
            drawCoin();
            drawSure();
        }
        if(gp.gameState == gp.mapState){
            drawMap();
        }
        drawDayNight();
    }
    public void drawMap() {
        int worldCol = 0;
        int worldRow = 0;
        int tileNum;
        g2.setColor(new Color(0,0,0,190));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        if(gp.mapNumber == 2){
            gp.tileM.currentMapSize = 50;
        }
        else 
            gp.tileM.currentMapSize = 100;

        while(worldCol < gp.tileM.currentMapSize && worldRow < gp.tileM.currentMapSize){

            if(gp.mapNumber == 2){             
                tileNum = gp.tileM.mapTileNum2[worldCol][worldRow];
            }
            else 
                tileNum = gp.tileM.mapTileNum[worldCol][worldRow];

            int worldX = worldCol * 3 + gp.tileSize * 5;
            int worldY = worldRow * 3 + gp.tileSize * 3;
            
                g2.drawImage(gp.tileM.tile[tileNum].image,worldX,worldY,3,3,null);
    
            
            
           worldCol++;

            if(worldCol == gp.tileM.currentMapSize){
                                
                worldCol = 0;
                worldRow++;
            }        
        }
    }
    public void drawSubTrade(){
        g2.setColor(new Color(0,0,0,210));
        g2.fillRoundRect(gp.tileSize * 7, gp.tileSize * 5, gp.tileSize * 2, gp.tileSize * 3, 10, 10);
        g2.setColor(Color.white);
        g2.setFont(new Font("Segoe UI",Font.BOLD,16));
        g2.drawString("Buy", gp.tileSize * 7 + gp.tileSize /2, gp.tileSize * 5 + gp.tileSize /2);
        g2.drawString("Sell", gp.tileSize * 7+ gp.tileSize /2, gp.tileSize * 6 + gp.tileSize /2);
        g2.drawString("Leave", gp.tileSize * 7+ gp.tileSize /2, gp.tileSize * 7 + gp.tileSize /2);
        g2.drawRoundRect(gp.tileSize * 7 ,gp.tileSize * 5 + (gp.tileSize * subStateY),gp.tileSize * 2, 4 * gp.tileSize / 6,10,10);

    }
    public void drawSell(){
        drawInventory(gp.player,gp.tileSize * 5,gp.tileSize * 2,gp.tileSize * 6,gp.tileSize*4 , true);
        g2.setColor(new Color(0,0,0,190));
        g2.fillRoundRect(gp.tileSize * 5,gp.tileSize ,gp.tileSize * 3 / 2,gp.tileSize * 2 / 3,10,10);
        g2.setFont(new Font("Segoe UI",Font.BOLD,12));
        g2.setColor(Color.white);
        if(gp.player.inventory[tradeSellY][tradeSellX] != null){
            g2.drawString("Price: " + gp.player.inventory[tradeSellY][tradeSellX].price,gp.tileSize * 5 + gp.tileSize / 6, gp.tileSize + gp.tileSize / 6);
        }
        
        
    }
    public void drawSure(){
        if(current == buy){
            if(gp.npc[0].inventory[tradeBuyY][tradeBuyX] != null){
                if(buying == true){
                    if(gp.player.totalCoin > gp.npc[0].inventory[tradeBuyY][tradeBuyX].price){
                        gp.player.totalCoin -= gp.npc[0].inventory[tradeBuyY][tradeBuyX].price;
                        gp.player.putObjectsToInventory(gp.npc[0].inventory[tradeBuyY][tradeBuyX]);
                        if(gp.npc[0].inventory[tradeBuyY][tradeBuyX].totalAmount == 0){
                            gp.npc[0].inventory[tradeBuyY][tradeBuyX].totalAmount++;
                        }
                        gp.gameState = gp.tradeState;
                    }
                    else {
                        g2.setColor(new Color(30,30,30,240));
                        g2.fillRoundRect(gp.tileSize * 6,gp.tileSize * 5,gp.tileSize * 4,gp.tileSize * 2,10,10);
                        g2.setFont(new Font("Segoe UI",Font.BOLD,8));
                        g2.setColor(Color.white);
                        g2.drawString("You dont have enough money for this item" , gp.tileSize * 6 + gp.tileSize / 2, gp.tileSize * 6 );
                        
                    }
                    buying = false;
                }
                else{
                    g2.setColor(new Color(30,30,30,240));
                    g2.fillRoundRect(gp.tileSize * 6,gp.tileSize * 5,gp.tileSize * 4,gp.tileSize * 2,10,10);
    
                    g2.setColor(Color.white);
                    g2.setFont(new Font("Segoe UI",Font.BOLD,12));
                    g2.drawString("Do you want to buy " + gp.npc[0].inventory[tradeBuyY][tradeBuyX].name , gp.tileSize * 6 + gp.tileSize / 2, gp.tileSize * 5 + gp.tileSize / 2);
                    g2.drawString("Yes", gp.tileSize * 6 + gp.tileSize / 2,gp.tileSize * 6 + gp.tileSize / 2);
                    g2.drawString("No", gp.tileSize * 9,gp.tileSize * 6 + gp.tileSize / 2);
                    g2.drawRoundRect(gp.tileSize * 6 + gp.tileSize * sureX  * 5 / 2 ,gp.tileSize * 6 + gp.tileSize / 5,gp.tileSize * 2 - gp.tileSize / 2, gp.tileSize / 2,10,10);
                }
            }      
        }
        else if(current == sell){
            if(gp.player.inventory[tradeSellY][tradeSellX] != null){
                if(selling == true){
                    gp.player.inventory[tradeSellY][tradeSellX].totalAmount--;
                    gp.player.totalCoin += gp.player.inventory[tradeSellY][tradeSellX].price;
    
                    if(gp.player.inventory[tradeSellY][tradeSellX].totalAmount == 0){
                        gp.player.inventory[tradeSellY][tradeSellX] = null;
                    }
                    selling = false;
                    gp.gameState = gp.tradeState;
                }
                else{
                        g2.setColor(new Color(30,30,30,240));
                        g2.fillRoundRect(gp.tileSize * 6,gp.tileSize * 5,gp.tileSize * 4,gp.tileSize * 2,10,10);
        
                        g2.setColor(Color.white);
                        g2.setFont(new Font("Segoe UI",Font.BOLD,12));
                        g2.drawString("Do you want to sell " + gp.player.inventory[tradeSellY][tradeSellX].name , gp.tileSize * 6 + gp.tileSize / 2, gp.tileSize * 5 + gp.tileSize / 2);
                        g2.drawString("Yes", gp.tileSize * 6 + gp.tileSize / 2,gp.tileSize * 6 + gp.tileSize / 2);
                        g2.drawString("No", gp.tileSize * 9,gp.tileSize * 6 + gp.tileSize / 2);
                        g2.drawRoundRect(gp.tileSize * 6 + gp.tileSize * sureX  * 5 / 2 ,gp.tileSize * 6 + gp.tileSize / 5,gp.tileSize * 2 - gp.tileSize / 2, gp.tileSize / 2,10,10);
                }
            }
            
        }
            
    }
    public void drawBuy() {
        drawInventory(gp.npc[0], gp.tileSize * 2, gp.tileSize * 2, gp.tileSize * 4, gp.tileSize * 3,true);
        g2.setFont(new Font("Segoe UI",Font.BOLD,20));
        g2.setColor(Color.white);
        if(gp.npc[0].inventory[tradeBuyY][tradeBuyX] != null){
            g2.drawString("Name: " + gp.npc[0].inventory[tradeBuyY][tradeBuyX].name, gp.tileSize * 2, gp.tileSize * 5);
            g2.drawString("Price: "+gp.npc[0].inventory[tradeBuyY][tradeBuyX].price , gp.tileSize * 2, gp.tileSize * 5 + gp.tileSize / 2);
            if(gp.npc[0].inventory[tradeBuyY][tradeBuyX].damage > 0){
                g2.drawString("Damage: "+gp.npc[0].inventory[tradeBuyY][tradeBuyX].damage , gp.tileSize * 2, gp.tileSize * 6 );
            }
            if(gp.npc[0].inventory[tradeBuyY][tradeBuyX].defense > 0){
                g2.drawString("Defense: "+gp.npc[0].inventory[tradeBuyY][tradeBuyX].defense , gp.tileSize * 2, gp.tileSize * 6 );
            }
        }
        else{
            g2.drawString("None", gp.tileSize * 2, gp.tileSize * 5);
        }
    }
    public void drawStats() {
        int x = 5 * gp.tileSize;
        int y = 2 * gp.tileSize;
        int width = 6 * gp.tileSize;
        int height = 8 * gp.tileSize;  

        g2.setColor(new Color(0,0,0,230));
        g2.fillRect(x, y, width, height);

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4));
        g2.drawRect(x, y, width, height);
        x += gp.tileSize * 0.8;
        y += gp.tileSize;
        

        g2.setFont(new Font("Segoe UI",Font.PLAIN,16));
        g2.drawString("Name:",x,y);

        x += gp.tileSize * 2.5;

        g2.drawString(gp.player.name, x, y);
            
        x-=gp.tileSize* 2.5;
        y += gp.tileSize / 2;

        g2.setFont(new Font("Segoe UI",Font.PLAIN,16));
        g2.drawString("Damage:",x,y);

        x += gp.tileSize * 2.5;

        g2.drawString(gp.player.damage + "", x, y);
            
        x-=gp.tileSize* 2.5;
        y += gp.tileSize / 2;

        g2.setFont(new Font("Segoe UI",Font.PLAIN,16));
        g2.drawString("Health:",x,y);

        x += gp.tileSize * 2.5;

        g2.drawString(gp.player.maxHealth + "", x, y);
            
        x-=gp.tileSize* 2.5;
        y += gp.tileSize / 2;

        g2.setFont(new Font("Segoe UI",Font.PLAIN,16));
        g2.drawString("Defense:",x,y);

        x += gp.tileSize * 2.5;

        g2.drawString(gp.player.defense + "", x, y);
            
        x-=gp.tileSize* 2.5;
        y += gp.tileSize / 2;

        

        g2.setFont(new Font("Segoe UI",Font.PLAIN,16));
        g2.drawString("Level:",x,y);

        x += gp.tileSize * 2.5;

        g2.drawString(gp.player.level + "", x, y);
            
        x-=gp.tileSize* 2.5;
        y += gp.tileSize / 2;

        
        g2.setFont(new Font("Segoe UI",Font.PLAIN,16));
        g2.drawString("Weapon:",x,y);

        x += gp.tileSize * 2.5;
        if(gp.player.weapon == null){
            g2.drawString("None", x, y);
        }
        else
            g2.drawString(gp.player.weapon.name, x, y);
            
        x-=gp.tileSize* 2.5;
        y += gp.tileSize / 2;


        g2.setFont(new Font("Segoe UI",Font.PLAIN,16));
        g2.drawString("Armor:",x,y);

        x += gp.tileSize * 2.5;

        if(gp.player.armor == null){
            g2.drawString("None", x, y);
        }
        else
            g2.drawString(gp.player.armor.name, x, y);
            
        x-=gp.tileSize* 2.5;
        y += gp.tileSize / 2;



        g2.setFont(new Font("Segoe UI",Font.PLAIN,16));
        g2.drawString("Coin:",x,y);

        x += gp.tileSize * 2.5;

        g2.drawString(gp.player.totalCoin + "", x, y);
            
        x-=gp.tileSize* 2.5;
        y += gp.tileSize / 2;



        g2.setFont(new Font("Segoe UI",Font.PLAIN,16));
        g2.drawString("Total Kills",x,y);

        x += gp.tileSize * 2.5;

        g2.drawString(gp.player.totalKills + "", x, y);
            
        x-=gp.tileSize* 2.5;
        y += gp.tileSize / 2;



        g2.setFont(new Font("Segoe UI",Font.PLAIN,16));
        g2.drawString("Total Deaths",x,y);

        x += gp.tileSize * 2.5;

        g2.drawString(gp.player.totalDeaths + "", x, y);
            
        x-=gp.tileSize* 2.5;
        y += gp.tileSize / 2;

    }
    public void drawTitle(){
        int x = (int)(gp.tileSize * 5.5);
        int y = gp.tileSize * 3;

        g2.setFont(new Font("Segoe UI",Font.PLAIN,32));
        g2.setColor(Color.darkGray);
        g2.drawString("Edvençur Taym",x + 2,y + 2);
        g2.setColor(Color.white);
        g2.drawString("Edvençur Taym",x,y);

        x += gp.tileSize * 1.5;
        y += gp.tileSize * 3;
        g2.setFont(new Font("Segoe UI",Font.PLAIN,16));
        g2.drawImage(gp.player.down1,x - gp.tileSize,y - gp.tileSize / 3 + choice * gp.tileSize,null);
      
        g2.drawString("New Game",x,y);

        y += gp.tileSize;
        g2.drawString("Settings",x,y);
        y += gp.tileSize;
        x += gp.tileSize / 2;
        g2.drawString("Quit",x,y);

        if(gp.kh.enter){
            if(choice == 0){
                gp.gameState = gp.playState;
            }
            if(choice == 1){
                
            }
            if(choice == 2){
                System.exit(0);
                
            }
        }
    }
    public void drawDialogue(){
      
   
            int x = gp.tileSize * 2;
            int y = gp.tileSize / 2;
            int width = gp.screenWidth - (gp.tileSize * 5);
            int height = gp.tileSize * 4;

            Color c = new Color(0,0,0,230);
            g2.setColor(c);
            g2.fillRoundRect(x,y,width,height,35,35);

            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(4));
            g2.drawRoundRect(x + 3 , y + 3, width - 6 , height - 6 , 25, 25);

            x += gp.tileSize;
            y += gp.tileSize;
            g2.setFont(new Font("Segoe UI",Font.BOLD,24));
            for(String line : currentDiyalog.split("\n")){
                g2.drawString(line, x, y); 
                y+= 25;
            }
             
           
           
    }
    public void drawDeathScreen(){

        Color c = new Color(150,0,0,190);
        g2.setColor(c);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,72F));
        g2.drawString("YOU DIED" ,gp.tileSize * 4,gp.tileSize * 4);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,24F));
        g2.drawString("Respawn" ,gp.tileSize * 7,gp.tileSize * 7);

    }
    public void drawHealth(){
        BufferedImage currentHeart = null;
        int Xlocation = 0;
        for(int k = 0; k < gp.player.health; k++){
            if(k % 2 == 0){
                currentHeart = gp.player.Health1;
                if(k != 0){
                    Xlocation += gp.tileSize;
                }
            }
            else 
                currentHeart = gp.player.Health2;
                
            g2.drawImage(currentHeart,Xlocation,0,gp.tileSize,gp.tileSize,null);
        }
        Xlocation = 0;
    }
    public void drawCoin(){
        int moneyLength = 1;

        if(gp.player.totalCoin > 999){
            moneyLength = 3;
        }
        else if(gp.player.totalCoin > 99){
            moneyLength = 2;
        }
        else if(gp.player.totalCoin > 9){
            moneyLength = 1;
        }
        try {
            BufferedImage goldCoin = ImageIO.read(getClass().getResourceAsStream("/tiles/Coin.png"));;
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,24F));
            g2.setColor(new Color(240,200,20));
            g2.drawString(gp.player.totalCoin + "",gp.screenWidth - gp.tileSize - moneyLength * gp.originaltileSize * 3 / 2 ,gp.originaltileSize * 2);
            g2.drawImage(goldCoin,gp.screenWidth - gp.tileSize,12,gp.tileSize / 2,gp.tileSize / 2,null);
        }catch (Exception e) {
           e.printStackTrace();
        }
        
    }
    public void screenDarkening(){
        
        Color c = new Color(0,0,0,darkColor);
        g2.setColor(c);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        
        if(goBack){
            darkColor -= 3;
        }
        else 
            darkColor += 3;
        
        if(darkColor == 240){
            goBack = true;
            if(gp.mapNumber == 1){
                gp.mapNumber = 2;
                gp.player.worldX = 24 * gp.tileSize;
                gp.player.worldY = 24 * gp.tileSize;
            }
            else {
                gp.mapNumber = 1;
                gp.player.worldX = 59 * gp.tileSize;
                gp.player.worldY = 25 * gp.tileSize;
            }
            
        }
        if(darkColor == 0){
            gp.screenChange = false;
            goBack = false;
        }
    }
    public void drawInventory(Entity e , int x,int y, int width , int height,boolean trading){

        int col = 0;
        int row = 0;

        if(e.type == 0 && !trading){
            g2.setColor(new Color(30,30,30,230));
            g2.fillRoundRect(x,y,width,height,10,10);
    
            g2.setColor(Color.lightGray);
     
    
            width = gp.tileSize;
            height = gp.tileSize;
    
            while(x < gp.tileSize * 11 && y < gp.tileSize * 6){

                
                if(e.inventory[row][col] != null){
                    g2.setColor(Color.lightGray);
                    g2.drawImage(e.inventory[row][col].image,x,y,width,height,null);
                    g2.setFont(new Font("Segoe UI",Font.PLAIN,12));
                    g2.setColor(Color.white);
                    g2.drawString(e.inventory[row][col].totalAmount + " ", x + gp.tileSize * 4 / 5, y + gp.tileSize * 5 / 6);
                }
                x += gp.tileSize;
                col++;
                if(x == gp.tileSize * 11){
                    x = gp.tileSize * 5;
                    y += gp.tileSize;
                    col = 0;
                    row++;
                }
            }
            x = gp.tileSize * 5;
            y = gp.tileSize * 2;
            g2.setStroke(new BasicStroke(4));
            g2.setColor(Color.white);
            g2.drawRoundRect(x + gp.tileSize * inventoryX - 3,(y + gp.tileSize * inventoryY) - 3,gp.tileSize + 6,gp.tileSize + 6,10,10);
            
            if(e.inventory[inventoryY][inventoryX] != null){
                if(gp.kh.enter){

                    if(e.inventory[inventoryY][inventoryX].type == 0){
                        gp.player.equipObject(e.inventory[inventoryY][inventoryX]);
                    }
                    else{
                        gp.player.weapon = null;
                    }
                    
                    if( e.inventory[inventoryY][inventoryX].type == 2){
                        gp.player.useObject(e.inventory[inventoryY][inventoryX]);
                    }
              
                    gp.player.hand = e.inventory[inventoryY][inventoryX];
                    
                        
                    
                }
            }

            g2.setStroke(new BasicStroke(2));
            g2.setColor(new Color(150,150,150));
            g2.drawRoundRect(x + gp.tileSize * inventoryX - 3,(y + gp.tileSize * inventoryY) - 3,gp.tileSize + 6,gp.tileSize + 6,10,10);
        }
        else if(e.type == 0 && trading){
            g2.setColor(new Color(30,30,30,230));
            g2.fillRoundRect(x,y,width,height,10,10);
    
            g2.setColor(Color.lightGray);
     
    
            width = gp.tileSize;
            height = gp.tileSize;
    
            while(x < gp.tileSize * 11 && y < gp.tileSize * 6){
                
                if(e.inventory[row][col] != null){
                    g2.setColor(Color.lightGray);
                    g2.drawImage(e.inventory[row][col].image,x,y,width,height,null);
                    g2.setFont(new Font("Segoe UI",Font.PLAIN,10));
                    g2.setColor(Color.white);
                    g2.drawString(e.inventory[row][col].totalAmount + " ", x + gp.tileSize * 4 / 5 , y  + gp.tileSize);
                }
                x += gp.tileSize;
                col++;
                if(x == gp.tileSize * 11){
                    x = gp.tileSize * 5;
                    y += gp.tileSize;
                    col = 0;
                    row++;
                }
            }
            x = gp.tileSize * 5;
            y = gp.tileSize * 2;
            g2.setStroke(new BasicStroke(4));
            g2.setColor(Color.white);
            g2.drawRoundRect(x + gp.tileSize * tradeSellX - 3,(y + gp.tileSize * tradeSellY) - 3,gp.tileSize + 6,gp.tileSize + 6,10,10);
            

            g2.setStroke(new BasicStroke(2));
            g2.setColor(new Color(150,150,150));
            g2.drawRoundRect(x + gp.tileSize * tradeSellX - 3,(y + gp.tileSize * tradeSellY) - 3,gp.tileSize + 6,gp.tileSize + 6,10,10);
        }
        else if(e.type != 0){
            g2.setColor(new Color(30,30,30,230));
            g2.fillRoundRect(x,y,width,height + gp.tileSize * 2,10,10);
    
            g2.setColor(Color.darkGray);
     
    
            width = gp.tileSize;
            height = gp.tileSize;
    
            while(x < gp.tileSize * 6 && y < gp.tileSize * 4){
                
                if(e.inventory[row][col] != null){
                    g2.drawImage(e.inventory[row][col].image,x,y,width,height,null);
                }
                x += gp.tileSize;
                col++;
                if(x == gp.tileSize * 6){
                    x = gp.tileSize * 2;
                    y += gp.tileSize;
                    col = 0;
                    row++;
                }
            }
            x = gp.tileSize * 2;
            y = gp.tileSize * 2;
            g2.setStroke(new BasicStroke(4));
            g2.setColor(Color.white);
            g2.drawRoundRect(x + gp.tileSize * tradeBuyX - 3,(y + gp.tileSize * tradeBuyY) - 3,gp.tileSize + 6,gp.tileSize + 6,10,10);
            
            if(e.inventory[tradeBuyY][tradeBuyX] != null){
                if(gp.kh.enter){

                    if(e.inventory[tradeBuyY][tradeBuyX].type == 0){
                        gp.player.equipObject(e.inventory[tradeBuyY][tradeBuyX]);
                    }
                    else if( e.inventory[tradeBuyY][tradeBuyX].type == 2){
                        gp.player.useObject(e.inventory[tradeBuyY][tradeBuyX]);
                        e.inventory[tradeBuyY][tradeBuyX] = null;
                    }
                    
                }
            }

            g2.setStroke(new BasicStroke(2));
            g2.setColor(new Color(150,150,150));
            g2.drawRoundRect(x + gp.tileSize * tradeBuyX - 3,(y + gp.tileSize * tradeBuyY) - 3,gp.tileSize + 6,gp.tileSize + 6,10,10);
            
        } 

         
        
    }
    public void drawSettings(){
        int x = 5 * gp.tileSize;
        int y = 2 * gp.tileSize;
        int width = 6 * gp.tileSize;
        int height = 8 * gp.tileSize;

        g2.setColor(new Color(0,0,0,230));
        g2.fillRect(x, y, width, height);

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4));
        g2.drawRect(x, y, width, height);

        x += gp.tileSize * 2;
        y += gp.tileSize;
        g2.setFont(new Font("Segoe UI",Font.PLAIN,32));
        g2.setColor(Color.darkGray);
        g2.drawString("Settings",x + 2,y + 2);
        g2.setColor(Color.white);
        g2.drawString("Settings",x,y);

        y += gp.tileSize * 2 ;
        g2.setFont(new Font("Segoe UI",Font.PLAIN,24));
        g2.drawString(">",x - gp.tileSize,y + choiceSettings * gp.tileSize);
      
        g2.drawString("Controls",x,y);

        y += gp.tileSize;
        g2.drawString("Sound",x,y);
        y += gp.tileSize;
        g2.drawString("Player Stats",x,y);
        y += gp.tileSize;
        g2.drawString("Leave",x,y);

        if(gp.kh.enter){
            if(choiceSettings == 0){
                gp.gameState = gp.controlsState;
            }
            if(choiceSettings == 1){
                
            }
            if(choiceSettings == 2){
                gp.gameState = gp.statsState;
            }
            if(choiceSettings == 3){
                gp.gameState = gp.titleState;
                gp.kh.enter = false;
            }
        }
    }
    public void drawControls(){
        int x = 5 * gp.tileSize;
        int y = 2 * gp.tileSize;
        int width = 6 * gp.tileSize;
        int height = 8 * gp.tileSize;  

        g2.setColor(new Color(0,0,0,230));
        g2.fillRect(x, y, width, height);

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4));
        g2.drawRect(x, y, width, height);

            x += gp.tileSize * 0.8;
            y += gp.tileSize;

            g2.setColor(Color.white);
            g2.setFont(new Font("Segoe UI",Font.PLAIN,16));
            g2.drawString("W",x,y);

            x += gp.tileSize * 1.5;

            g2.drawString("Walk Upwards", x, y);
            
            x-=gp.tileSize* 1.5;
            y += gp.tileSize / 2;




            g2.drawString("A",x,y);

            x += gp.tileSize * 1.5;

            g2.drawString("Strafe Right", x, y);
            
            x-=gp.tileSize* 1.5;
            y += gp.tileSize / 2;



            g2.drawString("S",x,y);

            x += gp.tileSize* 1.5;

            g2.drawString("Walk Downwards", x, y);
            
            x-=gp.tileSize* 1.5;
            y += gp.tileSize/2;
  


            g2.drawString("D",x,y);

            x += gp.tileSize* 1.5;

            g2.drawString("Strafe Left", x, y);
            
            x-=gp.tileSize* 1.5;
            y += gp.tileSize * 1.5;



            g2.drawString("E",x,y);

            x += gp.tileSize* 1.5;

            g2.drawString("Open/Close Inventory", x, y);
            
            x-=gp.tileSize* 1.5;
            y += gp.tileSize / 2 ;



            g2.drawString("SPACE",x,y);

            x += gp.tileSize * 1.5;

            g2.drawString("Attack", x, y);
            
            x-=gp.tileSize * 1.5;
            y += gp.tileSize /2;



            g2.drawString("ENTER",x,y);

            x += gp.tileSize* 1.5;

            g2.drawString("Talk to NPC/Select Items", x, y);
            
            x-=gp.tileSize* 1.5;
            y += gp.tileSize /2;



            g2.drawString("ESC",x,y);

            x += gp.tileSize* 1.5;

            g2.drawString("Options Menu", x, y);
            
            x-=gp.tileSize* 1.5;



            y += gp.tileSize /2;



            g2.drawString("M",x,y);

            x += gp.tileSize* 1.5;

            g2.drawString("Opens Map", x, y);
            
            x-=gp.tileSize* 1.5;
        
    }
    public void drawHand(){
        g2.setColor(new Color(30,30,30,190));
        g2.fillRoundRect(gp.tileSize * 5,gp.tileSize * 10,gp.tileSize + 10,gp.tileSize + 10,10,10);


        if(gp.player.hand != null){
            g2.drawImage(gp.player.hand.image,gp.tileSize * 5 + 5,gp.tileSize * 10 + 5,gp.tileSize,gp.tileSize,null);
            g2.setColor(Color.white);
            g2.setFont(new Font("Segoe UI",Font.BOLD,12));
            g2.drawString(gp.player.hand.totalAmount + "",gp.tileSize * 5 + gp.tileSize * 5 / 6,gp.tileSize * 11);
        }
           
    }
    public void drawDayNight(){

        if(time <= 2048){
            time += 2;
        }
        else if(time > 1800 && time <= 2100){
            time = time + 1/2;
            gp.light.draw(g2);
        }
        else if(time > 2100){
            time = 0;
            
        }

        g2.setColor(new Color(0,0,0,time / 16));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        System.out.println(time);
    }
}
