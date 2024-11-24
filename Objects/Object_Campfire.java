package Objects;

import Main.Game;

public class Object_Campfire extends objects{

    Game gp;
    String name = "Campfire";
    
    
    public Object_Campfire(Game gp){
        super(gp);
        setup("/tiles/9campfire",name,true);
        type = 3;
    }
}
