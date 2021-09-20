public class TestGeometricObject {

    public static void main(String[] args) {

        Rectangle rectangle1 = new Rectangle(15.0, 2.0, "red", true);
        Rectangle rectangle2 = new Rectangle(2.0, 3.0);

        Triangle triangle1 = new Triangle("blue", false, 15.00, 7.00, 10.00);
        Triangle triangle2 = new Triangle(30.00, 30.00, 2.00);

        Circle circle1 = new Circle(3.14);
        Circle circle2 = new Circle(2, "green", true);

        System.out.println( "The biggest rectangle:\n" +
                GeometricObject.max(rectangle1, rectangle2).toString() + "\n" );

        System.out.println( "The biggest circle:\n" +
                GeometricObject.max(circle1, circle2).toString() + "\n" );

        System.out.println( "The biggest triangle:\n" +
                GeometricObject.max(triangle1, triangle2).toString() + "\n");

        System.out.println( "The biggest geometric object is:\n" +
                GeometricObject.max( GeometricObject.max(rectangle1, rectangle2) ,GeometricObject.max(GeometricObject.max(triangle1, triangle2), GeometricObject.max(circle1, circle2)  )  ).toString());

    }
}
