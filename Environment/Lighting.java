package Environment;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

import Main.Game;

public class Lighting{
    BufferedImage darknessFilter;
    int circleSize;
    Game gp;

    public Lighting(Game gp, int circleSize) {

		this.gp = gp;
        this.circleSize = circleSize;
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
				
		// Get the center x and y of the light circle
		int centerX = gp.player.screenX + (gp.tileSize)/2;
		int centerY = gp.player.screenY + (gp.tileSize)/2;
			
		// Create a gradation effect
		Color color[] = new Color[12];
		float fraction[] = new float[12];
		
		color[0] = new Color(0,0,0,0.1f);
		color[1] = new Color(0,0,0,0.42f);
		color[2] = new Color(0,0,0,0.52f);
		color[3] = new Color(0,0,0,0.61f);
		color[4] = new Color(0,0,0,0.69f);
		color[5] = new Color(0,0,0,0.76f);
		color[6] = new Color(0,0,0,0.82f);
		color[7] = new Color(0,0,0,0.87f);
		color[8] = new Color(0,0,0,0.91f);
		color[9] = new Color(0,0,0,0.94f);
		color[10] = new Color(0,0,0,0.96f);
		color[11] = new Color(0,0,0,0.98f);
		
		fraction[0] = 0f;
		fraction[1] = 0.4f;
		fraction[2] = 0.5f;
		fraction[3] = 0.6f;
		fraction[4] = 0.65f;
		fraction[5] = 0.7f;
		fraction[6] = 0.75f;
		fraction[7] = 0.8f;
		fraction[8] = 0.85f;
		fraction[9] = 0.9f;
		fraction[10] = 0.95f;
		fraction[11] = 1f;

        
		// Create a gradation paint settings
		RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (circleSize/2), fraction, color);
		
		// Set the gradient data on g2
		g2.setPaint(gPaint);
		
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
	}
    public void draw(Graphics2D g2){

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.94f));
		g2.drawImage(darknessFilter,0,0,null);
    }
}