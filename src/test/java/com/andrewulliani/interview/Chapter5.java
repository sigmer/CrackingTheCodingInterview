package com.andrewulliani.interview;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Chapter5 {
    
    /**
     * You are given two 32-bit numbers, N and M, and two bit positions, i and j.
     * Write a method to set all bits between i and j in N equal to M
     * (e.g., M becomes a substring of N located at i and starting at j).
     * <p>EXAMPLE:<br>
     * Input: N = 10000000000, M = 10101, i = 2, j = 6<br>
     * Output: N = 10001010100<br>
     * </p>
     */
    @Test
    public void test1() {
        int output = setBitRange(0b10000000000, 0b10101, 2, 6);
        System.out.println(toBinaryString(output));
        Assert.assertEquals(output, 0b10001010100);
    }
    
    private int setBitRange(int n, int m, int i, int j) {
        int max = ~0;   // all 1's
        System.out.println(toBinaryString(max));
        
        // create 1's until position j, and then 0's
        int left = max - ((1 << j) - 1);
        System.out.println(toBinaryString(left));
        
        // create 1's after position i
        int right = (1 << i) - 1;
        System.out.println(toBinaryString(right));
        
        int mask = left | right;
        
        System.out.println(toBinaryString(mask));
        
        int res = (n & mask) | (m << i);
        System.out.println(toBinaryString(res));
        
        return res;
    }
    
    /**
     * Given a (decimal - e.g. 3.72) number that is passed in as a string, print the binary
     * representation. If the number can not be represented accurately in binary, print “ERROR”
     */
    @Test
    public void test2() {
        Assert.assertNull(getBinaryRepresentation(null));
        Assert.assertEquals(getBinaryRepresentation("11"), "1011");
    }
    
    private String getBinaryRepresentation(String decimal) {
        if (decimal == null) {
            return null;
        }
        
        String[] split = decimal.split("\\.");
        if (split.length == 1) {
            try {
                return toBinaryString(Integer.parseInt(decimal));
            } catch (NumberFormatException nfe) {
                return "ERROR"; // invalid input
            }
        }
        
        if (split.length == 2) {
            String before = split[0];
            String after = split[1];
            
            String binaryBefore;
            try {
                binaryBefore = toBinaryString(Integer.parseInt(before)) + ".";
            } catch (NumberFormatException nfe) {
                return "ERROR"; // invalid input
            }
            
            return binaryBefore + ".";  // TODO: calculate the decimal part
        } else {
            return "ERROR"; //invalid input
        }
    }
    
    /**
     * Explain what the following code does: ((n & (n-1)) == 0).
     */
    @Test
    public void test4() {
        /*
         * The expression determines if n is a power of 2.
         * However, it does not correctly handle 0, which
         * is not a power of 2. That can be fixed by also
         * checking if n is 0. 
         */
        
        Assert.assertTrue(isPower2(1));
        Assert.assertTrue(isPower2(2));
        Assert.assertTrue(isPower2(4));
        Assert.assertTrue(isPower2(8));
        Assert.assertTrue(isPower2(16));
        Assert.assertTrue(isPower2(32));
        Assert.assertTrue(isPower2(64));
        Assert.assertTrue(isPower2(128));
        Assert.assertTrue(isPower2(256));
        Assert.assertTrue(isPower2(512));
        Assert.assertTrue(isPower2(1024));
        
        Assert.assertFalse(isPower2(0));
        Assert.assertFalse(isPower2(3));
        Assert.assertFalse(isPower2(6));
        Assert.assertFalse(isPower2(9));
    }
    
    private boolean isPower2(int n) {
        /*
         * n != 0 handles the zero-case where 0 is not a power of 2
         * 
         * (n & (n-1)) will equal zero for a power of 2
         */
        return n != 0 && ((n & (n-1)) == 0);
    }
    
    private String toBinaryString(int n) {
        if (n == 0) {
            return "0";
        }
        String binary = "";
        while (n != 0) {
            // if the remainder after dividing by 2 is 0, then the
            // first binary digit is a 0.
            int remainder = n % 2;
            binary = remainder + binary;
            
            // shift off the first binary digit
            n = n >> 1;
        }
        return binary;
    }
}
