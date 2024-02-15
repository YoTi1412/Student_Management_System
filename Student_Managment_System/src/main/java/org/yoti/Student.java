package org.yoti;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Student {
    // Create a Scanner object to read user input
    public static Scanner scanner = new Scanner(System.in);
    private String firstName;
    private String lastName;
    private String gradeLevel;
    private int gradeYear;
    private int studentID;
    private List<String> courses = new ArrayList<>();
    private int tuitionBalance = 0;
    private static int courseCost = 600;
    private static int ID = 1000;
    private static final String[] AVAILABLE_COURSES = {"History 101", "Mathematics 101", "English 101", "Chemistry 101", "Computer Science 101"};

    public Student() {
        // Prompt the user to enter the first name and last name of the student
        System.out.println("Enter student first name : ");
        this.firstName = scanner.nextLine();
        System.out.println("Enter student last name : ");
        this.lastName = scanner.nextLine();
        // Set the grade level of the student
        gradeLevel();
        // Generate a unique student ID for the student
        generateStudentID();
        // Enroll the student in courses
        enrollInCourses();
    }

    // Method to set the grade level of the student
    public void gradeLevel() {
        System.out.println("1 - Freshman");
        System.out.println("2 - Sophomore");
        System.out.println("3 - Junior");
        System.out.println("4 - Senior");
        System.out.println("Enter student grade level: ");
        boolean validInput = false;
        while (!validInput) {
            try {
                this.gradeYear = scanner.nextInt();
                scanner.nextLine();
                if (this.gradeYear >= 1 && this.gradeYear <= 4) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid grade level (1-4).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid grade level (1-4).");
                scanner.nextLine();
            }
        }
        // Map the grade year to the corresponding grade level
        switch (this.gradeYear) {
            case 1:
                this.gradeLevel = "Freshman";
                break;
            case 2:
                this.gradeLevel = "Sophomore";
                break;
            case 3:
                this.gradeLevel = "Junior";
                break;
            case 4:
                this.gradeLevel = "Senior";
                break;
            default:
                this.gradeLevel = "Invalid";
                break;
        }
    }

    // Method to generate a unique student ID for the student
    private void generateStudentID() {
        ID++;
        this.studentID = Integer.parseInt(String.valueOf(gradeYear) + String.valueOf(ID));
    }

    // Method to enroll the student in courses
    public void enrollInCourses() {
        System.out.println("Available courses:");
        for (String course : AVAILABLE_COURSES) {
            System.out.println("- " + course);
        }
        System.out.println("Enter the courses you want to enroll in (type 'q' to finish):");
        int count = 0;
        while (count < 5) {
            String course = scanner.nextLine();
            if (course.equalsIgnoreCase("q")) {
                break;
            }
            if (isValidCourse(course)) {
                courses.add(course);
                tuitionBalance += courseCost; // Increase tuition balance
                System.out.println("Enrolled in " + course);
                count++;
            } else {
                System.out.println("Invalid course. Please enter a valid course or 'q' to finish.");
            }
        }
    }

    // Method to validate if the entered course is available and not already enrolled
    private boolean isValidCourse(String course) {
        if (courses.contains(course)) {
            System.out.println("You are already enrolled in " + course);
            return false;
        }
        for (String availableCourse : AVAILABLE_COURSES) {
            if (availableCourse.equalsIgnoreCase(course)) {
                return true;
            }
        }
        return false;
    }

    // Method to display enrolled courses
    public void viewEnrolledCourses() {
        System.out.println("Enrolled courses :");
        for (String course : courses) {
            System.out.println("    - " + course);
        }
    }

    // Method to view tuition balance
    public void viewTuitionBalance() {
        System.out.println("the tuition balance of " + "\"" + this.lastName + "\"" + " is : $" + tuitionBalance);
    }

    // Method to handle tuition payment
    public void payTuition() {
        boolean paid = false;
        while (!paid) {
            System.out.println("Enter your payment (or type 'q' to quit): ");
            System.out.print("$");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Payment cancelled.");
                break;
            }
            try {
                int payment = Integer.parseInt(input);
                if (payment <= tuitionBalance && payment > 0) {
                    tuitionBalance -= payment;
                    System.out.println("You paid $" + payment);
                    paid = true;
                } else {
                    System.out.println("Invalid payment amount. Please enter a valid amount.");
                    viewTuitionBalance();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid amount or 'q' to quit.");
            }
        }
        viewTuitionBalance();
    }

    // Method to show student information
    public void showStudentInfo() {
        System.out.println("Student Information:");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("ID: " + studentID);
        System.out.println("Grade Level: " + gradeLevel);
        viewEnrolledCourses();
        viewTuitionBalance();
    }
}
