import java.util.ArrayList;
import java.util.Scanner;

// Student class to hold student data
class Student {
    private int id;
    private String name;
    private double marks;

    // Constructor
    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    // To display student details
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Marks: " + marks;
    }
}

public class StudentManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Method to add a student
    public static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Marks: ");
        double marks = scanner.nextDouble();

        students.add(new Student(id, name, marks));
        System.out.println("Student added successfully!");
    }

    // Method to view all students
    public static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            System.out.println("\n--- Student Records ---");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    // Method to update a student
    public static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Student s : students) {
            if (s.getId() == id) {
                System.out.print("Enter new name: ");
                String name = scanner.nextLine();
                System.out.print("Enter new marks: ");
                double marks = scanner.nextDouble();

                s.setName(name);
                s.setMarks(marks);

                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // Method to delete a student
    public static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();

        for (Student s : students) {
            if (s.getId() == id) {
                students.remove(s);
                System.out.println("Student deleted successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("===== Student Record Management System =====");

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}
