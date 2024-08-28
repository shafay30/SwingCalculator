import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SwingCalculator extends JFrame implements ActionListener {
    private JTextField inputField;
    private JButton[] numberButtons = new JButton[10];
    private JButton[] functionButtons = new JButton[8];
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decButton, equButton, delButton, clrButton;
    private JPanel panel;

    public SwingCalculator() {
        // Frame setup
        setTitle("Calculator");
        setSize(420, 550);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Input field
        inputField = new JTextField();
        inputField.setBounds(50, 25, 300, 50);
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setEditable(false);
        add(inputField);

        // Function buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;

        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            functionButtons[i].setFocusable(false);
        }

        // Number buttons
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            numberButtons[i].setFocusable(false);
        }

        // Panel to hold number and function buttons
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Add buttons to panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(divButton);

        // Add panel to frame
        add(panel);

        // Equal, Del, and Clr buttons
        equButton.setBounds(50, 430, 145, 50);
        delButton.setBounds(205, 430, 145, 50);
        clrButton.setBounds(50, 490, 300, 50);

        equButton.setFont(new Font("Arial", Font.PLAIN, 18));
        equButton.setFocusable(false);
        equButton.addActionListener(this);

        add(equButton);
        add(delButton);
        add(clrButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Number and decimal point input
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                inputField.setText(inputField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            inputField.setText(inputField.getText().concat("."));
        }

        // Operation buttons
        if (e.getSource() == addButton) {
            inputField.setText(inputField.getText().concat("+"));
        }
        if (e.getSource() == subButton) {
            inputField.setText(inputField.getText().concat("-"));
        }
        if (e.getSource() == mulButton) {
            inputField.setText(inputField.getText().concat("*"));
        }
        if (e.getSource() == divButton) {
            inputField.setText(inputField.getText().concat("/"));
        }

        // Evaluate the expression when the equal button is pressed
        if (e.getSource() == equButton) {
            try {
                String expression = inputField.getText();
                double result = evaluate(expression); // Call custom evaluation function
                inputField.setText(String.valueOf(result)); // Show result in text field
            } catch (Exception ex) {
                inputField.setText("Error");
            }
        }

        // Clear the input field
        if (e.getSource() == clrButton) {
            inputField.setText("");
        }

        // Delete the last character
        if (e.getSource() == delButton) {
            String text = inputField.getText();
            inputField.setText(text.length() > 0 ? text.substring(0, text.length() - 1) : "");
        }
    }

    // Simple evaluation method for basic expressions
    private double evaluate(String expression) {
        // Replace multiple operators with a single operator, if needed
        expression = expression.replaceAll("--", "+");

        // Split expression into components
        String[] tokens = expression.split("(?<=[-+*/])|(?=[-+*/])");

        double result = 0;
        double currentValue = 0;
        String operator = "+";

        for (String token : tokens) {
            token = token.trim();
            if (token.matches("[+\\-*/]")) {
                operator = token;
            } else {
                currentValue = Double.parseDouble(token);
                switch (operator) {
                    case "+":
                        result += currentValue;
                        break;
                    case "-":
                        result -= currentValue;
                        break;
                    case "*":
                        result *= currentValue;
                        break;
                    case "/":
                        result /= currentValue;
                        break;
                }
            }
        }
        return result;
    }

    // Main method
    public static void main(String[] args) {
        new SwingCalculator();
    }
}
