import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PenduControleur implements ActionListener {

    private PenduModele model;
    private PenduVue view;

    public PenduControleur(PenduModele model, PenduVue view) {
        this.model = model;
        this.view = view;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
