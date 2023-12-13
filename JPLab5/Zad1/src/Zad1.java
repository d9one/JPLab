import javax.swing.*;

public class Zad1 extends JFrame {
    public Zad1() {
        super("Zadanie1");
        ControlPanelZad1 controlPanelZad1 = new ControlPanelZad1();
        setContentPane(controlPanelZad1);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Zad1::new);
    }
}
