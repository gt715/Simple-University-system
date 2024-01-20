package org.assignment.entity;

import java.time.LocalDate;

public class Student {
    private int id;
    private String name;
    private LocalDate birthOfDate;

    public Student(int id, String name, LocalDate birthOfDate) {
        this.id = id;
        this.name = name;
        this.birthOfDate = birthOfDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(LocalDate birthOfDate) {
        this.birthOfDate = birthOfDate;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Student student = (Student)o;
            return this.id == student.id;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthOfDate=" + birthOfDate +
                '}';
    }
}
