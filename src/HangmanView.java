import javax.swing.*;
import java.awt.*;

/**
 * HangmanView represents the graphical user interface for the Hangman game.
 * It displays the hangman figure based on the number of errors and shows the result message.
 */
public class HangmanView extends JPanel {

	private HangmanModel model;

	/**
	 * Constructs a HangmanView object with the specified HangmanModel.
	 *
	 * @param model the HangmanModel representing the game state
	 */
	public HangmanView(HangmanModel model) {
		this.model = model;
		setBackground(Color.WHITE);
		super.setPreferredSize(new Dimension(1000, 1000));
	}

	/**
	 * Draws the hangman figure based on the number of errors.
	 *
	 * @param g        the Graphics object for drawing
	 * @param nbErrors the number of errors made by the player
	 */
	private void dessinerPendu(Graphics g, int nbErrors) {
		switch (nbErrors) {
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

	/**
	 * Displays the result message indicating win or loss.
	 *
	 * @param isWin a boolean value indicating whether the player won or lost
	 */
	public void displayResultMessage(boolean isWin) {
		if (isWin) {
			add(new JDialog(((JFrame) this.getParent()), "You Lose", true));
		} else {
			add(new JDialog(((JFrame) this.getParent()), "You Win", true));
		}
	}
}
