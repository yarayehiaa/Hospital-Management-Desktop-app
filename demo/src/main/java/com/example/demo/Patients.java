package com.example.demo;

public class Patients {
    private String name;
    private int age;
    private int firstTime;
    private String symptoms;
    private String gender;

    public Patients(String name, int age, int firstTime, String symptoms, String gender) {
        this.name = name;
        this.age = age;
        this.firstTime = firstTime;
        this.symptoms = symptoms;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getFirstTime() {
        return firstTime;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstTime(int firstTime) {
        this.firstTime = firstTime;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
