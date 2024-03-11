import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HangmanView extends JPanel implements KeyListener {

    private HangmanModel model;

    public HangmanView(HangmanModel model) {
        this.model = model;
        setBackground(Color.WHITE);
        this.addKeyListener(this);
        super.setPreferredSize(new Dimension(400, 300));

        // The application cannot work without those lines, but they are bad (I read it online)
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("You pressed : " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
