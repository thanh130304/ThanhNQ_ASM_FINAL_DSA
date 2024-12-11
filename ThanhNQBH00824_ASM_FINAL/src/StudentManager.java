import java.util.Random;
import java.util.Scanner;

public class StudentManager {
    private StudentStack studentStack;

    // Constructor
    public StudentManager() {
        studentStack = new StudentStack(10000); // Max 10,000 students
    }

    // Generate random students
    private void generateRandomStudents(Scanner scanner) {
        Random random = new Random();
        System.out.print("Enter the number of students to generate: ");
        int numberOfStudents = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numberOfStudents; i++) {
            String id = "ID" + (i + 1);
            String name = "Student" + (i + 1);
            double marks = random.nextDouble() * 10; // Random marks between 0 and 10
            studentStack.push(new Student(id, name, marks));
        }
        System.out.println("Generated " + numberOfStudents + " random students!");
    }

    // Display all students
    private void displayAllStudents() {
        if (studentStack.isEmpty()) {
            System.out.println("No students found.");
        } else {
            Student[] students = studentStack.getAll();
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Add a new student
    private void addStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Student Marks: ");
        double marks = scanner.nextDouble();
        scanner.nextLine();

        studentStack.push(new Student(id, name, marks));
        System.out.println("Student added successfully!");
    }

    // Edit an existing student
    private void editStudent(Scanner scanner) {
        System.out.print("Enter Student ID to edit: ");
        String id = scanner.nextLine();

        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new marks: ");
        double newMarks = scanner.nextDouble();
        scanner.nextLine();

        boolean updated = studentStack.editById(id, newName, newMarks);
        if (updated) {
            System.out.println("Student information updated successfully!");
        } else {
            System.out.println("Student with ID " + id + " not found!");
        }
    }

    // Delete a student
    private void deleteStudent(Scanner scanner) {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();

        boolean success = studentStack.deleteById(id);
        if (success) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student with ID " + id + " not found!");
        }
    }

    // Sort students by marks
    private void sortStudents(Scanner scanner) {
        if (studentStack.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("1. Quick Sort");
        System.out.println("2. Bubble Sort");
        System.out.print("Choose sorting method: ");
        int choice = scanner.nextInt();
        long startTime, endTime;

        if (choice == 1) {
            startTime = System.nanoTime();
            studentStack.sortStudentsByMarksQuick(0, studentStack.size() - 1);
            endTime = System.nanoTime();
            System.out.println("Students sorted using Quick Sort.");
        } else if (choice == 2) {
            startTime = System.nanoTime();
            studentStack.sortStudentsByMarksBubble();
            endTime = System.nanoTime();
            System.out.println("Students sorted using Bubble Sort.");
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        displayAllStudents();
        System.out.println("Execution Time: " + (endTime - startTime) + " nanoseconds");
    }

    // Search a student by ID
    private void searchStudentById(Scanner scanner) {
        System.out.print("Enter Student ID to search: ");
        String id = scanner.nextLine();

        Student student = studentStack.findById(id);
        if (student != null) {
            System.out.println("Found: " + student);
        } else {
            System.out.println("Student with ID " + id + " not found!");
        }
    }

    // Run the program
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Generate Random Students");
            System.out.println("2. Display All Students");
            System.out.println("3. Add Student");
            System.out.println("4. Edit Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Sort Students By Marks");
            System.out.println("    1. Quick Sort");
            System.out.println("    2. Bubble Sort");
            System.out.println("7. Search Student By ID");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    generateRandomStudents(scanner);
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    addStudent(scanner);
                    break;
                case 4:
                    editStudent(scanner);
                    break;
                case 5:
                    deleteStudent(scanner);
                    break;
                case 6:
                    sortStudents(scanner);
                    break;
                case 7:
                    searchStudentById(scanner);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        manager.run();
    }
}
