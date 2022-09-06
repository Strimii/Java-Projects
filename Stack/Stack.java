public class Stack {
    private int[] elem;
    private int StackTop;

    public Stack(int size) {
        elem = new int[size];
        StackTop = -1;

    }
    public boolean isEmpty() {
        return StackTop < 0;
    }
    public void push(int element) {
        if (StackTop == elem.length - 1) {
            int[] newElements = new int[elem.length * 2];
            for (int x = 0; x < elem.length; x++) {
                newElements[x] = elem[x];
            }
            elem = newElements;
        }
        elem[++StackTop] = element;
    }
    public int pop() {
        if (this.isEmpty())
            throw new IllegalArgumentException();
        return elem[StackTop--];
    }
    public int peek() {
        if (this.isEmpty())
            throw new IllegalArgumentException();
        return elem[StackTop];
    }
}
