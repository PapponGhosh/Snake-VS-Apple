package snake_vs_apple;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;

@SuppressWarnings("serial")
public class Level_1 extends JPanel implements ActionListener{

	static final int sw = 1000;  	  //screen width
	static final int sh = 700;   	  //screen height
	static final int os = 20;    	  //object size
	static final int n = (sw*sh)/os;  //total places to keep objects
	static int delay = 0;
	final int x[] = new int[n];
	final int y[] = new int[n];
	int bp = 4;  					  //body parts of the snake
	int score=0;
	int ax, ay;  	 				  //x and y coordinates of the fruit
	char dir = 'R';  				  //direction of the snake
	Timer t;
	boolean running = false;	      //boolean value to check if the game is really running or not
	Random random;
	JFrame f = new JFrame();
	
	ImageIcon fruit = new ImageIcon("fruit.png");
	ImageIcon bg = new ImageIcon("all background//day field.png");
	
	ImageIcon pipe_middle = new ImageIcon("pipes//pipe horizontal.png");
	ImageIcon pipe_left = new ImageIcon("pipes//pipe_left.png");
	ImageIcon pipe_right = new ImageIcon("pipes//pipe_right.png");
	
	ImageIcon right_head = new ImageIcon("black snake head//black snake head right.png");
	ImageIcon left_head = new ImageIcon("black snake head//black snake head left.png");
	ImageIcon up_head = new ImageIcon("black snake head//black snake head up.png");
	ImageIcon down_head = new ImageIcon("black snake head//black snake head down.png");
	
	ImageIcon body_horizontal = new ImageIcon("black snake body//black snake horizontal body.png");
	ImageIcon body_vertical = new ImageIcon("black snake body//black snake vertical body.png");
	
	
	Level_1(int snake_running_time){
		random = new Random();
		delay = snake_running_time;
		y[0]=100;						//snake will start moving from (0,100) coordinate at the very beginning of the game
		
		this.setPreferredSize(new Dimension(sw,sh));	//size of the game map
		this.setFocusable(true);
		this.setBackground(Color.green);
		this.addKeyListener(new MyKeyAdapter());
		
		f.add(this);
		f.setTitle("Snake Game");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.pack();
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		
		startGame();
	}
	
	public void startGame() {
		newApple();
		running = true;
		t = new Timer(delay, this);
		t.start();
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg.getImage(), 0, 20, sw, sh, null);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if(running) {
			
			//apple
			g.drawImage(fruit.getImage(), ax, ay, null);
			
			//pipes
			for(int i=60; i<=900; i++) {
				if(i==60) {
					g.drawImage(pipe_left.getImage(), i, 196,os,os+6, null);
					g.drawImage(pipe_left.getImage(), i, 496,os,os+6, null);
				}
				else if(i==900) {
					g.drawImage(pipe_right.getImage(), i, 197,os,os+6, null);
					g.drawImage(pipe_right.getImage(), i, 497,os,os+6, null);
				}
				else {
					g.drawImage(pipe_middle.getImage(), i, 200, null);
					g.drawImage(pipe_middle.getImage(), i, 500, null);
				}
			}
			
			for(int i=0; i<bp; i++) {
				//snake head
				if(i==0) {
					switch(dir) {
					case 'R':
						g.drawImage(right_head.getImage(), x[i], y[i], null);
						break;
					case 'D':
						g.drawImage(down_head.getImage(), x[i], y[i], null);
						break;
					case 'L':
						g.drawImage(left_head.getImage(), x[i], y[i],  null);
						break;
					case 'U':
						g.drawImage(up_head.getImage(), x[i], y[i], null);
						break;
					}
				}
				//snake body
				else{
					switch(dir) {
					case 'L':
					case 'R':
						g.drawImage(body_horizontal.getImage(), x[i], y[i], os, os, null);
						break;
					case 'U':
					case 'D':
						g.drawImage(body_vertical.getImage(), x[i], y[i], os, os, null);
						break;
					}
				}
			}
			
			g.setColor(Color.black);
			g.drawLine(0,20,1000,20);
			
			g.setFont(new Font("Ariel", Font.BOLD, 16));
			g.drawString("Score: "+score, 4, 16);			//showing score dynamically
			
			g.setColor(Color.blue);
			g.setFont(new Font("Ariel", Font.BOLD, 14));
			g.drawString("LEVEL 1", 940, 16);
		}
		else
			try {
				gameOver();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void newApple() {
		ax = random.nextInt((int)(sw/os))*os;
		ay = random.nextInt((int)(sh/os))*os;
		
		while(true) {
			if(((ax>=60 && ax<=900) && (ay==200 || ay==500)) || ay<=20) {
				ax = random.nextInt((int)(sw/os))*os;
				ay = random.nextInt((int)(sh/os))*os;
			}
			else
				break;
		}
	}
	
	public void move() {
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
	
	public void checkApple() {
		int max=40, min=20;			//score will always increase in between 20 and 40
		if(x[0]==ax && y[0]==ay) {
			newApple();
			score+=Math.floor(Math.random()*(max-min+1)+min);
			if(score>=100) {
				t.stop();
				f.dispose();
				new Level_2(score, delay);
			}
			bp++;
		}
	}
	
	public void checkCol() {
		for(int i=bp; i>0; i--) {
			if(x[0] == x[i] && y[0] == y[i]) {
				running = false;
			}
		}
		for(int i=60; i<=900; i++) {
			if((x[0]==i && y[0]==200) || (x[0]==i && y[0]==500)) {
				running = false;
			}
		}
		
		if(x[0] < 0 || x[0] >= sw || y[0] < 20 || y[0] >= sh) //collides with the border
			running = false;

		
		if(running == false) {
			t.stop();
		}
	}
	
	public void gameOver() throws IOException {
		f.dispose();
		new GameOver_for_LevelMode(score, delay);
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
			move();
			checkApple();
			checkCol();
		}
		repaint();
	}
}
