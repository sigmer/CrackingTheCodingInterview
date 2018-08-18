package com.andrewulliani.interview;

import java.util.HashSet;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import structures.LinkedList;
import structures.Node;

public class Chapter2 {
    
    @Test
    public void test1() {
        LinkedList<Integer> list = new LinkedList<>(1, 2, 3, 4, 5);
        Assert.assertEquals(list.length(), 5);
        removeDuplicates(list);
        Assert.assertEquals(list.length(), 5);
        
        list = new LinkedList<>(1, 1, 3);
        Assert.assertEquals(list.length(), 3);
        removeDuplicates(list);
        Assert.assertEquals(list.length(), 2);
        
        list = new LinkedList<>(1, 2, 1);
        Assert.assertEquals(list.length(), 3);
        removeDuplicates(list);
        Assert.assertEquals(list.length(), 2);
        
        list = new LinkedList<>(1, 2, 3, 4, 3, 5);
        Assert.assertEquals(list.length(), 6);
        removeDuplicates(list);
        Assert.assertEquals(list.length(), 5);
        
        list = new LinkedList<>();
        Assert.assertEquals(list.length(), 0);
        removeDuplicates(list);
        Assert.assertEquals(list.length(), 0);
        
        list = new LinkedList<>(1, 1, 2, 3, 4, 3, 5);
        Assert.assertEquals(list.length(), 7);
        removeDuplicates(list);
        Assert.assertEquals(list.length(), 5);
        
        list = new LinkedList<>(1, 1, 2, 3, 3, 3, 5);
        Assert.assertEquals(list.length(), 7);
        removeDuplicates(list);
        Assert.assertEquals(list.length(), 4);
    }
    
    private void removeDuplicatesBuff(final LinkedList<Integer> list) {
        Set<Integer> unique = new HashSet<>();
        
        Node<Integer> node = list.getHead();
        if (node == null) {
            return;
        }
        unique.add(node.getData());
        
        while (node.next() != null) {
            Node<Integer> next = node.next();
            if (unique.contains(next.getData())) {
                node.next(next.next());
            } else {
                unique.add(next.getData());
            }
            node = node.next();
        }
    }
    
    private void removeDuplicates(final LinkedList<Integer> list) {
        Node<Integer> node = list.getHead();
        if (node == null) {
            return;
        }
        
        Node<Integer> next = node.next();
        while (next != null) {
            if (node.getData() == next.getData()) {
                // next node is the same so skip it
                node.next(next.next());
                next = next.next();
            } else {
                // next node is okay, but remove all future nodes that match the current node
                Node<Integer> ok = next;
                while (ok != null) {
                    Node<Integer> afterOk = ok.next();
                    if (afterOk != null && node.getData() == afterOk.getData()) {
                        ok.next(afterOk.next());
                    }
                    ok = ok.next();
                }
                node = next;
                next = next.next();
            }
        }
    }
    
    @Test
    public void test2() {
        Assert.assertEquals(findNthToLastItem(new LinkedList<Integer>(5), 1), new Integer(5), "find 1st from last when there is 1 item");
        Assert.assertEquals(findNthToLastItem(new LinkedList<Integer>(), 1), null, "fails if list it empty");
        Assert.assertEquals(findNthToLastItem(new LinkedList<Integer>(5), 2), null, "fails if a list with 1 item is too short");
        Assert.assertEquals(findNthToLastItem(new LinkedList<Integer>(5, 6), 3), null, "fails if the length of the list is too short");
        Assert.assertEquals(findNthToLastItem(new LinkedList<Integer>(5, 6), -1), null, "fails if n is not a positive integer");
        Assert.assertEquals(findNthToLastItem(new LinkedList<Integer>(5, 6), 0), null, "fails if n is zero");
        Assert.assertEquals(findNthToLastItem(new LinkedList<Integer>(5, 6, 7, 8), 1), new Integer(8));
        Assert.assertEquals(findNthToLastItem(new LinkedList<Integer>(5, 6, 7, 8), 2), new Integer(7));
        Assert.assertEquals(findNthToLastItem(new LinkedList<Integer>(5, 6, 7, 8), 3), new Integer(6));
        Assert.assertEquals(findNthToLastItem(new LinkedList<Integer>(5, 6, 7, 8), 4), new Integer(5));
    }
    
    private Integer findNthToLastItem(LinkedList<Integer> list, int n) {
        if (n <= 0) {
            return null;
        }
        
        Node<Integer> node = list.getHead();
        
        int count = 0;
        while (node != null) {
            count++;
            node = node.next();
        }
        
        int index = count - n;
        
        if (index < 0) {
            return null;
        }
        
        node = list.getHead();
        for (int i=0; i < index; i++) {
            node = node.next();
        }
        return node.getData();
    }
    
