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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(this.id.equals(((Student)obj).id)) {
            return true;
        }
        return false;
    }
}