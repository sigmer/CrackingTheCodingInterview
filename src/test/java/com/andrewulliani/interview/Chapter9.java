package com.andrewulliani.interview;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Assert;
import org.testng.annotations.Test;

import sort.SortHelper;

public class Chapter9 {

    @Test
    public void testBubbleSort() {
        Assert.assertArrayEquals(
                new Integer[] {1, 2, 3, 4, 5, 6},
                SortHelper.bubbleSort(new Integer[] {5, 4, 6, 1, 3, 2}));
    }
    
    @Test
    public void testSelectionSort() {
        Assert.assertArrayEquals(
                new Integer[] {1, 2, 3, 4, 5, 6},
                SortHelper.selectionSort(new Integer[] {5, 4, 6, 1, 3, 2}));
    }
    
    @Test
    public void testMergeSort() {
        Assert.assertArrayEquals(
                new Integer[] {1, 2, 3, 4, 5, 6},
                SortHelper.mergeSort(new Integer[] {5, 4, 6, 1, 3, 2}));
    }
    
    /**
     * You are given two sorted arrays, A and B, and A has a large enough buffer
     * at the end to hold B. Write a method to merge B into A in sorted order.
     */
    @Test
    public void test1() {
        int[] tmp = new int[] {1, 3, 5, 7};
        int[] b = new int[] {2, 4, 6};
        int[] a = getBufferedArray(tmp, b);
        
        Assert.assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 7}, mergeSorted(a, b));
    }
    
    private int[] mergeSorted(int[] a, int[] b) {
        int index = 0;
        for (int num : b) {
            while (a[index] != 0 && num > a[index]) {
                index++;
            }
            
            // insert the element from 'b' at index
            // and shift the rest of 'a' to the right
            int insert = num;
            for (int i=index; i < a.length && insert != 0; i++) {
                int tmp = a[i];
                a[i] = insert;
                insert = tmp;
            }
        }
        return a;
    }
    
    /**
     * Create an array with a length of the sum of the
     * lengths of <code>a</code> and <code>b</code>, and
     * the contents of <code>a</code> inserted at the
     * front of the array.
     * @param a
     * @param b
     * @return An array of length equal to <code>a.length + b.length</code>
     * with the contents of array <code>a</code>
     */
    private int[] getBufferedArray(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        for (int i=0; i < a.length; i++) {
            c[i] = a[i];
        }
        return c;
    }
    
    /**
     * Write a method to sort an array of strings so that all the
     * anagrams are next to each other.
     */
    @Test
    public void test2() {
        String[] strings = sortAnagrams(new String[] {"xab", "abc", "xyz", "bax", "cd", "bca", "zyx", "def"});
        String prepend = "";
        for (String s : strings) {
            System.out.print(prepend + s);
            prepend = ", ";
        }
        System.out.print('\n');
    }
    
    private String[] sortAnagrams(String[] strings) {
        Arrays.sort(strings, new Comparator<String>() {
            public int compare(String s1, String s2) {
                char[] a1 = s1.toCharArray();
                char[] a2 = s2.toCharArray();
                Arrays.sort(a1);
                Arrays.sort(a2);
                return new String(a1).compareTo(new String(a2));
            }
        });
        return strings;
    }
}
