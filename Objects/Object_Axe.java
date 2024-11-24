package Objects;

import Main.Game;

public class Object_Axe extends objects{

    Game gp;
    String name = "Axe";
    
    
    public Object_Axe(Game gp){
        super(gp);
        setup("/tiles/Axe",name,false);
        damage = 2;
        type = 0;
        price = 10;
    }
}
