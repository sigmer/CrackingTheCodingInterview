package structures;

public class LinkedList<T> {
    
    private Node<T> head = null;
    
    public LinkedList(T...items) {
        Node<T> currentNode = null;
        for (T item : items) {
            Node<T> nextNode = new Node<>(item);
            if (currentNode != null) {
                currentNode.next(nextNode);
            }
            
            currentNode = nextNode;
            
            if (getHead() == null) {
                this.head = currentNode;
            }
        }
    }
    
    public Node<T> getHead() {
        return head;
    }
    
    public void setHead(Node<T> node) {
        this.head = node;
    }
    
    public void add(Node<T> node) {
        Node<T> end = getHead();
        if (end == null) {
            setHead(node);
            return;
        }
        while (end.next() != null) {
            end = end.next();
        }
        end.next(node);
    }
    
    public int length() {
        Node<T> node = getHead();
        if (node == null) {
            return 0;
        }
        int len = 1;
        while (node.next() != null) {
            node = node.next();
            len++;
        }
        return len;
    }
    
    public boolean contains(T t) {
        Node<T> node = getHead();
        while (node != null) {
            if (t.equals(node.getData())) {
                return true;
            }
            node = node.next();
        }
        return false;
    }
    
    @Override
    public String toString() {
        Node<T> node = getHead();
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            if (!node.equals(getHead())) {
                sb.append(" -> ");
            }
            sb.append(node.getData());
            node = node.next();
        }
        return sb.toString();
    }
    
}
