package snake_vs_apple;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;
import javax.swing.*;

@SuppressWarnings("serial")
public class Endless_Mode extends JPanel implements ActionListener{

	static final int sw = 1000;  	  //screen width
	static final int sh = 600;   	  //screen height
	static final int os = 20;    	  //object size
	static final int n = (sw*sh)/os;  //total places to keep objects
	static int delay = 85;
	final int x[] = new int[n];
	final int y[] = new int[n];
	int bp = 4;  					  //body parts of the snake
	int score=0;
	int c=0;
	int bigfruit_time=0;
	int bx, by;						  //x and y coordinates of the big fruit
	int ax, ay;  	 				  //x and y coordinates of the fruit
	char dir = 'R';  				  //direction of the snake
	Timer t;
	boolean running = false;	      //boolean value to check if the game is really running or not
	boolean run = false;
	Random random;
	JFrame f = new JFrame();
	
	ImageIcon body_left_and_right = new ImageIcon("endless mode snake body//body horizontal.png");
	ImageIcon body_up_and_down = new ImageIcon("endless mode snake body//body vertical.png");
	
	ImageIcon head_right = new ImageIcon("endless mode snake head//right head.png");
	ImageIcon head_down = new ImageIcon("endless mode snake head//down head.png");
	ImageIcon head_left = new ImageIcon("endless mode snake head//left head.png");
	ImageIcon head_up = new ImageIcon("endless mode snake head//up head.png");
	
	ImageIcon fruit = new ImageIcon("fruit.png");
	ImageIcon bigfruit = new ImageIcon("Big fruit.png");
	ImageIcon bg = new ImageIcon("all background//field.png");
	
	Endless_Mode() throws IOException{

		random = new Random();
		
		this.setPreferredSize(new Dimension(sw,sh));  //size of the game map
		this.setFocusable(true);
		this.setBackground(Color.green);
		this.addKeyListener(new MyKeyAdapter());
		this.setLayout(null);
		
		f.add(this);
		f.setTitle("Snake Game");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		y[0]=100; 		//snake will start moving from (0,100) coordinate at the very beginning of the game
		StartGame();
	}
	
	public void StartGame() {
		NewApple();
		running = true;
		t = new Timer(delay, this);
		t.start();
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		//g.drawImage(bg.getImage(), 0, 20, sw, sh, null);  //Background
		draw(g);
	}
	
	public void draw(Graphics g) {
		if(running) {
			if(run) {
				g.drawImage(bigfruit.getImage(), bx, by, os, os, null);
			}
			
			g.drawImage(fruit.getImage(), ax, ay, null);
			
			for(int i=0; i<bp; i++) {
				if(i==0) {
					switch(dir) {
					case 'R':
						g.drawImage(head_right.getImage(), x[i], y[i], os, os, null);
						break;
					case 'D':
						g.drawImage(head_down.getImage(), x[i], y[i], os, os, null);
						break;
					case 'L':
						g.drawImage(head_left.getImage(), x[i], y[i], os, os, null);
						break;
					case 'U':
						g.drawImage(head_up.getImage(), x[i], y[i], os, os, null);
						break;
					}
				}
				else{
					switch(dir) {
					case 'L':
					case 'R':
						g.drawImage(body_left_and_right.getImage(), x[i], y[i], os, os, null);
						break;
					case 'U':
					case 'D':
						g.drawImage(body_up_and_down.getImage(), x[i], y[i], os, os, null);
						break;
					}
				}
			
			}
			
			g.setColor(Color.black);
			g.drawLine(0, 20, sw, 20);
			
			g.setFont(new Font("Ariel", Font.BOLD, 16));
			g.drawString("Score: "+score, 4, 16);			//showing score dynamically
		} else
			try {
				GameOver();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void NewApple() {
		ax = random.nextInt((int)(sw/os))*os;
		ay = random.nextInt((int)(sh/os))*os;
		while(true) {
			if(ay<=20)
				ay = random.nextInt((int)(sh/os))*os;		//fruit can't be in the most upper row
			else
				break;
		}
	}
	public void NewBigApple() {
		bx = random.nextInt((int)(sw/os))*os;
		by = random.nextInt((int)(sh/os))*os;
		while(true) {
			if(by<=20)
				by = random.nextInt((int)(sh/os))*os;		//big fruit can't be in the most upper row
			else
				break;
		}
	}
	
	public void Move() {
		for(int i=bp; i>0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(dir) {
		case 'U':
			y[0] = y[0]-os;
			break;
		case 'D':
			y[0] = y[0]+os;
			break;
		case 'L':
			x[0] = x[0]-os;
			break;
		case 'R':
			x[0] = x[0]+os;
			break;
		}
	}
	
	public void CheckApple() {
		if(x[0]==ax && y[0]==ay) {
			if(!run) {
				bigfruit_time++;
			}
			NewApple();
			score+=20;
			bp++;
		}
	}
	public void CheckBigApple() {
		if(x[0]==bx && y[0]==by) {
			c=0;
			run = false;
			score+=50;
			bp++;
		}
	}
	
	public void CheckCol() {
		for(int i=bp; i>0; i--) {
			if(x[0] == x[i] && y[0] == y[i]) {
				running = false;
			}
		}
		
		if(x[0] >= sw)
			x[0] = 0;
		else if(x[0] < 0)
			x[0]=sw;
		else if(y[0] >= sh)
			y[0] = 20;
		else if(y[0] < 20)
			y[0]=sh;
		
		if(running == false) {
			t.stop();
		}
	}
	
	public void GameOver() throws IOException {
		
		f.dispose();
		new GameOver_for_EndlessMode(score);
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		
		public void keyPressed(KeyEvent e) {		//taking keyboard key input for moving the snake
			
			switch(e.getKeyCode()) {
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				if(dir != 'U') {
				dir = 'D';
				break; }
				
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				if(dir != 'D') {
				dir = 'U';
				break; }
			
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				if(dir != 'R') {
				dir = 'L';
				break; }

			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				if(dir != 'L') {
				dir = 'R';
				break; }

			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(running) {
			if(bigfruit_time==2) {
				run = true;
				NewBigApple();
				bigfruit_time = 0;
			}
			if(c==50) {
				//for the timing of the big fruit
				c=0;
				run=false;
			}
			Move();
			CheckApple();
			if(run)
				CheckBigApple();
			CheckCol();
			if(run)
				c++;
		}
		repaint();
	}
}
