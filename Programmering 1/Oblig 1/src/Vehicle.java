import java.util.*;

public abstract class Vehicle implements Comparable<Vehicle>, Cloneable, Driveable{

    private String colour, name, serialNumber;
    private int model, price, direction;
    private double speed;
    private Calendar buyingDate;

    protected java.util.Scanner input = new Scanner(System.in);

    public Vehicle(){
        this.buyingDate = Calendar.getInstance();
    }

    public Vehicle(String name, String colour, int price,
                   int model, String serialNumber, int direction){

        this.colour = colour;
        this.name = name;
        this.serialNumber = serialNumber;
        this.model = model;
        this.price = price;
        this.direction = direction;
        this.buyingDate = Calendar.getInstance();
    }

    public void stop(){
        this.speed = 0;
        System.out.println("Stopped vehicle");
    }



    public void setAllFields(){

        System.out.print("Type the vehicle colour: ");
        this.colour = input.nextLine();

        System.out.print("Type the name of the vehicle: ");
        this.name = input.nextLine();

        System.out.print("Type the serial # of the vehicle: ");
        this.serialNumber = input.nextLine();

        System.out.print("Type the vehicle model number: ");
        this.model = input.nextInt();

        System.out.print("Type the vehicle price: ");
        this.price = input.nextInt();

    }

    @Override
    public int compareTo(Vehicle o) {

        return Integer.compare(this.price, o.price);

    }

    public Object clone() throws CloneNotSupportedException {
        Vehicle vehicleClone = (Vehicle) super.clone();
        Calendar buyingDateClone = (Calendar) buyingDate.clone();
        vehicleClone.setBuyingDate(buyingDateClone);
        return vehicleClone;
    }

    public abstract void turnLeft(int degrees);

    public abstract void turnRight(int degrees);

    public String toString(){
        return "\n The name of the vehicle is: " + name +
                "\n The color of the vehicle is: " + colour +
                "\n The serial number of the vehicle is: " + serialNumber +
                "\n The model number of the vehicle is: " + model +
                "\n The price of the vehicle is: " + price +
                "\n The direction of the vehicle is: " + direction + " degrees" +
                "\n The speed of the vehicle is: " + speed + " km/h";
    }


    public void setColour(String colour){
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Calendar getBuyingDate() {
        return buyingDate;
    }

    public void setBuyingDate(Calendar buyingDate) {
        this.buyingDate = buyingDate;
    }

}
