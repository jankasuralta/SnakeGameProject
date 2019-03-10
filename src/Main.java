import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame snake = new JFrame ();
		Gameplay gameplay = new Gameplay (); //object gameplay from class gameplay
		// properties of the frame
		snake.setBackground(Color.BLACK);
		snake.setBounds(15, 15, 900, 700);
		snake.setVisible(true);
		snake.setResizable(false); //false in order for the user not to resize the game
		snake.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		snake.add(gameplay); //add object gameplay to JFrame
		

	}

}
  