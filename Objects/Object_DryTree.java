package Objects;

import Main.Game;

public class Object_DryTree extends objects{

    Game gp;
    String name = "DryTree";
    
    
    public Object_DryTree(Game gp){
        super(gp);
        setup("/tiles/breaktree",name,true);
        type = 4;
        destructable = true;
    }
}