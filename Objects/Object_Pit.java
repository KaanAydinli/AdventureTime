package Objects;

import Main.Game;

public class Object_Pit extends objects{

    Game gp;
    String name = "Pit";
    

    public Object_Pit(Game gp){
        super(gp);
        setup("/tiles/pit",name,false);
        type = 3;
    }
}
