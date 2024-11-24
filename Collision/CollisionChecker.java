package Collision;


import java.awt.Rectangle;

import Main.Game;
import entity.Entity;

public class CollisionChecker {

    Game gp;
    public CollisionChecker(Game gp){
        this.gp = gp;
    }
    public void checkCollisionTile(Entity e){

        int tempLeftX = (int)e.hitbox.getX();
        int tempRightX =  (int)e.hitbox.getX() + (int)e.hitbox.getWidth();
        int tempTopY =  (int)e.hitbox.getY();
        int tempBottomY = (int)e.hitbox.getY() + (int)e.hitbox.getHeight();


        int tempLeftCol = tempLeftX / gp.tileSize;
        int tempRightCol = tempRightX / gp.tileSize;
        int tempTopRow = tempTopY / gp.tileSize;
        int tempBottomRow = tempBottomY / gp.tileSize;


        int tile1,tile2;


        String direction = e.direction;
        if(e.knockback == true){
            direction = e.knockbackdirection;
        }


        switch(direction){
            case "up":
            tempTopRow  = (tempTopY - e.speed )/gp.tileSize;

            if(gp.mapNumber == 2){
                tile1 = gp.tileM.mapTileNum2[tempLeftCol][tempTopRow];
                tile2 = gp.tileM.mapTileNum2[tempRightCol][tempTopRow];
            }
            else{
                tile1 = gp.tileM.mapTileNum[tempLeftCol][tempTopRow];
                tile2 = gp.tileM.mapTileNum[tempRightCol][tempTopRow];
            }
            
            if(gp.tileM.tile[tile1].collision == true || gp.tileM.tile[tile2].collision == true){
                e.collisionOn = true;
                if(e.type == 3 && tile1 != -1 && tile2 != -1 && (gp.tileM.tile[tile1].name.equals("water") || gp.tileM.tile[tile2].name.equals("water"))){
                    e.collisionOn = false;
                }
            }

            if(e.type == 3 && tile1 != -1 && tile2 != -1 && (gp.tileM.tile[tile1].name.equals("grass") || gp.tileM.tile[tile2].name.equals("grass") )){
                e.collisionOn = true;
            }
            
            
            break;

            case "down":
            tempBottomRow  = (tempBottomY + e.speed )/gp.tileSize;
            if(gp.mapNumber == 2){
                tile1 = gp.tileM.mapTileNum2[tempLeftCol][tempBottomRow];
                tile2 = gp.tileM.mapTileNum2[tempRightCol][tempBottomRow];
            }
            else{
                tile1 = gp.tileM.mapTileNum[tempLeftCol][tempBottomRow];
                tile2 = gp.tileM.mapTileNum[tempRightCol][tempBottomRow];
            }
            if(gp.tileM.tile[tile1].collision == true || gp.tileM.tile[tile2].collision == true){
                e.collisionOn = true;
                if(e.type == 3 && tile1 != -1 && tile2 != -1 &&  (gp.tileM.tile[tile1].name.equals("water") || gp.tileM.tile[tile2].name.equals("water"))){
                    e.collisionOn = false;
                }
                           
            }

            if(e.type == 3 &&tile1 != -1 && tile2 != -1 &&  (gp.tileM.tile[tile1].name.equals("grass") || gp.tileM.tile[tile2].name.equals("grass") )){
                e.collisionOn = true;
            }
            
            break;

            case "left":
            tempLeftCol  = (tempLeftX - e.speed )/gp.tileSize;

            if(gp.mapNumber == 2){
                tile1 = gp.tileM.mapTileNum2[tempLeftCol][tempTopRow];
                tile2 = gp.tileM.mapTileNum2[tempLeftCol][tempBottomRow];
            }
            else{
                tile1 = gp.tileM.mapTileNum[tempLeftCol][tempTopRow];
                tile2 = gp.tileM.mapTileNum[tempLeftCol][tempBottomRow];
            }
            if(gp.tileM.tile[tile1].collision == true || gp.tileM.tile[tile2].collision == true){
                e.collisionOn = true;
                if(e.type == 3 && tile1 != -1 && tile2 != -1 && (gp.tileM.tile[tile1].name.equals("water") || gp.tileM.tile[tile2].name.equals("water"))){
                    e.collisionOn = false;
                }
                
            }

            if(e.type == 3 && tile1 != -1 && tile2 != -1 &&  (gp.tileM.tile[tile1].name.equals("grass") || gp.tileM.tile[tile2].name.equals("grass") )){
                e.collisionOn = true;
            }

            break;

            case "right":
            tempRightCol  = (tempRightX + e.speed )/gp.tileSize;

            if(gp.mapNumber == 2){
                tile1 = gp.tileM.mapTileNum2[tempRightCol][tempTopRow];
                tile2 = gp.tileM.mapTileNum2[tempRightCol][tempBottomRow];
            }
            else{
                tile1 = gp.tileM.mapTileNum[tempRightCol][tempTopRow];
                tile2 = gp.tileM.mapTileNum[tempRightCol][tempBottomRow];
            }
            if(gp.tileM.tile[tile1].collision == true || gp.tileM.tile[tile2].collision == true){
                e.collisionOn = true;
                if(e.type == 3 && tile1 != -1 && tile2 != -1 &&  (gp.tileM.tile[tile1].name.equals("water") || gp.tileM.tile[tile2].name.equals("water"))){
                    e.collisionOn = false;
                }
                
            }

            if(e.type == 3 && tile1 != -1 && tile2 != -1 &&   (gp.tileM.tile[tile1].name.equals("grass") || gp.tileM.tile[tile2].name.equals("grass") )){
                e.collisionOn = true;
            }
            
            break;
            
            
        }

    }
    public int checkCollisionObject(Entity e, boolean player){
        int index = 999;

        for(int i = 0; i < gp.obj.length; i++){

            if(gp.obj[i] != null && gp.obj[i].mapNumberPlace == gp.mapNumber){


                //e.hitbox.x = e.worldX + e.hitbox.x;
                //e.hitbox.y = e.worldY + e.hitbox.y;
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch(e.direction){

                    case "up":
                        e.hitbox.y -= e.speed;

                        if(e.hitbox.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                e.collisionOn = true;
                            }

                            if(player == true){
                                index = i;
                            }
                        }

                    break;

                    case "down":
                        e.hitbox.y += e.speed;
                        if(e.hitbox.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                e.collisionOn = true;
                            }
                            if(player == true){
                               index = i;
                            }
                        }

                    break;

                    case "left":
                        e.hitbox.x -= e.speed;
                        if(e.hitbox.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                e.collisionOn = true;
                            }

                            if(player == true){
                               index = i;
                            }
                        }

                    break;

                    case "right":
                        e.hitbox.x += e.speed;
                        if(e.hitbox.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                e.collisionOn = true;
                            }

                            if(player == true){
                                index = i;
                            }
                        }

                    break;
                }

                e.hitbox.x = e.defaulthitboxX + e.worldX;
                e.hitbox.y = e.defaulthitboxY + e.worldY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }


        return index;
    }
    public int checkCollisionObject(Rectangle attackArea){
        int index = 999;

        for(int i = 0; i < gp.obj.length; i++){

            if(gp.obj[i] != null && gp.obj[i].mapNumberPlace == gp.mapNumber){


                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;


                        if(attackArea.intersects(gp.obj[i].solidArea)){
                           index = i; 
                            
                         }
                         gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                         gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    
    }
    public int checkCollisionEntity(Rectangle attackArea){
        int index = 999;

        for(int i = 0; i < gp.monster.length; i++){

            if(gp.monster[i] != null && gp.monster[i].mapNumberPlace == gp.mapNumber){

                        if(attackArea.intersects(gp.monster[i].hitbox)){
                           index = i; 
                            
                         }
            }
    
        }
        return index;
    }

}