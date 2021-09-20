import java.util.Scanner;

public class ObligSteinSaksPapir1 {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        int RandomTall = (int)(Math.random()*3);
        String[] NavneVerdier = {"Saks","Stein","Papir"};

        System.out.print("Saks (0), Stein (1) eller Papir (2)? ");
        int BrukerInput = input.nextInt();

        if ( BrukerInput < 0 || BrukerInput > 2 ){
            System.out.println("Ugyldig tall, kun 0, 1 eller 2 aksepteres.");
            return;
        }

        System.out.print("Datamaskin er " + NavneVerdier[RandomTall] + ". Du er " + NavneVerdier[BrukerInput] + ". ");
        if ( BrukerInput - RandomTall == 0){
            System.out.print("Uavgjort.");
        } else if ( BrukerInput+1 == RandomTall || (BrukerInput == 2 && RandomTall == 0)){
            System.out.print("Du tapte.");
        } else {
            System.out.print("Du har vunnet.");
        }
    }
}
