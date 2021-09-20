package Multithreading;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Array {
  private static int[] array;
  public static ArrayList<Integer> result = new ArrayList<>();

  public static void main(String[] args) {

    int arrayLength = 100000000;
    int numberUpTo = 10000000;
    int searchFor = 10;
    int threads = 10;

    populateArray(arrayLength, numberUpTo);

    ExecutorService executor = Executors.newFixedThreadPool(threads);
    for (int i = 0; i < threads; i++) {
      executor.execute(new SearchThread(searchFor, array, (arrayLength/threads)*i, (i+1)*(arrayLength/threads), result, i));
    }
  }

  public static void populateArray(int arrayLength, int upTo){
    array = new int[arrayLength];
    for( int i = 0; i < arrayLength; i++){
      array[i] = (int)(Math.random()*upTo);
    }
  }
}

class SearchThread extends Thread{
  int searchFor, from, to, thread;
  int[] searchIn;
  ArrayList<Integer> searchResult;

  public SearchThread(int searchFor, int[] searchIn, int from, int to, ArrayList<Integer> searchResult, int thread) {
    this.searchFor = searchFor;
    this.from = from;
    this.to = to;
    this.searchIn = searchIn;
    this.searchResult = searchResult;
    this.thread = thread;
  }

  public void run() {
    Search.search(searchFor, searchIn, from, to, searchResult, thread);
  }
}

class Search{
  public static void search( int searchFor, int[] searchIn, int from, int to, ArrayList<Integer> searchResult, int thread){
    while ( from<to ){
      if(searchIn[from] == searchFor){
        searchResult.add(from);
        System.out.println(searchFor + " found at index " + from + " using thread " + thread);
      }
      from++;
    }
    System.out.println(thread + " Done");
  }
}
