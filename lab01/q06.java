public class q06 {
    public static void main(String[] args) {
        long totalMilliseconds = java.lang.System.currentTimeMillis();

        long totalSeconds = totalMilliseconds / 1000;
        long currentSecond = totalSeconds % 60;
        
        long totalMinutes = totalSeconds / 60;
        long currentMinute = totalMinutes % 60;

        long totalHours = totalMinutes / 60;
        long currenHour = totalHours % 24;

        System.err.printf("%d:%02d:%02d", currenHour, currentMinute, currentSecond);
    }
}