    @Test
    public void test3() {
        LinkedList<Integer> list = new LinkedList<>(1, 2, 3, 4, 5);
        Node<Integer> node3 = list.getHead().next().next();
        removeNode(node3);
        Assert.assertEquals(list.length(), 4);
        Assert.assertTrue(list.contains(1) && list.contains(2) && list.contains(4) && list.contains(5));
        Assert.assertFalse(list.contains(3));
    }
    
    private void removeNode(Node<Integer> node) {
        Node<Integer> next = node.next();
        if (next == null) {
            // cannot remove node
            return;
        }
        
        node.setData(next.getData());
        node.next(next.next());
    }
    
    @Test
    public void test4() {
        LinkedList<Integer> num1 = new LinkedList<Integer>(3, 1, 5);
        LinkedList<Integer> num2 = new LinkedList<Integer>(5, 9, 2);
        LinkedList<Integer> sum = addLinkedList(num1, num2);
        System.out.println(sum.toString());
        
        num1 = new LinkedList<Integer>(0, 0, 1);
        num2 = new LinkedList<Integer>(5, 2);
        sum = addLinkedList(num1, num2);
        System.out.println(sum.toString());
        
        num1 = new LinkedList<Integer>(5, 7);
        num2 = new LinkedList<Integer>();
        sum = addLinkedList(num1, num2);
        System.out.println(sum.toString());
    }
    
    private LinkedList<Integer> addLinkedList(LinkedList<Integer> num1, LinkedList<Integer> num2) {
        Node<Integer> n1 = num1.getHead();
        Node<Integer> n2 = num2.getHead();
        LinkedList<Integer> sumList = new LinkedList<Integer>();
//        Node<Integer> sumNode = null;
        Node<Integer> prevDigit = null;
        
        int carry = 0;
        while (n1 != null || n2 != null) {
            int int1, int2;
            if (n1 != null) {
                int1 = n1.getData();
                n1 = n1.next();
            } else {
                int1 = 0;
            }
            if (n2 != null) {
                int2 = n2.getData();
                n2 = n2.next();
            } else {
                int2 = 0;
            }
            
            int sum = int1 + int2 + carry;
            
            // carry the 1 for the next digit if needed
            if (sum >= 10) {
                carry = 1;
                sum = sum - 10;
            } else {
                carry = 0;
            }
            
            Node<Integer> digit = new Node<Integer>(sum);
            if (prevDigit != null) {
                digit.next(prevDigit);
            }
            prevDigit = digit;
            sumList.setHead(digit);
            
            
//            if (sumNode == null) {
//                sumList = new LinkedList<Integer>(sum);
//                sumNode = sumList.getHead();
//            } else {
//                sumNode.next(new Node<Integer>(sum));
//                sumNode = sumNode.next();
//            }
        }
        
        return sumList;
    }
    
    @Test
    public void test5() {
        
        LinkedList<Character> list = new LinkedList<Character>();
        Node<Character> dup = new Node<Character>('C');
        list.add(new Node<Character>('A'));
        list.add(new Node<Character>('B'));
        list.add(dup);
        list.add(new Node<Character>('D'));
        list.add(new Node<Character>('E'));
        list.add(new Node<Character>('F'));
        list.add(new Node<Character>('G'));
        list.add(dup);
        
        Character foundDup = findLoopNode(list).getData();
        System.out.println(foundDup);
        Assert.assertEquals(foundDup, new Character('C'));
        
        list = new LinkedList<Character>();
        dup = new Node<Character>('A');
        list.add(dup);
        list.add(new Node<Character>('B'));
        list.add(new Node<Character>('C'));
        list.add(dup);
        
        foundDup = findLoopNode(list).getData();
        System.out.println(foundDup);
        Assert.assertEquals(foundDup, new Character('A'));
        
        list = new LinkedList<Character>();
        list.add(new Node<Character>('A'));
        list.add(new Node<Character>('B'));
        list.add(new Node<Character>('C'));
        
        Assert.assertNull(findLoopNode(list));
    }
    
    private Node<Character> findLoopNode(LinkedList<Character> list) {
        Node<Character> node1 = list.getHead();
        if (node1 == null) {
            return null;
        }
        Node<Character> node2 = node1;
        
        while (node2.next() != null && node2.next().next() != null) {
            node1 = node1.next();
            node2 = node2.next().next();
            if (node1.equals(node2)) {
                break;
            }
        }
        
        if (node1.equals(node2)) {
            node1 = list.getHead();
            while (!node1.equals(node2)) {
                node1 = node1.next();
                node2 = node2.next();
            }
            return node1;
        } else {
            return null;
        }
    }
}
