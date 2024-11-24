package Objects;

import Main.Game;

public class Object_FishingRod extends objects{

    Game gp;
    String name = "FishingRod";
    
    
    public Object_FishingRod(Game gp){
        super(gp);
        setup("/tiles/fishingrod",name,false);
        damage = 1;
        type = 0;
    }
}
