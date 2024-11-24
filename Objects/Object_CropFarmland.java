package Objects;

import Main.Game;

public class Object_CropFarmland extends objects{

    Game gp;
    String name = "CropFarmland";
    
    
    public Object_CropFarmland(Game gp){
        super(gp);
        setup("/tiles/cropfarmland",name,false);
        type = 3;
    }
}
