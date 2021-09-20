import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class USCapitals {

    BufferedReader br = new BufferedReader(new FileReader("src/USCapitals.txt"));
    HashMap<String, String> capitals = new HashMap<>();

    public USCapitals() throws IOException {
        try {
            String line = br.readLine();

            while (line != null) {
                capitals.put(line.split(",\\s*")[0], line.split(",\\s*")[1]);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
    }

}

class runUSCapitals {
    public static void main(String[] args ) throws IOException {

        USCapitals u = new USCapitals();
        Scanner input = new Scanner(System.in);

        while ( true ) {
            System.out.print("Type your state to get capital (Type exit to quit): ");
            String state = input.nextLine();
            String cap = u.capitals.get(state);

            if (cap != null) {
                System.out.println("The capital of " + state + " is " + cap);
            } else if ( state.equals("exit") ){
                System.out.println("Shutting down");
                break;
            } else {
                System.out.println("State not found");
            }
        }
    }
}

