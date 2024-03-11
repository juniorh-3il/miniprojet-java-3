import javax.swing.*;

public class HangmanApplication {

    public static void main(String[] args) {
        HangmanModel model = new HangmanModel();
        HangmanView view = new HangmanView(model);
        HangmanController controller = new HangmanController(model, view);

        JFrame frame = new JFrame("Jeu du pendu");
        frame.getContentPane().add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
