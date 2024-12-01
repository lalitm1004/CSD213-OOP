import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class q1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(q1::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("FutureAmountCalculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLayout(new GridLayout(5, 2, 10, 10));
        JLabel lblInvestmentAmount = new JLabel("Investment Amount:");
        JTextField txtInvestmentAmount = new JTextField();

        JLabel lblAnnualInterestRate = new JLabel("Annual Interest Rate (%):");
        JTextField txtAnnualInterestRate = new JTextField();

        JLabel lblNumYears = new JLabel("Number of Years:");
        JTextField txtNumYears = new JTextField();

        JLabel lblFutureValue = new JLabel("Future Value:");
        JLabel lblFutureValueResult = new JLabel("0.00");

        JButton btnCalculate = new JButton("Calculate");

        // Add components to the frame
        frame.add(lblInvestmentAmount);
        frame.add(txtInvestmentAmount);
        frame.add(lblAnnualInterestRate);
        frame.add(txtAnnualInterestRate);
        frame.add(lblNumYears);
        frame.add(txtNumYears);
        frame.add(lblFutureValue);
        frame.add(lblFutureValueResult);
        frame.add(btnCalculate);

        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double investmentAmount = Double.parseDouble(txtInvestmentAmount.getText());
                    double annualInterestRate = Double.parseDouble(txtAnnualInterestRate.getText()) / 100;
                    double numYears = Double.parseDouble(txtNumYears.getText());

                    double futureValue = investmentAmount * Math.pow(1 + annualInterestRate/12, numYears*12);

                    DecimalFormat df = new DecimalFormat("#,##0.00");
                    lblFutureValueResult.setText(df.format(futureValue));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        frame,
                        "Please enter valid numeric values.",
                        "Input Error", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        // Set frame to be visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}