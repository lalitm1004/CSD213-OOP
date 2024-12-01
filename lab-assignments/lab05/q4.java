import java.util.Scanner;

class SleepyThread implements Runnable {
    String name;
    long sleepDurationMs;
    Thread t;

    public SleepyThread(String name, long sleepDurationMs) {
        this.t = new Thread(this, name);
        this.name = name;
        this.sleepDurationMs = sleepDurationMs;
    }

    public void run() {
        System.out.printf("thread %s sleeping for %d mS\n", name, sleepDurationMs);
        try {
            Thread.sleep(sleepDurationMs);
        } catch (InterruptedException e) {
            System.err.printf("thread %s interrupted > %s\n", name, e);
        }
        System.out.printf("thread %s waking up\n", name);
    }
}

public class q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("x mS > ");
        long x = sc.nextLong();
        System.out.print("y mS > ");
        long y = sc.nextLong();
        System.out.print("z mS > ");
        long z = sc.nextLong();

        SleepyThread t1 = new SleepyThread("t1", y);
        SleepyThread t2 = new SleepyThread("t2", z);

        t1.t.start();
        t2.t.start();
        System.out.printf("thread main sleeping for %d mS\n", x);
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            System.err.printf("thread main interrupted > %s\n", e);
        }
        System.out.println("thread main waking up");

        try {
            t1.t.join();
            t2.t.join();
        } catch (InterruptedException e) {
            System.err.printf("thread main interrupted > %s\n", e);
        }
        System.out.println("all threads finished execution");
        sc.close();
    }
}