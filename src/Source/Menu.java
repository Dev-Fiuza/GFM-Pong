package Source;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {
	
	public String[] options = {"Iniciar Jogo", "Cr�ditos"};
	
	public int currentOption = 0;
	public int maxOption = options.length - 1;
	
	public boolean up,down, enter;
	
	public void update() {
		if(up) {
			up = false;
			currentOption--;
			if(currentOption < 0) {
				currentOption = maxOption;
			}
		}
		if(down) {
			down = false;
			currentOption++;
			if(currentOption > maxOption) {
				currentOption = 0;
			}
		}
		
		if(enter) {
			enter = false;
			if(options[currentOption] == "Iniciar Jogo") {
				Game.gameState = "NORMAL";
			} else if (options[currentOption] == "Cr�ditos") {
				Game.gameState = "CR�DITOS";
			}
		}
		
	}
	
	public void render(Graphics g) {
		//fundo do menu
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		
		//t�tulo do game e informa��o adicional
		g.setColor(Color.RED);
		g.setFont(new Font("Verdana", Font.BOLD, 120));
		g.drawString("GFM's Pong", 90, 120);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 40));
		g.drawString("Use as setas ou as teclas | w | | s | para", 100, 500);
		g.drawString("navegar no menu", 300, 540);
		g.drawString("Para selecionar uma op��o pressione enter", 90, 600);
		
		//op��es do menu
		g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 45));
		g.drawString("Iniciar Jogo", 340, 280);
		g.drawString("Cr�ditos", 340, 360);
		
		if(options[currentOption] == "Iniciar Jogo") {
			g.drawString(">", 310, 280);
		}	else if(options[currentOption] == "Cr�ditos") {
			g.drawString(">", 310, 360);
		}
		
	}

}
