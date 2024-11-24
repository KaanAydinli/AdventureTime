package EntitySpawner;

import Main.Game;

public class Spawner {

    Game gp;
    int monsterCount = 0;
    int cooldown = 0;
    public Spawner(Game gp) {
        this.gp = gp;
        
    }
    public void generateFish(){}
    public void generateZombie(){

        for(int i = 0; i < gp.monster.length;i++){
            if(gp.monster[i] != null){
                monsterCount++;
            }

            if(monsterCount < 5){
                if(gp.monster[i] == null && cooldown > 700){
                    gp.aSetter.setMonster(i);
                    cooldown = 0;
                }
                
            }
        }
        cooldown++;
        monsterCount = 0;
    }
    
}
