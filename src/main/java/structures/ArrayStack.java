package structures;

public class ArrayStack {
    
    public enum Instance {
        A(0), B(1), C(2);
        
        private int pos;
        
        Instance(int num) {
            pos = num;
        }
        
        protected int getPosition() {
            return pos;
        }
    }
    
    private Character[] array;
    private static final int capacity = 90;

    public ArrayStack() {
        // each stack can have 30 (90/3) items
        array = new Character[capacity];
    }
    
    public Character pop(Instance inst) {
        int pos = inst.getPosition();
        Character pop = null;
        while (array[pos] != null) {
            pop = array[pos];
            pos += 3;
            
            if (pos > capacity - 1) {
                throw new ArrayIndexOutOfBoundsException(String.format("Each stack can only contain %d items", capacity/3));
            }
        }
        
        if (pop == null) {
            return null;
        }
        
        array[pos-3] = null;
        return pop;
    }
    
    public void push(Character c, Instance inst) {
        int pos = inst.getPosition();
        while (array[pos] != null) {
            pos += 3;
            
            if (pos > capacity - 1) {
                throw new ArrayIndexOutOfBoundsException(String.format("Each stack can only contain %d items", capacity/3));
            }
        }
        
        array[pos] = c;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (int i=0; i < 3; i++) {
            sb.append(String.format("Stack %d:", i+1));
            int pos = i;
            while (array[pos] != null) {
                sb.append(" " + array[pos]);
                pos += 3;
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
}
