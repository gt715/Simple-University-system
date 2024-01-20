package org.assignment;

import java.time.LocalDate;
import java.util.Scanner;

import org.assignment.dsa.SparseTable;
import org.assignment.entity.Course;
import org.assignment.entity.Student;

public class Main {

    static SparseTable sparseTable = new SparseTable();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome To My Program (choose option to start :) ):");
        while(true) {
            System.out.println("\n\n");
            displayOptions();
            System.out.print("Enter Your Option: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> addStudent(scanner);
                case 2 -> addCourse(scanner);
                case 3 -> enroll(scanner);
                case 4 -> setStudentGrade(scanner);
                case 5 -> getStudentGrade(scanner);
                case 6 -> unEnroll(scanner);
                case 7 -> deleteStudent(scanner);
                case 8 -> deleteCourse(scanner);
                case 9 -> displayStudentsInSystem();
                case 10 -> displayCoursesInSystem();
                case 11 -> displayCoursesForStudent(scanner);
                case 12 -> displayStudentsInCourse(scanner);
                default -> System.exit(0);
            }
        }




    }

    public static void displayOptions() {
        System.out.println("""
                Options you can choose:
                >> 1) Add Student.
                >> 2) Add Course.
                >> 3) Enroll Course
                >> 4) Set Student Grade At a Course
                >> 5) Get Student Grade At a Course
                >> 6) UnEnroll Course
                >> 7) Delete Student.
                >> 8) Delete Course.
                >> 9) display Students In system.
                >> 10) display Courses In system.
                >> 11) display Courses for Student.
                >> 12) display Students In Course.
                >> 0) Exit.
                """);
    }

    public static void addStudent(Scanner scanner) {
        System.out.println("Enter Student Details: ");
        System.out.print(">> Enter Student id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print(">> Enter Student name: ");
        String name = scanner.nextLine();
        System.out.print(">> Enter Student age: ");
        int age = scanner.nextInt();
        sparseTable.addStudent(new Student(id, name, LocalDate.now().minusYears(age)));
    }

    public static void enroll(Scanner scanner) {
        System.out.print(">> Enter Student id: ");
        int studentId = scanner.nextInt();
        System.out.print(">> Enter Course id: ");
        int courseId = scanner.nextInt();
        sparseTable.enroll(studentId, courseId);
    }

    public static void unEnroll(Scanner scanner) {
        System.out.print(">> Enter Student id: ");
        int studentId = scanner.nextInt();
        System.out.print(">> Enter Course id: ");
        int courseId = scanner.nextInt();
        sparseTable.unEnroll(studentId, courseId);
    }

    public static void setStudentGrade(Scanner scanner) {
        System.out.print(">> Enter Student id: ");
        System.out.print(">> Enter Student id: ");
        int studentId = scanner.nextInt();
        System.out.print(">> Enter Course id: ");
        int courseId = scanner.nextInt();
        System.out.print(">> Enter Student Grade: ");
        char grade = scanner.next().charAt(0);
        sparseTable.addGrade(studentId, courseId, grade);
    }

    public static void getStudentGrade(Scanner scanner) {
        System.out.print(">> Enter Student id: ");
        System.out.print(">> Enter Student id: ");
        int studentId = scanner.nextInt();
        System.out.print(">> Enter Course id: ");
        int courseId = scanner.nextInt();
        sparseTable.getGrade(studentId, courseId);
    }

    public static void deleteStudent(Scanner scanner) {
        System.out.print("Enter Student id: ");
        int id = scanner.nextInt();
        sparseTable.deleteStudent(id);
    }

        public static void addCourse(Scanner scanner) {
        System.out.println("Enter Course Details: ");
        System.out.print(">> Enter Course id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print(">> Enter Course title: ");
        String title = scanner.nextLine();
        sparseTable.addCourse(new Course(id, title));
    }

    public static void deleteCourse(Scanner scanner) {
        System.out.print("Enter Course id: ");
        int id = scanner.nextInt();
        sparseTable.deleteCourse(id);
    }

    public static void displayStudentsInSystem() {
        sparseTable.displayStudents();
    }

    public static void displayCoursesForStudent(Scanner scanner) {
        System.out.print("Enter Student id: ");
        int id = scanner.nextInt();
        sparseTable.displayCourses(id);
    }

    public static void displayCoursesInSystem() {
        sparseTable.displayCourses();
    }


    public static void displayStudentsInCourse(Scanner scanner) {
        System.out.print("Enter Course id: ");
        int id = scanner.nextInt();
        sparseTable.displayStudents(id);
    }


}
