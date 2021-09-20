import java.util.Scanner;

public class ObligEkteOgUekteBroker {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Oppgi teller: ");
        int Teller = input.nextInt();
        System.out.print("Oppgi nevner: ");
        int Nevner = input.nextInt();

        if ( Teller < Nevner ){
            System.out.println(Teller + "/" + Nevner + " er en ekte brøk.");
        } else if ( Teller % Nevner == 0){
            System.out.println(Teller + "/" + Nevner + " er en uekte brøk.");
            System.out.println("Det kan reduseres til " + Teller/Nevner + ".");
        } else {
            System.out.println(Teller + "/" + Nevner + " er en uekte brøk.");
            System.out.println("Det er et blandet tall " + Teller/Nevner + " + " + Teller%Nevner + "/" + Nevner + ".");
        }
    }
}
