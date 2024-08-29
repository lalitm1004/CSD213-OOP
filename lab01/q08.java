import java.util.Scanner;

class q08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("enter 10 nums (sorted)");
        double[] nums = new double[10];

        for (int i = 0; i < nums.length; i++) {
            System.out.printf("%d > ", i+1);
            nums[i] = sc.nextDouble();
        }

        System.out.print("enter target num > ");
        double target = sc.nextDouble();

        sc.close();

        insertionSort(nums);
        int targetIndex = binarySearch(nums, target, 0, nums.length);
        System.out.printf("target found at index: %d", targetIndex);
    }

    public static void insertionSort(double[] arr) {
        for (int i = 1; i < arr.length; i++) {
            double key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j -= 1;
            }
            arr[j + 1] = key;
        }
    }

    public static int binarySearch(double[] sortedArr, double targetNum, int startIndex, int endIndex) {
        while (startIndex <= endIndex) {
            int mid = startIndex + (endIndex - startIndex) / 2;
            if (sortedArr[mid] == targetNum) {
                return mid;
            } else if (sortedArr[mid] < targetNum) {
                startIndex = mid + 1;
            } else {
                endIndex = mid - 1;
            }
        }
        return -1;
    }
}