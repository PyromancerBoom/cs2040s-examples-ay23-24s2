public class StackWithArray {
    private int[] array; // Our "stack"
    private int topIndex = -1; // Top pointer to keep track
    private final int capacity; // Maximum capacity of the stack

    // Constructor to initialize the stack with a given capacity
    public StackWithArray(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
    }

    /*
     * Check if the stack is empty
     * 
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return topIndex == -1;
    }

    /*
     * Check if the stack is full
     * 
     * @return true if the stack is full, false otherwise
     */
    public boolean isFull() {
        return topIndex == capacity - 1;
    }

    /*
     * Push an element to the top of the stack
     * But if stack is full, we throw an exception
     * 
     * @param number the element to be pushed
     * 
     * @throws Exception if the stack is full
     * 
     * @return void
     */
    public void push(int number) throws Exception {
        if (isFull()) {
            throw new Exception("Stack is full");
        }
        // otherwise, increment top index and insert the element
        topIndex++;
        array[topIndex] = number;
    }

    /*
     * Pop the top element of the stack
     * As we pop, we decrement the top index
     * 
     * @return the top element
     */
    public int pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("Stack is empty");
        }
        // otherwise, remove the top element and decrement top index
        int topElement = array[topIndex];
        topIndex--;
        return topElement;
    }

    /*
     * Peek at the top element of the stack
     * 
     * @return the top element
     */
    public int peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("Stack is empty");
        }
        return array[topIndex];
    }

    // Helper method to print the stack
    public void print() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.print("Stack (top to bottom): ");
        for (int i = topIndex; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        // Test 1: Normal Push and Pop
        StackWithArray stack = new StackWithArray(5);
        stack.push(10);
        stack.push(20);
        System.out.println("Popped: " + stack.pop()); // what would this print ?
        System.out.println("Popped: " + stack.pop());

        // Test 2: Empty Stack (attempting pop)
        try {
            stack.pop();
        } catch (Exception e) {
            System.out.println("Exception (expected): " + e.getMessage());
        }
        // stack.pop(); // what if i just call pop() without try-catch block ?

        // Test 3: Full Stack (attempting push)
        try {
            stack.push(30);
            stack.print();
            stack.push(40);
            stack.print();
            stack.push(50);
            stack.print();
            stack.push(60);
            stack.print();
            stack.push(70);
            stack.print();
            stack.push(80); // what would happen here ?
            System.out.println("Code will never reach here");
        } catch (Exception e) {
            System.out.println("Exception (expected): " + e.getMessage());
        }
    }
}
