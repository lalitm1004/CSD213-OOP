package q05;


import java.util.Scanner;

public class q05 {
    private static boolean[] seats = new boolean[10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1 for first class");
            System.out.println("2 for economy");
            System.out.print("enter choice > ");
            int choice = sc.nextInt();

            if (choice == 1) {
                if (!assignSeat(0, 5)) {
                    System.out.print("first class is full. switch to economy? (y/n) > ");
                    if (sc.next().equalsIgnoreCase("y")) {
                        if (!assignSeat(5, 10)) {
                            System.out.println("all seats full. next flight in 3 hours");
                            break;
                        }
                    } else {
                        System.out.println("next flight in 3 hours.");
                    }
                }
            } else if (choice == 2) {
                if (!assignSeat(5, 10)) {
                    System.out.print("economy is full. switch to first class? (y/n) > ");
                    if (sc.next().equalsIgnoreCase("y")) {
                        if (!assignSeat(0, 5)) {
                            System.out.println("all seats full. next flight in 3 hours");
                            break;
                        }
                    } else {
                        System.out.println("next flight in 3 hours.");
                    }
                }
            } else {
                System.out.println("invalid input.");
            }
        }
        sc.close();
    }

    private static boolean assignSeat(int start, int end) {
        for (int i = start; i < end; i++) {
            if (!seats[i]) {
                seats[i] = true;
                System.out.println("-----BOARDINGPASS-----");
                System.out.printf("Seat : %d\n", i + 1);
                System.out.printf("Class : %s\n", start < 5 ? "First class" : "Economy");
                System.out.println("----------------------");
                return true;
            }
        }
        return false;
    }
}