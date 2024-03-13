import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HangmanView extends JPanel {

    private HangmanModel model;

    public HangmanView(HangmanModel model) {
        this.model = model;
        setBackground(Color.WHITE);
        super.setPreferredSize(new Dimension(400, 300));
    }
}
