package com.andrewulliani.interview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.stream.IntStream;

import org.testng.Assert;
import org.testng.annotations.Test;

import structures.tree.BinaryTreeNode;
import structures.tree.TreeNode;

public class Chapter4 {
    
    @Test
    public void test1() {
        TreeNode<Integer> root = new TreeNode<>(1);
        IntStream.range(2, 7).forEach(i -> {
            TreeNode<Integer> node = root.addChild(i);
            IntStream.range(1, 3).forEach(j -> {
                node.addChild(j);
            });
        });
        Assert.assertTrue(isBalanced(root));
        
        TreeNode<Integer> a = new TreeNode<>(1);
        TreeNode<Integer> b = new TreeNode<>(2);
        TreeNode<Integer> c = new TreeNode<>(3);
        TreeNode<Integer> d = new TreeNode<>(4);
        TreeNode<Integer> e = new TreeNode<>(5);
        TreeNode<Integer> f = new TreeNode<>(6);
        TreeNode<Integer> g = new TreeNode<>(7);
        a.addChild(b);
        a.addChild(c);
        a.addChild(d);
        b.addChild(e);
        d.addChild(f);
        f.addChild(g);
        Assert.assertFalse(isBalanced(a));
    }
    
    private int maxDepth(TreeNode<Integer> root) {
        if (root.isLeaf()) {
            return 0;
        }
        int max = root.getChildren().stream().mapToInt(child -> maxDepth(child)).max().getAsInt();
        return 1 + max;
    }
    
    private int minDepth(TreeNode<Integer> root) {
        if (root.isLeaf()) {
            return 0;
        }
        int max = root.getChildren().stream().mapToInt(child -> maxDepth(child)).min().getAsInt();
        return 1 + max;
    }
    
    private boolean isBalanced(TreeNode<Integer> root) {
        int max = maxDepth(root);
        int min = minDepth(root);
        return max - min <= 1;
    }
    
    @Test
    public void test2() {
        TreeNode<Integer> a = new TreeNode<>(1);
        TreeNode<Integer> b = new TreeNode<>(2);
        TreeNode<Integer> c = new TreeNode<>(3);
        TreeNode<Integer> d = new TreeNode<>(4);
        TreeNode<Integer> e = new TreeNode<>(5);
        TreeNode<Integer> f = new TreeNode<>(6);
        
        a.addChild(b);
        b.addChild(c);
        c.addChild(d);
        e.addChild(c);
        c.addChild(f);
        
        Assert.assertTrue(routeExists(a, a));
        Assert.assertTrue(routeExists(b, b));
        Assert.assertTrue(routeExists(a, b));
        Assert.assertFalse(routeExists(b, a));
        Assert.assertTrue(routeExists(e, f));
        Assert.assertFalse(routeExists(e, b));
        Assert.assertFalse(routeExists(a, e));
    }
    
    private boolean routeExists(TreeNode<Integer> pointA, TreeNode<Integer> pointB) {
        if (pointA.equals(pointB)) {
            return true;
        }
        if (pointA.isLeaf()) {
            return false;
        }
        
        boolean exists = false;
        for (TreeNode<Integer> child: pointA.getChildren()) {
            if (routeExists(child, pointB)) {
                exists = true;
                break;
            }
        }
        return exists;
    }
    
    @Test
    public void test3() {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7};
        BinaryTreeNode<Integer> tree = createBinaryTree(array);
        tree.printTree();
    }
    
    private BinaryTreeNode<Integer> createBinaryTree(int[] array) {
        if (array.length == 0) {
            return null;
        }
        if (array.length == 1) {
            return new BinaryTreeNode<Integer>(array[0]);
        }
        
        int middleIndex = Math.round((array.length / 2) - 0.5f);
        
        int[] firstHalf = new int[middleIndex];
        int[] secondHalf = new int[array.length - middleIndex - 1];
        
        for (int i=0; i < array.length; i++) {
            if (i < middleIndex) {
                firstHalf[i] = array[i];
            }
            else if (i > middleIndex) {
                secondHalf[i - middleIndex - 1] = array[i];
            }
        }
        
        BinaryTreeNode<Integer> node = new BinaryTreeNode<Integer>(array[middleIndex]);
        node.setLeft(createBinaryTree(firstHalf));
        node.setRight(createBinaryTree(secondHalf));
        
        return node;
    }
    
    @Test
    public void test4() {
        Map<Integer, Queue<BinaryTreeNode<Integer>>> map =
                getMapOfDepths(createBinaryTree(IntStream.range(0, 3).toArray()));
        printDepths(map);
        
        Map<Integer, Queue<BinaryTreeNode<Integer>>> map2 =
                getMapOfDepths(createBinaryTree(IntStream.range(0, 10).toArray()));
        printDepths(map2);
    }
    
    private Map<Integer, Queue<BinaryTreeNode<Integer>>> getMapOfDepths(BinaryTreeNode<Integer> root) {
        Map<Integer, Queue<BinaryTreeNode<Integer>>> map = new HashMap<>();
        int depth = 0;
        map.put(depth, new LinkedList<>());
        
        // perform a breadth-first traversal of the binary search tree
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        BinaryTreeNode<Integer> previousNode = null;
        while (!queue.isEmpty()) {
            BinaryTreeNode<Integer> currentNode = queue.remove();
            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }
            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
            
            // if the current node is less than the previous node,
            // then we have reach the start of a new depth
            if (previousNode != null && currentNode.getData() < previousNode.getData()) {
                depth++;
            }
            
            if (!map.containsKey(depth)) {
                map.put(depth, new LinkedList<>());
            }
            
            Queue<BinaryTreeNode<Integer>> list = map.get(depth);
            list.add(currentNode);
            previousNode = currentNode;
        }
        
        return map;
    }
    
    private void printDepths(Map<Integer, Queue<BinaryTreeNode<Integer>>> depthMap) {
        for (int i=0; i <= depthMap.keySet().size() - 1; i++) {
            System.out.print("Depth " + i + ": ");
            for (BinaryTreeNode<Integer> node : depthMap.get(i)) {
                System.out.print(" " + node.getData());
            }
            System.out.println();
        }
    }
    
    @Test
    public void test5() {
        BinaryTreeNode<Integer> root = createBinaryTree(new int[] {2, 6, 8, 13, 17, 18, 20, 22});
        Assert.assertEquals(findNext(root).getData(), new Integer(18));
        Assert.assertEquals(findNext(root.getLeft().getRight()).getData(), new Integer(17));
        Assert.assertEquals(findNext(root.getLeft().getLeft()).getData(), new Integer(8));
        Assert.assertNull(findNext(root.getRight().getRight()));
    }
    
    private BinaryTreeNode<Integer> findNext(BinaryTreeNode<Integer> node) {
        if (node.getRight() != null) {
            // the next node is the smallest on the right tree
            BinaryTreeNode<Integer> next = node.getRight();
            while (next.getLeft() != null) {
                next = next.getLeft();
            }
            return next;
        }
        
        return findNextUp(node);
    }
    
    private BinaryTreeNode<Integer> findNextUp(BinaryTreeNode<Integer> node) {
        if (node.getParent() == null) {
            return null;
        }
        
        if (node.getParent().getData() < node.getData()) {
            // go up the tree until the parent is greater than the child
            return findNextUp(node.getParent());
        }
        else {
            return node.getParent();
        }
    }
}
