package com.andrewulliani.interview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Chapter1 {
    
    /**
     * Implement an algorithm to determine if a string has all unique characters.
     * What if you can not use additional data structures?
     */
    @Test
    public void test1() {
        Assert.assertTrue(isUnique("abcdefg"));
        Assert.assertFalse(isUnique("abcdcfg"));
        Assert.assertFalse(isUnique(null));
        
        Assert.assertTrue(isUnique2("abcdefg"));
        Assert.assertFalse(isUnique2("abcdcfg"));
        Assert.assertFalse(isUnique2(null));
    }
    
    private boolean isUnique(String testString) {
        if (testString == null) {
            return false;
        }
        List<Character> used = new ArrayList<Character>();
        for (int i=0; i<testString.length(); i++) {
            char c = testString.charAt(i);
            if (used.contains(c)) {
                return false;
            }
            used.add(c);
        }
        return true;
    }
    
    private boolean isUnique2(String testString) {
        if (testString == null) {
            return false;
        }
        for (int i=0; i<testString.length(); i++) {
            char c = testString.charAt(i);
            if (testString.indexOf(String.valueOf(c)) != testString.lastIndexOf(String.valueOf(c))) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Write code to reverse a C-Style String.
     * (C-String means that “abcd” is represented as five characters, including the null character.)
     */
    @Test
    public void test2() {
        Assert.assertEquals(reverseCString("abcd" + '\0'), "dcba" + '\0');
    }
    
    private String reverseCString(String testString) {
        char[] reverse = new char[testString.length()];
        for (int i=0, j=testString.length()-2; j>=0; i++, j--) {
            reverse[i] = testString.charAt(j);
        }
        reverse[testString.length() - 1] = '\0';
        return String.valueOf(reverse);
    }
    
    /**
     * Design an algorithm and write code to remove the duplicate
     * characters in a string without using any additional buffer.
     * <p>NOTE: One or two additional variables are fine. An extra copy of the array is not.</p>
     * <p>FOLLOW UP<br>
     * Write the test cases for this method.</p>
     */
    @Test
    public void test3() {
        String input = "abcdaefg";
        String removedDups = removeDuplicates(input);
        Assert.assertEquals(removedDups, "abcdefg");
        Assert.assertNull(removeDuplicates(null));
    }
    
    private String removeDuplicates(String testString) {
        if (testString == null) {
            return null;
        }
        
        char[] chars = testString.toCharArray();
        for (int i=0; i < chars.length; i++) {
            if (testString.indexOf(chars[i]) < i) {
                chars[i] = 0;
            }
        }
        
        String s = "";
        for (char c : chars) {
            if (c != 0) {
                s += c;
            }
        }
        return s;
    }
    
    /**
     * Write a method to decide if two strings are anagrams or not.
     */
    @Test
    public void test4() {
        Assert.assertTrue(isAnagram("abcde", "dabec"));
        Assert.assertFalse(isAnagram("abcde", "adabec"));
        Assert.assertFalse(isAnagram(null, null));
        Assert.assertFalse(isAnagram("andrew", "and"));
        Assert.assertTrue(isAnagram("andrew", "rwnade"));
    }
    
    private boolean isAnagram(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        
        if (s1.length() != s2.length()) {
            return false;
        }
        
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        
        for (int i=0; i < c1.length; i++) {
            int index = -1;
            for (int j=i; j < c2.length; j++) {
                if (c1[i] == c2[j]) {
                    index = j;
                }
            }
            if (index < 0) {
                return false;
            }
            
            char tmp = c2[i];
            c2[i] = c2[index];
            c2[index] = tmp;
        }
        
        return true;
    }
    
    /**
     * Write a method to replace all spaces in a string with ‘%20’.
     */
    @Test
    public void test5() {
        Assert.assertEquals(replaceSpaces("foo bar baz"), "foo%20bar%20baz");
    }
    
    private String replaceSpaces(String testString) {
        StringBuilder sb = new StringBuilder();
        for (char c : testString.toCharArray()) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    /**
     * Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes,
     * write a method to rotate the image by 90 degrees. Can you do this in place?
     */
    @Test
    public void test6() {
        int[][] image = new int[][] {
            {00, 01,  02, 03,  04, 05,  06, 07},
            {10, 11,  12, 13,  14, 15,  16, 17},
            
            {20, 21,  22, 23,  24, 25,  26, 27},
            {30, 31,  32, 33,  34, 35,  36, 37},
            
            {40, 41,  42, 43,  44, 45,  46, 47},
            {50, 51,  52, 53,  54, 55,  56, 57},
            
            {60, 61,  62, 63,  64, 65,  66, 67},
            {70, 71,  72, 73,  74, 75,  76, 77}
        };
        printImage(image);
        printImage(rotate90(image));
    }
    
    private int[][] rotate90(int[][] image) {
        int len = image.length;
        int[][] newImage = new int[len][len];
        for (int i=0, c=len-1; i < len; i++, c--) {
            
            for (int j=0; j < len; j++) {
                newImage[j][c] = image[i][j];
            }
        }
        return newImage;
    }
    
    private void printImage(int[][] image) {
        for (int i=0; i < image.length; i++) {
            int[] row = image[i];
            for (int j=0; j < row.length; j++) {
                System.out.printf("%d ", image[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
    
    /**
     * Write an algorithm such that if an element in an MxN matrix is 0,
     * its entire row and column is set to 0.
     */
    @Test
    public void test7() {
        int[][] matrix = new int[][] {
            {1, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 1}
        };
        printImage(matrix);
        printImage(setZeros(matrix));
    }
    
    private int[][] setZeros(int[][] matrix) {
        Set<Integer> zeroRows = new HashSet<>();
        Set<Integer> zeroCols = new HashSet<>();
        
        for (int r=0; r < matrix.length; r++) {
            int[] row = matrix[r];
            for (int c=0; c < row.length; c++) {
                if (matrix[r][c] == 0) {
                    zeroRows.add(r);
                    zeroCols.add(c);
                }
            }
        }
        
        for (int r=0; r < matrix.length; r++) {
            int[] row = matrix[r];
            for (int c=0; c < row.length; c++) {
                if (zeroRows.contains(r) || zeroCols.contains(c)) {
                    matrix[r][c] = 0;
                }
            }
        }
        
        return matrix;
    }
    
    /**
     * Assume you have a method isSubstring which checks if one word is a substring of another.
     * Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only
     * one call to isSubstring (i.e., “waterbottle” is a rotation of “erbottlewat”).
     */
    @Test
    public void test8() {
        Assert.assertTrue(isRotation("waterbottle", "erbottlewat"));
        Assert.assertFalse(isRotation("weterbottle", "erbottlewat"));
        Assert.assertFalse(isRotation("waterbottle", "erbottlewate"));
    }
    
    private boolean isRotation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        
        return isSubstring(s1, s2 + s2);
    }
    
    private boolean isSubstring(String sub, String s) {
        return s.indexOf(sub) > -1;
    }
}
