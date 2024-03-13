import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HangmanController implements ActionListener {

    private HangmanModel model;
    private HangmanView view;
    private ArrayList<JButton> letterButtons;
    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public HangmanController(HangmanModel model, HangmanView view) {
        this.model = model;
        this.view = view;
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
    }
}
