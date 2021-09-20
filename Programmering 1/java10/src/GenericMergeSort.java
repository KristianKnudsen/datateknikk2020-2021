import java.util.Arrays;

public class GenericMergeSort {

    public static <E extends Comparable<E>> void mergeSort(E[] list){

        int x = list.length;
        mergeSort( list, 0, x-1);

    }

    public static <E extends Comparable<E>> void mergeSort(E[] list, int low, int high){

        if(low < high ){
            int mid = (low + high)/2;
            mergeSort(list, low, mid);
            mergeSort(list, mid+1, high);
            merge(list, low, mid+1, high);
        }
    }

    private static <E extends Comparable<E>> void merge(E[] list, int low, int mid, int high){
//        Finner lengden av de splittede arrayene. Den minste verdien burde være 1.
        int list1length = mid - low;
        int list2length = high - mid + 1;

//        System.out.println("low = " + low + " mid = " + mid + " high = " + high);
//        System.out.println(list1length);
//        System.out.println(list2length);

        E[] tempArray1 = (E[]) new Comparable[list1length];
        E[] tempArray2 = (E[]) new Comparable[list2length];

        E[] mergedSortedArray = (E[]) new Comparable[list1length+list2length];

        // fyller temparray
        System.arraycopy(list, low, tempArray1, 0, list1length);
        System.arraycopy(list, mid, tempArray2, 0, list2length);

//        System.out.println("Temp1 = " + Arrays.toString(tempArray1) + " Temp2 = " + Arrays.toString(tempArray2));

//        Sjekker om verdien i array1 er mindre enn verdier i array2, hvis verdien er mindre plasseres den i
//        mergedSortedArray på riktig plass og markerer verdien null i array1.
        for ( int i = 0; i < tempArray1.length; i++ ){
            for ( int j = 0; j < tempArray2.length; j++ ){
                if (tempArray1[i].compareTo(tempArray2[j]) < 0){
                    mergedSortedArray[j+i] = tempArray1[i];
                    tempArray1[i] = null;
                    break;
                }
            }
        }
//        System.out.println("Temp1 = " + Arrays.toString(tempArray1) + " Temp2 = " + Arrays.toString(tempArray2));
//        System.out.println(Arrays.toString(mergedSortedArray));

//        Tar alle verdier fra array2 og plasserer dem på de tomme plassene i mergedSortedArray.
//        Når den har gådd gjennom hele array2 og den ikke er ferdig tar den resten av array1 som ikke er null.
        int k = 0;
        for ( int i = 0; i < mergedSortedArray.length; i++ ){
            if ( mergedSortedArray[i] == null ){
                if ( k < tempArray2.length ) {
                    mergedSortedArray[i] = tempArray2[k];
                    k++;
                } else {
                    for ( int j = 0; j < tempArray1.length; j++ ) {
                        if ( tempArray1[j] != null ) {
                            mergedSortedArray[i] = tempArray1[j];
                            tempArray1[j] = null;
                            break;
                        }
                    }
                }
            }
        }

        System.arraycopy(mergedSortedArray, 0, list, low, mergedSortedArray.length);

//        System.out.println(Arrays.toString(mergedSortedArray));
    }
}

class TestMergeSort{
    public static void main(String[] args ) {

        Integer[] intArray = {101, 41, 41, ri(), -ri(), ri(), ri(), -ri(), ri(), ri(), ri(), -101 };
        System.out.println("Unsorted: " + Arrays.toString(intArray));

        System.out.println(intArray.length);

        GenericMergeSort.mergeSort(intArray);

        System.out.println("Sorted with mergesort: " + Arrays.toString(intArray));
        System.out.println(intArray.length);

    }

    private static int ri(){
        return (int) (Math.random() * 100);
    }
}