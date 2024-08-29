import java.util.Scanner;
import java.util.ArrayList;

public class q03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter nums > ");
        String numStr = sc.nextLine();
        sc.close();
        ArrayList<Integer> nums = convertToNums(numStr);
        for (int num: nums) {
            if (isPrime(num)) System.out.printf("%d ", num);
        }
    }

    public static ArrayList<Integer> convertToNums(String numStr) {
        // convert str to int arrayList
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (String s: numStr.split(" ")) {
            nums.add(Integer.parseInt(s));
        }
        return nums;
    }

    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        int sqrt = (int)Math.sqrt(num);
        for (int i = 3; i <= sqrt; i += 2) { // Skip even numbers
            if (num % i == 0) return false;
        }
        return true;
    }
}