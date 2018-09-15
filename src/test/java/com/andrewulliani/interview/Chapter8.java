package com.andrewulliani.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Chapter8 {

    /**
     * Write a method to generate the nth Fibonacci number.
     */
    @Test
    public void test1() {
        Assert.assertEquals(getFibonacci(1), 0);
        Assert.assertEquals(getFibonacci(2), 1);
        Assert.assertEquals(getFibonacci(3), 1);
        Assert.assertEquals(getFibonacci(4), 2);
        Assert.assertEquals(getFibonacci(5), 3);
        Assert.assertEquals(getFibonacci(6), 5);
        Assert.assertEquals(getFibonacci(7), 8);
        Assert.assertEquals(getFibonacci(8), 13);
        
        Assert.assertEquals(getFibonacciRecursively(1), 0);
        Assert.assertEquals(getFibonacciRecursively(2), 1);
        Assert.assertEquals(getFibonacciRecursively(3), 1);
        Assert.assertEquals(getFibonacciRecursively(4), 2);
        Assert.assertEquals(getFibonacciRecursively(5), 3);
        Assert.assertEquals(getFibonacciRecursively(6), 5);
        Assert.assertEquals(getFibonacciRecursively(7), 8);
        Assert.assertEquals(getFibonacciRecursively(8), 13);
    }
    
    private int getFibonacci(int n) {
        if (n <= 1) {
            return 0;
        }
        int prev = 0;
        int fib = 1;
        for (int i=2; i < n; i++) {
            int tmp = fib;
            fib += prev;
            prev = tmp;
        }
        return fib;
    }
    
    private int getFibonacciRecursively(int n) {
        return getFibonacciRecursively(n, 1, 0, 1);
    }
    
    private int getFibonacciRecursively(int n, int c, int fib, int next) {
        return n == c ? fib : getFibonacciRecursively(n, c + 1, next, next + fib);
    }
    
    /**
     * Imagine a robot sitting on the upper left hand corner of an NxN grid.
     * The robot can only move in two directions: right and down.
     * How many possible paths are there for the robot?
     * <p>FOLLOW UP<br>
     * Imagine certain squares are “off limits”, such that the robot can not step on them.
     * Design an algorithm to get all possible paths for the robot.</p>
     */
    @Test
    public void test2() {
        System.out.println(numPathsToCorner(2, 2));
        Assert.assertEquals(numPaths(2), 5);
    }
    
    private int numPaths(int n) {
        return numPaths(n, n);
    }
    
    private int numPaths(int w, int h) {
        return 1 + (w > 1 ? numPaths(w - 1, h) : 0) + (h > 1 ? numPaths(w, h - 1) : 0);
    }
    
    private int numPathsToEdge(int w, int h) {
        if (w == 1 && h == 1) {
            return 1;
        }
        else if (w > 1 && h <= 1) {
            return 1 + numPaths(w - 1, h);
        }
        else if (h > 1 && w <= 1) {
            return 1 + numPaths(w, h - 1);
        }
        return numPaths(w - 1, h) + numPaths(w, h - 1);
    }
    
    private int numPathsToCorner(int w, int h) {
        if (w == 1 && h == 1) {
            return 1;
        }
        else if (w > 1 && h <= 1) {
            return numPaths(w - 1, h);
        }
        else if (h > 1 && w <= 1) {
            return numPaths(w, h - 1);
        }
        return numPaths(w - 1, h) + numPaths(w, h - 1);
    }
    
    /**
     * Write a method that returns all subsets of a set.
     */
    @Test
    public void test3() {
        Set<Set<Integer>> all = getAllSubsets(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)));
        for (Set<Integer> s : all) {
            System.out.println(s);
        }
    }
    
    private Set<Set<Integer>> getAllSubsets(Set<Integer> set) {
        Set<Set<Integer>> all = new HashSet<>();
        getAllSubsets(set, all);
        return all;
    }
    
    private void getAllSubsets(Set<Integer> set, Set<Set<Integer>> all) {
        if (set.isEmpty()) {
            return;
        }
        all.add(set);
        for (Integer i : set) {
            Set<Integer> subset = new HashSet<Integer>(set);
            subset.remove(i);
            getAllSubsets(subset, all);
        }
    }
    
    /**
     * Write a method to compute all permutations of a string.
     */
    @Test
    public void test4() {
        List<String> all = allPermutations("melissa");
        for (String s : all) {
            System.out.println(s);
        }
    }
    
    private List<String> allPermutations(String s) {
        List<String> list = new ArrayList<>();
        if (s.length() == 1) {
            list.add(s);
            return list;
        }
        for (int i=0; i < s.length(); i++) {
            char c = s.charAt(i);
            String rest = s.substring(0, i) + s.substring(i+1);
            List<String> allRest = allPermutations(rest);
            for (String sub : allRest) {
                list.add(c + sub);
            }
        }
        return list;
    }
}
