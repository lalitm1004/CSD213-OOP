import java.util.Scanner;

public class q05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("enter interest rate > ");
        double annualInterestRate = sc.nextDouble();
        System.out.print("enter num years > ");
        double numYears = sc.nextDouble();
        System.out.print("enter loan amount > ");
        double loanAmount = sc.nextDouble();
        sc.close();

        double monthlyInterestRate = annualInterestRate / 1200;

        double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - (1 / Math.pow(1 + monthlyInterestRate, numYears * 12)));
        System.err.printf("montly interest payment > %.2f", monthlyPayment);
    }
}