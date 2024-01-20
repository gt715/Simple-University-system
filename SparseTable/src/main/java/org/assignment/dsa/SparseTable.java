package org.assignment.dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assignment.entity.Course;
import org.assignment.entity.Student;

public class SparseTable {
    private final List<LinkedList> studentsList;
    private final List<LinkedList> coursesList;
    private final Map<Integer, Student> students;
    private final Map<Integer, Course> courses;

    // Constructor for SparseTable class
    public SparseTable() {
        students = new HashMap<>(); // HashMap to store Student objects
        courses = new HashMap<>(); // HashMap to store Course objects
        studentsList = new ArrayList<>(); // ArrayList to store list of students
        coursesList = new ArrayList<>(); // ArrayList to store list of courses
    }

    // Method to add a student to the SparseTable
    public void addStudent(Student student) {
        if (contains(student)) { // Check if the student already exists in the SparseTable
            System.out.println("Student with id: " + student.getId() + " Already In the System");
        } else {
            students.put(student.getId(), student); // Add the student to the students HashMap
        }
    }

    // Method to delete a student from the SparseTable
    public void deleteStudent(int studentId) {
        if (!students.containsKey(studentId)) { // Check if the student does not exist in the SparseTable
            System.out.println("There is No Student with id: " + studentId + " already");
            return;
        } else if (findStudentsLinkedList(students.get(studentId)) == null) { // Check if the student does not exist in the studentsList
            students.remove(studentId); // Remove the student from the students HashMap
            System.out.println("Student with id: " + studentId + " deleted Successfully");
        } else {
            Student student = students.get(studentId); // Get the student from the students HashMap
            LinkedList list = findStudentsLinkedList(student); // Find the LinkedList of the student
            Course course = list.getHead().getCourse(); // Get the course of the student
            for(LinkedList courseList: coursesList) { // Iterate over the coursesList
                if (courseList.getHead().getCourse().equals(course)) { // Check if the course equals the course of the student
                    Node deletedNode = courseList.deleteFromStudent(student); // Delete the student from the courseList
                    course = deletedNode.nextCourse == null ? null : deletedNode.nextCourse.getCourse(); // Get the next course of the deleted node
                    System.out.println("Course got from deleted node");
                }
            }
            studentsList.remove(list); // Remove the list from the studentsList
        }
        students.remove(studentId); // Remove the student from the students HashMap
        System.out.println("Student with id: " + studentId + " deleted successfully");
    }


    // Method to delete a course from the SparseTable
    public void deleteCourse(int courseId) {
        // Check if the course does not exist in the SparseTable
        if (!courses.containsKey(courseId)) {
            System.out.println("There is No Course with id: " + courseId + " already");
        }
        // Check if the course does not exist in the coursesList
        else if (findCoursesLinkedList(courses.get(courseId)) == null) {
            // Remove the course from the courses HashMap
            courses.remove(courseId);
            System.out.println("Course with id: " + courseId + " deleted Successfully");
        }
        else {
            // Get the course from the courses HashMap
            Course course = courses.get(courseId);
            // Find the LinkedList of the course
            LinkedList list = findCoursesLinkedList(course);
            // Get the student of the course
            Student student = list.getHead().getStudent();
            // Iterate over the studentsList
            for(LinkedList studentList: studentsList) {
                // Check if the student equals the student of the course
                if (studentList.getHead().getStudent().equals(student)) {
                    // Delete the course from the studentList
                    Node deletedNode = studentList.deleteFromCourse(course);
                    // Get the next student of the deleted node
                    student = deletedNode.nextStudent == null ? null : deletedNode.nextStudent.getStudent();
                }
            }
            // Remove the list from the coursesList
            coursesList.remove(list);
        }
        // Remove the course from the courses HashMap
        courses.remove(courseId);
        System.out.println("Course with id: " + courseId + " deleted successfully");
    }


