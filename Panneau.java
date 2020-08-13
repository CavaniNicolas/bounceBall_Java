import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panneau extends JPanel {

	private int cote = 50;
	private int posX = -cote;
	private int posY = -cote;

	public void paintComponent(Graphics g) {
		// On remet un fond blanc
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// On dessine une balle verte
		g.setColor(Color.green);
		g.fillOval(this.posX, this.posY, cote, cote);
		g.fillOval(0, 0, cote, cote);

		// Pour que la balle soit une tete de serpent
		// try {
		// 	Image img = ImageIO.read(new File("snakeHead.png"));
		// 	g.drawImage(img, this.posX, this.posY, cote, cote, this);
		// } catch (IOException e) {
		// 	e.printStackTrace();
		// }
	}

	public int getCote() {
		return this.cote;
	}

	public void setCote(int cote) {
		this.cote = cote;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

}