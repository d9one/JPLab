import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlPanelZad1 extends JPanel {
    private JPanel buttonPanel;
    private JTextField a3textField;
    private JTextField b3textField;
    private JTextField c3textField;
    private JTextField a2textField;
    private JTextField b2textField;
    private JTextField c2textField;
    private JTextField a1textField;
    private JTextField b1textField;
    private JTextField c1textField;
    private JTextField operationtextField;
    private JButton solveButton;
    private JLabel Result;
    private JTextField c0textField;
    private JPanel mainPanel;

    private static Map<String, JTextField> symbolFields = new HashMap<>();


    public class MyDocumentListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            if(!(operationtextField.getText().equals("+") || operationtextField.getText().equals("-"))){
                JOptionPane.showMessageDialog(null, "Wprowadz poprawny symbol operacji (+/-)", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {

        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }
    public ControlPanelZad1(){
        MyDocumentListener myDocumentListener = new MyDocumentListener();
        operationtextField.getDocument().addDocumentListener(myDocumentListener);

        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                symbolFields.clear();
                symbolFields.put("a1", a1textField);
                symbolFields.put("a2", a2textField);
                symbolFields.put("a3", a3textField);
                symbolFields.put("b1", b1textField);
                symbolFields.put("b2", b2textField);
                symbolFields.put("b3", b3textField);
                symbolFields.put("c0", c0textField);
                symbolFields.put("c1", c1textField);
                symbolFields.put("c2", c2textField);
                symbolFields.put("c3", c3textField);
                for (Map.Entry<String, JTextField> entry: symbolFields.entrySet()) {
                    String symbol = entry.getKey();
                    JTextField textField = entry.getValue();
                    String value = textField.getText();
                    System.out.println(symbol + " " + value);
                }
                if(operationtextField.getText()==null){
                    JOptionPane.showMessageDialog(null, "Podaj znak operacji (+/-)", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
                    List<String> ABC = calculateABC(symbolFields);
                    for (String s: ABC) {
                        System.out.println(s);
                    }
//                    String result;
//                    result = ABC[0] + "+" + ABC[1] + "=" + ABC[2];
//                    System.out.println(result);
//                    System.out.println(finalResult(result));
//                    System.out.println(sumaStronna(finalResult(result)));


            }
        });
        setLayout(new FlowLayout());
        add(mainPanel);

    }
    public List<String> calculateABC(Map<String,JTextField> symbolFields){
        Equation equation = new Equation();
        Variable a1=null, a2=null, a3, b1, b2, b3, c0, c1, c2, c3;
        List<String>result = new ArrayList<>();

        for (Map.Entry<String, JTextField> entry: symbolFields.entrySet()) {
            String symbol = entry.getKey();
            JTextField textField = entry.getValue();
            String value = textField.getText();
            if(symbol.equals("a1")){
                a1 = new Variable(100 * operationSign(), value);
                equation.addToLeft(a1);
            }
            else if(symbol.equals("a2")){
                 a2 = new Variable(10 * operationSign(), value);
                equation.addToLeft(a2);
            }
            else if(symbol.equals("a3")){
                a3 = new Variable(operationSign(), value);
                equation.addToLeft(a3);
            }
            if(symbol.equals("b1")){
                b1 = new Variable(100 * operationSign(), value);
                equation.addToLeft(b1);
            }
            else if(symbol.equals("b2")){
                b2 = new Variable(10 * operationSign(), value);
                equation.addToLeft(b2);
            }
            else if(symbol.equals("b3")){
                b3 = new Variable(operationSign(), value);
                equation.addToLeft(b3);
            }
            if(symbol.equals("c0")){
                c0 = new Variable(1000 * operationSign(), value);
                equation.addToRight(c0);
            }
            else if(symbol.equals("c1")){
                c1 = new Variable(100 * operationSign(), value);
                equation.addToRight(c1);
            }
            else if(symbol.equals("c2")){
                c2 = new Variable(10 * operationSign(), value);
                equation.addToRight(c2);
            }
            else if(symbol.equals("c3")){
                c3 = new Variable(operationSign(), value);
                equation.addToRight(c3);
            }
        }
        equation.simplify();

        List<Variable> variablesAfterSimplification = new ArrayList<>();
        variablesAfterSimplification.addAll(equation.left);
        variablesAfterSimplification.addAll(equation.right);

        for (Variable x:variablesAfterSimplification) {
            equation.flipVariable(x);
            result.add(equation.toString());
        }
//        equation.flipVariable(a1);
//        return List.of(equation.toString());
//        A = a1 + "+" + a2 + "+" + a3;
//        B = b1 + "+" +  b2 + "+" + b3;
//        C = c0 + "+" + c1 + "+" + c2 + "+" + c3;

//        return new String[]{A, B, C};
        return result;
    }



    public int operationSign(){
        if(operationtextField.getText().equals("+")){
            return 1;
        }
        return -1;
    }
    public String finalResult(String s) {
        String[] parts = s.split("=");
        String left = parts[0];
        String right = parts[1];
        String leftAfter = "";
        String rightAfter = "";

        String[] partAddL = left.split("\\+");
        int counter = 0;

        for (String part : partAddL) {
            counter++;
            if (part.contains("*")) {
                int index = part.indexOf("*");
                if (index >= 0) {
                    if (index < part.length() - 1 && Character.isDigit(part.charAt(index + 1))) {
                        int mnoznik = Integer.parseInt(String.valueOf(part.charAt(index + 1)));
                        int mnozna = Integer.parseInt(part.substring(0, index));
                        int wynik = mnozna * mnoznik;
                        part = String.valueOf(wynik);
                    } else {
                        String help = part.substring(0, index) + part.substring(index + 1);
                        part = help;
                    }
                }
                if (counter < 6) {
                    leftAfter = leftAfter + part + "+";
                } else {
                    leftAfter = leftAfter + part;
                }
            } else {
                if (counter < 6) {
                    leftAfter = leftAfter + part + "+";
                } else {
                    leftAfter = leftAfter + part;
                }
            }
        }

        String[] partAddR = right.split("\\+");
        counter = 0;

        for (String part : partAddR) {
            counter++;
            if (part.contains("*")) {
                int index = part.indexOf("*");
                if (index >= 0) {
                    if (index < part.length() - 1 && Character.isDigit(part.charAt(index + 1))) {
                        int mnoznik = Integer.parseInt(String.valueOf(part.charAt(index + 1)));
                        int mnozna = Integer.parseInt(part.substring(0, index));
                        int wynik = mnozna * mnoznik;
                        part = String.valueOf(wynik);
                    } else {
                        String help = part.substring(0, index) + part.substring(index + 1);
                        part = help;
                    }
                }
                if (counter < 4) {
                    rightAfter = rightAfter + part + "+";
                } else {
                    rightAfter = rightAfter + part;
                }
            } else {
                if (counter < 4) {
                    rightAfter = rightAfter + part + "+";
                } else {
                    rightAfter = rightAfter + part;
                }
            }
        }

        return leftAfter + "=" + rightAfter;
    }

    public static String sumaStronna(String s) {
        int sumaL = 0, sumaR = 0, suma = 0;
        String[] parts = s.split("=");
        String left = parts[0];
        String right = parts[1];
        String[] partsL = left.split("\\+");
        String[] partsR = right.split("\\+");
        List<String> zmienneL = new ArrayList<>();
        List<String> zmienneR = new ArrayList<>();

        for (String part : partsL) {
            try {
                int x = Integer.parseInt(part);
                sumaL += x;
            } catch (NumberFormatException e) {
                zmienneL.add(part);
            }
        }

        for (String part : partsR) {
            try {
                int x = Integer.parseInt(part);
                sumaR += x;
            } catch (NumberFormatException e) {
                zmienneR.add(part);
            }
        }

        suma = sumaR - sumaL;

        return String.join("+", zmienneL) + "=" + String.join("+", zmienneR) + "+" + String.valueOf(suma);
    }
}

