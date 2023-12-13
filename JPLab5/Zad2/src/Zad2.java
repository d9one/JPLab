import javax.swing.*;

public class Zad2 extends JFrame {
    public Zad2() {
        super("Zadanie2");

        ControlPanel controlPanel = new ControlPanel();
        setContentPane(controlPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Zad2::new);
    }
}
