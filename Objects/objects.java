package Objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.Game;

public class objects {
    public BufferedImage image;
    public String name;
    public int damage;
    public int defense;
    public boolean collision;
    public int worldX;
    public int worldY;
    public int mapNumberPlace;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public int price;
    public int totalAmount = 1;
    public int type;//0 Equippable -- 1 Consumable -- 2 Usable -- 3 Stable -- 4 Destructable
    public boolean destructable = false;
    public int timer;
    public boolean startTimer = false;
    public  boolean regrow = false;
    Game gp;

    public objects(Game gp){
        this.gp = gp;
    }

    public void setup(String imagePath , String name , boolean collision){
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.image = image;
        this.name = name;
        this.collision = collision;
    }
    public void changeCollision(objects o){

        if(o.collision){
            o.collision = false;
        }
        else 
            o.collision = true;
    }
    public void reGrow(objects o){
        if(regrow){
            o.setup("/tiles/breaktree",name,true);
            o.destructable = true;
            regrow = false;
        }
    }
    public void update(){
        if(startTimer){
            timer++;
            if(timer == 3600){
                timer = 0;
                startTimer = false;
                regrow = true;
            }
        }
        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null && gp.obj[i].regrow){
                gp.obj[i].reGrow(gp.obj[i]);
            }
        }
    }
    public void draw(Graphics2D g2){

       
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize, null);
    }
}
