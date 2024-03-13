import javax.swing.*;
import java.awt.*;

public class HangmanView extends JPanel {

	private HangmanModel model;

	public HangmanView(HangmanModel model) {
		this.model = model;
		setBackground(Color.WHITE);
		super.setPreferredSize(new Dimension(400, 300));
	}

	private void dessinerPendu(Graphics g, int nbErrors) {
		switch(nbErrors) {
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

	public void displayResultMessage(boolean isWin) {
		if (isWin) {
			add(new JDialog((JFrame) this.getParent(), "perdu", true));
		} else {
			add(new JDialog((JFrame) this.getParent(), "gagné", true));
		}
	}
}
