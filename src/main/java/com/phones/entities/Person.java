package com.phones.entities;

import com.phones.annotations.LocalizedName;

@LocalizedName("Person")
public abstract class Person {
    public enum Sex {
        Male,
        Female;
    }

    public enum Position {
        Student,
        Manager,
        Designer,
        Programmer,
        Tester;
    }
    private String name;
    private String surname;
    private Sex sex;
    private String email;
    private Position position;

    @LocalizedName("Name")
    public String getName() {
        return name;
    }

    @LocalizedName("Surname")
    public String getSurname() {
        return surname;
    }

    @LocalizedName("Sex")
    public Sex getSex() {
        return sex;
    }

    @LocalizedName("Email")
    public String getEmail() {
        return email;
    }

    @LocalizedName("Position")
    public Position getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
