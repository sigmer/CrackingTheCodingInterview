package structures;

public class Stack<T> {
    
    Node<T> top;
    
    public Stack() {
        top = null;
    }
    
    public T pop() {
        if (top == null) {
            return null;
        }
        Node<T> popped = top;
        top = top.next();
        return popped.getData();
    }
    
    public void push(T data) {
        Node<T> pushed = new Node<T>(data);
        pushed.next(top);
        top = pushed;
    }
    
    public T peek() {
        return top.getData();
    }
    
    public boolean isEmpty() {
        return top == null;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> node = top;
        sb.append("Stack:");
        while (node != null) {
            sb.append(" " + node.getData());
            node = node.next();
        }
        sb.append("\n");
        return sb.toString();
    }

}
