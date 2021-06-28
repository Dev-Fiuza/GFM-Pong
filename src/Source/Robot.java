package Source;

import java.awt.Color;
import java.awt.Graphics;

public class Robot {
	
	public static double x, y;
	public static int width, height;
	
	public Robot(int x, int y) {
		Robot.x = x;
		Robot.y = y;
		width = 120;
		height = 15;
	}

	public void update() {
		x += (Ball.x - x - 60)*0.125 ;
		
			if (x + width > Game.WIDTH * Game.SCALE) {
			x = Game.WIDTH * Game.SCALE - width;
		} else if (x < (Game.WIDTH * Game.SCALE) / 3) {
			x = (Game.WIDTH * Game.SCALE) / 3;
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, width, height);
	}
	
	
}
