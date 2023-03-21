package com.entities;

import com.annotations.LocalizedName;

@LocalizedName("Student")
public class Student extends Person {
    private String university;
    private String faculty;
    private String specialisation;
    private int course;
    private String group;

    @LocalizedName("University")
    public String getUniversity() {
        return university;
    }

    @LocalizedName("Faculty")
    public String getFaculty() {
        return faculty;
    }

    @LocalizedName("Specialization")
    public String getSpecialisation() {
        return specialisation;
    }

    @LocalizedName("Course")
    public int getCourse() {
        return course;
    }

    @LocalizedName("Group")
    public String getGroup() {
        return group;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
