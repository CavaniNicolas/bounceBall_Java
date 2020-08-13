import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.plaf.ColorUIResource;

public class Bouton extends JButton implements MouseListener {

	private String name;
	private Color topColor = Color.blue;
	private Color botColor = Color.cyan;

	public Bouton(String str) {
		super(str);
		this.name = str;
		this.addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;

		if (this.isEnabled() == false) {
			this.topColor = Color.blue;
			this.botColor = Color.red;
		}

		// Met la couleur degradee dans le bouton
		GradientPaint gp = new GradientPaint(0, 0, this.topColor, 0, this.getHeight(), this.botColor);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		// g2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 30, 30);

		// Mesure la hauteur et la longueur du texte dans le bouton
		FontMetrics fm = g2d.getFontMetrics();
		int height = fm.getHeight();
		int width = fm.stringWidth(this.name);

		// Affiche le texte dans le bouton
		g2d.setColor(Color.black);
		g2d.drawString(this.name, this.getWidth()/2 - width/2, this.getHeight()/2 + height/4);
	}


	public void mouseClicked(MouseEvent event) {
	}

	public void mousePressed(MouseEvent event) {
		this.topColor = Color.green;
		this.botColor = new ColorUIResource(240, 200, 150);
	}

	public void mouseReleased(MouseEvent event) {
		if (event.getX() > 0 && event.getX() < this.getWidth()
		&& event.getY() > 0 && event.getY() < this.getHeight()) {
			this.topColor = Color.yellow;
			this.botColor = Color.white;
		} else {
			this.topColor = Color.blue;
			this.botColor = Color.cyan;
		}
	}

	public void mouseEntered(MouseEvent event) {
		this.topColor = Color.yellow;
		this.botColor = Color.white;
	}

	public void mouseExited(MouseEvent event) {
		this.topColor = Color.blue;
		this.botColor = Color.cyan;
	}

	public void setMyEnabled(boolean b) {
		this.setEnabled(b);
		if (b == true) {
			this.topColor = Color.blue;
			this.botColor = Color.cyan;
		}
	}

}