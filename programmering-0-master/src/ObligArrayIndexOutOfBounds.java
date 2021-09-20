import java.util.Scanner;

public class ObligArrayIndexOutOfBounds {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int[] integers = new int[100];

        intFillArray(integers);

        try {

            System.out.print("Enter an index: ");
            int index = input.nextInt();
            System.out.println("The element is " + integers[index]);

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.print("Out of bounds");
        }
    }

    private static void intFillArray(int[] array){
        for ( int i = 0; i < array.length; i++){
         array[i] = (int)(Math.random()*10000);
        }
    }
}
