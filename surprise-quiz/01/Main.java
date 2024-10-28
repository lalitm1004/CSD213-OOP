import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Done in pair
// Lalit Maurya lm742 2310110164
// Jia Swapnil Khot jk637 2310110491

public class Main {

    public static void main(String[] args) {
        Player player = new Player("Gronk", 100);
        System.out.println(player.toString());
        saveObject(player);
        try {
            player.read(readValues());
        } catch (IndexOutOfBoundsException e) {
            // do nothing
        }
        System.out.println(player);
    }

    public static ArrayList<String> readValues() {
        ArrayList<String> values = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        int index = 0;
        System.out.println("-----options-----");
        System.out.println("0 > quit");
        System.out.println("1 > read to object");
        System.out.println("-----------------");

        while (!quit) {
            System.out.print("option > ");
            try {
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 0:
                        quit = true;
                        break;
                    case 1:
                        System.out.print("read value > ");
                        String stringInput = sc.nextLine();
                        values.add(index, stringInput);
                        index++;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR: option must be an integer (either 0 or 1) | " + e);
            }
        }
        sc.close();
        return values;
    }

    public static void saveObject(ISaveable objectToSave) {
        for (int i = 0; i < objectToSave.write().size(); i++) {
            System.out.println("saving " + objectToSave.write().get(i) + " to storage");
        }
    }
}
