import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ObligTestAccount {
    public static void main(String[] args){

        Account account1122 = new Account(1122, 2000);

        if ( account1122.withdraw(2500) ){
            System.out.println("Uttak av 2500 lyktes");
        } else {
            System.out.println("Uttak av 2500 feilet");
        }

        if ( account1122.deposit(3000) ){
            System.out.println("Innskudd på 3000 lyktes");
        } else {
            System.out.println("Innskudd på 3000 feilet");
        }

        String balance = String.format("%.2f", account1122.getBalance());
        System.out.println("Saldoen er: " + balance + " kroner");

        Account.setAnnualInterestRate(4.50);

        System.out.println("Månedlig rente blir: " + account1122.getMonthlyInterest() + " kroner");

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Kontoen ble opprettet: " + df.format(account1122.getDateCreated().getTime()));
    }
}

class Account{

    private int id;
    private double balance;
    private static double annualInterestRate;
    private final Calendar dateCreated;

    public Account(){
        dateCreated = Calendar.getInstance();
    }

    public Account(int id, double balance){
        this.id = id;
        this.balance = balance;
        dateCreated = Calendar.getInstance();
    }

    public Calendar getDateCreated(){
        return dateCreated;
    }

    public int getId(){
        return id;
    }

    public double getBalance(){
        return balance;
    }

    public static void setAnnualInterestRate(double annualInterestRate){
        Account.annualInterestRate = annualInterestRate;
    }

    public static double getAnnualInterestRate(){
        return annualInterestRate;
    }

    public double getMonthlyInterest(){
        return balance * (annualInterestRate / 1200);
    }

    public boolean withdraw(double deduction){
        if ( deduction <= balance && deduction >= 0 ){
            balance -= deduction;
            return true;
        }
        return false;
    }

    public boolean deposit(double deposit){
        if ( deposit >= 0 ){
            balance += deposit;
            return true;
        }
        return false;
    }
}