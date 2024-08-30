import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class q02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter nums > ");
        String numStr = sc.nextLine();
        sc.close();
        int maxNum = getMax(numStr);
        System.out.println(maxNum);
    }

    public static int getMax(String numStr) {
        // convert str to int arrayList
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (String s: numStr.split(" ")) {
            nums.add(Integer.parseInt(s));
        }

        // sort arrayList and return last element
        Collections.sort(nums);
        return nums.getLast();
    }
}