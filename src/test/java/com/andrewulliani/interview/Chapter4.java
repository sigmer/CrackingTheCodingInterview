package com.andrewulliani.interview;

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
}
