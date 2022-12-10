package com.example.studentApp.model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    private static final Model _instance = new Model();

    public static Model instance(){
        return _instance;
    }
    private Model(){
        for (int i=0; i<5; i++) {
            addStudent(new Student("name "+i, ""+i, "phone", "address", true));
        }
    }

    List<Student> data = new LinkedList<>();

    public List<Student> getAllStudents(){
        return data;
    }

    public void addStudent(Student st){
        data.add(st);
    }

    public void updateStudent(Student oldSt, Student newSt){
        data.remove(oldSt);
        data.add(newSt);
    }

    public void deleteStudent(Student st){
        data.remove(st);
    }


}
