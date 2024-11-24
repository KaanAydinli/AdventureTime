package Objects;

import Main.Game;

public class Object_Key extends objects{

    Game gp;
    String name = "Key";
    
    
    public Object_Key(Game gp){
        super(gp);
        setup("/tiles/key",name,false);
        type = 2;
    }
}
