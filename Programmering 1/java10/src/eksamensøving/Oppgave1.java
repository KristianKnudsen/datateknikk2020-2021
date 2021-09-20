package eksamensøving;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Oppgave1 { // Object i/o
    public static void main(String[] args) {
        ArrayList<Loan> loans = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream("loans.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)){

            Loan value = (Loan) ois.readObject();

            while (value != null){
                loans.add(value);
                value = (Loan) ois.readObject();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Existing loans: \n " + loans.toString());

        loans.add(new Loan((int)(Math.random()*100000), Math.random()*10000));
        System.out.println("Added new loan: \n " + loans.toString());

        try (FileOutputStream fos = new FileOutputStream("loans.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)){

            Loan value;
            for (Loan loan : loans) {
                value = loan;
                oos.writeObject(value);
            }

        } catch (Exception e){
            e.printStackTrace();
        }


        System.out.println("New loan saved");

    }
}

class Loan implements Serializable {
    private int account;
    private double balance;
    public Loan(int account, double balance){
        this.account = account;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "account=" + account +
                ", balance=" + balance +
                '}';
    }
}

class lageComparator {
    public static void main(String[] args) {
        Rectangle[] r1List = new Rectangle[5];
        Rectangle[] result;

        for ( int i = 0; i < r1List.length; i++ ){
            r1List[i] = new Rectangle((int)(Math.random()*10), "Blue", true);
        }
        System.out.println("Before sort: " + Arrays.toString(r1List));
        result = SelectionSortWithComparator.selectionSort( r1List, new GeometricObjectComparator() );
        System.out.println("After sort: " + Arrays.toString(result));

        System.out.println("Proof that array is cloned and not refrenced: \n" + Arrays.toString(r1List));

    }
}

abstract class GeometricObject {
    int area;
    String color;

    public GeometricObject(int area, String color) {
        this.area = area;
        this.color = color;
    }
}

class Rectangle extends GeometricObject {
    boolean poop;

    public Rectangle(int area, String color, boolean poop) {
        super(area, color);
        this.poop = poop;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "area=" + area +
                ", color='" + color + '\'' +
                '}';
    }
}

class SelectionSortWithComparator {

    public static <E> E[] selectionSort(E[] list, Comparator<? super E> comparator){
        E[] result = list.clone();
        E temp;

        for ( int i = 0; i < list.length; i++ ){
            for ( int j = 0; j < list.length-1; j++ ){
                if (comparator.compare(result[j], result[j + 1]) > 0){
                    temp = result[j];
                    result[j] = result[j+1];
                    result[j+1] = temp;
                }
            }
        }

        return result;
    }
}

class GeometricObjectComparator implements Comparator<GeometricObject> {

    @Override
    public int compare(GeometricObject o1, GeometricObject o2) {
        if ( o1.area > o2.area ){
            return 1;
        } else if ( o1.area < o2.area){
            return -1;
        }
        return 0;
    }
}