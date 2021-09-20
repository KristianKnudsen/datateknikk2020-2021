package account;

import java.util.Date;

public class Transaction {

    private Date datePerformed;
    private char transactionType;
    private double amount, balance;
    private String description;


    public Transaction(){

    }

    public Transaction(double amount, char type,
                       double balance, String description ){

        this.amount = amount;
        this.transactionType = type;
        datePerformed = new java.util.Date();
        this.balance = balance;
        this.description = description;

    }

    public Date getDatePerformed(){
        return datePerformed;
    }

    public char getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getDescription(){
        return description;
    }

    public String toString(){
        return String.format("%tF %<tT %-13s %-13.2f %-13.2f %-13s", datePerformed,
                transactionType, amount, balance, description );
    }
}
