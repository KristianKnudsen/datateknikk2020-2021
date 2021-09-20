public class Rectangle extends GeometricObject {
    private double width;
    private double height;

    public Rectangle() {
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle(double width, double height, String color, boolean filled ){
        super(color, filled);
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Rectangle created on: " + getDateCreated().getTime() +
                "\nWidth: " + getWidth() +
                "\nHeight: " + getHeight() +
                "\nColor: " + getColor() +
                "\nFilled: " + isFilled();
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof GeometricObject){
            return getArea() == ((GeometricObject) o).getArea();
        }

        return false;

    }

    /** Return width */
    public double getWidth() {
        return width;
    }

    /** Set a new width */
    public void setWidth(double width) {
        this.width = width;
    }

    /** Return height */
    public double getHeight() {
        return height;
    }

    /** Set a new height */
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

}