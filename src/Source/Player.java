package Source;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	public static int x, y, width, height;
	public boolean right, left;

	public Player(int x, int y) {
		Player.x = x;
		Player.y = y;
		width = 120;
		height = 15;
	}

	public void update() {
		if (right) {
			x += 6;
		} else if (left) {
			x -= 6;
		}

		if (x + width > Game.WIDTH * Game.SCALE) {
			x = Game.WIDTH * Game.SCALE - width;
		} else if (x < (Game.WIDTH * Game.SCALE) / 3) {
			x = (Game.WIDTH * Game.SCALE) / 3;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}

}
