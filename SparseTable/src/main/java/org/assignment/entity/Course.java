package org.assignment.entity;

public class Course {
    private int id;
    private String title;

    public Course(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && o.getClass() == getClass()) {
            Course course = (Course)o;
            return id == course.getId();
        }
        return false;
    }

    public String toString() {
        return "Course{id=" + id + ", title='" + title + "'}";
    }
}
