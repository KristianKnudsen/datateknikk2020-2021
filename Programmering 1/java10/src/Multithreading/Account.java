package Multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
  private int balance;
  private int accountNumber;
  private static final Lock lock = new ReentrantLock();
  private static final Condition transfer = lock.newCondition();

  public Account(int accountNumber, int balance) {
    this.accountNumber = accountNumber;
    this.balance = balance;
  }

  void withdraw(int amount) {
    lock.lock();
    try {
      while(balance < amount){
        transfer.await();
      }
      balance -= amount;
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  void deposit(int amount) {
    lock.lock();
    try {
      balance += amount;
      transfer.signalAll();
    } catch (Exception e) {
      e.printStackTrace();
    }finally {
      lock.unlock();
    }
  }

  int getAccountNumber() {
    return accountNumber;
  }

  public int getBalance() {
    return balance;
  }
}
