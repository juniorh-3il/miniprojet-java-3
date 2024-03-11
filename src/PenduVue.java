import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PenduVue extends JPanel implements KeyListener {

    private PenduModele modele;

    public PenduVue(PenduModele modele) {
        this.modele = modele;
        setBackground(Color.WHITE);
        this.addKeyListener(this);
        super.setPreferredSize(new Dimension(400, 300));

        // Essentiel au fonctionnement du programme mais très illégal (askip)
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Waouh tu as appuyé " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