    // Method to unenroll a student from a course
    public void unEnroll(int studentId, int courseId) {
        // Check if the course does not exist in the SparseTable
        if (!courses.containsKey(courseId)){
            System.out.println("There is No Course with id: " + courseId + " already");
            return;
        }
        // Check if the student does not exist in the SparseTable
        else if(!students.containsKey(studentId)) {
            System.out.println("There is N0 Student with id: " + studentId + " already");
            return;
        }
        // Get the student from the students HashMap
        Student student = students.get(studentId);
        // Get the course from the courses HashMap
        Course course = courses.get(courseId);
        // Check if the student has not enrolled the course
        if(!hasEnrolled(student, course))
            System.out.println("Student Whose id: " + studentId + " hasn't Enrolled Course with id: " + courseId);
        else {
            // Find the LinkedList of the student
            LinkedList studentList = findStudentsLinkedList(student);
            // Find the LinkedList of the course
            LinkedList courseList = findCoursesLinkedList(course);
            // Delete the course from the studentList
            studentList.deleteNodeStudent(course);
            // Delete the student from the courseList
            courseList.deleteNodeCourse(student);
            // Check if the studentList is empty
            if (studentList.isEmpty())
                // Remove the list from the studentsList
                studentsList.remove(studentList);
            // Check if the courseList is empty
            if (courseList.isEmpty())
                // Remove the list from the coursesList
                coursesList.remove(courseList);
            System.out.println("Student With id: " + studentId + " has withdrew Course with id: " + courseId + " successfully");
        }
    }


    // Method to add a course to the SparseTable
    public void addCourse(Course course) {
        // Check if the course already exists in the SparseTable
        if (contains(course)) {
            System.out.println("Course with id: " + course.getId() + " Already In the System");
        } else {
            // Add the course to the courses HashMap
            courses.put(course.getId(), course);
        }
    }


    // Method to enroll a student in a course
    public void enroll(int studentId, int courseId) {
        // Check if the student does not exist in the SparseTable
        if (!students.containsKey(studentId)) {
            System.out.println("Student whose id: " + studentId + " not found in System");
        }
        // Check if the course does not exist in the SparseTable
        else if (!courses.containsKey(courseId)) {
            System.out.println("Course whose id: " + courseId + " not found in System");
        }
        else {
            // Get the student from the students HashMap
            Student student = students.get(studentId);
            // Get the course from the courses HashMap
            Course course = courses.get(courseId);
            // Find the LinkedList of the course
            LinkedList courseLinkedList = findCoursesLinkedList(course);
            // Find the LinkedList of the student
            LinkedList studentLinkedList = findStudentsLinkedList(student);
            // Check if the courseLinkedList does not exist
            if (courseLinkedList == null) {
                // Create a new LinkedList for the course
                courseLinkedList = new LinkedList();
                // Add the LinkedList to the coursesList
                coursesList.add(courseLinkedList);
            }

            // Check if the studentLinkedList does not exist
            if (studentLinkedList == null) {
                // Create a new LinkedList for the student
                studentLinkedList = new LinkedList();
                // Add the LinkedList to the studentsList
                studentsList.add(studentLinkedList);
            }

            // Create a new Node for the student and course
            Node node = new Node(student, course, '-');
            // Add the Node to the studentLinkedList
            studentLinkedList.add(node, true);
            // Add the Node to the courseLinkedList
            courseLinkedList.add(node, false);
        }
    }


    // Method to add a grade to a course for a student
    public void addGrade(int studentId, int courseId, char grade) {
        // Check if the student does not exist in the SparseTable
        if (!students.containsKey(studentId)) {
            System.out.println("Student whose id: " + studentId + " not found in System");
            return;
        }
        // Check if the course does not exist in the SparseTable
        else if (!courses.containsKey(courseId)) {
            System.out.println("Course whose id: " + courseId + " not found in System");
            return;
        }
        // Get the student from the students HashMap
        Student student = students.get(studentId);
        // Get the course from the courses HashMap
        Course course = courses.get(courseId);
        // Check if the student has not enrolled the course
        if (!hasEnrolled(student, course)) {
            System.out.println("Student with id: " + studentId + " didn't enroll Course with id: " + courseId);
        }
        else {
            // Find the LinkedList of the student
            LinkedList studentLinkedList = findStudentsLinkedList(student);
            // Add the grade to the course for the student
            studentLinkedList.addGrade(course, grade);
        }
    }


    // This method retrieves the grade of a student in a specific course.
    public void getGrade(int studentId, int courseId) {
        // Check if the student exists in the system.
        if (!students.containsKey(studentId)) {
            System.out.println("Student whose id: " + studentId + " not found in System");
            return;
        }
        // Check if the course exists in the system.
        else if (!courses.containsKey(courseId)) {
            System.out.println("Course whose id: " + courseId + " not found in System");
            return;
        }
        // Retrieve the Student and Course objects.
        Student student = students.get(studentId);
        Course course = courses.get(courseId);

        // Check if the student has enrolled in the course.
        if (!hasEnrolled(student, course)) {
            System.out.println("Student with id: " + studentId + " didn't enroll Course with id: " + courseId);
        } else {
            // Find the linked list of students.
            LinkedList studentLinkedList = findStudentsLinkedList(student);
            // Get the grade of the student in the course.
            char grade = studentLinkedList.getGrade(course);

            // Check if the student has completed the course.
            if (grade == '-')
                System.out.println("This Student whose id: " + studentId + " hasn't completed The Course with id: " + courseId+ "  Yet");
            else
                System.out.println("Grade of Student whose id: " + studentId + " is: " + grade);
        }
    }


