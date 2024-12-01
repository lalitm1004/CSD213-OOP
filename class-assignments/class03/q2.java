import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class q2 extends JFrame {
    private JTextField displayField;
    private double result = 0;
    private String lastCommand = "=";
    private boolean start = true;

    public q2() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel displayPanel = new JPanel();
        displayField = new JTextField(20);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);
        displayPanel.add(displayField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            buttonPanel.add(button);
            if (label.matches("[0-9.]")) {
                button.addActionListener(new NumberAction());
            } else {
                button.addActionListener(new OperatorAction());
            }
        }

        setLayout(new BorderLayout());
        add(displayPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(_ -> {
            start = true;
            result = 0;
            lastCommand = "=";
            displayField.setText("");
        });
        add(clearButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private class NumberAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String input = event.getActionCommand();
            if (start) {
                displayField.setText("");
                start = false;
            }
            displayField.setText(displayField.getText() + input);
        }
    }

    private class OperatorAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();

            if (!start) {
                calculate(Double.parseDouble(displayField.getText()));
                start = true;
            }
            lastCommand = command;
        }
    }

    private void calculate(double x) {
        switch (lastCommand) {
            case "+": result += x; break;
            case "-": result -= x; break;
            case "*": result *= x; break;
            case "/": result /= x; break;
            case "=": result = x; break;
        }
        displayField.setText("" + result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new q2().setVisible(true);
            }
        });
    }
}