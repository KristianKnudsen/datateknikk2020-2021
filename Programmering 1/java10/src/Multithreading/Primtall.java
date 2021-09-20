package Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Primtall {
    public static void main(String[] args) {
        int threads = 1;
        long number = 9223372036854775783L;

//        ExecutorService executor = Executors.newFixedThreadPool(threads);
//        for (int i = 1; i <= threads; i++) {
//            executor.execute(new PrimtallThread(number, (number/threads)*i, (i+1)*(number/threads), i));
//            System.out.println("From = " + (number/threads)*i);
//            System.out.println("To " + (i+1)*(number/threads));
//        }
        System.out.println(TestPrimTall.bruteForcePrime(number,0,0));
    }
}

class PrimtallThread implements Runnable {
    long n;
    long from;
    long to;
    int thread;

    public PrimtallThread(long n, long from, long to, int thread){
        this.n = n;
        this.from = from;
        this.to = to/2;
        this.thread = thread;
    }

    @Override
    public void run() {
        System.out.println("Thread" + thread + ": " + TestPrimTall.testIfPrime(n, from, to));
    }
}

class TestPrimTall {
    public static boolean testIfPrime(long n, long from, long to) {
        if (Math.sqrt(n) % 1 == 0) {
            return false;
        } else if (n % 2 == 0) {
            return false;
        } else {
            return bruteForcePrime(n, from, to);
        }
    }

    public static boolean bruteForcePrime(long n, long from, long to) {

        if (n <= 1) {
            return false;
        } else if (n <= 3) {
            return true;
        } else if ((n % 2 == 0) || (n % 3 == 0)) {
            return false;
        }
        int i = 5;
        while (i * i <= n) {
            if (n % i == 0) {
                return false;
            }
            i = i +6;
        }
        return true;
    }
}