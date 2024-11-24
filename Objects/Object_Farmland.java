package Objects;

import Main.Game;

public class Object_Farmland extends objects{

    Game gp;
    String name = "Farmland";
    
    
    public Object_Farmland(Game gp){
        super(gp);
        setup("/tiles/Farmland",name,false);
        type = 3;
    }
}
