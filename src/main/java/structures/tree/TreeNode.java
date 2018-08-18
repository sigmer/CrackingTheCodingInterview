package structures.tree;

import java.util.ArrayList;
import java.util.List;

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
    
    public void addChild(T data) {
        addChild(new TreeNode<T>(data));
    }
    
    public void addChild(TreeNode<T> node) {
        children.add(node);
    }
}
