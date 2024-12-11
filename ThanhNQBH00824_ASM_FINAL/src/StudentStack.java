public class StudentStack {
    private Student[] stack;
    private int top;

    // Constructor
    public StudentStack(int capacity) {
        stack = new Student[capacity];
        top = -1;
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Check if stack is full
    public boolean isFull() {
        return top == stack.length - 1;
    }

    // Push student onto stack
    public void push(Student student) {
        if (isFull()) {
            System.out.println("Stack is full!");
            return;
        }
        stack[++top] = student;
    }

    // Pop student from stack
    public Student pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        return stack[top--];
    }

    // Peek top student from stack
    public Student peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        return stack[top];
    }

    // Get size of the stack
    public int size() {
        return top + 1;
    }

    // Get all students in the stack
    public Student[] getAll() {
        Student[] result = new Student[size()];
        System.arraycopy(stack, 0, result, 0, size());
        return result;
    }

    // Find student by ID
    public Student findById(String id) {
        for (int i = 0; i <= top; i++) {
            if (stack[i].getId().equals(id)) {
                return stack[i];
            }
        }
        return null; // Return null if not found
    }

    // Edit a student by ID
    public boolean editById(String id, String newName, double newMarks) {
        for (int i = 0; i <= top; i++) {
            if (stack[i].getId().equals(id)) {
                stack[i] = new Student(id, newName, newMarks);
                return true;
            }
        }
        return false; // Student with the given ID not found
    }

    // Delete student by ID
    public boolean deleteById(String id) {
        boolean found = false;
        Student[] tempStack = new Student[stack.length];
        int newIndex = 0;

        for (int i = 0; i <= top; i++) {
            if (stack[i].getId().equals(id)) {
                found = true; // Skip the student with matching ID
            } else {
                tempStack[newIndex++] = stack[i];
            }
        }

        if (found) {
            stack = tempStack;
            top = newIndex - 1;
        }
        return found;
    }

    // Bubble Sort by marks
    public void sortStudentsByMarksBubble() {
        for (int i = 0; i < size() - 1; i++) {
            for (int j = 0; j < size() - 1 - i; j++) {
                if (stack[j].getMarks() > stack[j + 1].getMarks()) {
                    Student temp = stack[j];
                    stack[j] = stack[j + 1];
                    stack[j + 1] = temp;
                }
            }
        }
    }

    // Quick Sort by marks
    public void sortStudentsByMarksQuick(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            sortStudentsByMarksQuick(low, pi - 1);
            sortStudentsByMarksQuick(pi + 1, high);
        }
    }

    // Partition for Quick Sort
    private int partition(int low, int high) {
        double pivot = stack[high].getMarks();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (stack[j].getMarks() <= pivot) {
                i++;
                Student temp = stack[i];
                stack[i] = stack[j];
                stack[j] = temp;
            }
        }
        Student temp = stack[i + 1];
        stack[i + 1] = stack[high];
        stack[high] = temp;
        return i + 1;
    }
}
