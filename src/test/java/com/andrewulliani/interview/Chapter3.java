package com.andrewulliani.interview;

import org.testng.Assert;
import org.testng.annotations.Test;

import structures.ArrayStack;
import structures.ArrayStack.Instance;
import structures.MinStack;
import structures.QueueByStack;
import structures.SetOfStacks;
import structures.Stack;

public class Chapter3 {
    
    @Test
    public void test1() {
        ArrayStack stack = new ArrayStack();
        stack.push('A', Instance.A);
        stack.push('B', Instance.A);
        stack.push('C', Instance.A);
        
        stack.push('L', Instance.B);
        stack.push('M', Instance.B);
        stack.push('N', Instance.B);
        stack.pop(Instance.B);
        
        stack.push('X', Instance.C);
        stack.push('Y', Instance.C);
        stack.push('Z', Instance.C);
        
        System.out.println(stack.toString());
    }
    
    @Test
    public void test2() {
        MinStack stack = new MinStack();
        
        stack.push(5);
        stack.push(3);
        stack.push(8);
        stack.push(6);
        stack.push(2);
        stack.push(4);
        stack.pop();
        stack.pop();
        
        System.out.println(stack.toString());
        Assert.assertEquals(stack.getMin(), new Integer(2));
    }
    
    @Test
    public void test3() {
        SetOfStacks<Integer> setOfStacks = new SetOfStacks<>();
        setOfStacks.push(1);
        setOfStacks.push(2);
        setOfStacks.push(3);
        setOfStacks.push(4);
        setOfStacks.push(5);
        setOfStacks.push(6);
        setOfStacks.push(7);
        setOfStacks.pop();
        setOfStacks.pop();
        setOfStacks.pop();
        setOfStacks.push(8);
        setOfStacks.push(9);
        setOfStacks.push(10);
        setOfStacks.popAt(0);
        setOfStacks.popAt(0);
        setOfStacks.push(11);
        setOfStacks.popAt(1);
        
        System.out.println(setOfStacks.toString());
    }
    
    @Test
    public void test4() {
        Stack<Integer> A = new Stack<Integer>();
        Stack<Integer> B = new Stack<Integer>();
        Stack<Integer> C = new Stack<Integer>();
        
        A.push(3);
        A.push(2);
        A.push(1);
        
        int bCount = 0;
        int cCount = 0;
        
//        Integer disk = A.pop();
//        while (disk != null) {
//            B.push(disk);
//            disk = A.pop();
//            C.push(disk);
//            disk = B.pop();
//            C.push(disk);
//            
//            disk = A.pop();
//            if (disk == null) {
//                break;
//            }
//            
//            disk = C.pop();
//            B.push(disk);
//        }
    }
    
    @Test
    public void test5() {
        QueueByStack<Integer> queue = new QueueByStack<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        int d = queue.dequeue();
        Assert.assertEquals(d, 1);
        queue.enqueue(5);
        queue.enqueue(6);
        d = queue.dequeue();
        Assert.assertEquals(d, 2);
        d = queue.dequeue();
        Assert.assertEquals(d, 3);
        
        System.out.println(queue.toString());
    }
    
    @Test
    public void test6() {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(5);
        stack.push(4);
        stack.push(2);
        System.out.println(stack.toString());
        System.out.println(sort(stack));
//        System.out.println(stack.toString());
    }
    
    private Stack<Integer> sort(Stack<Integer> stack) {
        Stack<Integer> stack2 = new Stack<Integer>();
        while (!stack.isEmpty()) {
            Integer tmp = stack.pop();
            while (!stack2.isEmpty() && tmp > stack2.peek()) {
                stack.push(stack2.pop());
            }
            stack2.push(tmp);
        }
        return stack2;
    }
}
