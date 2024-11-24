package Objects;

import Main.Game;

public class Object_Carrot extends objects{

    Game gp;
    String name = "Carrot";
    
    
    public Object_Carrot(Game gp){
        super(gp);
        setup("/tiles/carrot",name,false);
        type = 1;
        price = 2;
    }
}
