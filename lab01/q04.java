import java.util.Scanner;

public class q04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.err.print("enter n > ");
        int n = sc.nextInt();
        sc.close();
        for (int i = 1; i <= n; i++) {
            fizzBuzz(i);
        }
    }

    public static void fizzBuzz(int num) {
        String output = num + " > ";
        
        if (num % 3 == 0) output += "Fizz";
        if (num % 5 == 0) output += "Buzz";

        System.out.println(output);
    }
}