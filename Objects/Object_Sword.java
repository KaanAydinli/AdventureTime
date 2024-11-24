package Objects;

import Main.Game;

public class Object_Sword extends objects{

    Game gp;
    String name = "Sword";
    
    
    public Object_Sword(Game gp){
        super(gp);
        setup("/tiles/GreatSword",name,false);
        damage = 4;
        type = 0;
        price = 10;
    }
}
