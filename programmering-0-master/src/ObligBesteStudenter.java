import java.util.Scanner;

public class ObligBesteStudenter {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        System.out.print("Oppgi antall studenter: ");
        int AntallStudenter = input.nextInt();
        String[] ForstePlass = {"0", "Navn"};
        String[] AndrePlass = {"0", "Navn"};

        for ( int i = 1; i <= AntallStudenter; i++ ) {
            System.out.print("Oppgi studentens score og navn på student " + i + ":");
            int Score = input.nextInt();
            String Navn = input.nextLine();

            if ( Score > Integer.parseInt(ForstePlass[0]) ){
                ForstePlass[0] = Integer.toString(Score);
                ForstePlass[1] = Navn;
            } else if ( Score > Integer.parseInt(AndrePlass[0]) ){
                AndrePlass[0] = Integer.toString(Score);
                AndrePlass[1] = Navn;
            }
        }

        System.out.print("\n");

        switch ( AntallStudenter ){
            case 0:
                System.out.println("Ingen studentdata tilgjengelig");
                break;
            case 1:
                System.out.println("Bare en student oppgitt: \n" + ForstePlass[1] + " med score: " + ForstePlass[0] );
                break;
            default:
                System.out.println("Top to studenter:");
                System.out.println("Første plass: " + ForstePlass[1] + " med score: " + ForstePlass[0]);
                System.out.println("Andre plass: " + AndrePlass[1] + " med score: " + AndrePlass[0]);
        }
    }
}
