package brickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	public MapGenerator(int row, int col) {
		map = new int[row][col];
		for(int i=0; i < map.length; i++) {
			for(int j=0; j < map[0].length; j++ ) {
				map[i][j] = 1;
			}
		}
		map[0][2]=3;
		map[0][7]=3;
		map[0][9]=3;
		map[1][0]=3;
		map[1][4]=3;
		map[3][7]=3;
		map[4][0]=3;
		map[0][5]=2;
		map[1][6]=2;
		map[2][3]=2;
		map[2][8]=2;
		map[3][1]=2;
		map[3][5]=2;
		map[4][6]=2;
		map[4][9]=2;
		brickWidth= 540/col;
		brickHeight = 180/row;
	}
	public void draw(Graphics2D g) {
		for(int i=0; i< map.length;i++) {
			for(int j=0; j< map[0].length; j++ ) {
				if(map[i][j] == 3) {
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i* brickHeight + 50, brickWidth , brickHeight);
				
					g.setColor(Color.red);
					g.fill3DRect(j * brickWidth + 80, i* brickHeight + 50, brickWidth , brickHeight,false);
					for(int m=1; m<=4; m++)
						g.draw3DRect(j * brickWidth + 80 + m, i*brickHeight + 50 + m, brickWidth -m-1, brickHeight-m-1, true);
				}
				else if(map[i][j] == 2) {
					g.setColor(Color.ORANGE);
					//g.fillRect(j * brickWidth + 80, i* brickHeight + 50, brickWidth , brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i* brickHeight + 50, brickWidth , brickHeight);
					g.setColor(Color.orange);
					g.fill3DRect(j * brickWidth + 80, i* brickHeight + 50, brickWidth , brickHeight,false);
					for(int m=1; m<=4; m++)
						g.draw3DRect(j * brickWidth + 80 + m, i*brickHeight + 50 + m, brickWidth -m-1, brickHeight-m-1, true);	
				}
				else if(map[i][j] == 1) {
					g.setColor(Color.yellow);
					//g.fillRect(j * brickWidth + 80, i* brickHeight + 50, brickWidth , brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i* brickHeight + 50, brickWidth , brickHeight);
					g.setColor(Color.yellow);
					g.fill3DRect(j * brickWidth + 80, i* brickHeight + 50, brickWidth , brickHeight,false);
					for(int m=1; m<=4; m++)
						g.draw3DRect(j * brickWidth + 80 + m, i*brickHeight + 50 + m, brickWidth -m-1, brickHeight-m-1, true);    
				}
			}
		}
	}
	public void setBricksValue(int value, int row, int col) {
		map[row][col] =value; 
	}
}