    // This method checks if a given element (either a Student or a Course) exists in the system.
    private <E> boolean contains(E element) {
        // If the element is a Student, check if the student exists in the system.
        if (element instanceof Student student)
            return students.containsKey(student.getId());
            // If the element is a Course, check if the course exists in the system.
        else if (element instanceof Course course)
            return courses.containsKey(course.getId());
        // If the element is neither a Student nor a Course, return false.
        return false;
    }

    // This method displays all the students in the system.
    public void displayStudents() {
        // If there are no students in the system, print a message indicating this.
        if (students.isEmpty()) {
            System.out.println("There are no Students in the System");
        } else {
            // If there are students in the system, print each student.
            System.out.println("Students in the System are: ");
            for(Student student: students.values()) {
                System.out.println(">> " + student);
            }
        }
    }

    // This method displays all the students enrolled in a specific course.
    public void displayStudents(int courseId) {
        // Check if the course exists in the system.
        if (!courses.containsKey(courseId)) {
            System.out.println("There is no course with id : " + courseId);
        } else {
            // Retrieve the Course object.
            Course course = courses.get(courseId);
            // Find the linked list of students enrolled in the course.
            LinkedList studentsInCourse = findCoursesLinkedList(course);
            // If no students are enrolled in the course, print a message indicating this.
            if (studentsInCourse == null) {
                System.out.println("This Course whose id : " + courseId + " isn't enrolled by any student.");
            } else {
                // If students are enrolled in the course, print each student.
                System.out.println("Students In course whose id: " + courseId + " are: ");
                for(Node node = studentsInCourse.getHead(); node != null; node = node.nextStudent)
                    System.out.println(">> " + node.getStudent());
            }
        }
    }


    // This method displays all the courses a specific student is enrolled in.
    public void displayCourses(int studentId) {
        // Check if the student exists in the system.
        if (!students.containsKey(studentId)) {
            System.out.println("There is no Student with id: " + studentId);
        } else {
            // Retrieve the Student object.
            Student student = students.get(studentId);
            // Find the linked list of courses the student is enrolled in.
            LinkedList coursesEnrolledByStudent = findStudentsLinkedList(student);
            // If the student is not enrolled in any courses, print a message indicating this.
            if (coursesEnrolledByStudent == null) {
                System.out.println("This Student whose id: " + studentId + " didn't enroll any Course.");
            } else {
                // If the student is enrolled in courses, print each course.
                System.out.println("Courses Enrolled by student whose id: " + studentId + " are: ");
                for(Node node = coursesEnrolledByStudent.getHead(); node != null; node = node.nextCourse)
                    System.out.println(">> " + node.getCourse());
            }
        }
    }


    // This method displays all the courses in the system.
    public void displayCourses() {
        // If there are no courses in the system, print a message indicating this.
        if (courses.isEmpty()) {
            System.out.println("There are no Courses in the System");
        } else {
            // If there are courses in the system, print each course.
            System.out.println("Courses in the System are: ");
            for(Course course: courses.values()) {
                System.out.println(">> " + course);
            }
        }
    }


    // This method finds the linked list of courses that a specific student is enrolled in.
    private LinkedList findStudentsLinkedList(Student student) {
        // Iterate over each linked list in the list of students.
        for(LinkedList list: studentsList) {
            // If the head of the linked list matches the student, return the linked list.
            if (list.getHead().getStudent().equals(student))
                return list;
        }
        // If no matching linked list is found, return null.
        return null;
    }

    // This method finds the linked list of students enrolled in a specific course.
    private LinkedList findCoursesLinkedList(Course course) {
        // Iterate over each linked list in the list of courses.
        for(LinkedList list: coursesList) {
            // If the head of the linked list matches the course, return the linked list.
            if(list.getHead().getCourse().equals(course))
                return list;
        }
        // If no matching linked list is found, return null.
        return null;
    }

    // This method checks if a specific student is enrolled in a specific course.
    private boolean hasEnrolled(Student student, Course course) {
        // Find the linked lists of the student and the course.
        LinkedList studentList = findStudentsLinkedList(student);
        LinkedList courseList = findCoursesLinkedList(course);
        // If either linked list is null, return false.
        if (studentList == null || courseList == null)
            return false;
        // If the student's linked list contains the course, return true.
        return studentList.contains(course);
    }

}

