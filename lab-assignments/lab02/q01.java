class Circle2D {
    double x, y;
    double radius;

    Circle2D() {
        x = 0;
        y = 0;
        radius = 1;
    }

    Circle2D(double xCoord, double yCoord, double radius) {
        x = xCoord;
        y = yCoord;
        this.radius = radius;
    }

    double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    boolean contains(double x, double y) {
        return Math.hypot((x - this.x), (y - this.y)) < radius;
    }

    boolean contains(Circle2D circle) {
        return Math.hypot((x - this.x), (y - this.y)) <= radius - circle.radius;
    }

    boolean overlaps(Circle2D circle) {
        return Math.hypot((x - this.x), (y - this.y)) < radius + circle.radius && !contains(circle);
    }
}

public class q01 {
    public static void main(String[] args) {
        Circle2D c1 = new Circle2D(2, 2, 5.5);
        System.out.printf("c1 area > %.2f : perimeter > %.2f\n", c1.getArea(), c1.getPerimeter());
        System.out.printf("c1 contains (3, 3) %b\n", c1.contains(3, 3));
        System.out.printf("c1 contains Circle(4, 5, 10.5) %b\n", c1.contains(new Circle2D(4, 5, 10.5)));
        System.out.printf("c1 overlaps Circle(3, 5, 2.3) %b\n", c1.overlaps(new Circle2D(3, 5, 2.3)));
    }
}