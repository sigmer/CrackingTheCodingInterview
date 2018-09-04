package structures.tree;

import java.util.stream.IntStream;

public class BinaryTreeNode<T> {
    
    private T data;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;
    private BinaryTreeNode<T> parent;
    
    public BinaryTreeNode(T data) {
        this.data = data;
        left = null;
        right = null;
    }
    
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
        if (this.left != null) {
            this.left.parent = this;
        }
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
        if (this.right != null) {
            this.right.parent = this;
        }
    }

    public BinaryTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(BinaryTreeNode<T> parent) {
        this.parent = parent;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
    
    @Override
    public String toString() {
        return getData().toString();
    }
    
    public void printTree() {
        StringBuilder sb = new StringBuilder();
        printTree(this, 0, sb);
        System.out.println(sb.toString());
    }
    
    protected void printTree(BinaryTreeNode<T> node, int depth, StringBuilder sb) {
        IntStream.range(0, depth).forEach(i -> sb.append("  "));
        sb.append(node.toString() + "\n");
        if (node.getLeft() != null) {
            printTree(node.getLeft(), depth + 1, sb);
        }
        if (node.getRight() != null) {
            printTree(node.getRight(), depth + 1, sb);
        }
    }
}
