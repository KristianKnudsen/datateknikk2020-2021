public class ObligCelsiusogFahrenheit {
    public static void main(String[] args){

        double celsius = 40;
        double fahrenheit = 120;
        int celsiusStep = -1;
        int fahrenheitStep = -10;
        int numberOfRows = 10;

        printTable(celsius, fahrenheit, celsiusStep, fahrenheitStep, numberOfRows);
    }
    private static void printTable(double c, double f, int cS, int fS, int numberOfRows){
        System.out.printf("%-13s", "Celsius");
        System.out.printf("%-13s", "Fahrenheit");
        System.out.printf("%-13s", "Fahrenheit");
        System.out.printf("%-13s%n", "Celsius");

        for(int i = 0; i < numberOfRows; i++){
            System.out.printf("%-13.1f", c+cS*i);
            System.out.printf("%-13.1f", celsiusToFahrenheit(c+cS*i));

            System.out.printf("%-13.1f", f+fS*i);
            System.out.printf("%-13.2f%n", fahrenheitToCelsius(f+fS*i));
        }
    }

    public static double celsiusToFahrenheit(double celsius){
        return (9.0/5)*celsius+32;
    }

    public static double fahrenheitToCelsius(double fahrenheit){
        return (5.0/9)*(fahrenheit-32);
    }
}
