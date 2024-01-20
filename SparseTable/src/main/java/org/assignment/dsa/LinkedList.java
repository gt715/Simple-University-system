package org.assignment.dsa;

import org.assignment.entity.Course;
import org.assignment.entity.Student;

public class LinkedList {
    private Node head; // Head of the linked list
    private Node tail; // Tail of the linked list

    // Default constructor
    public LinkedList() {
    }

    // Getter for head
    public Node getHead() {
        return this.head;
    }

    // Setter for head
    public void setHead(Node head) {
        this.head = head;
    }

    // Getter for tail
    public Node getTail() {
        return this.tail;
    }

    // Setter for tail
    public void setTail(Node tail) {
        this.tail = tail;
    }

    // Check if the linked list is empty
    public boolean isEmpty() {
        return head == null;
    }

    // Add a node to the student linked list
    private void addToStudent(Node node) {
        if (head == null) {
            head = tail = node;
        } else {
            tail.nextCourse = node;
            tail = node;
        }
    }

    // Add a node to the course linked list
    private void addToCourse(Node node) {
        if (head == null) {
            head = tail = node;
        } else {
            tail.nextStudent = node;
            this.tail = node;
        }
    }

    // Add a node to the appropriate linked list
    public void add(Node node, boolean inStudent) {
        if (inStudent) {
            this.addToStudent(node);
        } else {
            this.addToCourse(node);
        }
    }




    // Method to delete a student from the student linked list
    public Node deleteFromStudent(Student student) {
        Node deletedNode = null; // Node to be deleted

        // If the list only contains one node
        if (head == tail) {
            deletedNode = head; // The node to be deleted is the head (also the tail)
            head = tail = null; // The list is now empty
        }
        // If the head node is the one to be deleted
        else if(head.getStudent().equals(student)) {
            deletedNode = head; // The node to be deleted is the head
            head = head.nextStudent; // The head is now the next node
        }
        // If the node to be deleted is not the head
        else {
            // Traverse the list until the end or until the next node is the one to be deleted
            for(Node node = head; node.nextStudent != null; node = node.nextStudent) {
                if (node.nextStudent.getStudent().equals(student)) {
                    deletedNode = node.nextStudent; // The node to be deleted is the next node
                    node.nextStudent = node.nextStudent.nextStudent; // Bypass the deleted node
                    break;
                }
            }
        }
        return deletedNode; // Return the deleted node
    }


    // Method to delete a course from the course linked list
    public Node deleteFromCourse(Course course) {
        Node deletedNode = null; // Node to be deleted

        // If the list only contains one node
        if (head == tail) {
            deletedNode = head; // The node to be deleted is the head (also the tail)
            head = tail = null; // The list is now empty
        }
        // If the head node is the one to be deleted
        else if(head.getCourse().equals(course)) {
            deletedNode = head; // The node to be deleted is the head
            head = head.nextCourse; // The head is now the next node
        }
        // If the node to be deleted is not the head
        else {
            // Traverse the list until the end or until the next node is the one to be deleted
            for(Node node = head; node.nextCourse != null; node = node.nextCourse) {
                if (node.nextCourse.getCourse().equals(course)) {
                    deletedNode = node.nextCourse; // The node to be deleted is the next node
                    node.nextCourse = node.nextCourse.nextCourse; // Bypass the deleted node
                    break;
                }
            }
        }
        return deletedNode; // Return the deleted node
    }


    // Method to delete a student node from the course linked list
    public void deleteNodeStudent(Course course) {
        // If the list only contains one node
        if (head == tail)
            head = tail = null; // The list is now empty
        else {
            // Traverse the list until the end or until the next node's course is the one to be deleted
            for(Node node = head; node.nextCourse != null; node = node.nextCourse) {
                if (node.nextCourse.getCourse().equals(course)){
                    node.nextCourse = node.nextCourse.nextCourse; // Bypass the deleted node
                    break;
                }
            }
        }
    }


    // Method to delete a course node from the student linked list
    public void deleteNodeCourse(Student student) {
        // If the list only contains one node
        if (head == tail)
            head = tail = null; // The list is now empty
        else {
            // Traverse the list until the end or until the next node's student is the one to be deleted
            for(Node node = head; node.nextStudent != null; node = node.nextStudent) {
                if (node.nextStudent.getStudent().equals(student))
                    node.nextStudent = node.nextStudent.nextStudent; // Bypass the deleted node
            }
        }
    }


    // Method to add a grade to a course
    public void addGrade(Course course, char grade) {
        // Traverse the list until the end or until the node's course is the one to be graded
        for(Node node = head; node != null; node = node.nextCourse) {
            // If the node's course is the one to be graded
            if (node.getCourse().equals(course)) {
                // Set the grade for the node
                node.setGrade(grade);
                // Exit the loop as the grade has been added
                break;
            }
        }
    }

    // Method to get the grade of a course
    public char getGrade(Course course) {
        // Traverse the list until the end or until the node's course is the one whose grade is to be fetched
        for(Node node = head; node != null; node = node.nextCourse) {
            // If the node's course is the one whose grade is to be fetched
            if (node.getCourse().equals(course)) {
                // Return the grade of the node
                return node.getGrade();
            }
        }
        // If the course is not found in the list, return '-'
        return '-';
    }


    // Method to check if a course is in the linked list
    public boolean contains(Course course) {
        // Traverse the list until the end or until the node's course is the one to be checked
        for(Node node = head; node != null; node = node.nextCourse) {
            // If the node's course is the one to be checked
            if (node.getCourse().equals(course))
                return true; // The course is in the list
        }
        // If the course is not found in the list, return false
        return false;
    }


    @Override
    public String toString() {
        return "LinkedList{" +
                "head=" + head +
                ", tail=" + tail +
                '}';
    }
}
