package Objects;

import Main.Game;

public class Object_Door extends objects{

    Game gp;
    String name = "Door";
    
    
    public Object_Door(Game gp){
        super(gp);
        setup("/tiles/doorclosed",name,true);
        type = 3;
    }
}
