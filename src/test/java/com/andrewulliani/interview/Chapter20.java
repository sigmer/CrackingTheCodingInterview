package com.andrewulliani.interview;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Chapter20 {

    /**
     * Write a function that adds two numbers. You should not use + or any arithmetic operators.
     */
    @Test
    public void test1() {
        Assert.assertEquals(addBinary(2, 4), 6);
        Assert.assertEquals(addBinary(3, 6), 9);
        Assert.assertEquals(addBinary(23, 9), 32);
    }
    
    private int addBinary(int a, int b) {
        // if b=0 then the sum is a
        if (b == 0) {
            return a;
        }
        
        // XOR is like doing a sum without carrying the remainders
        int sum = a ^ b;
        
        // AND shifted left is like getting just the remainders
        int carry = (a & b) << 1;
        
        return addBinary(sum, carry);
    }
    
    /**
     * Write a method to shuffle a deck of cards.
     * It must be a perfect shuffle - in other words, each 52! permutations
     * of the deck has to be equally likely.
     * Assume that you are given a random number generator which is perfect.
     */
    @Test
    public void test2() {
        int[] deck = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        shuffle(deck);
        String prepend = "";
        for (int i : deck) {
            System.out.print(prepend + i);
            prepend = ", ";
        }
        System.out.println();
    }
    
    private void shuffle(int[] deck) {
        for (int i=0; i < deck.length; i++) {
            int randIndex = (int)(Math.random() * (deck.length - i)) + i;
            int tmp = deck[i];
            deck[i] = deck[randIndex];
            deck[randIndex] = tmp;
        }
    }
    
    /**
     * Write a method to randomly generate a set of m integers from an array of size n.
     * Each element must have equal probability of being chosen.
     */
    @Test
    public void test3() throws Exception {
        int[] random = getRandomlyFromArray(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 3);
        String prepend = "";
        for (int i : random) {
            System.out.print(prepend + i);
            prepend = ", ";
        }
        System.out.println();
        
        try {
            getRandomlyFromArray(new int[] {1}, 2);
            Assert.fail("Should have thrown an exception");
        } catch (Exception e) {
            // expected
        }
    }
    
    private int[] getRandomlyFromArray(int[] array, int m) throws Exception {
        if (m > array.length) {
            throw new Exception(String.format("Cannot get a set of %d integers from an array of size %d", m, array.length));
        }
        
        int[] ret = new int[m];
        for (int i=0; i < m; i++) {
            int randIndex = (int)(Math.random() * (array.length - i)) + i;
            ret[i] = array[randIndex];
            int temp = array[i];
            array[i] = array[randIndex];
            array[randIndex] = temp;
        }
        return ret;
    }
    
    /**
     * You have a large text file containing words. Given any two words,
     * find the shortest distance (in terms of number of words) between them in the file.
     * Can you make the searching operation in O(1) time?
     * What about the space complexity for your solution?
     */
    @Test
    public void test5() {
        String[] words = new String[] {
                "A", "B", "C", "D", "E", "F", "F", "B", "H", "I", "J", "K"
        };
        
        Assert.assertEquals(shortestDistanceBetween(words, "B", "F"), 1);
        Assert.assertEquals(shortestDistanceBetween(words, "A", "E"), 4);
        Assert.assertEquals(shortestDistanceBetween(words, "I", "A"), 9);
    }
    
    private int shortestDistanceBetween(String[] words, String w1, String w2) {
        Integer dist = null;
        Integer pos1 = null;
        Integer pos2 = null;
        
        for (int i=0; i < words.length; i++) {
            if (words[i].equals(w1)) {
                pos1 = i;
                
                if (pos2 != null && (dist == null || pos1 - pos2 < dist)) {
                    dist = pos1 - pos2;
                }
            }
            
            if (words[i].equals(w2)) {
                pos2 = i;
                
                if (pos1 != null && (dist == null || pos2 - pos1 < dist)) {
                    dist = pos2 - pos1;
                }
            }
        }
        
        return dist;
    }
}
