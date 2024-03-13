import components.HangmanDisplay;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * HangmanController manages user interactions and updates between the HangmanModel and HangmanView.
 * It handles button clicks for letter selection and updates the game state accordingly.
 */
public class HangmanController implements ActionListener {

	public static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	private HangmanModel model;
	private HangmanView view;
	private HangmanDisplay hangmanDisplay;
	private JLabel guessWordLabel; // TODO: Rename this variable to provide a more descriptive name
	private ArrayList<JButton> letterButtons;

	/**
	 * Constructs a HangmanController object with the specified HangmanModel and HangmanView.
	 *
	 * @param model the HangmanModel representing the game state
	 * @param view  the HangmanView representing the game interface
	 */
	public HangmanController(HangmanModel model, HangmanView view) {
		this.model = model;
		this.view = view;
		this.hangmanDisplay = (HangmanDisplay) this.view.add(new HangmanDisplay());
		this.guessWordLabel = (JLabel) this.view.add(new JLabel());
		this.letterButtons = new ArrayList<>();
		for (char letter : HangmanController.ALPHABET) {
			JButton newButton = new JButton(String.valueOf(letter));
			letterButtons.add(newButton);
			view.add(newButton);
			newButton.addActionListener(this);
		}
	}

	/**
	 * Handles actionPerformed event for the buttons.
	 * Updates the game state and view based on the user's interaction.
	 *
	 * @param e the ActionEvent object representing the user's action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonPressed = (JButton) e.getSource();
		buttonPressed.setEnabled(false);
		this.model.pickLetter(buttonPressed.getText().charAt(0));
		this.hangmanDisplay.setNbErrors(this.model.getNbErrors());
		this.guessWordLabel.setText(this.model.getDisplayedWord());
		if (this.model.getNbErrors() == 8) {
			this.view.displayResultMessage(false);
		} else if (!this.model.getDisplayedWord().contains("_")) {
			this.view.displayResultMessage(true);
		}
	}
}

