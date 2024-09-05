import java.util.Arrays;
import java.util.Scanner;

public class q01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("enter size of array > ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.printf("%d > ", i+1);
            arr[i] = sc.nextInt();
        }
        sc.close();

        System.out.println("array > " + Arrays.toString(arr));

        int smallestEven = findSmallestEven(arr, 0, Integer.MAX_VALUE);
        System.out.printf("smallest even number > %s\n", smallestEven == -1 ? "none" : smallestEven);

        int largestOdd = findLargestOdd(arr, 0, Integer.MIN_VALUE);
        System.out.printf("largest odd number > %s\n", largestOdd == -1 ? "none" : largestOdd);

        int largestPrimeFactor = findLargestPrimeFactorInArray(arr, 0, -1);
        System.out.printf("largest prime factor > %s\n", largestPrimeFactor == -1 ? "none" : largestPrimeFactor);
    }

    public static int findSmallestEven(int[] arr, int index, int currentMin) {
        if (index == arr.length) {
            return currentMin == Integer.MAX_VALUE ? -1 : currentMin;
        }

        if (arr[index] % 2 == 0 && arr[index] < currentMin) {
            currentMin = arr[index];
        }

        return findSmallestEven(arr, index + 1, currentMin);
    }

    public static int findLargestOdd(int[] arr, int index, int currentMax) {
        if (index == arr.length) {
            return currentMax == Integer.MIN_VALUE ? -1 : currentMax;
        }

        if (arr[index] % 2 == 1 && arr[index] > currentMax) {
            currentMax = arr[index];
        }

        return findLargestOdd(arr, index + 1, currentMax);
    }

    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        int sqrt = (int)Math.sqrt(num);
        for (int i = 3; i <= sqrt; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static int findLargestPrimeFactor(int num, int divisor) {
        if (num == 1) return -1;
        if (num % divisor == 0) {
            if (isPrime(divisor)) {
                return Math.max(divisor, findLargestPrimeFactor(num / divisor, divisor));
            }
        }
        return findLargestPrimeFactor(num, divisor + 1);
    }

    public static int findLargestPrimeFactorInArray(int[] arr, int index, int currentMax) {
        if (index == arr.length) {
            return currentMax == -1 ? -1 : currentMax;
        }

        int largestPrimeFactor = findLargestPrimeFactor(arr[index], 2);
        currentMax = largestPrimeFactor > currentMax ? largestPrimeFactor : currentMax;
        return findLargestPrimeFactorInArray(arr, index + 1, currentMax);
    }
}