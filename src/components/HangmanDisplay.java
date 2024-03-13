package components;

import javax.swing.*;
import java.awt.*;

public class HangmanDisplay extends JPanel {

	private int nbErrors;

	public HangmanDisplay() {
		super();
		this.nbErrors = 0;
	}

	public void incrementNbErrors() {
		this.nbErrors++;
		repaint();
	}

	public void resetNbErrors() {
		this.nbErrors = 0;
		repaint();
	}

	public void setNbErrors(int nbErrors) {
		this.nbErrors = nbErrors;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.drawHangman(g);
	}

	private void drawHangman(Graphics g) {
		switch (this.nbErrors) {
			case 8:
				// Jambe droite
				g.drawLine(250, 250, 275, 300);
			case 7:
				// Jambe gauche
				g.drawLine(250, 250, 225, 300);
			case 6:
				// Bras droit
				g.drawLine(250, 150, 275, 200);
			case 5:
				// Bras gauche
				g.drawLine(250, 150, 225, 200);
			case 4:
				// Corps
				g.drawLine(250, 150, 250, 250);
			case 3:
				// Tête
				g.drawLine(250, 50, 250, 100);
				g.drawOval(225, 100, 50, 50);
			case 2:
				// Barre horizontale
				g.drawLine(100, 50, 250, 50);
			case 1:
				// Potence verticale
				g.drawLine(100, 50, 100, 300);
				break;
			default:
				break;
		}
	}
}
