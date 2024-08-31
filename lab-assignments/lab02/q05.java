import java.util.Arrays;
import java.util.Scanner;

public class q05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("enter 1 to sort an integer array, 2 to sort a string array > ");
        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.print("enter size of integer array > ");
            int n = sc.nextInt();
            int[] intArray = new int[n];
            for (int i = 0; i < n; i++) {
                System.out.printf("%d > ", i+1);
                intArray[i] = sc.nextInt();
            }

            System.out.print("reverse (true/false) > ");
            boolean reverse = sc.nextBoolean();

            if (reverse) {
                sort(intArray, n, reverse);
            } else {
                sort(intArray, n);
            }
        } else if (choice == 2) {
            System.out.print("enter size of string array > ");
            int n = sc.nextInt();
            sc.nextLine();
            String[] strArray = new String[n];
            for (int i = 0; i < n; i++) {
                System.out.printf("%d > ", i+1);
                strArray[i] = sc.nextLine();
            }
            sort(strArray, n);
        } else {
            System.out.println("invalid choice");
        }
        sc.close();
    }

    public static void sort(int[] arr, int n) {
        Arrays.sort(arr);
        System.out.println("Sorted Integer Array (Ascending): " + Arrays.toString(arr));
    }

    public static void sort(int[] arr, int n, boolean reverse) {
        Arrays.sort(arr);
        if (reverse) {
            reverseArray(arr);
            System.out.println("Sorted Integer Array (Descending): " + Arrays.toString(arr));
        } else {
            System.out.println("Sorted Integer Array (Ascending): " + Arrays.toString(arr));
        }
    }

    public static void reverseArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void sort(String[] arr, int n) {
        Arrays.sort(arr);
        System.out.println("Sorted String Array (Lexicographically): " + Arrays.toString(arr));
    }
}