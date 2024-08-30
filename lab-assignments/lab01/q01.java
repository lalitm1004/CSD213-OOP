import java.util.Scanner;

public class q01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter number > ");
        int num = sc.nextInt();
        sc.close();
        
        long factorial = factorial(num);
        System.out.printf("%d! = %d", num, factorial);
    }

    public static long factorial(int num) {
        long factorial = 1;
        for (int i = 1; i <= num; i++) {
            factorial *= i;
        }
        return factorial;
    }
}