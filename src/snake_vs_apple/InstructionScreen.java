package snake_vs_apple;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.*;

public class InstructionScreen implements ActionListener{
	JFrame f = new JFrame();
	JPanel p = new JPanel();
	JButton b = new JButton();
	JLabel l = new JLabel();
	JLabel l2 = new JLabel();
	JLabel l3 = new JLabel();
	ImageIcon bg = new ImageIcon("Ins.JPG");
	
	InstructionScreen(){
		
		//Frame Work
		f.setSize(689, 275);
		f.setVisible(true);
		f.setTitle("How to Play");
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		
		//Panel Work
		p.setSize(689, 270);
		p.setBackground(Color.black);
		p.setLayout(null);
		
		//Label Work
		JLabel bg_label = new JLabel();
		bg_label.setIcon(bg);
		bg_label.setBounds(0, -100, 930, 390);
		
		//Button Work
		b.setText("Back to Menu");
		b.setFocusable(false);
		b.setBackground(new Color(173,255,47));
		b.setBounds(2, 205, 115, 28);
		b.addActionListener(this);
		
		//Adding all
		f.add(p);
		p.add(bg_label);
		p.add(b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b) {
			try {
				new MenuScreen();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			f.dispose();
		}
		
	}
	
}