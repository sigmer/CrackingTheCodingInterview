package structures;

public class SetOfStacks<Integer> {
    
    private int threshold;
    private Stack<Integer>[] stacks;
    private int currentStackIndex;
    private int currentStackSize;
    
    public SetOfStacks() {
        threshold = 5;
        stacks = new Stack[256];
        currentStackIndex = 0;
        currentStackSize = 0;
    }
    
    public void push(Integer data) {
        if (currentStackSize >= threshold) {
            currentStackIndex++;
            currentStackSize = 0;
        }
        
        if (stacks[currentStackIndex] == null) {
            stacks[currentStackIndex] = new Stack<Integer>();
        }
        
        stacks[currentStackIndex].push(data);
        currentStackSize++;
    }
    
    public Integer pop() {
        Integer popped = stacks[currentStackIndex].pop();
        currentStackSize--;
        if (currentStackSize == 0) {
            stacks[currentStackIndex] = null;
            if (currentStackIndex == 0) {
                currentStackSize = 0;
            } else {
                currentStackSize = threshold;
                currentStackIndex--;
            }
        }
        
        return popped;
    }
    
    public Integer popAt(int index) {
        if (stacks[index] == null) {
            return null;
        }
        
        Integer popped = stacks[index].pop();
        
        for (int i=index+1; i <= currentStackIndex; i++) {
            Stack<Integer> tempStack = new Stack<Integer>();
            Integer p = stacks[i].pop();
            while (p != null) {
                tempStack.push(p);
                p = stacks[i].pop();
            }
            stacks[i-1].push(tempStack.pop());
            Integer tp = tempStack.pop();
            while (tp != null) {
                stacks[i].push(tp);
                tp = tempStack.pop();
            }
        }
        
        Integer lastStackPop = stacks[currentStackIndex].pop();
        if (lastStackPop == null) {
            stacks[currentStackIndex] = null;
            if (currentStackIndex == 0) {
                currentStackSize = 0;
            } else {
                currentStackSize = threshold;
                currentStackIndex--;
            }
        } else {
            stacks[currentStackIndex].push(lastStackPop);
        }
        
        return popped;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < stacks.length; i++) {
            if (stacks[i] == null) {
                break;
            }
            sb.append(i + " ");
            sb.append(stacks[i].toString());
            sb.append("\n");
        }
        
        return sb.toString();
    }

}
