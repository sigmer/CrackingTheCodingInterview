package com.andrewulliani.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.IntStream;

import org.testng.Assert;
import org.testng.annotations.Test;

import structures.tree.BinaryTreeNode;
import structures.tree.TreeNode;

public class Chapter4 {
    
    /**
     * Implement a function to check if a tree is balanced.
     * For the purposes of this question, a balanced tree is defined
     * to be a tree such that no two leaf nodes differ in distance
     * from the root by more than one.
     */
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
    
    /**
     * Given a directed graph, design an algorithm to
     * find out whether there is a route between two nodes.
     */
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
    
    /**
     * Given a sorted (increasing order) array,
     * write an algorithm to create a binary tree with minimal height.
     */
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
    
    /**
     * Given a binary search tree, design an algorithm which creates
     * a linked list of all the nodes at each depth
     * (i.e., if you have a tree with depth D, you’ll have D linked lists).
     */
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
    
    /**
     * Write an algorithm to find the ‘next’ node (i.e., in-order successor)
     * of a given node in a binary search tree where each node has a link to its parent.
     */
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
    
    /**
     * Design an algorithm and write code to find the first
     * common ancestor of two nodes in a binary tree.
     * Avoid storing additional nodes in a data structure.
     * NOTE: This is not necessarily a binary search tree.
     */
    @Test
    public void test6() {
        BinaryTreeNode<Integer> root = createBinaryTree(IntStream.range(0, 31).toArray());
        
        Assert.assertEquals(numOccurances(root, 2), 1);
        Assert.assertEquals(numOccurances(root, -1), 0);
        Assert.assertEquals(numOccurances(root, -1, 20), 1);
        Assert.assertEquals(numOccurances(root, 1, 2, 18, -1), 3);
        
        Assert.assertEquals(commonAncestor(root, 8, 20), root);
        Assert.assertEquals(commonAncestor(root, 6, 13), root.getLeft());
        Assert.assertEquals(commonAncestor(root, 16, 21), root.getRight().getLeft());
        Assert.assertEquals(commonAncestor(root, 17, 15), root);
        Assert.assertNull(commonAncestor(root, -1, 15));
        Assert.assertNull(commonAncestor(root, -1, -2));
        Assert.assertNull(commonAncestor(root, -1, 4));
    }
    
    private BinaryTreeNode<Integer> commonAncestor(BinaryTreeNode<Integer> root, int v1, int v2) {
        int numLeft = numOccurances(root.getLeft(), v1, v2);
        int numRight = numOccurances(root.getRight(), v1, v2);
        
        if ((root.getData().intValue() == v1 || root.getData().intValue() == v2) &&
                numLeft + numRight == 1) {
            return root;
        }
        
        if (numLeft == 1 && numRight == 1) {
            return root;
        }
        
        if (numLeft > 1) {
            return commonAncestor(root.getLeft(), v1, v2);
        }
        
        if (numRight > 1) {
            return commonAncestor(root.getRight(), v1, v2);
        }
        
        return null;
    }
    
    private int numOccurances(BinaryTreeNode<Integer> root, int... ints) {
        return (IntStream.of(ints).anyMatch(i -> root.getData().intValue() == i) ? 1 : 0) +
                (root.getLeft() != null ? numOccurances(root.getLeft(), ints) : 0) +
                (root.getRight() != null ? numOccurances(root.getRight(), ints) : 0);
    }
    
    /**
     * You have two very large binary trees: T1, with millions of nodes,
     * and T2, with hundreds of nodes.
     * Create an algorithm to decide if T2 is a subtree of T1.
     */
    @Test
    public void test7() {
        BinaryTreeNode<Integer> t1 = createBinaryTree(IntStream.range(0, 20).toArray());
        
        Assert.assertTrue(isSubtree(t1, createBinaryTree(new int[] {1, 2, 4})));
        Assert.assertFalse(isSubtree(t1, createBinaryTree(new int[] {1, 2, 3})));
        Assert.assertFalse(isSubtree(t1, createBinaryTree(new int[] {1, 2, 12})));
        Assert.assertFalse(isSubtree(createBinaryTree(new int[] {1}), createBinaryTree(new int[] {1, 2, 4})));
    }
    
    private boolean isSubtree(BinaryTreeNode<Integer> t1, BinaryTreeNode<Integer> t2) {
        if (t1 == null) {
            return t2 == null;
        }
        
        if (isSubtreeFromRoot(t1, t2)) {
            return true;
        }
        else {
            return isSubtree(t1.getLeft(), t2) || isSubtree(t1.getRight(), t2);
        }
    }
    
    private boolean isSubtreeFromRoot(BinaryTreeNode<Integer> t1, BinaryTreeNode<Integer> t2) {
        boolean isSubtree = t1.getData().equals(t2.getData());
        
        if (isSubtree && t2.getLeft() != null) {
            isSubtree = t1.getLeft() != null && isSubtreeFromRoot(t1.getLeft(), t2.getLeft());
        }
        if (isSubtree && t2.getRight() != null) {
            isSubtree = t1.getRight() != null && isSubtreeFromRoot(t1.getRight(), t2.getRight());
        }
        return isSubtree;
    }
    
    /**
     * You are given a binary tree in which each node contains a value.
     * Design an algorithm to print all paths which sum up to that value.
     * Note that it can be any path in the tree - it does not have to start at the root.
     */
    @Test
    public void test8() {
        BinaryTreeNode<Integer> tree = BinaryTreeNode.createTree(new int[] {1, 4, 2, 5, 4, 3, 2, 9, 6, 7});
        tree.printTree();
        String paths = printPaths(tree);
        System.out.println(paths);
        
        Assert.assertTrue(paths.contains("1 -> 2 -> 3\n"));
        Assert.assertTrue(paths.contains("1 -> 4 -> 5\n"));
        Assert.assertTrue(paths.contains("4 -> 5 -> 9\n"));
        Assert.assertTrue(paths.contains("2 -> 2\n"));
        Assert.assertTrue(paths.contains("4 -> 4\n"));
        Assert.assertFalse(paths.contains("1 -> 4 -> 4\n"));
        Assert.assertFalse(paths.contains("1 -> 2\n"));
    }
    
    private String printPaths(BinaryTreeNode<Integer> root) {
        StringBuilder sb = new StringBuilder();
        printPaths(root, new ArrayList<Integer>(), sb);
        return sb.toString();
    }
    
    private void printPaths(BinaryTreeNode<Integer> root, List<Integer> prev, StringBuilder sb) {
        if (root == null) {
            return;
        }
        
        Integer data = root.getData();
        
        int sum = 0;
        for (int i = prev.size()-1; i >= 0; i--) {
            sum += prev.get(i);
            if (data.equals(sum)) {
                for (int j=i; j < prev.size(); j++) {
                    sb.append(prev.get(j));
                    if (j < prev.size() - 1) {
                        sb.append(" -> ");
                    }
                }
                sb.append(" -> " + data + "\n");
            }
        }
        
        List<Integer> leftList = new ArrayList<>(prev);
        leftList.add(data);
        printPaths(root.getLeft(), leftList, sb);
        
        List<Integer> rightList = new ArrayList<>(prev);
        rightList.add(data);
        printPaths(root.getRight(), rightList, sb);
    }
}
