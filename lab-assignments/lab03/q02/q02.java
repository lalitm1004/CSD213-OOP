package q02;


class Triangle {
    private double side1, side2, side3;

    public Triangle() {
        this.side1 = 1;
        this.side2 = 1;
        this.side3 = 1;
    }

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double getSide1() {
        return side1;
    }

    public void setSide1(double side1) {
        this.side1 = side1;
    }

    public double getSide2() {
        return side2;
    }

    public void setSide2(double side2) {
        this.side2 = side2;
    }

    public double getSide3() {
        return side3;
    }

    public void setSide3(double side3) {
        this.side3 = side3;
    }

    public double getArea() {
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    public double getPerimeter() {
        return side1 + side2 + side3;
    }
}

public class q02 {
    public static void main(String[] args) {
        Triangle t1 = new Triangle(4, 5, 6);
        Triangle t2 = new Triangle(1.5, 2.5, 3.5);

        System.out.printf("triangle 1:\n\tside1 > %.2f\n\tside2 > %.2f\n\tside3 > %.2f\n\tperimeter > %.2f\n\tarea > %.2f\n", t1.getSide1(), t1.getSide2(), t1.getSide3(), t1.getPerimeter(), t1.getArea());
        System.out.printf("triangle 2:\n\tside1 > %.2f\n\tside2 > %.2f\n\tside3 > %.2f\n\tperimeter > %.2f\n\tarea > %.2f\n", t2.getSide1(), t2.getSide2(), t2.getSide3(), t2.getPerimeter(), t2.getArea());
    }
}