package components;

import javax.swing.*;
import java.awt.*;

/**
 * HangmanDisplay is a custom JPanel component that displays the hangman figure based on the number of errors.
 */
public class HangmanDisplay extends JPanel {

	private int nbErrors;

	/**
	 * Constructs a HangmanDisplay object with zero errors.
	 */
	public HangmanDisplay() {
		super();
		setPreferredSize(new Dimension(400, 400));
		setVisible(true);
		this.nbErrors = 0;
	}

	/**
	 * Sets the number of errors to the specified value.
	 *
	 * @param nbErrors the number of errors to set
	 */
	public void setNbErrors(int nbErrors) {
		this.nbErrors = nbErrors;
		repaint();
	}

	/**
	 * Overrides the paintComponent method to draw the hangman figure based on the number of errors.
	 *
	 * @param g the Graphics object for drawing
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.drawHangman(g);
	}

	/**
	 * Draws the hangman figure based on the number of errors.
	 *
	 * @param g the Graphics object for drawing
	 */
	private void drawHangman(Graphics g) {
		switch (this.nbErrors) {
			case 8:
				// Right leg
				g.drawLine(250, 250, 275, 300);
			case 7:
				// Left leg
				g.drawLine(250, 250, 225, 300);
			case 6:
				// Right arm
				g.drawLine(250, 150, 275, 200);
			case 5:
				// Left arm
				g.drawLine(250, 150, 225, 200);
			case 4:
				// Body
				g.drawLine(250, 150, 250, 250);
			case 3:
				// Head
				g.drawLine(250, 50, 250, 100);
				g.drawOval(225, 100, 50, 50);
			case 2:
				// Horizontal bar
				g.drawLine(100, 50, 250, 50);
			case 1:
				// Vertical pole
				g.drawLine(100, 50, 100, 300);
				break;
			default:
				break;
		}
	}
}
