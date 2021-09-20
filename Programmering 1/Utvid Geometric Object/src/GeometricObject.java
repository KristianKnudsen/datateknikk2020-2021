import java.util.GregorianCalendar;

public abstract class GeometricObject implements Comparable{

  private String color;
  private boolean filled;
  private GregorianCalendar dateCreated;

  public GeometricObject(){
    this.dateCreated = new GregorianCalendar();
  }

  public GeometricObject(String color, boolean filled){
    this.color = color;
    this.filled = filled;
    this.dateCreated = new GregorianCalendar();
  }

  public int compareTo(Object o) {

    return Double.compare(this.getArea(), ((GeometricObject) o).getArea());

  }

  public static GeometricObject max(GeometricObject o1, GeometricObject o2){

    if (o1.compareTo(o2) == -1) {
      return o2;
    } else if (o1.compareTo(o2) == 1){
      return o1;
    } else {
      System.out.print("Compared objects are the same size.");
      return null;
    }

  }

  public abstract boolean equals(Object o);

  public GregorianCalendar getDateCreated() {
    return dateCreated;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public boolean isFilled() {
    return filled;
  }

  public void setFilled(boolean filled) {
    this.filled = filled;
  }

  public abstract double getArea();

  public abstract double getPerimeter();


}