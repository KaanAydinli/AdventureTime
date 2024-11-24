package entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.Game;
import Objects.objects;

public class Entity {
    Game gp;
    Random gen = new Random();
    public int worldX,worldY;
    public int startX;
    public int startY;
    public int fishX;
    public int fishY;
    public int speed;
    public int defaultSpeed;
    public int health;
    public int maxHealth;
    public int damage;
    public int defense;
    public int level;
    public int currentExp;
    public int expNeededForNextLevel;
    public objects weapon = null;
    public objects hand = null;
    public objects armor = null;
    public int type;
    public boolean alive = true;
    public boolean collisionOn = false;
    public boolean destroy;
    public boolean knockback = false;
    public boolean attack = false;
    public boolean fish = false;
    public String attackdirection;
    public String knockbackdirection;
    public Rectangle hitbox = new Rectangle(8,  16,32,32);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int defaulthitboxX;
    public int defaulthitboxY;
    public int actionLockCounter;
    public int knockbackCounter;
    public String direction;
    public int imageCount = 0;
    public int imageTotal = 1;
    public int mapNumberPlace;
    int targetX = 0;
    int targetY = 0;
    public BufferedImage down1,down2,right1,right2,left1,left2,up1,up2,Health1,Health2;
    public BufferedImage weapondown1,weapondown2,weaponright1,weaponright2,weaponleft1,weaponleft2,weaponup1,weaponup2;
    public BufferedImage punchup1,punchdown1,punchleft1,punchright1;

    public int diyalogIndex = 0;
    public String diyalog[] = new String[20];
    public objects[][] inventory;

    public Entity(Game gp){
        this.gp = gp;
    }
    public void setAction(){}
    public void roamAround(){
        
        actionLockCounter++;
        if(actionLockCounter == 100){
            int i = gen.nextInt(100) + 1;

            if(i < 25){
                direction = "up";
            }
            else if(i > 25 && i < 50){
                direction = "down";
            }
            else if(i > 50 && i < 75){
                direction = "left";
            }
            else if(i > 75 && i < 100){
                direction = "right";
            }
            actionLockCounter = 0;
        }  

    }
    public boolean die(){ 
        if(health <= 0){
            alive = false;
            gp.player.currentExp += 10;
            gp.player.totalCoin += 10;
            gp.player.totalKills++;
          }
          else 
              alive = true;
          return alive;    
    }
    public boolean insideRadius(){
        if(gp.player.getX() < worldX + 4 * gp.tileSize && gp.player.getX() > worldX - 4 * gp.tileSize 
        && gp.player.getY() < worldY + 4 * gp.tileSize && gp.player.getY() > worldY - 4 * gp.tileSize){
           return true;         
        }   
        else 
            return false;         
    }
    public void speak(){}
    public void knockback(){

        if(gp.player.invincible < 10){

        }
        else {
            gp.player.knockbackdirection = direction;
            gp.player.speed += 10;
            gp.player.knockback = true;
        }     
    }
    public void update(){
        
        hitbox.setLocation(worldX + 8,worldY + 16);

        if(knockback){
            gp.cChecker.checkCollisionTile(this);
                if(collisionOn == true){
                    knockbackCounter = 0;
                    knockback = false;
                    speed = defaultSpeed;
                }
                else if(collisionOn == false){
                    switch(knockbackdirection){
                        case "up": worldY -= speed; break;
                        case "down": worldY += speed; break;
                        case "left":worldX -= speed; break;
                        case "right":worldX += speed; break;
                    }
                }
                knockbackCounter++;
                if(knockbackCounter == 10){
                    knockback = false;
                    knockbackCounter = 0;
                    speed = defaultSpeed;
                }
        }
        else{
            setAction();

            collisionOn = false;
            gp.cChecker.checkCollisionTile(this);
            gp.cChecker.checkCollisionObject(this, false);
            
            if(collisionOn == false){
                switch(direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left":worldX -= speed; break;
                    case "right":worldX += speed; break;
                }
            }
        }       
            imageCount++;
        if(imageCount > 10){
            if(imageTotal == 1){
                imageTotal = 2;
            }
            else if(imageTotal == 2){
                imageTotal = 1;
            }
            imageCount = 0;
        }     
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch(direction){

            case "down":
                if(imageTotal == 1){
                    image = down1;
                }
                else if(imageTotal == 2){
                    image = down2;
                }
                break;
            case "up":
                if(imageTotal == 1){
                    image = up1;
                }
                else if(imageTotal == 2){
                    image = up2;
                }
                break;
            case "right":
                if(imageTotal == 1){
                    image = right1;
                }
                else if(imageTotal == 2){
                    image = right2;
                }
                break;
            case "left":
                if(imageTotal == 1){
                    image = left1;
                }
                else if(imageTotal == 2){
                    image = left2;
                }
                break;
        }

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
      
        
       
            g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize, null);  
            //rotate(image, screenX, screenY, g2);

        double oneSection =  (double)gp.tileSize / maxHealth;
        double currentSection = oneSection * health;

        if(type == 2){
            g2.setColor(new Color(30,30,30));
            g2.fillRect(screenX - 3,screenY - 18,gp.tileSize + 6,16);
        
            g2.setColor(new Color(255,20,40));
            g2.fillRect(screenX,screenY - 15,(int)currentSection,10);
            
        } 
    }
    public BufferedImage setup(String imagePath){
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
    public void rotate(BufferedImage image , int screenX, int screenY, Graphics2D g2,int degree){
        AffineTransform at = AffineTransform.getTranslateInstance(screenX,screenY);
        at.rotate(Math.toRadians(degree), image.getWidth() /2,image.getHeight() / 2); 
        g2.drawImage(image, at, null);
    }
}
