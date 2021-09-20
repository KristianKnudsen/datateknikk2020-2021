package account;

import java.util.ArrayList;
import java.util.Date;

public class Account  {
	private static double annualInterestRate;
	private double balance;
	private final Date dateCreated;
	private int id;
	private String name;
	private final ArrayList<Transaction> transactions;
	
	public Account(Date dateCreated) {
		this.dateCreated = dateCreated;
		transactions = new ArrayList<Transaction>();
	}
	
	public Account(String name, int id, double balance, Date dateCreated) {
		this.name = name;
		this.id = id;
		this.balance = balance;
		this.dateCreated = dateCreated;

		transactions = new ArrayList<Transaction>();
	}


	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public Date getDateCreated() {return dateCreated;}
	
	public double getBalance() {return balance;}
	
	public String getName() {return name;}
	
	public int getID() {return id;}
	
	public void setBalance(double balance) {this.balance = balance;}
	
	public void setID(int id) {this.id = id;}
	
	public static double getAnnualInterestRate() {return annualInterestRate;}
	
	public double getMonthlyInterest() {
	    return balance * (annualInterestRate / 1200);
	}
	
	public static void setAnnualInterestRate(double newAnnualInterestRate) {
	    annualInterestRate = newAnnualInterestRate;
	  }
	
	public void withdraw(double amount, String name) {
		balance -= amount;
		transactions.add(new Transaction(amount, 'W', balance, name));
	}

	public void deposit(double amount, String name) {
		balance += amount;
		transactions.add(new Transaction(amount, 'D', balance, name));
	}
		
	@Override
	public String toString() {
		return String.format("Name: %s\nID: %d\nBalance: %.2f\nDate created: %tF %4$tT",
				name, id, balance, dateCreated);
	}
	
}

class CreateTransObjects{
	public static void main(String[] args) {

		Account arnesAccount = new Account("Arne", 1337, 10000, new java.util.Date());
		Account.setAnnualInterestRate(5.5);

		arnesAccount.deposit(100, "Pant");
		arnesAccount.deposit(69, "Lønn");
		arnesAccount.deposit(5000, "Finn.no");

		arnesAccount.withdraw(1000, "Mat");
		arnesAccount.withdraw(543.12, "Drikke");
		arnesAccount.withdraw(123, "koffein");

		System.out.println(arnesAccount.toString());
		System.out.println("Annual interest rate: " + Account.getAnnualInterestRate());

		System.out.println("Transactions: ");
		System.out.printf("%-19s %-13s %-13s %-13s %-13s\n", "Date",
				"Type", "amount" , "balance", "description" );

		ArrayList<Transaction> arnesTransactions = arnesAccount.getTransactions();

		for (Transaction arnesTransaction : arnesTransactions) {
			System.out.println(arnesTransaction.toString());
		}

	}
}

