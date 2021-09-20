import java.util.Scanner;

public class ObligTelefonTastatur {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        System.out.print("Oppgi en bokstav: ");
        char BrukerInput = Character.toUpperCase(input.next().charAt(0));
        int KTall; //Korresponderende Tall


        if ( BrukerInput >= 'A' && BrukerInput <= 'Z' ) {

            if( BrukerInput <= 'R' ) { KTall = 2 + (BrukerInput - 'A' ) / 3; }
            else if ( BrukerInput == 'S'){ KTall = 7; }
            else if ( BrukerInput == 'T' || BrukerInput == 'U' || BrukerInput == 'V' ){ KTall = 8; }
            else { KTall = 9; }

            System.out.print("Korresponderende tall for " + BrukerInput + " er: " + KTall);

        } else {

            System.out.println(BrukerInput + " har ikke et korresponderende tall.");

        }
    }
}
