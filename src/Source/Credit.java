package Source;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Credit {

	public boolean enter;
	
	public void update() {
		if(enter) {
			enter = false;
			Game.gameState = "MENU";
		}
	}
	
	public void render(Graphics g) {
		//fundo dos créditos
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		
		//créditos
		g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 25));
		g.setColor(Color.GREEN);
		g.drawString("Este jogo é uma produção feita exclusivamente por GFM", 160, 140);
		g.setColor(Color.YELLOW);
		g.drawString("Se você está jogando este jogo, saiba que ele te ama e te guarda ", 125, 180);
		g.setColor(Color.BLUE);
		g.drawString("no fundo do coração <3", 340, 220);
		g.setColor(Color.RED);
		g.drawString("Pressione enter novamente para voltar ao menu", 220, 600);
		g.setFont(new Font("Arial", Font.BOLD, 80));
		g.setColor(Color.WHITE);
		g.drawString("Obrigado amigos!", 140, 360);
	}
	
}
