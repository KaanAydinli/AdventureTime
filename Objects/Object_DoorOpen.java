package Objects;

import Main.Game;

public class Object_DoorOpen extends objects{

    Game gp;
    String name = "DoorOpen";
    
    
    public Object_DoorOpen(Game gp){
        super(gp);
        setup("/tiles/dooropen",name,false);
        type = 3;
    }
}