package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import Main.Game;
import Main.KeyHandler;
import Objects.Object_Farmland;
import Objects.objects;

public class Player extends Entity{
    Game gp;
    KeyHandler kh;
    public  int screenX;
    public  int screenY;   
    public int attackCounter = 20;
    public int healCounter = 0;
    public int totalCoin = 0;
    public int invincible = 0;
    public int keyUsed = 0;
    public String name = "Kaan";
    public int totalKills = 0;
    public int totalDeaths = 0;
    public int fishingCounter = 0;
    public int fishingX = 0;
    public int fishingY = 0;
    public BufferedImage axedown1,axedown2,axeup1,axeup2,axeleft1,axeleft2,axeright1,axeright2;
    

    public Player(Game gp, KeyHandler kh){
        super(gp);

        this.gp = gp;
        this.kh = kh;
        screenX = (gp.screenWidth / 2) - gp.tileSize / 2;
        screenY = (gp.screenHeight / 2) - gp.tileSize / 2;
        setDefaultValues();
        getPlayerImage();
        showHealth();
        direction = "down";
        hitbox.x = 8;
        hitbox.y = 16;
        defaulthitboxX = hitbox.x;
        defaulthitboxY = hitbox.y;
        inventory = new objects[4][6];
    

    }
    public void interactNPC(){

        gp.npc[0].speak();
                   
    }
    
