package entity;

import Main.Game;
import Main.KeyHandler;

public class Zombie extends Entity{

    Game gp;
    KeyHandler kh;
    Player player;

    public Zombie(Game gp){
        super(gp);

        this.gp = gp;

        setDefaultValues();
        getZombieImage();
        direction = "right";
    }
    public void setDefaultValues(){
        defaultSpeed = 2;
        speed = defaultSpeed;
        damage = 1;
        health = 5;
        mapNumberPlace = 2;
        maxHealth = 5;
        type = 2;
        hitbox.x = 8;
        hitbox.y = 16;
        hitbox.width = 32;
        hitbox.height = 32;
    }
    public void setX(int x){
        this.worldX = x;
    }
    public void setY(int y){
        this.worldY = y;
    }

    public void getZombieImage(){
        try {
            left1 = setup("/zombie/zombieleft1");
            left2 = setup("/zombie/zombieleft2");
            up2 = setup("/zombie/zombieleft2");
            up1 = setup("/zombie/zombieleft2");
            down1 = setup("/zombie/zombieleft2");
            down2 = setup("/zombie/zombieleft2");
            right1 = setup("/zombie/zombieright1");
            right2 = setup("/zombie/zombieright2");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setAction(){

        
        die();

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
                if(this.hitbox.intersects(gp.player.hitbox)){
                    knockback();
                    gp.player.receiveDamage(damage);
                               
                }
            }
            else{
                roamAround();
            }
        
    }
}
