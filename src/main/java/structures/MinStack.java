package structures;

public class MinStack extends Stack<Integer> {
    
    private Stack<Integer> minStack;

    public MinStack() {
        super();
        minStack = new Stack<Integer>();
    }
    
    @Override
    public Integer pop() {
        Integer popped = super.pop();
        if (popped == null) {
            return null;
        }
        
        Integer min = minStack.pop();
        if (!popped.equals(min)) {
            minStack.push(min);
        }
        return popped;
    }
    
    @Override
    public void push(Integer data) {
        Integer min = minStack.pop();
        if (min == null) {
            minStack.push(data);
        }
        else if (data < min) {
            minStack.push(min);
            minStack.push(data);
        }
        else {
            minStack.push(min);
        }
        super.push(data);
    }
    
    public Integer getMin() {
        Integer min = minStack.pop();
        minStack.push(min);
        return min;
    }
}
