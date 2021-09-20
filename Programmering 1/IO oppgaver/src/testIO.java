import java.io.*;
import java.util.ArrayList;

public class testIO {


    public static void main(String[] args) {

        try (FileInputStream fis = new FileInputStream("loans.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            // read object from file
            for ( int i = 0; i < 1000; i++ ) {
                Loan user = (Loan) ois.readObject();
                System.out.println(user.toString());
            }
            // print object


        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }




        try {

            FileOutputStream fileOut = new FileOutputStream("loans.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for ( int i = 0; i < 1000; i++ ) {
                Loan loan1 = new Loan(i,i,i);
                objectOut.writeObject(loan1);
            }
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }





        System.out.println("Hello world");




    }
}
