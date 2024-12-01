import java.util.Scanner;

public class q3 {
    private final int n;
    private boolean letterTurn = false;

    public q3(int n) {
        this.n = Math.min(n, 26);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("n [1, 26] > ");
        int n = sc.nextInt();

        if (n < 1 || n > 26) {
            System.out.println("invalid input. n must lie between 1 and 26");
            sc.close();
            return;
        }

        q3 printer = new q3(n);

        Thread numberThread = new Thread(printer::printNaturalNumbers);
        Thread letterThread = new Thread(printer::printLetters);

        numberThread.start();
        letterThread.start();

        sc.close();
    }

    private synchronized void printNaturalNumbers() {
        for (int i = 1; i <= n; i++) {
            while (letterTurn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            for (int j = 1; j <= i; j++) {
                System.out.print(j + (j == i ? "\t" : "\n"));
            }
            letterTurn = true;
            notify();
        }
    }

    private synchronized void printLetters() {
        for (char c = 'A'; c < 'A' + n; c++) {
            while (!letterTurn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            System.out.println(c);
            letterTurn = false; 
            notify();
        }
    }
}
