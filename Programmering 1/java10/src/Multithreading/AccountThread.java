package Multithreading;

import java.util.Random;

public class AccountThread extends Thread {
  private Bank bank;
  private boolean debug;
  private int accountIndex;
  private int maxTransferAmount;
  private Random random;


  public AccountThread(Bank b, int from, int max){
    bank = b;
    accountIndex = from;
    maxTransferAmount = max;
  }

  public void run() {
    try {
      while (!interrupted()) {
        int toAccount = ( int ) (bank.numberOfAccounts() * Math.random());
        int amount = ( int ) (maxTransferAmount * Math.random());
        bank.transfer(accountIndex, toAccount, amount);
        sleep(1);
      }
    } catch (InterruptedException ignored) {
    }
  }
}