    public void getPlayerImage(){
        try {
            down1 = setup("/player/down1");
            down2 = setup("/player/down2");
            right1 = setup("/player/right1");
            right2 = setup("/player/right2");
            left1 = setup("/player/left1");
            left2 = setup("/player/left2");
            up1 =  setup("/player/up1");
            up2 = setup("/player/up2");

            weapondown1 = setup("/player/downattack1");
            weapondown2 = setup("/player/downattack2");
            weaponleft1 = setup("/player/leftattack1");
            weaponleft2 = setup("/player/leftattack2");
            weaponright1 = setup("/player/rightattack1");
            weaponright2 = setup("/player/rightattack2");
            weaponup1 = setup("/player/upattack1");
            weaponup2 = setup("/player/upattack2");

            punchdown1 = setup("/player/punchdown");
            punchup1 = setup("/player/punchup");
            punchright1 = setup("/player/punchright");
            punchleft1 = setup("/player/punchleft");

            axeleft1 = setup("/player/axeleft1");
            axeleft2 = setup("/player/axeleft2");
            axeright1 = setup("/player/axeright1");
            axeright2 = setup("/player/axeright2");
            axeup1 = setup("/player/axeup1");
            axeup2 = setup("/player/axeup2");
            axedown1 = setup("/player/axedown1");
            axedown2 = setup("/player/axedown2");

        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    public void setDefaultValues(){
        worldX = 50 * gp.tileSize;
        worldY = 50 * gp.tileSize;

        defaultSpeed = 4;
        speed = defaultSpeed;
        health = 10;
        maxHealth = 10;
        defense = 0;
        damage = 1;
        weapon = null;
        level = 1;
        currentExp = 0;
        expNeededForNextLevel = 15;
        totalCoin = 400;

        knockback = false;
        type = 0;

        attackArea.height = 32;
        attackArea.width = 32;      

        
    }
    public void farm(int index , int farmX , int farmY){

        farmX += gp.tileSize / 2;
        farmY += gp.tileSize / 2;

        switch(direction){
            case "up": farmY -= gp.tileSize; break;
            case "down":farmY += gp.tileSize; break;
            case "left": farmX -= gp.tileSize;break;
            case "right": farmX += gp.tileSize; break;
        }

        if(index != 999){
            if(hand != null && hand.name.equals("Carrot")){
                if(gp.obj[index].name.equals("Farmland")){
                    gp.obj[index].setup("/tiles/cropfarmland", "CropFarmland", false);
                    removeObjectFromInventory(hand);
                }
            }
        }
        if(hand != null && hand.name.equals("Log")){
            if(gp.tileM.tile[gp.tileM.mapTileNum[farmY / gp.tileSize][farmX / gp.tileSize]].name.equals("grass")){
                createNewObject(new Object_Farmland(gp) , farmX / gp.tileSize, farmY / gp.tileSize);
            }
        }

    }
    public void createNewObject(objects o, int x , int y) {

		for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] == null && o != null){
                gp.obj[i] = o;
                gp.obj[i].worldX = x * gp.tileSize;
                gp.obj[i].worldY = y * gp.tileSize;
                gp.obj[i].mapNumberPlace = 1;
                o = null;
            }
        }
	}
	public void levelUp(){
        if(currentExp > expNeededForNextLevel){
            currentExp -= expNeededForNextLevel;
            level++;
        }
    }
    public void interactObj(int index){
        if(index != 999){
            if(gp.obj[index].name.equals("Pit")){
                gp.screenChange = true;
            }
            if(gp.obj[index].name.equals("Door")){

                if(hand != null && hand.name.equals("Key")){
                    removeObjectFromInventory(hand);
                    
                    gp.obj[index].setup("/tiles/dooropen", "DoorOpen", false);

                }

            }
            if(gp.obj[index].name.equals("Campfire")){
                receiveDamage(1);
            }
        }  
    }
    public void putObjectsToInventory(objects o){
        boolean  notHave = true;
        
            for(int i = 0; i < inventory.length;i++){
                for(int k = 0;k < inventory[2].length;k++){
    
                    if(inventory[i][k] != null && inventory[i][k] == o){
                        inventory[i][k].totalAmount++;
                        o = null;
                        notHave = false;
                        
                    }
                }
            }
            if(notHave){
                for(int i = 0; i < inventory.length;i++){
                    for(int k = 0;k < inventory[2].length;k++){
        
                        if(inventory[i][k] == null && o != null){
                            inventory[i][k] = o;                           
                            o = null;
                            
                        }
                    }
                }
            }
        
        
    }
    public void removeObjectFromInventory(objects o){
        for(int i = 0; i < inventory.length;i++){
            for(int k = 0;k < inventory[2].length;k++){

                if(gp.player.inventory[i][k] == o){
                    if(gp.player.inventory[i][k].totalAmount > 1){
                        gp.player.inventory[i][k].totalAmount--;
                    }
                    else{
                        gp.player.inventory[i][k] = null;
                        hand = null;
                    }
                }
            }
        }
    }
    public void equipObject(int index){
        
        damage = gp.obj[index].damage;
        defense = gp.obj[index].defense;
        hand = gp.obj[index];
    }
    public void equipObject(objects obj){
        
        damage = obj.damage;
        defense = obj.defense;
        weapon = obj;
        hand = obj;
    }
    public void useObject(objects obj){
        if(obj.name.equals("Key")){
            keyUsed++;
        }
    }
    public void healGradually(){
        if(healCounter == 2000){
            if(health < maxHealth){
                health++;
            }
            healCounter = 0;
        }
        healCounter++;
    }
    public void showHealth(){
        Health1 = setup("/tiles/Heart1");
        Health2 = setup("/tiles/Heart2");
        
    }
    public void attack(){
        
        if(kh.space){
            if(kh.spaceReleased){
                if(weapon == null){
                    attack = true;
                    kh.spaceReleased = false;
                    attackdirection = direction;
                }
                else if(weapon.name.equals("FishingRod") && fish == false){
                    fish();
                    fish = true;
                    fishX = worldX;
                    fishY = worldY;
                    kh.spaceReleased = false;
                    fishingCounter = 0;
                }            
                else{
                    attack = true;
                    kh.spaceReleased = false;
                    attackdirection = direction;
                }
            }
            
        }
        if(attack){
            if(attackCounter == 20){

                int tempX = worldX;
                int tempY = worldY;

                switch(direction){
                    case "up": tempY -= gp.tileSize; break;
                    case "down":tempY += gp.tileSize; break;
                    case "left": tempX -= gp.tileSize;break;
                    case "right": tempX += gp.tileSize; break;
                }
               
                attackArea.x = tempX;
                attackArea.y = tempY;

                int tileIndex = gp.cChecker.checkCollisionObject(attackArea);
                int monsterIndex = gp.cChecker.checkCollisionEntity(attackArea);

                if(weapon != null && weapon.name.equals("Axe")){
                    chopWood(tileIndex);
                }
                
                farm(tileIndex,worldX,worldY);
                
                damageMonster(monsterIndex);
                

            }
            attackCounter--;
            if(attackCounter == 0){
                attackCounter = 20;
                attack = false;
                fish = false;
            }          
        }
    }
    public void damageMonster(int index){

        if(index != 999){
            knockback(gp.monster[index]);
            gp.monster[index].health  -= damage;
        }
    }
    public void chopWood(int index){

        if(index != 999){
            if(gp.obj[index].destructable){
                gp.obj[index].setup("/tiles/treetrunk", "Treetrunk", false);
                gp.obj[index].startTimer = true;
                putObjectsToInventory(gp.obj[8]);
                gp.obj[index].destructable = false;
            }
        }
    }
    public void fish() {
        for(int i = 0; i < gp.npc.length; i++){
            if(gp.npc[i] != null && gp.npc[i].type == 3){
                gp.npc[i].targetX = worldX;
                gp.npc[i].targetY = worldY;
            }
        }
        
    }
    public int getX(){
        return worldX;
    }
    public int getY(){
        return worldY;
    }
    public void setX(int x){
        worldX = x;
    }
    public void setY(int y){
        worldY = y;
    }
    public void knockback(Entity e){
        e.knockbackdirection = attackdirection;
        e.speed += 10;
        e.knockback = true;
    }
    public void receiveDamage(int damage){
        if(invincible < 10){
            invincible++;
        }
        else{
            health -= damage;
            invincible = 0;
        }


    }
    public void update(){
        
        hitbox.setLocation(worldX + 8,worldY + 16);

        attack();
        levelUp();
        healGradually();

        if(health <=  0){
            gp.gameState = gp.deathState;
            totalDeaths++;
            
        }
        
        if(knockback){
            gp.cChecker.checkCollisionTile(this);

                if(collisionOn == true){
                    knockbackCounter = 0;
                    knockback = false;
                    speed = defaultSpeed;
                }
                else if(collisionOn == false){
                    switch(direction){
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
        else {
            if((kh.down | kh.right || kh.left || kh.up || kh.enter )){
            
                if(kh.down){
                    direction = "down";
                }
                if(kh.up){
                    direction = "up";
                }
                if(kh.right){
                    direction = "right";
                }
                if(kh.left){
                    direction = "left";
                }
                collisionOn = false;
                gp.cChecker.checkCollisionTile(this);
                
                int objIndex = gp.cChecker.checkCollisionObject(this, true);
                interactObj(objIndex);           

                if(gp.npc[0].insideRadius() && gp.gameState == gp.dialogueState){
            
                    interactNPC();
                    
                }
    
                if(collisionOn == false && kh.enter == false){
                    switch(direction){
                        case "up": worldY -= speed; break;
                        case "down": worldY += speed; break;
                        case "left":worldX -= speed; break;
                        case "right":worldX += speed; break;
                    }
                }
                kh.enter = false;               
            }
        }
                 
            if(imageCount > 20){
                if(imageTotal == 1){
                    imageTotal = 2;
                }
                else if(imageTotal == 2){
                    imageTotal = 1;
                }
                imageCount = 0;
            }       
        imageCount++;
            
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        BufferedImage current1 = null;
        BufferedImage current2 = null;

        if(attack){
            if(weapon == null){
                switch(direction){
                    case "up": current1 = punchup1; current2 = punchup1;break;
                    case "down": current1 = punchdown1; current2 = punchdown1;break;
                    case "left": current1 = punchleft1; current2 = punchleft1;break;
                    case "right": current1 = punchright1; current2 = punchright1;break;
                }
            }
            else if(weapon.name.equals("Sword")){
                switch(direction){
                    case "up": current1 = weaponup1; current2 = weaponup2;break;
                    case "down": current1 = weapondown1; current2 = weapondown2;break;
                    case "left": current1 = weaponleft1; current2 = weaponleft2;break;
                    case "right": current1 = weaponright1; current2 = weaponright2;break;
                }
            }
            else if(weapon.name.equals("Axe")){
                switch(direction){
                    case "up": current1 = axeup1; current2 = axeup2;break;
                    case "down": current1 = axedown1; current2 = axedown2;break;
                    case "left": current1 = axeleft1; current2 = axeleft2;break;
                    case "right": current1 = axeright1; current2 = axeright2;break;
                }
            }
            switch(direction){
                
                case "down":
                    if(imageTotal == 1){
                        image = current1;
                    }
                    else if(imageTotal == 2){
                        image = current2;
                    }
                    g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize * 2,null);
                    break;
                case "up":
                    if(imageTotal == 1){
                        image = current1;
                    }
                    else if(imageTotal == 2){
                        image = current2;
                    }
                    g2.drawImage(image,screenX,screenY - gp.tileSize,gp.tileSize,gp.tileSize * 2,null);
                    break;
                case "right":
                    if(imageTotal == 1){
                        image = current1;
                    }
                    else if(imageTotal == 2){
                        image = current2;
                    }
                    g2.drawImage(image,screenX,screenY,gp.tileSize * 2,gp.tileSize,null);
                    break;
                case "left":          
                   
                    if(imageTotal == 1){
                        image = current1;
                    }
                    else if(imageTotal == 2){
                        image = current2;
                    }      
                    g2.drawImage(image,screenX - gp.tileSize,screenY,gp.tileSize * 2,gp.tileSize,null);          
                    break;
            }
  
        }
        else{
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
            g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);     
        }
        if(weapon != null && weapon.name.equals("FishingRod") && fish){
            int screenXt = (fishX - 2 * gp.tileSize )- worldX + gp.player.screenX;
            int screenYt = (fishY - 2 * gp.tileSize) - worldY+ gp.player.screenY;

            g2.setColor(Color.white);
            g2.drawLine(screenX + gp .tileSize / 2,screenY + gp. tileSize / 2,screenXt,screenYt);
            
        }
    }
    public void respawn(){
        setDefaultValues();
    }
}
