package snake_vs_apple;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class GameOver_for_LevelMode implements ActionListener{
	
	int delay;
	JFrame f;
	JButton quit_button;
	JButton menu_back_button;

	GameOver_for_LevelMode(int score, int delay) throws IOException{
		this.delay = delay;
		
		//High Score
		BufferedReader r = new BufferedReader(new FileReader("level score.txt"));
		boolean high_s=false;
		int high_score=Integer.parseInt(r.readLine());
		r.close();
			
		if(score>high_score) {		//if the current score is greater than the previous high score only then the bufferedWriter will work
			high_s=true;
			BufferedWriter w = new BufferedWriter(new FileWriter("level score.txt"));
			w.write(Integer.toString(score));		//will write the current score as the high score in file
			w.close();
		}
		
		
		//Panel Work
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(800,600));
		p.setBackground(Color.black);
		p.setFocusable(true);
		p.setLayout(null);
		p.addKeyListener(new game_restart());
		
		
		//Label Work
		JLabel gameover_text = new JLabel("Game Over");
		gameover_text.setFont(new Font("Ink Free", Font.BOLD, 80));
		gameover_text.setForeground(Color.red);
		gameover_text.setBounds(180, 80, 450, 70);
		
		JLabel show_score = new JLabel("Score: "+String.valueOf(score));
		show_score.setFont(new Font("Ink Free", Font.BOLD, 50));
		show_score.setForeground(Color.green);
		show_score.setBounds(255, 160, 380, 50);
		
		JLabel show_high_score = new JLabel("High Score!");
		show_high_score.setFont(new Font("Ink Free", Font.BOLD, 20));
		show_high_score.setForeground(Color.blue);
		show_high_score.setBounds(450, 145, 250, 20);
		
		JLabel press_enter_text = new JLabel("Press Enter to Restart");
		press_enter_text.setFont(new Font("Ink Free", Font.BOLD, 60));
		press_enter_text.setForeground(Color.white);
		press_enter_text.setBounds(80, 280, 650, 50);
		
		
		//Button Work
		menu_back_button = new JButton();
		menu_back_button.setText("Back to Menu");
		menu_back_button.setFocusable(false);
		menu_back_button.setBounds(320, 395, 120, 30);
		menu_back_button.addActionListener(this);
		
		quit_button = new JButton();
		quit_button.setText("Quit Game");
		quit_button.setFocusable(false);
		quit_button.setBounds(320, 430, 120, 30);
		quit_button.addActionListener(this);
		
		
		f = new JFrame();
		f.setTitle("GAME OVER");
		f.setResizable(false);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		p.add(gameover_text);
		p.add(show_score);
		
		if(high_s)
		p.add(show_high_score);		//it will show "high score!" if only it's really the high score
		
		p.add(press_enter_text);
		p.add(menu_back_button);
		p.add(quit_button);
		f.add(p);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public class game_restart extends KeyAdapter{
		@SuppressWarnings("static-access")
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==e.VK_ENTER) {
				f.dispose();
				new Level_1(delay);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==menu_back_button) {
			f.dispose();
			try {
				new MenuScreen();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else
			System.exit(0);
	}
}