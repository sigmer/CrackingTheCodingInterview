package structures;

public class Node<T> {
    
    private T data;
    private Node<T> next = null;
    
    public Node(T data) {
        this.data = data;
    }
    
    public Node<T> next() {
        return next;
    }
    
    public void next(Node<T> next) {
        this.next = next;
    }
    
    public void add(Node<T> node) {
        Node<T> currentNode = this;
        while (currentNode.next() != null) {
            currentNode = currentNode.next();
        }
        currentNode.next = node;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }

}
