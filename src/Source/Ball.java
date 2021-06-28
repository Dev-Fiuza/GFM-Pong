package Source;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

	public static double x, y, dx, dy;
	public int width, height;
	public double speed = 8.125;

	public static boolean point_bot = false;
	public static boolean point_player = false;

	public Ball(int x, int y) {
		Ball.x = x;
		Ball.y = y;
		width = 14;
		height = 14;

		int angle = new Random().nextInt(10) + 20;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));

	}

	public void update() {
		x += dx * speed;
		y += dy * speed;

		if (x + width > Game.WIDTH * Game.SCALE) {
			dx *= -1;
		} else if (x < (Game.WIDTH * Game.SCALE) / 3) {
			dx *= -1;
		}
		
		Rectangle bounds = new Rectangle((int)(x+(dx*speed)), (int)(y+(dy*speed)), width, height);
		Rectangle boundsPlayer = new Rectangle (Gameplay.player.x, Gameplay.player.y, Gameplay.player.width, Gameplay.player.height);
		Rectangle boundsRobot = new Rectangle ((int)Gameplay.robot.x, (int)Gameplay.robot.y, Gameplay.robot.width, Gameplay.robot.height);
		
		if(bounds.intersects(boundsPlayer)) {
			dy *= -1;
		} else if (bounds.intersects(boundsRobot)){
			dy*= -1;
		}
		
		// codificação para ponto player e bot
		if (y < Robot.y - Robot.height) {
			point_player = true;
		} else if (y > Player.y + Player.height) {
			point_bot = true;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval((int) x, (int) y, width, height);
	}

}
