import java.io.*;
import java.util.*;

public class TestLoanClass {

  static ArrayList<Loan> loanObjects = new ArrayList<Loan>();
  static ArrayList<Loan> unsavedLoans = new ArrayList<Loan>();

  public static void main(String[] args) {

    updateArrayOfLoans();

    while(true){
      Scanner input = new Scanner(System.in);
      System.out.println("What do you want to do: ");
      System.out.println("1..............Get info about saved loans");
      System.out.println("2........................Create new loans");
      System.out.println("3............................Exit program");
      int choice = input.nextInt();

      switch (choice){
        case 1:
          getInfo();
          break;
        case 2:
          createNewLoans();
          break;
        case 3:
          input.close();
          System.exit(0);
        default:
          System.out.println("Wrong input!");
      }
    }
  }


  public static void getInfo(){

    if ( loanObjects.size() == 0 ){
      System.out.println("No saved loans");
    } else {
      System.out.println("Number of saved loans: " + loanObjects.size());
    }

    for (int i = 0; i < loanObjects.size(); i++ ){
      System.out.println(loanObjects.get(i).toString());
    }

  }

  public static void createNewLoans(){

    System.out.println("What do you want to do: ");
    while(true){
      Scanner input = new Scanner(System.in);
      System.out.println("1.........................Create new loan");
      System.out.println("2......................Save created loans");
      System.out.println("3......................Show unsaved loans");
      System.out.println("4.....Back ( Unsaved loans will be gone )");
      int choice = input.nextInt();
      switch (choice){
        case 1:
          createNewLoan();
          break;
        case 2:
          System.out.println("Loans saved");
          saveUnsavedLoans();
          break;
        case 3:
          System.out.println("Number of unsaved loans: " + unsavedLoans.size());
          for (int i = 0; i < unsavedLoans.size(); i++){
            System.out.println( "unsaved loan " + i + ":\n" + unsavedLoans.get(i).toString() );
          }
          break;
        case 4:
          System.out.println("Returning to menu unsaved progress deleted.");
          unsavedLoans.clear();
          return;
        default:
          System.out.println("Wrong input!");
      }

    }
  }

  public static void createNewLoan(){
    Scanner input = new Scanner(System.in);
    System.out.println("Creating new loan: ");

    System.out.println("Enter yearly interest rate, for example 2,34");
    double annualInterestRate = input.nextDouble();

    System.out.print("Enter number of years as an integer: ");
    int numberOfYears = input.nextInt();

    System.out.print("Enter loan amount, for example, 120000.95: ");
    double loanAmount =  input.nextDouble();

    Loan loan =
            new Loan(annualInterestRate, numberOfYears, loanAmount);
    unsavedLoans.add(loan);
  }

  public static void updateArrayOfLoans() {

    try (FileInputStream fis = new FileInputStream("loans.dat");
         ObjectInputStream ois = new ObjectInputStream(fis)) {

        Loan temp = null;
        while ( true ){

          temp = (Loan) ois.readObject();
          if ( temp != null ) {
            loanObjects.add(temp);
          }
        }

    } catch (IOException | ClassNotFoundException ex) {
      System.out.println("Stopped updating arraylist");
    }

  }

  public static void saveUnsavedLoans(){

    loanObjects.addAll(unsavedLoans);
    unsavedLoans.clear();

    try {

      FileOutputStream fileOut = new FileOutputStream("loans.dat");
      ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

      for ( int i = 0; i < loanObjects.size(); i++ ) {
        Loan tempLoan = loanObjects.get(i);
        objectOut.writeObject(tempLoan);
      }


      objectOut.close();
      System.out.println("The Object  was succesfully written to a file");

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}


