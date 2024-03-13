import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HangmanController implements ActionListener {

	private HangmanModel model;
	private HangmanView view;
	private JPanel hangmanDisplay;
	private JLabel guessWordLabel; //TODO: new name must be chosen: the current one is dogshit!
	private ArrayList<JButton> letterButtons;
	public static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	public HangmanController(HangmanModel model, HangmanView view) {
		this.model = model;
		this.view = view;
		this.hangmanDisplay = (JPanel) this.view.add(new JPanel());
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
		this.guessWordLabel.setText(this.model.getDisplayedWord());
	}
}
