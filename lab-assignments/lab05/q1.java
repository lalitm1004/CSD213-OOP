public class q1 {
    private static int number;
    private static boolean hasNewNumber = false;

    public static void main(String[] args) {
        q1 producerConsumer = new q1();

        Thread producer = new Thread(() -> {
            while (true) {
                producerConsumer.produce();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            while (true) {
                producerConsumer.consume();
            }
        });

        producer.start();
        consumer.start();
    }

    public synchronized void produce() {
        while (hasNewNumber) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        number = (int) (Math.random() * 10000);
        System.out.println("generated > " + number);
        hasNewNumber = true;
        notify();
    }

    public synchronized void consume() {
        while (!hasNewNumber) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        String numberInWords = convertToWords(number);
        System.out.println("converted > " + numberInWords);
        hasNewNumber = false;
        notify();
    }

    private static String convertToWords(int number) {
        if (number == 0) {
            return "Zero";
        }

        String[] units = {
            "", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen",
            "fourteen", "fifteen", "sixteen",
            "seventeen", "eighteen", "nineteen"
        };

        String[] tens = {
            "", "", "twenty", "thirty", "forty",
            "fifty", "sixty", "seventy", "eighty",
            "ninety"
        };

        String[] thousands = {"", "thousand"};

        StringBuilder words = new StringBuilder();

        if (number / 1000 > 0) {
            words.append(units[number / 1000])
                .append(" ")
                .append(thousands[1])
                .append(" ");
            number %= 1000;
        }
        if (number / 100 > 0) {
            words.append(units[number / 100]).append(" hundred ");
            number %= 100;
        }
        if (number > 0) {
            if (number < 20) {
                words.append(units[number]);
            } else {
                words.append(tens[number / 10]).append(" ");
                if (number % 10 > 0) {
                    words.append(units[number % 10]);
                }
            }
        }

        return words.toString().trim();
    }
}
