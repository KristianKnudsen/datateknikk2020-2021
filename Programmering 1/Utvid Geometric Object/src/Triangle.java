public class Triangle extends GeometricObject{

  private double side1, side2, side3;

  public Triangle(){
    this.side1 = 1.0;
    this.side2 = 1.0;
    this.side3 = 1.0;
    setColor("Yellow");
  }

  public Triangle(double side){
    this.side1 = side;
    this.side2 = side;
    this.side3 = side;
    setColor("Blue");
    setFilled(true);
  }

  public Triangle(double side1, double side2, double side3){
    if (checkLegality(side1, side2, side3)) {
      this.side1 = side1;
      this.side2 = side2;
      this.side3 = side3;
      setColor("Red");
    }
  }

  public Triangle(String color, boolean filled, double side1, double side2, double side3) {
    super(color, filled);
    this.side1 = side1;
    this.side2 = side2;
    this.side3 = side3;
  }

  public boolean checkLegality(double side1, double side2, double side3){
    return side1 > 0 && side2 > 0 && side3 > 0
            && (side3 + side2) > side1
            && (side1 + side3) > side2
            && (side1 + side2) > side3;
  }

  public double getArea(){
    double s = ( side1 + side2 + side3 ) / 2;
    return Math.sqrt( s * ( s - side1 )*( s - side2 )*( s - side3 ) );
  }

  public double getPerimeter(){
    return side1+side2+side3;
  }

  public double getSide1() {
    return side1;
  }

  @Override
  public String toString(){
    if ( checkLegality(side1, side2, side3) ) {
      return "Triangle object created on " + getDateCreated().getTime() + "\ncolor: " + getColor()
              + "\nsides: " + getSide1() + "," + getSide2() + "," + getSide3()
              + "\ncolor: " + getColor()
              + "\nperimeter: " + getPerimeter()
              + " and filled: " + isFilled();

    } else { return "Triangle is illegal"; }
  }

  public boolean equals(Object o) {

    if (o instanceof GeometricObject){
      return getArea() == ((GeometricObject) o).getArea();
    }

    return false;

  }

  public double getSide2() {
    return side2;
  }

  public double getSide3() {
    return side3;
  }

  public void setSide1(double side1) {
    this.side1 = side1;
  }

  public void setSide2(double side2) {
    this.side2 = side2;
  }

  public void setSide3(double side3) {
    this.side3 = side3;
  }

}


