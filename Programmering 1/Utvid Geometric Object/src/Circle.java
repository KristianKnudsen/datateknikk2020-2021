public class Circle extends GeometricObject {
    private double radius;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(double radius, String color, boolean filled){
        super(color, filled);
        this.radius = radius;
    }

    public boolean equals(Object o) {

        if (o instanceof GeometricObject){
            return getArea() == ((GeometricObject) o).getArea();
        }

        return false;

    }

    /** Return radius */
    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Circle created on: " + getDateCreated().getTime() +
                "\nRadius: " + getRadius() +
                "\nColor: " + getColor() +
                "\nIs filled: " + isFilled();
    }

    /** Set a new radius */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return radius * radius * Math.PI;
    }

    /** Return diameter */
    public double getDiameter() {
        return 2 * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }

    /* Print the circle info */
    public void printCircle() {
        System.out.println("The circle is created " + getDateCreated() +
                " and the radius is " + radius);
    }
}