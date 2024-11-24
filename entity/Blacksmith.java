package entity;

import Main.Game;
import Objects.objects;

public class Blacksmith extends Entity{
    Game gp;

    boolean firstEncounter = true;

    public Blacksmith(Game gp){
        super(gp);

        this.gp = gp;

        setDefaultValues();
        getBlacksmithImage();
        setDialog();
        direction = "down";
        inventory = new objects[3][4];
    }
    public void setDefaultValues(){
        speed = 0;
        mapNumberPlace = 1;
        type = 1;
    }
    public void setX(int x){
        this.worldX = x;
    }
    public void setY(int y){
        this.worldY = y;
    }
    public void setDialog(){
        diyalog[0] = "Hello, you fellow traveller";
        diyalog[1] = "I am the blacksmith of this town";
        diyalog[2] = "Well... I was is a better saying";
        diyalog[3] = "Until the town got occupied by monsters";
        diyalog[4] = "I tried to stop the attack but there  \n was no hope";
        diyalog[5] = "They took my eye and my hand";
        diyalog[6] = "Good thing is that i can still make \n some good stuff";
        diyalog[7] = "I can see your warrior spirit";
        diyalog[8] = "You can defeat the monsters with these";
        diyalog[9] = "Consider checking my offers";
        diyalog[10] = "1";

    }
    public void getBlacksmithImage(){
        try {
            down1 = setup("/blacksmith/blacksmith");
            down2 = setup("/blacksmith/blacksmith");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void speak(){


            if(firstEncounter){
                gp.ui.currentDiyalog = diyalog[diyalogIndex];
                diyalogIndex++;
                    if(diyalogIndex == 10){
                        firstEncounter = false;
                    }                      
            }                       
         
    }
    public boolean insideRadius(){
        if(gp.player.getX() < worldX + gp.tileSize && gp.player.getX() > worldX - gp.tileSize 
        && gp.player.getY() < worldY + gp.tileSize && gp.player.getY() > worldY - gp.tileSize){
            if(gp.kh.enter){
                
                if(!firstEncounter){
                    gp.gameState = gp.tradeState;
                    gp.ui.subTrade = true;
                }
                else 
                    gp.gameState = gp.dialogueState;
            }
                
            
           return true;
           
        }   
        else {
            diyalogIndex = 0;           
            gp.gameState = gp.playState;
            return false;
        }
           
                     
    }
}
