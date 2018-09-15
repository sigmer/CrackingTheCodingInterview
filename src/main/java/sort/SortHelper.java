package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortHelper {

    public static <T extends Comparable<T>> T[] bubbleSort(T[] array) {
        boolean swapped;
        
        do {
            swapped = false;
            for (int i=0; i < array.length - 1; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    T temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
        
        return array;
    }
    
    public static <T extends Comparable<T>> T[] selectionSort(T[] array) {
        for (int i=0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j=i+1; j < array.length; j++) {
                if (array[minIndex].compareTo(array[j]) > 0) {
                    minIndex = j;
                }
            }
            
            if (minIndex > i) {
                T temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
        
        return array;
    }
    
    public static <T extends Comparable<T>> T[] mergeSort(T[] array) {
        if (array.length < 2) {
            return array;
        }
        
        int midIndex = array.length / 2;
        T[] firstHalf = mergeSort(Arrays.copyOfRange(array, 0, midIndex));
        T[] secondHalf = mergeSort(Arrays.copyOfRange(array, midIndex, array.length));
        
        return merge(firstHalf, secondHalf);
    }
    
    private static <T extends Comparable<T>> T[] merge(T[] a, T[] b) {
        List<T> merged = new ArrayList<>(a.length + b.length);
        int aIdx = 0;
        int bIdx = 0;
        while (aIdx < a.length || bIdx < b.length) {
            if (aIdx >= a.length) {
                merged.add(b[bIdx]);
                bIdx++;
            }
            else if (bIdx >= b.length) {
                merged.add(a[aIdx]);
                aIdx++;
            }
            else if (a[aIdx].compareTo(b[bIdx]) > 0) {
                merged.add(b[bIdx]);
                bIdx++;
            } else {
                merged.add(a[aIdx]);
                aIdx++;
            }
        }
        return merged.toArray(a);
    }
}
