import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanController implements ActionListener {

    private HangmanModel model;
    private HangmanView view;

    public HangmanController(HangmanModel model, HangmanView view) {
        this.model = model;
        this.view = view;

    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}
