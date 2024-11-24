package Objects;

import Main.Game;

public class Object_TreeLog extends objects{

    Game gp;
    String name = "Log";
    
    
    public Object_TreeLog(Game gp){
        super(gp);
        setup("/tiles/treelog",name,false);
        type = 3;
        price = 5;
    }
}
