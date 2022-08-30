package snake_vs_apple;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameModes_Screen extends JFrame implements ActionListener{
	ImageIcon bg = new ImageIcon("all background//snake.jpg");
	JButton endless_mode_button;
	JButton level_mode_button;
	JButton go_back_button;
	
	GameModes_Screen(){
		
		//Background Label containing the background picture
		JLabel bg_label = new JLabel();
		bg_label.setIcon(bg);
		bg_label.setBounds(0, 0, 280, 350);
		
		
		//Panel
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(280,350));
		p.setLayout(null);
		
		
		//Buttons
		endless_mode_button = new JButton("Endless Mode");
		endless_mode_button.setBounds(85, 70, 115, 30);
		endless_mode_button.setFocusable(false);
		endless_mode_button.setBackground(new Color(127,255,0));
		endless_mode_button.addActionListener(this);
		
		level_mode_button = new JButton("Level Mode");
		level_mode_button.setBounds(85, 105, 115, 30);
		level_mode_button.setFocusable(false);
		level_mode_button.setBackground(new Color(127,255,0));
		level_mode_button.addActionListener(this);
		
		go_back_button = new JButton("Go Back");
		go_back_button.setBounds(85, 140, 115, 30);
		go_back_button.setFocusable(false);
		go_back_button.setBackground(new Color(173,255,47));
		go_back_button.addActionListener(this);
		
		
		//Frame and adding
		this.setTitle("Select Mode");
		this.setResizable(false);
		this.setDefaultCloseOperation(GameModes_Screen.EXIT_ON_CLOSE);
		p.add(endless_mode_button);
		p.add(level_mode_button);
		p.add(go_back_button);
		p.add(bg_label);
		this.add(p);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==endless_mode_button) {
			this.dispose();
			try {
				new Endless_Mode();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==level_mode_button) {
			new Difficulty();
			this.dispose();
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

