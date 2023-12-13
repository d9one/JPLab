import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class ControlPanel extends JPanel implements ActionListener{
    private JButton abcButton;
    private JButton defButton;
    private JButton ghiButton;
    private JButton jklButton;
    private JButton mnoButton;
    private JButton pqrButton;
    private JButton stuvButton;
    private JButton wxyzButton;
    private JButton DMButton;
    private JButton CEButton;
    private JButton CButton;
    private JTextField textfield;
    private JButton equalsButton;
    private JButton divideButton;
    private JButton addButton;
    private JButton substractButton;
    private JPanel controlPanel;
    static String currentText="";
    static boolean uppercase = false;

    public ControlPanel(){

//        abcButton.addActionListener(this);
        defButton.addActionListener(this);
        ghiButton.addActionListener(this);
        jklButton.addActionListener(this);
        mnoButton.addActionListener(this);
        pqrButton.addActionListener(this);
        stuvButton.addActionListener(this);
        wxyzButton.addActionListener(this);
        substractButton.addActionListener(this);
        addButton.addActionListener(this);
        divideButton.addActionListener(this);
        equalsButton.addActionListener(this);
        DMButton.addActionListener(this);
        CEButton.addActionListener(this);
        CButton.addActionListener(this);
        abcButton.addActionListener(new ActionListener() {
            private long lastClick = -1;
            private int count;
            private String[] value = {"a", "b", "c"};
            @Override
            public void actionPerformed(ActionEvent e) {
                long now = System.currentTimeMillis();
                if (now - lastClick < 2000) {
                    count++;
                    if (count >= value.length) {
                        count = 0;
                    }
                } else {
                    count = 0;
                }
                currentText+=value[count];
                textfield.setText(currentText);
                lastClick = System.currentTimeMillis();
            }
        });



        setLayout(new FlowLayout());
        add(controlPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String source = e.getActionCommand();

        if(Objects.equals(source, "D/M")){
            uppercase=!uppercase;
        }
        else if(Objects.equals(source, "CE")){
            removeLast();
            textfield.setText(currentText);
        }
        else if(Objects.equals(source, "C")){
            currentText = "";
            textfield.setText(currentText);
        }
        else if (Objects.equals(source, "abc")) {
           
        }


    }

    public static void removeLast(){
        if(!currentText.isEmpty()){
            currentText = currentText.substring(0, currentText.length()-1);
        }
    }

}
