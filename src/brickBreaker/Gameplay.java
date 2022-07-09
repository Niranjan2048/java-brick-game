package brickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.geom.*;
import java.util.Random;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	private boolean play=false;
	private int score=0;
	
	private int totalBricks=82;
	
	private Timer time;
	
	private int delay=8;
	
	private int playerX = 310;
	
	private int ballposX = 360;
	private int ballposY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	private MapGenerator map;
	
	public Gameplay() {
		map = new MapGenerator(5,10);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(1,1,692,592);
		
		map.draw((Graphics2D)g);
		
		g.setColor(Color.yellow);
		g.fillRect(0,0,3,592);
		g.fillRect(0,0,692,3);
		g.fillRect(681,0,3,592);
		
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString(""+score, 570, 30);
		
		g.setColor(Color.green);
		g.fillRoundRect(playerX, 550, 100, 8, 15, 15);
		
		g.setColor(Color.lightGray);
		g.fillOval(ballposX,ballposY,20,20);
		
		if(totalBricks <= 0) {
			play =false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You won, Score : " + score, 260, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Space for Next Level", 230, 350);
		}
		
		if(ballposY > 570) {
			play =false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game over, Scores : " + score, 190, 300);
			

			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 350);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press E to Exit", 230, 400);
		}
		
		g.dispose();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 600) {
				playerX=600;
			}
			else {
				moveRight();
			}
		}	
	
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX < 10) {
				playerX=10;
			}
			else {
				moveLeft();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play=true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 82;
				//System.out.println(delay);
				map = new MapGenerator(5,10);
				
				repaint();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(!play) {
				play=true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 82;
				delay--;
				time = new Timer(delay, this);
				time.start();
				
				map = new MapGenerator(5,10);
				
				repaint();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_E)
			System.exit(0);
	}
	public void moveRight() {
		play = true;
		playerX+=27;
	}
	public void moveLeft() {
		play = true;
		playerX-=27;
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		time.start();
		
		if(play) {
			
			if(new Rectangle(ballposX, ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
				Random rand = new Random(); 
		        int rand_int1 = rand.nextInt(12); 
				ballYdir = - rand_int1;
			}
			A: for(int i=0; i< map.map.length; i++) {
				for(int j=0; j< map.map[0].length; j++) {
					if(map.map[i][j] == 3) {
						int brickX = j*map.brickWidth + 80;
						int brickY =  i*map.brickHeight + 50;
						int brickWidth =  map.brickWidth ;
						int brickHeight = map.brickHeight ;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBricksValue(2, i, j);
							totalBricks--;
							score+=5;
							
							
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
								ballXdir= -ballXdir;
								
							}
							else {
								ballYdir= -ballYdir;
							}
							
							break A;
						}
					}
					else if(map.map[i][j] == 2) {
						int brickX = j*map.brickWidth + 80;
						int brickY =  i*map.brickHeight + 50;
						int brickWidth =  map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBricksValue(1, i, j);
							totalBricks--;
							score+=5;
							
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
								ballXdir= -ballXdir;
							}
							else {
								ballYdir = -ballYdir;
							}
							
							break A;
						}
					}
					else if(map.map[i][j] == 1) {
						int brickX = j*map.brickWidth + 80;
						int brickY =  i*map.brickHeight + 50;
						int brickWidth =  map.brickWidth ;
						int brickHeight = map.brickHeight ;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBricksValue(0, i, j);
							totalBricks--;
							score+=5;
							
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
								ballXdir= -ballXdir;
							}
							else {
								ballYdir = -ballYdir;
							}
							
							break A;
						}
					}
				}
			}
			
			
			
			
			ballposX += ballXdir;
			ballposY += ballYdir;
			if(ballposX < 0) {
				ballXdir = - ballXdir;
			}
			if(ballposY < 0) {
				ballYdir = - ballYdir;
			}
			if(ballposX > 670) {
				ballXdir = - ballXdir;
			}
		}
		
		repaint();
	}
	

}
