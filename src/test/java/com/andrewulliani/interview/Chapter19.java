package com.andrewulliani.interview;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Chapter19 {
    
    /**
     * Write a function to swap a number in place without temporary variables.
     */
    @Test
    public void test1() {
        int a = 2, x = a;
        int b = 4, y = b;
        
        a = a + b;
        b = a - b;
        a = a - b;
        
        Assert.assertEquals(x, b);
        Assert.assertEquals(y, a);
        
        // better
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        
        Assert.assertEquals(x, a);
        Assert.assertEquals(y, b);
    }
    
}
