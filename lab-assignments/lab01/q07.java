import java.util.Scanner;

class q07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("enter 10 numbers");
        double[] nums = new double[10];

        for (int i = 0; i < nums.length; i++) {
            System.out.printf("%d > ", i+1);
            nums[i] = sc.nextDouble();
        }
        sc.close();

        insertionSort(nums);

        System.out.print("sorted array > ");
        for (int i = 0; i < nums.length; i++) {
            System.out.printf("%.2f, ", nums[i]);
        }
        
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
}