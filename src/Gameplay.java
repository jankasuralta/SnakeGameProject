//class for gameplay
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent; 		//implement arrow keys for snake movement
import java.awt.event.ActionListener;	//implement arrow keys for snake movement
import java.awt.event.KeyEvent;			//implement arrow keys for snake movement
import java.awt.event.KeyListener;		//implement arrow keys for snake movement
import java.util.Random;				// randomizer

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Gameplay extends JPanel implements KeyListener, ActionListener {
	
	
	private static final long serialVersionUID = 1L;
	//arrays for x-position and y-position
	private int[] snakeXlength = new int [750];
	private int[] snakeYlength = new int [750];
	
	//variables for detecting the movement of snake
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	//variables for snake face
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon leftmouth;
	
	private int lengthOfSnake = 3; //default length of the snake
	
	//the speed of the snake 
	private Timer timer;
	private int delay = 100;
	
	
	private ImageIcon snakeimage;//variable for snake enemy
	
	//default positions for food pick-up
	private int[] Xfood = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 
							   475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
	
	private int[] Yfood = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350 ,375, 400, 425, 450, 475, 500,						
							   525, 550, 575, 600, 625};
	
	private ImageIcon food;
	
	private Random random = new Random (); //variable for random food position
	
	private int Xposition = random.nextInt(34); //34 number of x positions
	private int Yposition = random.nextInt(23); //23 number of y positions
	
	private int Score = 0; //variable for score
	
	
	private int moves = 0;
	
	private ImageIcon titleImage; 
	

	public Gameplay() {
		
		addKeyListener(this); //adds KeyListener
		
		//setting formalities
		setFocusable (true);
		setFocusTraversalKeysEnabled (false);
		timer = new Timer(delay, this);
		timer.start(); 
	}
	
	 // paint method to draw everything in the panel
	public void paint(Graphics g) {
		
		if (moves == 0) {
			
			//the default position of the snake
			snakeXlength [2] = 50;
			snakeXlength [1] = 75;
			snakeXlength [0] = 100;
			
			snakeYlength [2] = 100;
			snakeYlength [1] = 100;
			snakeYlength [0] = 100;
			
			
		}
		//draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		//draw titleImage
		titleImage = new ImageIcon ("title.png");
		titleImage.paintIcon(this, g, 25, 11);
		
		//draw gameplay (border)
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		
		//draw gameplay (Background)
		g.setColor(Color.white);
		g.fillRect(25, 75,850, 575); 
		// values are calculated
		//sprites of the assets are 25x25
		// height and width must be a multiple of 25
		
		//draw score
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString ("Score: " +Score, 780, 30);
		
		//draw snake length
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString ("Length: " +lengthOfSnake, 780, 50);
		
		
		//defining the default position of the snake
		rightmouth = new ImageIcon ("rightmouth1.png");
		rightmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
		
		//loop
		for (int a = 0; a < lengthOfSnake; a++) {
			
			//detecting the direction of the snake
			if(a == 0 && right) {
				rightmouth = new ImageIcon ("rightmouth1.png");
				rightmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
			
			if(a == 0 && left) {
				leftmouth = new ImageIcon ("leftmouth1.png");
				leftmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
			
			if(a == 0 && down) {
				downmouth = new ImageIcon ("downmouth1.png");
				downmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
			
			if(a == 0 && up) {
				upmouth = new ImageIcon ("upmouth1.png");
				upmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
			
			//body of the snake
			if (a!=0) {
				
				snakeimage = new ImageIcon ("snakeimage1.png");
				snakeimage.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
		}
		
		//detecting if head of snake is colliding with the food
		food = new ImageIcon ("food.png"); //image of food
	
		//check of the food collided with the snake head
		if ((Xfood [Xposition] == snakeXlength[0] && Yfood[Yposition] == snakeYlength[0] ))
		{
			
			//increment snake length if food collided with snake head
			lengthOfSnake ++;
			Score++; //increment score
			
			//respawn food on another position
			Xposition = random.nextInt(34);
			Yposition = random.nextInt(23);
			
		}
		//draw food 
		food.paintIcon(this, g, Xfood[Xposition], Yfood[Yposition]);
		
		//detecting the colliding of head to the snake body for game over
		for (int body =1; body < lengthOfSnake; body++)
		{
			if (snakeXlength[body] == snakeXlength[0] && snakeYlength[body] == snakeYlength[0])
			{
				//if colliding occurs set all variables of movement to false
				right = false;
				left = false;
				up = false;
				down = false;
				
				//show the GAME OVER
				g.setColor(Color.BLACK);
				g.setFont(new Font ("arial", Font.BOLD, 50));
				g.drawString("GAME OVER", 300, 300);
				
				//show press "SPACEBAR" to restart the game
				g.setColor(Color.BLACK);
				g.setFont(new Font ("arial", Font.BOLD, 25));
				g.drawString("PRESS SPACE BAR or RETURN/ENTER TO RESTART THE GAME", 50, 340);
				
			}
		}
		g.dispose();						
	}
	
	
	//method added from ActionListener
	//code logic for the body
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		//check which variable is true based on the key press
		if (right)
		{
			for (int i = lengthOfSnake -1 ; i>=0; i--) {
				//shifting head position to next index
				snakeYlength[i+1] = snakeYlength[i];
			}
			//shifting the position of snakeXlength
			for (int i = lengthOfSnake; i>=0; i--) {
				if (i==0)
				{
					
					snakeXlength [i] = snakeXlength [i] + 25; //+25 because the body image is 25x25 
				}
				else
				{
					snakeXlength [i] = snakeXlength [i-1];
				}
				//if snake touches the border, it will come out on the opposite side
				if (snakeXlength[i] >850) {
					snakeXlength [i] =25;
				}
					
			}
			
			repaint (); //calls paint method again
			
			
		}
		if (left)
		{
			for (int i = lengthOfSnake ; i>=0; i--) {
				snakeYlength [i+1] = snakeYlength [i];
			}
			
			for (int i = lengthOfSnake; i>=0; i--) {
				if (i==0) 
				{
					
					snakeXlength [i] = snakeXlength [i] -25; 
				}
				else
				{
					snakeXlength [i] = snakeXlength [i-1];
				}
				
				if (snakeXlength[i] < 25) {
					snakeXlength [i] = 850;
				}
					
			}
			
			repaint ();
		}
		
		if (up)
		{
			for (int i = lengthOfSnake -1 ; i>=0; i--) {
			
				snakeXlength[i+1] = snakeXlength[i];
			}
			
			for (int i = lengthOfSnake; i>=0; i--) {
				if (i==0)
				{
					
					snakeYlength [i] = snakeYlength [i] - 25;
				}
				else
				{
					snakeYlength [i] = snakeYlength [i-1];
				}
				
				if (snakeYlength[i] < 75) {
					snakeYlength [i] = 625;
				}
					
			}
			
			repaint ();
		}
		if (down)
		{
			for (int i = lengthOfSnake -1 ; i>=0; i--) {
				
				snakeXlength[i+1] = snakeXlength[i];
			}
			
			for (int i = lengthOfSnake; i>=0; i--) {
				if (i==0)
				{
					
					snakeYlength [i] = snakeYlength [i] + 25;
				}
				else
				{
					snakeYlength [i] = snakeYlength [i-1];
				}
				
				if (snakeYlength[i] > 625) {
					snakeYlength [i] = 75;
				}
					
			}
			
			repaint ();
		}
		
		
	}
	//method added from KeyListener
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	//method added from KeyListener
	//detecting keys pressed
	@Override
	public void keyPressed(KeyEvent e) {
		
		// press space to restart game
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			moves = 0;
			Score = 0;
			lengthOfSnake = 3;
			repaint();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			moves = 0;
			Score = 0;
			lengthOfSnake = 3;
			repaint();
		}
		
		if (e.getKeyCode()== KeyEvent.VK_RIGHT) {
			
			moves++;// increment moves so that the snake will not always be in default position
			right = true;
			//check if snake is moving to the right then it cant be moved to left
			// otherwise it will collide on itself
			if (!left)
			{
				right = true; 
			}
			else
			{
				right = false;
				left = true;
			}
			 
			down = false;
			up = false;
			
		}	
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			left = true;
			
			if (!right)
			{
				left = true;
			}
			else
			{
				left = false;
				right = true;
			}
			down = false;
			up = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			up = true;
			
			if (!down)
			{
				up = true;
			}
			else
			{
				up = false;
				down = true;
			}
			left = false;
			right = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			down = true;
			
			if (!up)
			{
				down = true;
			}
			else
			{
				down = false;
				up = true;
			}
			left = false;
			right = false;
		}
	}
}
