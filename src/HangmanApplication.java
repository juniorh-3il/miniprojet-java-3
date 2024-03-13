import javax.swing.*;

import javax.swing.*;

/**
 * HangmanApplication is the main class for running the Hangman game.
 * It initializes the model, view, and controller components, and sets up the graphical user interface.
 */
public class HangmanApplication {

	/**
	 * The main method to start the Hangman game.
	 *
	 * @param args the command-line arguments (not used)
	 */
	public static void main(String[] args) {
		// Create HangmanModel, HangmanView, and HangmanController objects
		HangmanModel model = new HangmanModel();
		HangmanView view = new HangmanView(model);
		HangmanController controller = new HangmanController(model, view);

		// Create and configure the JFrame for the game
		JFrame frame = new JFrame("Hangman Game");
		frame.getContentPane().add(view);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}

