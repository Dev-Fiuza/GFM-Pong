package Source;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Gameplay {
	public static Player player;
	public static Robot robot;
	public static Ball ball;
	public int bot_pts = 0;
	public int player_pts = 0;
	public boolean mark_player = false;
	public boolean mark_bot = false;

	public Gameplay() {
		player = new Player(590, 615);
		robot = new Robot(590, 15);
		ball = new Ball(635, 315);
	}

	public void update() {
		// l�gica de cada um dos objetos
		player.update();
		robot.update();
		ball.update();

		// Contabiliza��o de pontos
		if (Ball.point_player == true) {
			Game.gameState = "PAUSED";
			Ball.point_player = false;
			player_pts++;
			mark_player = true;
		} else if (Ball.point_bot == true) {
			Game.gameState = "PAUSED";
			Ball.point_bot = false;
			bot_pts++;
			mark_bot = true;
		}

		// condi��o de fim de jogo
		if (player_pts == 15 || bot_pts == 15) {
			Game.gameState = "FINAL";
		}
		
		// reloca��o do player
		if (Game.gameState == "PAUSED") {
			Player.x = 590;
			Ball.x = 635;
			Ball.y = 315;
			Robot.x = 590;
		}
	}

	// Renderiza��o do gameState NORMAL
	public void render(Graphics g) {
		if (Game.gameState == "NORMAL") {
			// fundo do jogo
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);

			// Cabe�alho da Pontua��o, Delimita��o do campo
			g.setColor(Color.WHITE);
			g.drawLine((Game.WIDTH * Game.SCALE) / 3, 0, (Game.WIDTH * Game.SCALE) / 3, Game.HEIGHT * Game.SCALE);
			g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 60));
			g.drawString("Pontua��o", 20, 70);

			// Pontos jogador e rob� e adicionais
			g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 30));
			g.drawString("Jogador: " + player_pts, 30, 160);
			g.drawString("Rob�: " + bot_pts, 30, 220);
			g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 25));
			g.drawString("Aperte Esc para Pausar", 30, 560);

			// objetos
			player.render(g);
			robot.render(g);
			ball.render(g);
		}
	}

	// Renderiza��o do gameState PAUSED
	public void render2(Graphics g) {
		if (Game.gameState == "PAUSED") {

			// fundo do jogo
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);

			// objetos
			player.render(g);
			robot.render(g);
			ball.render(g);

			// Mensagem e Contabiliza��o de pontos
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 30));
			if (mark_player == true) {
				g.drawString("Voc� marcou", 10, 300);
				g.drawString("um ponto!", 10, 340);
			} else if (mark_bot == true) {
				g.drawString("O Rob� marcou", 10, 300);
				g.drawString("um ponto!", 10, 340);
			}

			// Cabe�alho da Pontua��o, Delimita��o
			g.setColor(Color.WHITE);
			g.drawLine((Game.WIDTH * Game.SCALE) / 3, 0, (Game.WIDTH * Game.SCALE) / 3, Game.HEIGHT * Game.SCALE);
			g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 60));
			g.drawString("Pontua��o", 20, 70);

			// Pontos jogador e rob�

			g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 30));
			g.drawString("Jogador: " + player_pts, 30, 160);
			g.drawString("Rob�: " + bot_pts, 30, 220);
			g.drawString("Aperte Espa�o para", 15, 460);
			g.drawString("lan�ar novamente a ", 15, 500);
			g.drawString("bola ", 15, 540);
		}
	}

	// Renderiza��o do gameState FINAL
	public void render3(Graphics g) {
		if (Game.gameState == "FINAL") {
			// fundo da tela
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);

			// player win
			if (player_pts == 15) {
				g.setColor(Color.YELLOW);
				g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 90));
				g.drawString("PARAB�NS!", 240, 120);
				g.drawString("Voc� ganhou o jogo ^^", 35, 240);
				g.setColor(Color.CYAN);
				g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 38));
				g.drawString("Pressione a tecla enter para iniciar uma nova partida!", 30, 500);
			}

			// bot win
			else if (bot_pts == 15) {
				g.setColor(Color.RED);
				g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 75));
				g.drawString("Poxa vida, voc� perdeu :/", 60, 120);
				g.setColor(Color.ORANGE);
				g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 48));
				g.drawString("N�o se desanime, pois n�o � s� de vit�rias", 30, 260);
				g.drawString("que vive o campe�o! Continue Lutando!", 30, 320);
				g.setColor(Color.CYAN);
				g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 38));
				g.drawString("Pressione a tecla enter para iniciar uma nova partida!", 30, 500);

			}

		}
	}

}
