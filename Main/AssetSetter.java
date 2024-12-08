package Main;

import Objects.Object_Axe;
import Objects.Object_Campfire;
import Objects.Object_Carrot;
import Objects.Object_CropFarmland;
import Objects.Object_Door;
import Objects.Object_DoorOpen;
import Objects.Object_DryTree;
import Objects.Object_Farmland;
import Objects.Object_FishingRod;
import Objects.Object_Key;
import Objects.Object_Pit;
import Objects.Object_RottenFlesh;
import Objects.Object_Sword;
import Objects.Object_TreeLog;
import entity.Blacksmith;
import entity.Zombie;
public class AssetSetter {
    Game gp;

    public AssetSetter(Game gp){
        this.gp = gp;
    }
    public void setEverything(){
        setNPC();
        setLocationNPC();
        setObjects();
    }
    public void setNPC(){
        gp.npc[0] = new Blacksmith(gp);
        //gp.npc[1] = new Fish(gp);
        gp.monster[0] = new Zombie(gp);
        gp.monster[1] = new Zombie(gp);
        setLocationNPC();
    }
    public void setLocationNPC(){
        gp.monster[0].worldX = 22 * gp.tileSize;
        gp.monster[0].worldY = 7 * gp.tileSize;

        gp.monster[0].startX = 22 * gp.tileSize;
        gp.monster[0].startY = 7 * gp.tileSize;

        gp.npc[0].worldX = 40 * gp.tileSize;
        gp.npc[0].worldY = 29 * gp.tileSize;

        //gp.npc[1].worldX = 20 * gp.tileSize;
        //gp.npc[1].worldY = 20 * gp.tileSize;

        gp.monster[1].worldX = 15 * gp.tileSize;
        gp.monster[1].worldY = 44 * gp.tileSize;

        gp.monster[1].startX = 15 * gp.tileSize;
        gp.monster[1].startY = 44 * gp.tileSize;
    }
    public void setObjects(){
        gp.obj[0] = new Object_Door(gp);
        gp.obj[0].worldX = 40 * gp.tileSize;
        gp.obj[0].worldY = 32 * gp.tileSize;
        gp.obj[0].mapNumberPlace = 1;

        gp.obj[1] = new Object_Pit(gp);
        gp.obj[1].worldX = 58 * gp.tileSize;
        gp.obj[1].worldY = 25 * gp.tileSize;
        gp.obj[1].mapNumberPlace = 1;


        gp.obj[2] = new Object_Pit(gp);
        gp.obj[2].worldX = 24 * gp.tileSize;
        gp.obj[2].worldY = 23 * gp.tileSize;
        gp.obj[2].mapNumberPlace = 2;

        
        gp.obj[3] = new Object_Sword(gp);
        gp.obj[4] = new Object_Key(gp);
        gp.obj[5] = new Object_FishingRod(gp);
        gp.obj[6] = new Object_DoorOpen(gp);
        gp.obj[7] = new Object_Axe(gp);
        gp.obj[8] = new Object_TreeLog(gp);
        
        gp.obj[9] = new Object_DryTree(gp);
        gp.obj[9].worldX = 53 * gp.tileSize;
        gp.obj[9].worldY = 46 * gp.tileSize;
        gp.obj[9].mapNumberPlace = 1;

        gp.obj[10] = new Object_DryTree(gp);
        gp.obj[10].worldX = 54 * gp.tileSize;
        gp.obj[10].worldY = 46 * gp.tileSize;
        gp.obj[10].mapNumberPlace = 1;

        gp.obj[11] = new Object_DryTree(gp);
        gp.obj[11].worldX = 55 * gp.tileSize;
        gp.obj[11].worldY = 46 * gp.tileSize;
        gp.obj[11].mapNumberPlace = 1;

        gp.obj[12] = new Object_DryTree(gp);
        gp.obj[12].worldX = 56 * gp.tileSize;
        gp.obj[12].worldY = 46 * gp.tileSize;
        gp.obj[12].mapNumberPlace = 1;

        gp.obj[13] = new Object_Carrot(gp);

        gp.obj[14] = new Object_Farmland(gp);
        gp.obj[14].worldX = 52 * gp.tileSize;
        gp.obj[14].worldY = 52 * gp.tileSize;
        gp.obj[14].mapNumberPlace = 1;

        gp.obj[15] = new Object_Farmland(gp);
        gp.obj[15].worldX = 51 * gp.tileSize;
        gp.obj[15].worldY = 52 * gp.tileSize;
        gp.obj[15].mapNumberPlace = 1;

        gp.obj[16] = new Object_CropFarmland(gp);



        gp.obj[17] = new Object_DryTree(gp);
        gp.obj[17].worldX = 66 * gp.tileSize;
        gp.obj[17].worldY = 66 * gp.tileSize;
        gp.obj[17].mapNumberPlace = 1;

        gp.obj[18] = new Object_DryTree(gp);
        gp.obj[18].worldX = 66 * gp.tileSize;
        gp.obj[18].worldY = 67 * gp.tileSize;
        gp.obj[18].mapNumberPlace = 1;

        gp.obj[19] = new Object_DryTree(gp);
        gp.obj[19].worldX = 66 * gp.tileSize;
        gp.obj[19].worldY = 68 * gp.tileSize;
        gp.obj[19].mapNumberPlace = 1;

        gp.obj[20] = new Object_DryTree(gp);
        gp.obj[20].worldX = 66 * gp.tileSize;
        gp.obj[20].worldY = 69 * gp.tileSize;
        gp.obj[20].mapNumberPlace = 1;

        gp.obj[21] = new Object_DryTree(gp);
        gp.obj[21].worldX = 67 * gp.tileSize;
        gp.obj[21].worldY = 66 * gp.tileSize;
        gp.obj[21].mapNumberPlace = 1;

        gp.obj[22] = new Object_DryTree(gp);
        gp.obj[22].worldX = 67 * gp.tileSize;
        gp.obj[22].worldY = 67 * gp.tileSize;
        gp.obj[22].mapNumberPlace = 1;

        gp.obj[23] = new Object_DryTree(gp);
        gp.obj[23].worldX = 67 * gp.tileSize;
        gp.obj[23].worldY = 68 * gp.tileSize;
        gp.obj[23].mapNumberPlace = 1;

        gp.obj[24] = new Object_DryTree(gp);
        gp.obj[24].worldX = 67 * gp.tileSize;
        gp.obj[24].worldY = 69 * gp.tileSize;
        gp.obj[24].mapNumberPlace = 1;

        gp.obj[25] = new Object_DryTree(gp);
        gp.obj[25].worldX = 68 * gp.tileSize;
        gp.obj[25].worldY = 66 * gp.tileSize;
        gp.obj[25].mapNumberPlace = 1;

        gp.obj[26] = new Object_DryTree(gp);
        gp.obj[26].worldX = 68 * gp.tileSize;
        gp.obj[26].worldY = 67 * gp.tileSize;
        gp.obj[26].mapNumberPlace = 1;

        gp.obj[27] = new Object_DryTree(gp);
        gp.obj[27].worldX = 68 * gp.tileSize;
        gp.obj[27].worldY = 68 * gp.tileSize;
        gp.obj[27].mapNumberPlace = 1;

        gp.obj[28] = new Object_DryTree(gp);
        gp.obj[28].worldX = 68 * gp.tileSize;
        gp.obj[28].worldY = 69 * gp.tileSize;
        gp.obj[28].mapNumberPlace = 1;

        gp.obj[29] = new Object_Campfire(gp);
        gp.obj[29].worldX = 52 * gp.tileSize;
        gp.obj[29].worldY = 81 * gp.tileSize;
        gp.obj[29].mapNumberPlace = 1;

        gp.obj[30] = new Object_RottenFlesh(gp);


        gp.player.inventory[0][1] = gp.obj[4];
        gp.player.inventory[0][2] = gp.obj[5];
        gp.player.inventory[0][3] = gp.obj[13];
        gp.player.inventory[0][0] = gp.obj[7];
        gp.npc[0].inventory[0][0] = gp.obj[3];
        gp.npc[0].inventory[0][1] = gp.obj[7];     
        gp.npc[0].inventory[0][2] = gp.obj[8];
        gp.npc[0].inventory[0][3] = gp.obj[13];
        gp.npc[0].inventory[1][0] = gp.obj[30];

    }
    public void setMonster(int index){
        gp.monster[index] = new Zombie(gp);
        gp.monster[index].worldX = 22 * gp.tileSize;
        gp.monster[index].worldY = 7 * gp.tileSize;

        gp.monster[index].startX = 22 * gp.tileSize;
        gp.monster[index].startY = 7 * gp.tileSize;


    }
}
