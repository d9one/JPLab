import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class StringCalculator extends JFrame {
    private JTextField textField;
    private boolean isUpperCase = false;
    private String currentInput = "";
    private String operation = "";
    private String previousInput = "";
    private long lastPressTime = 0;
    private char lastButton = '\0';

    private final Map<Character, String[]> lettersMap = new HashMap<>();

    public StringCalculator() {
        createUI();
        initializeLetterMappings();
    }

    private void createUI() {
        setTitle("String Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        textField = new JTextField(20);
        textField.setEditable(false);
        add(textField);

        addButtons();
        pack();
    }

    private void addButtons() {
        addButton("D/M", e -> toggleCase());
        String[] letterGroups = {"abc", "def", "ghi", "jkl", "mno", "pqr", "stu", "vwx", "yz"};
        for (String group : letterGroups) {
            addButton(group, e -> appendLetter(e.getActionCommand().charAt(0)));
        }
        addButton("CE", e -> removeLastCharacter());
        addButton("C", e -> clearAll());
        addButton("+", e -> setOperation("+"));
        addButton("-", e -> setOperation("-"));
        addButton("/", e -> setOperation("/"));
        addButton("=", e -> calculateResult());
    }

    private void addButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setActionCommand(text);
        button.addActionListener(listener);
        add(button);
    }

    private void toggleCase() {
        isUpperCase = !isUpperCase;
    }

    private void appendLetter(char group) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastPressTime > 1000 || lastButton != group) {
            lastButton = group;
            lastPressTime = currentTime;
            currentInput += isUpperCase ? Character.toUpperCase(group) : group;
        } else {
            String[] letters = lettersMap.get(lastButton);
            int lastCharIndex = currentInput.length() - 1;
            char lastChar = currentInput.charAt(lastCharIndex);
            String lettersString = String.join("", letters);
            int letterIndex = lettersString.indexOf(lastChar) + 1;
            if (letterIndex >= letters.length) {
                letterIndex = 0;
            }
            currentInput = currentInput.substring(0, lastCharIndex) + (isUpperCase ? letters[letterIndex].toUpperCase() : letters[letterIndex]);
        }
        textField.setText(currentInput);
    }


    private void removeLastCharacter() {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            textField.setText(currentInput);
        }
    }

    private void clearAll() {
        currentInput = "";
        previousInput = "";
        operation = "";
        textField.setText("");
    }

    private void setOperation(String op) {
        if (!currentInput.isEmpty()) {
            previousInput = currentInput;
            currentInput = "";
            operation = op;
            textField.setText("");
        }
    }

    private void calculateResult() {
        if (!previousInput.isEmpty() && !currentInput.isEmpty()) {
            switch (operation) {
                case "+":
                    currentInput = previousInput + currentInput;
                    break;
                case "-":
                    currentInput = previousInput.replaceAll(currentInput, "");
                    break;
                case "/":
                    currentInput = commonSubstring(previousInput, currentInput);
                    break;
            }
            textField.setText(currentInput);
            previousInput = "";
            operation = "";
        }
    }

    private String commonSubstring(String str1, String str2) {
        int maxLength = 0;
        int endIndex = 0;
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i;
                    }
                }
            }
        }

        return str1.substring(endIndex - maxLength, endIndex);
    }

    private void initializeLetterMappings() {
        lettersMap.put('a', new String[]{"a", "b", "c"});
        lettersMap.put('b', new String[]{"b", "c", "a"});
        lettersMap.put('c', new String[]{"c", "a", "b"});
        lettersMap.put('d', new String[]{"d", "e", "f"});
        lettersMap.put('e', new String[]{"e", "f", "d"});
        lettersMap.put('f', new String[]{"f", "d", "e"});
        lettersMap.put('g', new String[]{"g", "h", "i"});
        lettersMap.put('h', new String[]{"h", "i", "g"});
        lettersMap.put('i', new String[]{"i", "g", "h"});
        lettersMap.put('j', new String[]{"j", "k", "l"});
        lettersMap.put('k', new String[]{"k", "l", "j"});
        lettersMap.put('l', new String[]{"l", "j", "k"});
        lettersMap.put('m', new String[]{"m", "n", "o"});
        lettersMap.put('n', new String[]{"n", "o", "m"});
        lettersMap.put('o', new String[]{"o", "m", "n"});
        lettersMap.put('p', new String[]{"p", "q", "r"});
        lettersMap.put('q', new String[]{"q", "r", "p"});
        lettersMap.put('r', new String[]{"r", "p", "q"});
        lettersMap.put('s', new String[]{"s", "t", "u"});
        lettersMap.put('t', new String[]{"t", "u", "s"});
        lettersMap.put('u', new String[]{"u", "s", "t"});
        lettersMap.put('v', new String[]{"v", "w", "x"});
        lettersMap.put('w', new String[]{"w", "x", "v"});
        lettersMap.put('x', new String[]{"x", "v", "w"});
        lettersMap.put('y', new String[]{"y", "z"});
        lettersMap.put('z', new String[]{"z", "y"});
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StringCalculator calculator = new StringCalculator();
            calculator.setVisible(true);
        });
    }
}
