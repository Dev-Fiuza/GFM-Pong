package Source;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	public boolean isRunning = true;
	private Thread thread;

	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	public static final int SCALE = 4;

	public static String gameState = "MENU";

	private BufferedImage image;

	public Menu menu;
	public Credit credit;
	public Gameplay gameplay;

	public Game() {
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		initFrame();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		menu = new Menu();
		credit = new Credit();
		gameplay = new Gameplay();
	}

	public void initFrame() {
		frame = new JFrame("GFM's Pong");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	public synchronized void stop() {

	}

	public static void main(String[] args) {
		GameSound.music.loop();
		Game game = new Game();
		game.start();
	}

	public void update() {
		if (gameState == "MENU") {
			menu.update();
		} else if (gameState == "CRÉDITOS") {
			credit.update();
		} else if (gameState == "NORMAL") {
			gameplay.update();
		} 
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

		if (gameState == "MENU") {
			menu.render(g);
		} else if (gameState == "CRÉDITOS") {
			credit.render(g);
		} else if (gameState == "NORMAL") {
			gameplay.render(g);
		} else if (gameState == "PAUSED") {
			gameplay.render2(g);
		} else if (gameState == "FINAL") {
			gameplay.render3(g);
		}
		
		bs.show();
	}

	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				update();
				render();
				frames++;
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
		stop();
	}

	public void keyPressed(KeyEvent e) {
		
		//SISTEMAS DE TECLAS ESQUERDA DIREITA
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ||
				e.getKeyCode() == KeyEvent.VK_D) {
			if(Game.gameState == "NORMAL") {
				Gameplay.player.right = true;
			}
			
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT ||
				e.getKeyCode() == KeyEvent.VK_A) {
			if(Game.gameState == "NORMAL") {
				Gameplay.player.left = true;
			}
		}
		
		//SISTEMAS DE TECLAS CIMA BAIXO
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {

			if (gameState == "MENU") {
				menu.up = true;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {

			if (gameState == "MENU") {
				menu.down = true;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (gameState == "MENU") {
				menu.enter = true;
			} else if (gameState == "CRÉDITOS") {
				credit.enter = true;
			} else if(gameState == "FINAL") {
				gameState = "MENU";
				gameplay.bot_pts = 0;
				gameplay.player_pts = 0;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(gameState == "PAUSED") {
				gameState ="NORMAL";
				gameplay.mark_bot = false;
				gameplay.mark_player = false;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(gameState == "NORMAL") {
				gameState = "MENU";
			}
		}
		
	}

	public void keyReleased(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_RIGHT ||
				e.getKeyCode() == KeyEvent.VK_D) {
			if(Game.gameState == "NORMAL") {
				Gameplay.player.right = false;
			}
			
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT ||
				e.getKeyCode() == KeyEvent.VK_A) {
			if(Game.gameState == "NORMAL") {
				Gameplay.player.left = false;
			}
		}
		
	}

	public void keyTyped(KeyEvent e) {

	}

}
