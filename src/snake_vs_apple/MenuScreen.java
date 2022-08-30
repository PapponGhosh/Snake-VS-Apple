package snake_vs_apple;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MenuScreen extends JFrame implements ActionListener{

	ImageIcon bg = new ImageIcon("all background//snake.jpg");
	ImageIcon bg_text = new ImageIcon("all background//snake_text.png");
	
	JButton newgame_button = new JButton("New Game");
	JButton instruction_button = new JButton("Instructions");
	JButton highscore_button = new JButton("High Scores");
	JButton quit_button = new JButton("Quit Game");
	
	MenuScreen() throws IOException{
		//background label containing pictures
		JLabel bg_label = new JLabel();
		bg_label.setIcon(bg);
		bg_label.setBounds(0, 0, 280, 350);
		JLabel bg_text_label = new JLabel();
		bg_text_label.setIcon(bg_text);
		bg_text_label.setBounds(0, 20, 280, 80);
		
		
		//Panel
		JPanel p = new JPanel();
		p.setBackground(Color.black);
		p.setPreferredSize(new Dimension(280, 350));
		p.setLayout(null);
		
		
		//Buttons
		newgame_button.setBounds(90, 120, 105, 30);
		newgame_button.setFocusable(false);
		newgame_button.addActionListener(this);
		newgame_button.setBackground(new Color(127,255,0));
		
		instruction_button.setBounds(90, 150, 105, 30);
		instruction_button.setFocusable(false);
		instruction_button.addActionListener(this);
		instruction_button.setBackground(new Color(173,255,47));
		
		highscore_button.setBounds(90, 180, 105, 30);
		highscore_button.setFocusable(false);
		highscore_button.addActionListener(this);
		highscore_button.setBackground(new Color(127,255,0));
		
		quit_button.setBounds(90, 210, 105, 30);
		quit_button.setFocusable(false);
		quit_button.addActionListener(this);
		quit_button.setBackground(new Color(173,255,47));
		
		
		//Frame and add
		this.setTitle("MENU");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		p.add(newgame_button);
		p.add(instruction_button);
		p.add(highscore_button);
		p.add(quit_button);
		p.add(bg_text_label);
		p.add(bg_label);
		this.add(p);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==newgame_button) {
			this.dispose();
			new GameModes_Screen();
		}
		else if(e.getSource()==instruction_button) {
			this.dispose();
			new InstructionScreen();
		}
		else if (e.getSource()==highscore_button) {
			this.dispose();
			new HighScores_Screen();
		}
		else if(JOptionPane.showInternalConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION)==0)
			System.exit(0);
	}
}