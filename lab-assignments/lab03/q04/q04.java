package q04;


class Fan {
    static final int SLOW = 1;
    static final int MEDIUM = 2;
    static final int FAST = 3;

    private int speed;
    private boolean on;
    private double radius;
    private String color;

    public Fan() {
        this.speed = SLOW;
        this.on = false;
        this.radius = 5.0;
        this.color = "blue";
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        String output = "";

        if (on) {
            output += "\tspeed > " + speed + "\n";
        }
        output += "\tradius > " + radius + "\n\tcolor > " + color;
        return output;
    }
}

public class q04 {
    public static void main(String[] args) {
        Fan f1 = new Fan();
        Fan f2 = new Fan();

        f1.setSpeed(Fan.FAST);
        f1.setRadius(10);
        f1.setColor("yellow");
        f1.setOn(true);
        System.out.println("fan1:");
        System.out.println(f1.toString());

        f2.setSpeed(Fan.MEDIUM);
        f2.setColor("blue");
        f2.setOn(false);
        System.out.println("fan2:");
        System.out.println(f2.toString());
    }
}