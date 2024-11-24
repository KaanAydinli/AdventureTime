package Main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener {

    Game gp;
    public boolean up,down,left,right,enter, enterReleased,space,spaceReleased,escape;
    public KeyHandler(Game gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        space = false;
        
        if(gp.gameState == gp.playState){
            if(code == KeyEvent.VK_W){
                up = true;
            }
            if(code == KeyEvent.VK_S){
                down = true;
            }
            if(code == KeyEvent.VK_D){
                right = true;
            }
            if(code == KeyEvent.VK_A){
                left = true;
            }
            if(code == KeyEvent.VK_ENTER){
                enter = true;
                
            }
            if(code == KeyEvent.VK_SPACE){
                space = true;         
            }
            if(code == KeyEvent.VK_E){ 
                gp.gameState = gp.inventoryState;
            }
            if(code == KeyEvent.VK_ESCAPE){
                escape = true;
                gp.gameState = gp.settingsState;
            }
            if(code == KeyEvent.VK_R){
                gp.tileM.loadMap();
            }
            if(code == KeyEvent.VK_M){
                gp.gameState = gp.mapState;
            }

        }
        else if(gp.gameState == gp.mapState){
            if(code == KeyEvent.VK_M){
                gp.gameState = gp.playState;
            }
        }
        else if(gp.titleState == gp.gameState){

            if(code == KeyEvent.VK_W){
                gp.ui.choice--;
                if(gp.ui.choice < 0){
                    gp.ui.choice = 2;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.choice++;
                if(gp.ui.choice > 2){
                    gp.ui.choice = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                enter = true;
                
            }  
        }
        else if(gp.gameState == gp.inventoryState){
            if(code == KeyEvent.VK_W){
                gp.ui.inventoryY--;
                if(gp.ui.inventoryY < 0){
                    gp.ui.inventoryY = 3;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.inventoryY++;
                if(gp.ui.inventoryY > 3){
                    gp.ui.inventoryY = 0;
                }
            }
            if(code == KeyEvent.VK_A){
                gp.ui.inventoryX--;
                if(gp.ui.inventoryX < 0){
                    gp.ui.inventoryX = 5;
                }
            }
            if(code == KeyEvent.VK_D){
                gp.ui.inventoryX++;
                if(gp.ui.inventoryX > 5){
                    gp.ui.inventoryX = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                enter = true;
                
            }  
            if(code == KeyEvent.VK_E){
                gp.gameState = gp.playState;
                
            } 
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.playState;
                
            } 
        }
        else if(gp.gameState == gp.dialogueState){
           
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;    
            }  

        }
        else if(gp.gameState == gp.settingsState){
            if(code == KeyEvent.VK_ESCAPE){
                escape = true;
                gp.gameState = gp.playState;
            }
            if(code == KeyEvent.VK_W){
                gp.ui.choiceSettings--;
                if(gp.ui.choiceSettings < 0){
                    gp.ui.choiceSettings = 3;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.choiceSettings++;
                if(gp.ui.choiceSettings > 3){
                    gp.ui.choiceSettings = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                enter = true;
                
            }  
            if(code == KeyEvent.VK_ESCAPE){
                escape = true;
                gp.gameState = gp.playState;
            }
        }
        else if(gp.gameState == gp.controlsState){
            if(code == KeyEvent.VK_ESCAPE){
                escape = true;
                gp.gameState = gp.settingsState;
            }
        }
        else if(gp.gameState == gp.statsState){
            if(code == KeyEvent.VK_ESCAPE){
                escape = true;
                gp.gameState = gp.settingsState;
            }
        }
        else if(gp.gameState == gp.deathState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
                gp.player.respawn();
                gp.mapNumber = 1;
            }
        }
        else if(gp.gameState == gp.tradeState &&  gp.ui.subTrade){
            if(code == KeyEvent.VK_W){
                gp.ui.subStateY--;
                if(gp.ui.subStateY < 0){
                    gp.ui.subStateY = 2;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.subStateY++;
                
                if(gp.ui.subStateY > 2){
                    gp.ui.subStateY = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.subStateY == 0){
                    gp.ui.current = gp.ui.buy;
                }
                else if(gp.ui.subStateY == 1){
                    gp.ui.current = gp.ui.sell;
                }
                else if(gp.ui.subStateY == 2){
                    gp.gameState = gp.playState;
                }
                gp.ui.subTrade = false;
            }
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.playState;
            }
        }
        else if(gp.gameState == gp.tradeState &&  gp.ui.current == gp.ui.buy){
            if(code == KeyEvent.VK_W){
                gp.ui.tradeBuyY--;
                if(gp.ui.tradeBuyY < 0){
                    gp.ui.tradeBuyY = 1;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.tradeBuyY++;
                
                if(gp.ui.tradeBuyY > 1){
                    gp.ui.tradeBuyY = 0;
                }
            }
            if(code == KeyEvent.VK_A){
                gp.ui.tradeBuyX--;
                if(gp.ui.tradeBuyX < 0){
                    gp.ui.tradeBuyX = 3;
                }
            }
            if(code == KeyEvent.VK_D){
                gp.ui.tradeBuyX++;
                if(gp.ui.tradeBuyX > 3){
                    gp.ui.tradeBuyX = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER && gp.npc[0].inventory[gp.ui.tradeBuyY][gp.ui.tradeBuyX] != null){
                gp.gameState = gp.sureState;
            }
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.playState;
            }
        }
        else if(gp.gameState == gp.tradeState &&  gp.ui.current == gp.ui.sell){
            if(code == KeyEvent.VK_W){
                gp.ui.tradeSellY--;
                if(gp.ui.tradeSellY < 0){
                    gp.ui.tradeSellY = 3;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.tradeSellY++;
                if(gp.ui.tradeSellY > 3){
                    gp.ui.tradeSellY = 0;
                }
            }
            if(code == KeyEvent.VK_A){
                gp.ui.tradeSellX--;
                if(gp.ui.tradeSellX < 0){
                    gp.ui.tradeSellX = 5;
                }
            }
            if(code == KeyEvent.VK_D){
                gp.ui.tradeSellX++;
                if(gp.ui.tradeSellX > 5){
                    gp.ui.tradeSellX = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER  && gp.player.inventory[gp.ui.tradeSellY][gp.ui.tradeSellX] != null){
                gp.gameState = gp.sureState;
            }
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.playState;
            }
        }
        else if(gp.gameState == gp.sureState){
            
            if(code == KeyEvent.VK_A){
                gp.ui.sureX--;
                if(gp.ui.sureX < 0){
                    gp.ui.sureX = 0;
                }
            }
            if(code == KeyEvent.VK_D){
                gp.ui.sureX++;
                if(gp.ui.sureX > 1){
                    gp.ui.sureX = 1;
                }
            }
            if(code == KeyEvent.VK_ENTER && gp.ui.sureX == 0){   

                if(gp.ui.current == gp.ui.buy){
                    gp.ui.buying = true;
                }
                else if(gp.ui.current == gp.ui.sell){
                    gp.ui.selling = true;
                }
                
                    
            }
            if(code == KeyEvent.VK_ENTER && gp.ui.sureX == 1){   
                gp.gameState = gp.tradeState;
                    
            }
            
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.playState;
            }
        }
    
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        enterReleased = false;
        spaceReleased = false;

        if(code == KeyEvent.VK_W){
            up = false;
        }
        if(code == KeyEvent.VK_S){
            down = false;
        }
        if(code == KeyEvent.VK_D){
            right = false;
        }
        if(code == KeyEvent.VK_A){
            left = false;
        }
        if(code == KeyEvent.VK_ENTER){
            enterReleased = true;
            enter = false;

        }
        if(code == KeyEvent.VK_SPACE){
            spaceReleased = true;
            
        }
        if(code == KeyEvent.VK_ESCAPE){
            escape = false;
        }
    }  
}
