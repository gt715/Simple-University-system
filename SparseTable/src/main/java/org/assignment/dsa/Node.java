
package org.assignment.dsa;

import org.assignment.entity.Course;
import org.assignment.entity.Student;

public class Node {
    private Student student;
    private Course course;
    private char grade;
    Node nextStudent;
    Node nextCourse;

    public Node(Student student, Course course, char grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public Node(Student student, Course course, char grade, Node nextStudent, Node nextCourse) {
        this(student, course, grade);
        this.nextStudent = nextStudent;
        this.nextCourse = nextCourse;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Node{" +
                "student=" + student +
                ", course=" + course +
                ", grade=" + grade +
                '}';
    }
}
