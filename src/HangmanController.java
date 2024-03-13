import components.HangmanDisplay;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HangmanController implements ActionListener {

	public static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	private HangmanModel model;
	private HangmanView view;
	private HangmanDisplay hangmanDisplay;
	private JLabel guessWordLabel; //TODO: new name must be chosen: the current one is dogshit!
	private ArrayList<JButton> letterButtons;

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
