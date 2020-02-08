import java.awt.Color;

import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame obJFrame = new JFrame();
		Gameplay gameplay = new Gameplay();
		
		
		obJFrame.setBounds(10,10,905,700);
		obJFrame.setBackground(Color.DARK_GRAY);
		obJFrame.setResizable(false);
		obJFrame.setVisible(true);
		obJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obJFrame.add(gameplay);
		
		
		
		
		
		

	}

}
