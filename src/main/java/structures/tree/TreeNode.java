package structures.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TreeNode<T> {
    
    private T data;
    private List<TreeNode<T>> children;
    
    public TreeNode(T data) {
        this.data = data;
        children = new ArrayList<>();
    }
    
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }
    
    public TreeNode<T> addChild(T data) {
        TreeNode<T> node = new TreeNode<T>(data);
        addChild(node);
        return node;
    }
    
    public void addChild(TreeNode<T> node) {
        children.add(node);
    }
    
    public boolean isLeaf() {
        return children.size() == 0;
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
    
    protected void printTree(TreeNode<T> node, int depth, StringBuilder sb) {
        IntStream.range(0, depth).forEach(i -> sb.append("  "));
        sb.append(node.toString() + "\n");
        if (node.isLeaf()) {
            return;
        } else {
            for (TreeNode<T> child: node.getChildren()) {
                printTree(child, depth + 1, sb);
            }
        }
    }
}
