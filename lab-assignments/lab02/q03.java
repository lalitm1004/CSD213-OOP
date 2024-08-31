import java.util.Scanner;

class MinimumStack {
    int top, capacity;
    int[] stack;
    int minItem;

    MinimumStack(int capacity) {
        this.capacity = capacity;
        stack = new int[capacity];
        top = 0;
    }

    void push(int item) {
        if (top >= capacity) {
            System.out.println("OVERFLOW");
            return;
        }
        minItem = item < minItem || top == 0 ? item : minItem;
        stack[top++] = item;
    }

    void pop() {
        if (top <= 0) {
            System.out.println("UNDERFLOW");
            return;
        }
        top--;
        updateMin();
    }

    int peek() {
        if (top <= 0) {
            System.out.println("UNDERFLOW");
            return Integer.MIN_VALUE;
        }
        return stack[top-1];
    }

    int top() {
        if (top <= 0) {
            System.out.println("UNDERFLOW");
            return Integer.MIN_VALUE;
        }
        top--;
        updateMin();
        return stack[top];
    }

    int getMin() {
        return minItem;
    }

    void updateMin() {
        for (int i = 0; i < top; i++) {
            minItem = stack[i] < minItem ? stack[i] : minItem;
        }
    }

    void displayStack() {
        System.out.printf("[ ");
        for (int i = 0; i < top; i++) {
            System.out.printf("%d, ", stack[i]);
        }
        System.out.printf(" ]\n");
    }
}

public class q03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter capacity > ");
        int capacity = sc.nextInt();
        MinimumStack stack = new MinimumStack(capacity);

        int choice;
        displayMenu();
        do {
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("enter item to push > ");
                    int itemPush = sc.nextInt();
                    stack.push(itemPush);
                    break;
                case 2:
                    stack.pop();
                    System.out.println("popped item");
                    break;
                case 3:
                    int itemPeek = stack.peek();
                    System.out.printf("peeked item > %d\n", itemPeek);
                    break;
                case 4:
                    int itemTop = stack.top();
                    System.out.printf("top item > %d\n", itemTop);
                    break;
                case 5:
                    int itemMin = stack.getMin();
                    System.out.printf("min item > %d\n", itemMin);
                    break;
                case 6:
                    stack.displayStack();
                    break;
                case 7:
                    displayMenu();
                    break;
                case 8:
                    System.out.println("exiting");
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }
        } while (choice != 8);
        sc.close();
    }

    public static void displayMenu() {
        System.out.println("enter your choice:");
        System.out.println("1. push");
        System.out.println("2. pop");
        System.out.println("3. peek");
        System.out.println("4. top");
        System.out.println("5. getMin");
        System.out.println("6. display stack");
        System.out.println("7. display menu");
        System.out.println("8. exit");
    }
}