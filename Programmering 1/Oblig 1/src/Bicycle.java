import java.math.BigInteger;
import java.util.*;

public class Bicycle extends Vehicle{

    private int gears;
    private Calendar productionDate;

    public Bicycle(){}

    public Bicycle(String name, String colour, int price,
                   int model, String serialNumber, int direction, int gears){

        super(name, colour, price, model, serialNumber, direction);
        this.gears = gears;

    }

    public void breaks(int factor){
        setSpeed(getSpeed()/(factor*0.5)); // stige ved factor < 2
        System.out.println("Bicycle slowed down to: " + getSpeed() + "km/h");
    }

    public void accelerate(int factor){
        if (getSpeed() == 0){
            setSpeed( 0.3 * factor);
        } else {
            setSpeed(getSpeed()*factor*0.5); //farten vil reduseres hvis factor er under 2
        }
        if ( getSpeed() > MAX_SPEED_BIKE ) { setSpeed( MAX_SPEED_BIKE ); }

        System.out.println("Bike accelerated to: " + getSpeed() + "km/h");
    }

    public void setAllFields(){

        super.setAllFields();
        System.out.print("Type the gears of the bicycle: ");
        this.gears = input.nextInt();

    }


    @Override
    public void turnLeft(int degrees) {

        System.out.println("The bicycle turned " + degrees + " degrees to the left.");

    }

    @Override
    public void turnRight(int degrees) {

        System.out.println("The bicycle turned " + degrees + " degrees to the right.");

    }

    public Object clone() throws CloneNotSupportedException {
        Bicycle bicycleClone = (Bicycle) super.clone();
        if ( productionDate != null ) {
            Calendar productionDateClone = (Calendar) productionDate.clone();
            bicycleClone.setBuyingDate(productionDateClone);
        }
        return bicycleClone;
    }

    public String toString(){
        return super.toString() + "\n The bicycle has: " + getGears() + " gears";
    }

    public int getGears() {
        return gears;
    }

    public void setGears(int gears) {
        this.gears = gears;
    }

    public Calendar getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Calendar productionDate) {
        this.productionDate = productionDate;
    }


}
