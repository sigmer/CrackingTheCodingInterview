package structures;

public class QueueByStack<T> {
    
    private Stack<T> stack1;
    private Stack<T> stack2;
    
    private boolean q1Active = true;
    
    public QueueByStack() {
        stack1 = new Stack<T>();
        stack2 = new Stack<T>();
    }
    
    public void enqueue(T data) {
        if (!q1Active) {
            T d = stack2.pop();
            while (d != null) {
                stack1.push(d);
                d = stack2.pop();
            }
            q1Active = true;
        }
        stack1.push(data);
    }
    
    public T dequeue() {
        if (q1Active) {
            T data = stack1.pop();
            while (data != null) {
                stack2.push(data);
                data = stack1.pop();
            }
            q1Active = false;
        }
        
        return stack2.pop();
    }
    
    @Override
    public String toString() {
        return q1Active ? stack1.toString() : stack2.toString();
    }

}
