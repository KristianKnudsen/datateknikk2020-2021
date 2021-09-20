package eksamen;

import java.security.spec.ECField;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Oppgave {
    public static void main(String[] args) {
        System.out.println("Hello world");
    }
}

class Counter {

    private int finishedThreads = 0;
    private int numThreads = 0;
    private Lock lock = new ReentrantLock();
    private Condition wait = lock.newCondition();

    public Counter(int threads){
        this.numThreads = threads;
    }
    public void finish() {
        lock.lock();

        try {
            finishedThreads++;
            wait.signalAll();
        } catch ( Exception e ){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void waitForAllThreads() {
        lock.lock();
        try {
            while ( finishedThreads < numThreads ) {
                wait.await();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}



class Client implements Runnable{
    String navn;
    Counter counter;

    public Client(String navn, Counter t){
        this.navn = navn;
        this.counter = t;
    }

    public void run(){
        // do some work
        for (int i = 0; i < ( int ) (10000 * Math.random()); i++) {
            if(i % 50 == 0) System.out.print(".");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("x");
        counter.finish();
    }
}



class CounterTest {
    public static void main(String[] args) {
        final int THREADS = 40;

        Counter counter = new Counter(THREADS);

        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < THREADS && !pool.isShutdown(); i++) {
            Client thread = new Client("Test", counter);
            pool.execute(thread);
        }
        counter.waitForAllThreads();
        System.out.println("");
        System.out.println("All threads finished");

    }
}
