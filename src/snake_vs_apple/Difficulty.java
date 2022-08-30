package snake_vs_apple;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Difficulty extends JFrame implements ActionListener{
	JButton Very_Easy;
	JButton Easy;
	JButton Normal;
	JButton Hard;
	JButton Very_Hard;
	
	Difficulty(){
		//Panel
		JPanel p = new JPanel();
		p.setBackground(new Color(0,175,0));
		p.setPreferredSize(new Dimension(510, 60));
		p.setLayout(null);
		
		//Label
		JLabel l = new JLabel("Choose Difficulty");
		l.setFont(new Font("Ink Free",Font.BOLD, 18));
		l.setBounds(180, 2, 150, 20);
		l.setForeground(Color.white);
		
		//Button
		Very_Easy = new JButton("Very Easy");
		Very_Easy.setBounds(10, 30, 90, 20);
		Very_Easy.setBackground(new Color(0,255,85));
		Very_Easy.setFocusable(false);
		Very_Easy.addActionListener(this);
		Easy = new JButton("Easy");
		Easy.setBounds(110, 30, 90, 20);
		Easy.setBackground(new Color(0,255,85));
		Easy.setFocusable(false);
		Easy.addActionListener(this);
		Normal = new JButton("Normal");
		Normal.setBounds(210, 30, 90, 20);
		Normal.setBackground(new Color(0,255,85));
		Normal.setFocusable(false);
		Normal.addActionListener(this);
		Hard = new JButton("Hard");
		Hard.setBounds(310, 30, 90, 20);
		Hard.setBackground(new Color(0,255,85));
		Hard.setFocusable(false);
		Hard.addActionListener(this);
		Very_Hard = new JButton("Very Hard");
		Very_Hard.setBounds(410, 30, 90, 20);
		Very_Hard.setBackground(new Color(0,255,85));
		Very_Hard.setFocusable(false);
		Very_Hard.addActionListener(this);
		
		
		this.setTitle("DIFFICULTY");
		this.setResizable(false);
		p.add(Very_Easy);
		p.add(Easy);
		p.add(Normal);
		p.add(Hard);
		p.add(Very_Hard);
		p.add(l);
		this.add(p);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Very_Easy) {
			new Level_1(110);
			this.dispose();
		}
		else if(e.getSource() == Easy) {
			new Level_1(90);
			this.dispose();
		}
		else if(e.getSource() == Normal) {
			new Level_1(80);
			this.dispose();
		}
		else if(e.getSource() == Hard) {
			new Level_1(75);
			this.dispose();
		}
		else {
			new Level_1(60);
			this.dispose();
		}
	}
}
