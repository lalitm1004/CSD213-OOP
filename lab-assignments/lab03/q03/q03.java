package q03;

abstract class Shape {
    abstract double getArea();
    abstract void getInfo();
}

abstract class TwoDimensionalShape extends Shape {
    abstract double getPerimeter();
    abstract double getArea();
    abstract void getInfo();
}

class Circle extends TwoDimensionalShape {
    public double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius; 
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public void getInfo() {
        System.out.printf("circle:\n\tradius > %.2f\n", radius);
    }
}

class Square extends TwoDimensionalShape {
    public double side;

    public Square(double side) {
        this.side = side;
    }

    public double getPerimeter() {
        return 4 * side;
    }

    public double getArea() {
        return Math.pow(side, 2);
    }

    public void getInfo() {
        System.out.printf("square:\n\tside > %.2f\n", side);
    }
}

class Triangle extends TwoDimensionalShape {
    public double side1, side2, side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    public double getArea() {
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s * side2) * (s - side3));
    }

    public void getInfo() {
        System.out.printf("square:\n\tside1 > %.2f\n\tside2 > %.2f\n\tside3 > %.2f\n", side1, side2, side3);
    }
}

abstract class ThreeDimensionalShape extends Shape {
    abstract double getArea();
    abstract double getVolume();
    abstract void getInfo();
}

class Sphere extends ThreeDimensionalShape {
    public double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    public double getVolume() {
        return 4.0/3.0 * Math.PI * Math.pow(radius, 3);
    }

    public void getInfo() {
        System.out.printf("sphere:\n\tradius > %.2f\n", radius);
    }
}

class Cube extends ThreeDimensionalShape {
    public double side;

    public Cube(double side) {
        this.side = side;
    }

    public double getArea() {
        return 6 * Math.pow(side, 2);
    }

    public double getVolume() {
        return Math.pow(side, 3);
    }

    public void getInfo() {
        System.out.printf("cube:\n\tside > %.2f\n", side);
    }
}

class Tetrahedron extends ThreeDimensionalShape {
    public double side;

    public Tetrahedron(double side) {
        this.side = side;
    }

    public double getArea() {
        return Math.sqrt(3) * Math.pow(side, 2);
    }

    public double getVolume() {
        return Math.pow(side, 3) / (6 * Math.sqrt(2));
    }

    public void getInfo() {
        System.out.printf("tetrahedron:\n\tside > %.2f\n", side);
    }
}

public class q03 {
    public static void main(String[] args) {
        Shape[] arr = new Shape[] {
            new Circle(5),
            new Square(10),
            new Triangle(1.5, 2.5, 3.5),
            new Sphere(10),
            new Cube(20),
            new Tetrahedron(15),
        };
        
        for (Shape shape: arr) {
            shape.getInfo();

            if (shape instanceof TwoDimensionalShape) {
                TwoDimensionalShape twoDimShape = (TwoDimensionalShape) shape;
                System.out.printf("\tperimeter > %.2f\n\tarea > %.2f\n", twoDimShape.getPerimeter(), twoDimShape.getArea());
            }

            if (shape instanceof ThreeDimensionalShape) {
                ThreeDimensionalShape threeDimShape = (ThreeDimensionalShape) shape;
                System.out.printf("\tarea > %.2f\n\tvolume > %.2f\n", threeDimShape.getArea(), threeDimShape.getVolume());
            }
        }
    }
}