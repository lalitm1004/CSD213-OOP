import java.util.Scanner;

public class q02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 11;
        int[] arr = new int[n];
        System.out.println("enter arr elements -");
        for (int i = 0; i < n; i++) {
            System.out.printf("%d > ", i+1);
            arr[i] = sc.nextInt();
        }
        sc.close();
        printArray(arr);
        reverseArray(arr);
        printArray(arr);
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

    public static void printArray(int[] arr) {
        System.out.print("array > [ ");
        for (int i = 0; i < arr.length; i++){
            System.out.printf("%d, ", arr[i]);
        }
        System.out.print("]\n");
    }
}