package Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankTest {
  private static final int INITIAL_BALANCE = 10000;
  private static final int ACCOUNT_AMOUNT = 10;

  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(10);
    Bank bank = new Bank(ACCOUNT_AMOUNT, INITIAL_BALANCE, false);
    for ( int i = 0; i < 10; i++){
      executor.execute(new AccountThread(bank, i, 100));
    }
  }
}
