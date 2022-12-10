package com.example.studentApp.model;

public class Student {
    public String name;
    public String id;
    public String phone;
    public String address;
    public Boolean cb;

    public Student(String name, String id, String phone, String address, Boolean checked) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.cb = checked;
    }
}