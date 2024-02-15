package org.yoti;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static List<Student> studentsList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to the Student Management System!");
        String input;
        do {
            run();
            System.out.println("Enter 'quit' to exit or any other key to continue:");
            input = scanner.nextLine();
        } while (!input.equalsIgnoreCase("quit"));
    }

    public static void run() {
        int numberOfStudents = 0;
        boolean validInput = false;

        // Ask user for the number of students until valid input is received
        while (!validInput) {
            try {
                System.out.println("Enter the number of students to add to the database: ");
                numberOfStudents = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                validInput = true; // If no exception occurred, input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the input buffer
            }
        }

        // Populate the students array
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("\nStudent " + (i + 1) + ":");
            Student student = createStudent();
            studentsList.add(student);
        }

        // Display information for each student
        showAllStudents();

        // Example: Accessing and managing a student
        boolean manageMore = true;
        while (manageMore) {
            manageStudent();
            System.out.println("Do you want to manage another student? (yes/no): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("yes")) {
                manageMore = false;
            }
        }
    }

    public static Student createStudent() {
        return new Student();
    }

    // Method to show all students along with their index
    public static void showAllStudents() {
        System.out.println("\nAll Students:");
        for (int i = 0; i < studentsList.size(); i++) {
            System.out.println("-----------------------");
            System.out.println("Student With Index " + i + ":");
            studentsList.get(i).showStudentInfo();
            System.out.println("-----------------------");
        }
    }

    // Method to manage a student (show info and pay tuition)
    public static void manageStudent() {
        int studentIndex = 0; // Example index, replace with actual index
        boolean validInput = false;

        // Ask user for the student index until valid input is received
        while (!validInput) {
            try {
                System.out.println("Enter the index of the student you want to manage: ");
                studentIndex = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (studentIndex >= 0 && studentIndex < studentsList.size()) {
                    validInput = true; // If index is within bounds, input is valid
                } else {
                    System.out.println("Invalid index. Please enter a valid student index.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid student index.");
                scanner.nextLine(); // Clear the input buffer
            }
        }

        Student studentToManage = studentsList.get(studentIndex);
        studentToManage.showStudentInfo();
        studentToManage.payTuition();
    }
}
