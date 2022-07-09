package brickBreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartPage {
	private JFrame f = new JFrame("Start page");
	private JPanel panel =new JPanel();
	//panel.setBounds(5,5,100,100);
	private JButton bok = new JButton("Classic");
	
	//private JButton bok1 = new JButton("Survive 30");
	public StartPage() {
	
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		panel.add(bok);
		

		//panel.add(bok1);
		f.add(panel);
		
		
		
		
		bok.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				JFrame j = new JFrame();
				Gameplay gameplay= new Gameplay();
				j.setBounds(10,10,700,600);
				j.setTitle("Brick Breaker");
				j.setResizable(false);
				j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				j.add(gameplay);
				j.setVisible(true);
			}
		});
		/*bok1.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				JFrame j = new JFrame();
				Arcade gameplay= new Arcade();
				j.setBounds(10,10,700,600);
				j.setTitle("Brick Breaker");
				j.setResizable(false);
				j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				j.add(gameplay);
				j.setVisible(true);
			}
		});
		*/
		f.setBounds(10,10,700,600);
		f.setVisible(true);
	}
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(1,1,692,592);
	}
}
