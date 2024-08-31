import java.util.Scanner;

class Stack {
    int capacity, top;
    char[] stack;

    Stack(int capacity) {
        this.capacity = capacity;
        stack = new char[capacity];
        top = 0;
    }

    void push(char item) {
        if (top >= capacity) {
            System.out.println("OVERFLOW");
            return;
        }
        stack[top++] = item;
    }

    char pop() {
        if (top <= 0) {
            System.out.println("UNDERFLOW");
            return ' ';
        }
        return stack[--top];
    }

    boolean isEmpty() {
        return top <= 0;
    }
}

public class q04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter string to check > ");
        String input = sc.nextLine();
        sc.close();
        boolean isBalanced = isBalanced(input);
        System.out.println(isBalanced ? "balanced" : "not balanced");
    }

    public static boolean isBalanced(String input) {
        Stack stack = new Stack(100);
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!isBracket(c)) continue;

            if (isOpening(c)) {
                stack.push(c);
                continue;
            }

            if (stack.isEmpty()) return false;

            char check = stack.pop();
            if (!isMatching(c, check)) return false;

        }
        return stack.isEmpty();
    }

    public static boolean isBracket(char c) {
        return isOpening(c) || isClosing(c);
    }

    public static boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    public static boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    public static boolean isMatching(char b1, char b2) {
        switch (b1) {
            case ')':
                return b2 == '(';
            case ']':
                return b2 == '[';
            case '}':
                return b2 == '{';
            default:
                return false;
        }
    }
}