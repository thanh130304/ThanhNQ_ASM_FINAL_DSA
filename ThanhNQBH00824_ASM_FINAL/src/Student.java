public class Student {
    private String id;
    private String name;
    private double marks;
    private String rank;

    // Constructor
    public Student(String id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = Math.round(marks * 10.0) / 10.0; // Round marks to 1 decimal place
        this.rank = calculateRank(marks); // Determine rank based on marks
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public String getRank() {
        return rank;
    }

    // Private method to calculate rank based on marks
    private String calculateRank(double marks) {
        if (marks >= 0.0 && marks <= 5.0) {
            return "Fail";
        } else if (marks > 5.0 && marks <= 6.5) {
            return "Medium";
        } else if (marks > 6.5 && marks <= 7.5) {
            return "Good";
        } else if (marks > 7.5 && marks <= 9.0) {
            return "Very Good";
        } else if (marks > 9.0 && marks <= 10.0) {
            return "Excellent";
        } else {
            return "Invalid"; // For unexpected input
        }
    }

    // To string method to display student info
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Rank: " + rank;
    }
}
