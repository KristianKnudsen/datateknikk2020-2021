import account.Transaction;
import chapter11.Triangle;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        int number = Integer.MAX_VALUE + 1;

        System.out.print(number);

    }
}

class Test {
    public static void main(String[] args) {
        try {
            int i = 0;
            int y = 2 / i;
            System.out.println("Welcome to Java");
        }
        finally {
            System.out.println("The finally clause is executed");
        }
    }
}