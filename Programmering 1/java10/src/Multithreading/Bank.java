package Multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
  private static int TO_STRING_FREQUENCY = 0; // toString() counter
  private static int TEST_FREQUENCY = 0; // test() counter
  private Lock lock = new ReentrantLock();
  private long transactionCount; // number of transactions
  private int deviationCount; // Number of times sum of $$ isnt 100 000
  private int initialBalance; // start bal - should be 10k
  private Account[] accounts; // 10 accounts
  private int testCount = 0; // Number of runs on this bank
  private boolean debug;

  public Bank(int accountAmount, int initialBalance, boolean debug) {
    this.debug = debug;
    accounts = new Account[accountAmount];
    this.initialBalance = initialBalance;
    for (int i = 0; i < numberOfAccounts(); i++){
      accounts[i] = new Account(i, initialBalance);
    }
  }

  private void test(){
    TEST_FREQUENCY++;
    int totalBalance = 0;
    for ( int i = 0; i < numberOfAccounts(); i++){
      totalBalance += accounts[i].getBalance();
    }
    boolean totalBalanceIsRight = totalBalance==(numberOfAccounts()*initialBalance);
    if (!totalBalanceIsRight){
      deviationCount++;
    }
    System.out.println("Transactions: " + transactionCount +
            " balance: " + totalBalance +
            " Derivation count: " + deviationCount +
            " balance true: " + totalBalanceIsRight);
    if ( debug ){
      for ( int i = 0; i < numberOfAccounts(); i++){
        System.out.println("Account " + i + " balance: " + accounts[i].getBalance());
      }
    }
  }

  @Override
  public String toString() {
    TO_STRING_FREQUENCY++;
    return "Bank{" +
            "lock=" + lock +
            ", transactionCount=" + transactionCount +
            ", deviationCount=" + deviationCount +
            ", initialBalance=" + initialBalance +
            ", accounts=" + numberOfAccounts() +
            ", testCount=" + testCount +
            '}';
  }

  private int getDeviationCount() {
    return deviationCount;
  }

  private long getTransactionCount() {
    return transactionCount;
  }

  protected int numberOfAccounts(){
    return accounts.length;
  }

  protected void transfer( int from, int to, int amount){
      transactionCount++;
      if (transactionCount % 10000 == 0) {
        test();
      }
      accounts[from].withdraw(amount);
      accounts[to].deposit(amount);

  }

  private double getErrorPercentage(){
    return (double)getDeviationCount()/testCount;
  }
}
