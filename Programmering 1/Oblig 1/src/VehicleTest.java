import java.util.*;
import java.io.*;

public class VehicleTest {
  public static void main(String[] args) {
	  VehicleTest vtest = new VehicleTest();
    try {
      vtest.menuLoop();
    } catch(IOException e) {
      System.out.println("IO Exception!");
      System.exit(1);
    } catch(CloneNotSupportedException e) {
      System.out.println("CloneNotSupportedException");
      System.exit(1);
    }
  }

  private void menuLoop() throws IOException, CloneNotSupportedException {
    Scanner input = new Scanner(System.in);
    ArrayList<Vehicle> arr=new ArrayList<Vehicle>();
    Vehicle vehicle;

    arr.add(new Car("Volvo","Black",85000,2010,"1010-11",163,0));
    arr.add(new Bicycle("Diamant","yellow",4000,1993,"BC100",10,0));
    arr.add(new Car("Ferrari Testarossa","red",1200000,1996,"A112",350,0));
    arr.add(new Bicycle("DBS","pink",5000,1994,"42",10,0));

    while(true) {
      System.out.println("1...................................New car");
      System.out.println("2...............................New bicycle");
      System.out.println("3......................Find vehicle by name");
      System.out.println("4..............Show data about all vehicles");
      System.out.println("5.......Change direction of a given vehicle");
      System.out.println("6.........................Test clone method");
      System.out.println("7..................Test driveable interface");
      System.out.println("8..............................Exit program");
      System.out.println("...............................Your choice?");
      int choice = input.nextInt();

      switch (choice) {
        case 1:
          arr.add(new Car());
          arr.get(arr.size()-1).setAllFields();
          break;

        case 2:
          arr.add(new Bicycle());
          arr.get(arr.size()-1).setAllFields();
          break;

        case 3:
          System.out.print("Name of vehicle: ");
          input.nextLine();
          String nameOfVehicle = input.nextLine();

          for (Vehicle value : arr) {
            if ( nameOfVehicle.equals( value.getName() ) ) {
              System.out.println( value.toString() );
            }
          }

          break;
        case 4:
          for (Vehicle value : arr) {
            System.out.println( value.toString() );
          }
          break;

        case 5:
          System.out.print("Name of vehicle: ");
          input.nextLine();
          nameOfVehicle = input.nextLine();

          System.out.print("Direction [R/L]: ");
          char direction = input.next().charAt(0);

          System.out.print("Degrees [0-360]: ");
          int degrees = input.nextInt();

          for (Vehicle value : arr) {

            if ( nameOfVehicle.equals( value.getName() ) ) {

              if (direction == 'R'){

                value.turnRight(degrees);
              } else {

                value.turnLeft(degrees);
              }
            }
          }
          break;

        case 8:
          input.close();
          System.exit(0);

        case 6:
          cloneVehicleTest();
          break;

        case 7:
          testDriveable();
          break;

        default:
          System.out.println("Wrong input!");
      }
    }
  }
  public void cloneVehicleTest() throws CloneNotSupportedException {

    Car v1 = new Car("Volvo","Black",85000,2010,"1010-11",163,0);

    Car v2 = (Car) v1.clone();

    Calendar cal = Calendar.getInstance();

    cal.set(9999, Calendar.MARCH, 1);

    v2.setBuyingDate(cal);

    System.out.println("\nDate object are separate: ");
    System.out.println(v1.getBuyingDate().getTime()
            + "\n" + v2.getBuyingDate().getTime());


  }

  public void testDriveable(){
    Car car1 = new Car();
    car1.accelerate(10);
    car1.accelerate(5000);
    car1.breaks(10);
    car1.stop();

    Bicycle bicycle1 = new Bicycle();
    bicycle1.accelerate(10);
    bicycle1.accelerate(20000);
    bicycle1.breaks(10);
    bicycle1.stop();

  }
}




