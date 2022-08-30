package snake_vs_apple;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class HighScores_Screen extends JFrame implements ActionListener{
	
	JPanel right_panel; //since it will be used outside of the constructor
	
	JButton Menu_button = new JButton("Go Back");
	JButton Endless_button = new JButton("Endless Mode");
	JButton Level_button = new JButton("Level Mode");
	JButton Reset_endless_score_button = new JButton("Reset Score to 0");
	JButton Reset_level_score_button = new JButton("Reset Score to 0");
	
	ImageIcon bg = new ImageIcon("all background//high_s_bg.png");
	
	JLabel endless_score_label = new JLabel();
	JLabel level_score_label = new JLabel();
	JLabel high_score_label = new JLabel("High Score");
	JLabel bg_label = new JLabel(bg);
	
	HighScores_Screen(){
		
		//frame
		this.setTitle("High Scores");
		this.setResizable(false);
		this.setDefaultCloseOperation(HighScores_Screen.EXIT_ON_CLOSE);
		this.setSize(new Dimension(500,400));
		this.setLayout(null);
		
		
		//panels
		JPanel left_panel = new JPanel();
		left_panel.setBounds(0, 0, 170, 400);
		left_panel.setBackground(new Color(127,255,0));
		left_panel.setLayout(null);
		
		right_panel = new JPanel();
		right_panel.setBounds(172, 0, 328, 400);
		right_panel.setBackground(Color.black);
		right_panel.setLayout(null);
		
		
		//buttons
		Endless_button.setBounds(28, 105, 115, 25);
		Endless_button.setBackground(new Color(180,255,120));
		Endless_button.setFocusable(false);
		Endless_button.addActionListener(this);
		
		Level_button.setBounds(28, 135, 115, 25);
		Level_button.setBackground(new Color(180,255,120));
		Level_button.setFocusable(false);
		Level_button.addActionListener(this);
		
		Menu_button.setBounds(28, 165, 115, 25);
		Menu_button.setFocusable(false);
		Menu_button.addActionListener(this);
		Menu_button.setBackground(new Color(173,255,47));
		
		Reset_endless_score_button.setBounds(85, 270, 130, 25);
		Reset_endless_score_button.setFocusable(false);
		Reset_endless_score_button.addActionListener(this);
		Reset_endless_score_button.setBackground(new Color(173,255,47));
		
		Reset_level_score_button.setBounds(85, 270, 130, 25);
		Reset_level_score_button.setFocusable(false);
		Reset_level_score_button.addActionListener(this);
		Reset_level_score_button.setBackground(new Color(173,255,47));
		
		
		//Labels
		high_score_label.setFont(new Font("Ink Free", Font.BOLD, 40));
		high_score_label.setBounds(60, 100, 200, 50);
		high_score_label.setForeground(Color.black);
		
		endless_score_label.setFont(new Font("Ink Free", Font.BOLD, 40));
		endless_score_label.setBounds(90, 160, 200, 50);
		endless_score_label.setForeground(Color.black);
		
		level_score_label.setFont(new Font("Ink Free", Font.BOLD, 40));
		level_score_label.setBounds(90, 160, 200, 50);
		level_score_label.setForeground(Color.black);
		
		bg_label.setBounds(0, 0, 328, 400);
		
		
		//adding and frame
		left_panel.add(Endless_button);
		left_panel.add(Level_button);
		left_panel.add(Menu_button);
		this.add(left_panel);
		right_panel.add(bg_label);
		this.add(right_panel);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
	public int show_high_score_endless() throws NumberFormatException, IOException{
		try (BufferedReader r = new BufferedReader(new FileReader("endless score.txt"))) {
			return Integer.parseInt(r.readLine());
		}
	}
	public int show_high_score_level() throws NumberFormatException, IOException{
		try (BufferedReader r = new BufferedReader(new FileReader("level score.txt"))) {
			return Integer.parseInt(r.readLine());
		}
	}
	
	
	public void reset_endless_score() throws IOException {
		BufferedWriter w = new BufferedWriter(new FileWriter("endless score.txt"));
		w.write("00"); //writing zero value 
		w.close();
	}
	public void reset_level_score() throws IOException {
		BufferedWriter w = new BufferedWriter(new FileWriter("level score.txt"));
		w.write("00"); //writing zero value
		w.close();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==Endless_button) {
			try {
				endless_score_label.setText(String.valueOf(show_high_score_endless()));
			} catch (NumberFormatException | IOException e1) {
				e1.printStackTrace();
			}
			right_panel.removeAll();
			right_panel.add(high_score_label);
			right_panel.add(endless_score_label);
			right_panel.add(Reset_endless_score_button);
			right_panel.add(bg_label);
			this.add(right_panel);
			right_panel.repaint();
		}
		else if(e.getSource()==Reset_endless_score_button) {
			try {
				reset_endless_score();  //calling the reset score function for the endless mode to reset the high score
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			endless_score_label.setText("Done");
			right_panel.removeAll();
			right_panel.add(high_score_label);
			right_panel.add(endless_score_label);
			right_panel.add(bg_label);
			this.add(right_panel);
			right_panel.repaint();
		}
		
		else if(e.getSource()==Level_button) {
			try {
				level_score_label.setText(String.valueOf(show_high_score_level()));
			} catch (NumberFormatException | IOException e1) {
				e1.printStackTrace();
			}
			right_panel.removeAll();
			right_panel.add(high_score_label);
			right_panel.add(level_score_label);
			right_panel.add(Reset_level_score_button);
			right_panel.add(bg_label);
			this.add(right_panel);
			right_panel.repaint();
		}
		else if(e.getSource()==Reset_level_score_button) {
			try {
				reset_level_score();  //calling the reset score function for the level mode to reset the high score
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			endless_score_label.setText("Done");
			right_panel.removeAll();
			right_panel.add(high_score_label);
			right_panel.add(endless_score_label);
			right_panel.add(bg_label);
			this.add(right_panel);
			right_panel.repaint();
		}
		else {
			this.dispose();
			try {
				new MenuScreen();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}

