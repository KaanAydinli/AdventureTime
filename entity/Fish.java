package entity;

import java.util.Random;

import Main.Game;

public class Fish extends Entity{
    Game gp;
    Random gen;
    public Fish(Game gp){
        super(gp);

        this.gp = gp;
        gen = new Random();

        setDefaultValues();
        getFishImage();
        direction = "down";
        hitbox.setBounds(8,16,32,16);
    }
    public void setDefaultValues(){
        speed = 3;
        mapNumberPlace = 1;
        type = 3;
    }
    public void setX(int x){
        this.worldX = x;
    }
    public void setY(int y){
        this.worldY = y;
    }  
    public void getFishImage(){
        try {
            right1 = setup("/fish/fishright");
            right2 = setup("/fish/fishright");
            left1 = setup("/fish/fishleft");
            left2 = setup("/fish/fishleft");
            up1 = setup("/fish/fishleft");
            up2 = setup("/fish/fishleft");
            down1 = setup("/fish/fishleft");
            down2 = setup("/fish/fishleft");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setAction(){

        if(insideRadius()){

            if(worldX > gp.player.getX()){
                direction = "left";
            }
            else if(worldX < gp.player.getX()){
                direction = "right";     
            }
                
            if(worldY > gp.player.getY()){
                direction = "up";                            
            }
            else if(worldY < gp.player.getY()){
                direction = "down";         
            }
        }
        else{
            roamAround();
        }
      
    }
    /* 
    public void draw(Graphics2D g2){

        BufferedImage image = right1;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        int angle = (int)Math.toDegrees(Math.atan2(screenY - targetX, screenX - targetY));
        //System.out.println(angle);

        rotate(image, screenX, screenY, g2,angle);       
    }
        */
}
