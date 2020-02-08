import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class Gameplay extends JPanel implements KeyListener,ActionListener {
	private ImageIcon title;
	
	private ImageIcon upMouthIcon;
	private ImageIcon downMouthIcon;
	private ImageIcon leftMouthIcon;
	private ImageIcon rightMouthIcon;
	private ImageIcon snakeBodyHorizIcon;
	private ImageIcon snakeBodyVertIcon;
	public boolean first_time = true;
	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;
	public int Score = 0;
	public int[] SnakeXLength = new int[300];
	public int[] SnakeYLength = new int[300];
	private javax.swing.Timer timer;
	private boolean first_move = true;
	//private Timer timer;
	private boolean Game_over_trigger = false;
	private int difficulty = 100;
	private int SnakeLength = 3;
	private int [] FoodXPosition = {25,50,75,100,125,150,175,200,225,250,275,300,
		325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750	};
	private int [] FoodYPosition = {75,100,125,150,175,200,225,250,275,300,
			325,350,375,400,425,450,475,500,525,550,575	};
	private Random random = new Random();
	private ImageIcon foodImageIcon;
	
	
	private int FoodX_length= FoodXPosition.length;
	private int FoodY_length= FoodYPosition.length;
	private int rX = random.nextInt(FoodX_length);
	private int rY = random.nextInt(FoodY_length);
	
	
	
	
	public Gameplay() {

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		if (first_time) {
		System.out.println("Press Key to set the difficulty");
		System.out.println("1-Easy");
		System.out.println("2-Normal");
		System.out.println("3-Hard");
		difficulty = Set_Difficulty();
		}
		
		timer =  new javax.swing.Timer(difficulty,this);
		timer.start();
		
		
		
		
	}
	
	public int Set_Difficulty() {
		int output = 0;
		Scanner input = new Scanner(System.in);
		 output = input.nextInt();
		while (output!=1 && output!=2 && output!=3) {
			output = input.nextInt();
			
		}
		if (output==1) {
			difficulty = 300;
		}
		if (output==2) {
			difficulty = 200;
		}
		if (output==3) {
			difficulty = 100;
		}
		first_time = false;
		return difficulty;
		
	}
	
	
	public void  paint(Graphics g) {
		
		
		if (first_move) {
			
			
			
			SnakeXLength[2] = 50;
			SnakeXLength[1] = 75;
			SnakeXLength[0] = 100;
			
			SnakeYLength[2] = 100;
			SnakeYLength[1] = 100;
			SnakeYLength[0] = 100;
			
			foodImageIcon = new ImageIcon("food_final.png");
			foodImageIcon.paintIcon(this, g, FoodXPosition[0], FoodYPosition[1]);
			first_move = false;
			}
		
		
		
		
		//Draw Title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 855, 55);
		g.setColor(Color.orange);
		g.fillRect(25, 11, 854, 54);
		
		
		
		//Draw title image
		title = new ImageIcon("title2.png");
		title.paintIcon(this, g, 270, -8);
		
		//draw Score
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.BOLD,14));
		g.drawString("Score:"+Score, 800, 50);
		
		//Draw border Gameplay
		g.setColor(Color.white);
		g.drawRect(24, 74, 855, 577);
		
		//draw background Gameplay
		g.setColor(Color.black);
		g.fillRect(25, 75, 854, 576);
		
		
		
		rightMouthIcon = new ImageIcon("right_tr.png");
		rightMouthIcon.paintIcon(this, g, SnakeXLength[0], SnakeYLength[0]);
		
		for(int a = 0; a<SnakeLength; a++) {
			
			if ( a==0 && right) {
				rightMouthIcon = new ImageIcon("right_tr.png");
				rightMouthIcon.paintIcon(this, g, SnakeXLength[a], SnakeYLength[a]);
			}
			if ( a==0 && left) {
				leftMouthIcon = new ImageIcon("left_tr.png");
				leftMouthIcon.paintIcon(this, g, SnakeXLength[a], SnakeYLength[a]);
			}
			
			if ( a==0 && down) {
				downMouthIcon = new ImageIcon("down_tr.png");
				downMouthIcon.paintIcon(this, g, SnakeXLength[a], SnakeYLength[a]);
			}
			if ( a==0 && up) {
				upMouthIcon = new ImageIcon("up_tr.png");
				upMouthIcon.paintIcon(this, g, SnakeXLength[a], SnakeYLength[a]);
			}
			
			if (a!=0) {
				snakeBodyHorizIcon = new ImageIcon("body_tr.png");
				snakeBodyHorizIcon.paintIcon(this, g, SnakeXLength[a], SnakeYLength[a]);
			}
			
			
			
			
			
			
			
		}
		
		eat();
		foodImageIcon.paintIcon(this, g, FoodXPosition[rX], FoodYPosition[rY]);
		
		Game_Over();
		if (Game_over_trigger) {
			g.setColor(Color.RED);
			g.setFont(new Font("arial", Font.BOLD,35));
			g.drawString("Game Over", 350, 250);
			timer.stop();
		}
		
		g.dispose();
		
		
		
	}

	
	
	public void eat() {
		if (SnakeXLength[0] == FoodXPosition[rX] && 
				SnakeYLength[0] == FoodYPosition[rY]) {
			//moves++;
			SnakeLength++;
			Score++;
			rX = random.nextInt(FoodX_length);
			rY = random.nextInt(FoodY_length);
			
			
		}
		
	}
	
	public void Game_Over() {
		
		for (int i=1; i<=SnakeLength; i++) {
			if (SnakeXLength[0] == SnakeXLength[i] && 
					SnakeYLength[0] == SnakeYLength[i]) {
				Game_over_trigger = true;
				break;
				
			}
			
			
		}
		}
		
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//timer.start();
		
		if (right) {
			for (int r= SnakeLength-1; r>=0; r--) {
				SnakeYLength[r+1] = SnakeYLength[r];
			}
			//in case snakes goes out of bounds.
			for (int r = SnakeLength; r>=0; r--) {
				if (r==0) {
					SnakeXLength[0] = SnakeXLength[r] + 25;
				}else {
					SnakeXLength[r] = SnakeXLength[r-1];

				}
				if (SnakeXLength[r]>850) {
					SnakeXLength[r] = 25; 
				}
				
				
			}
			repaint();
		}
			
		
		
		if (left) {
			for (int r=SnakeLength-1; r>=0; r--) {
				SnakeYLength[r+1] = SnakeYLength[r];
			}
			
			for (int r = SnakeLength; r>=0; r--) {
				if (r==0) {
					SnakeXLength[0] = SnakeXLength[r] - 25;
				}else {
					SnakeXLength[r] = SnakeXLength[r-1];

				}
				if (SnakeXLength[r]<25) {
					SnakeXLength[r] = 850; 
				}
			}
				
			
			repaint();
		}
		
		if (up) {
			for (int r=SnakeLength-1; r>=0; r--) {
				SnakeXLength[r+1] = SnakeXLength[r];
			}
			
			for (int r = SnakeLength; r>=0; r--) {
				if (r==0) {
					SnakeYLength[0] = SnakeYLength[r] - 25;
				}else {
					SnakeYLength[r] = SnakeYLength[r-1];

				}
				if (SnakeYLength[r]<75) {
					SnakeYLength[r] = 625; 
				}
			}
				
			
			repaint();
		}
			
	
		
		if (down) {
			for (int r=SnakeLength-1; r>=0; r--) {
				SnakeXLength[r+1] = SnakeXLength[r];
			}
			
			for (int r = SnakeLength; r>=0; r--) {
				if (r==0) {
					SnakeYLength[0] = SnakeYLength[r] + 25;
				}else {
					SnakeYLength[r] = SnakeYLength[r-1];

				}
				if (SnakeYLength[r]>625) {
					SnakeYLength[r] = 75; 
				}
			}
				
			
			repaint();
			
	
		}
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()== KeyEvent.VK_RIGHT) {
			//moves++;
			right = true;
			if (left) {
				right  = false;
				left = true;
			}
			
			
			up = false;
			down = false;
			
		}
		
		if (e.getKeyCode()== KeyEvent.VK_LEFT) {
			//moves++;
			left = true;
			if (right) {
				left = false;
				right = true;
			}
			up = false;
			down = false;
		}
		
		if (e.getKeyCode()== KeyEvent.VK_UP) {
			//moves++;
			up = true;
			if (down) {
				up = false;
				down = true;
			}
			left = false;
			right = false;
			
		}
		
		if (e.getKeyCode()== KeyEvent.VK_DOWN) {
			//moves++;
			down = true;
			if (up) {
				down = false;
				up = true;
			}
			left = false;
			right = false;
			
		}
		
		
		
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
