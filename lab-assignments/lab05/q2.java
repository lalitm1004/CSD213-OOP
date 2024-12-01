public class q2 {
    private final int n;

    public q2(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        int n = 10;
        q2 printer = new q2(n);
        Thread naturalThread = new Thread(() -> printer.printNaturalNumbers());
        Thread evenThread = new Thread(() -> {
            try {
                naturalThread.join(); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            printer.printEvenNumbers();
        });

        Thread oddThread = new Thread(() -> {
            try {
                evenThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            printer.printOddNumbers();
        });

        naturalThread.start();
        evenThread.start();
        oddThread.start();
    }

    private void printNaturalNumbers() {
        System.out.println("first " + n + " natural numbers >");
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " ");
        }

        System.out.println();
    }

    private void printEvenNumbers() {
        System.out.println("first " + n + " even numbers >");
        for (int i = 1; i <= n; i++) {
            System.out.print((i * 2) + " ");
        }

        System.out.println();
    }

    private void printOddNumbers() {
        System.out.println("first " + n + " odd numbers > ");
        for (int i = 1; i <= n; i++) {
            System.out.print((i * 2 - 1) + " ");
        }
        
        System.out.println();
    }
}
