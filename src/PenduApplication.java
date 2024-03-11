import javax.swing.*;

public class PenduApplication {

    public static void main(String[] args) {
        PenduModele model = new PenduModele();
        PenduVue view = new PenduVue(model);
        PenduControleur controler = new PenduControleur(model, view);

        JFrame frame = new JFrame("Jeu du pendu");
        frame.getContentPane().add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
