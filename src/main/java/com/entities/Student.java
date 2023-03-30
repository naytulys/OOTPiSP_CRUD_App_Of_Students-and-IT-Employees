package com.entities;

import com.annotations.LocalizedName;

import java.io.Serializable;

@LocalizedName("Student")
public class Student extends Person implements Serializable {
    private String university;
    private String faculty;
    private String specialisation;
    private int course;
    private int group;
    private boolean isWorking;
    private boolean isPayingForStudy;


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
    public int getGroup() {
        return group;
    }

    @LocalizedName("Is working")
    public boolean getIsWorking() {
        return isWorking;
    }

    @LocalizedName("Is paying for study")
    public boolean getIsPayingForStudy() {
        return isPayingForStudy;
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

    public void setGroup(int group) {
        this.group = group;
    }

    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    public void setIsPayingForStudy(boolean isPayingForStudy) {
        this.isPayingForStudy = isPayingForStudy;
    }

    public Student(){

    }

    public Student(String name, String surname, Sex sex, String email, String university, String faculty, String specialisation, int course, int group, boolean isWorking, boolean isPayingForStudy) {
        super(name, surname, sex, email);
        this.university = university;
        this.faculty = faculty;
        this.specialisation = specialisation;
        this.course = course;
        this.group = group;
        this.isWorking = isWorking;
        this.isPayingForStudy = isPayingForStudy;
    }
}
