import java.util.*;

public class Car extends Vehicle {

    private int power;
    private Calendar productionDate;

    public Car(){}

    public Car(String name, String colour, int price,
               int model, String serialNumber, int direction, int power){

        super(name, colour, price, model, serialNumber, direction);
        this.power = power;
    }

    public void breaks(int factor){
        setSpeed(getSpeed()/factor);
        System.out.println("Car slowed down to: " + getSpeed() + "km/h");
    }

    public void accelerate(int factor){
        if (getSpeed() == 0){
            setSpeed( 0.5 * factor);
        } else {
            setSpeed(getSpeed()*factor);
        }
        if ( getSpeed() > MAX_SPEED_CAR ) { setSpeed( MAX_SPEED_CAR ); }

        System.out.println("Car accelerated to: " + getSpeed() + "km/h");
    }

    public Object clone() throws CloneNotSupportedException {
        Car carClone = (Car) super.clone();
        if ( productionDate != null ) {
            Calendar productionDateClone = (Calendar) productionDate.clone();
            carClone.setBuyingDate(productionDateClone);
        }
        return carClone;
    }

    public void turnRight(int degrees){

        if ( degrees > 0 && degrees < 360){

            setDirection( getDirection() + degrees );

            if( getDirection() >= 360 ){

                setDirection( getDirection() - 360 );

            }
        }
    }

    public void turnLeft(int degrees){

        if ( degrees > 0 && degrees < 360){

            setDirection( getDirection() - degrees );

            if( getDirection() < 0 ){

                setDirection( getDirection() - 360 );

            }
        }
    }

    public void setAllFields(){
        super.setAllFields();
        System.out.print("Type the cars horsepower: ");
        this.power = input.nextInt();
    }

    @Override
    public String toString() {
        return super.toString() + "\n The horsepower of the car is: " + getPower();
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Calendar getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Calendar productionDate) {
        this.productionDate = productionDate;
    }
}
