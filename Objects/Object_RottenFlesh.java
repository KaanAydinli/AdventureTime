package Objects;

import Main.Game;

public class Object_RottenFlesh extends objects{

    Game gp;
    String name = "RottenFlesh";
    
    
    public Object_RottenFlesh(Game gp){
        super(gp);
        setup("/tiles/rottenflesh",name,false);
        type = 2;
        price = 5;
    }
}
