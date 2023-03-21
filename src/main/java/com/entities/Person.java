package com.entities;

import com.annotations.LocalizedName;

@LocalizedName("Person")
public abstract class Person {
    public enum Sex {
        Male,
        Female;
    }

    private String name;
    private String surname;
    private Sex sex;
    private String email;

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

    public Person(){

    }

    public Person(String name, String surname, Sex sex, String email) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.email = email;
    }
}
