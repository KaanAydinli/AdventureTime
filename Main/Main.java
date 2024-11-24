package Main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
	
    public static void main(String[] args) {
        
        JFrame window = new JFrame();
		Game gp = new Game();
		ImageIcon icon = new ImageIcon("down1.png");
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Edvençur geym");
		window.setIconImage(icon.getImage());
		window.add(gp);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gp.startGameThread();
    }
	
}
