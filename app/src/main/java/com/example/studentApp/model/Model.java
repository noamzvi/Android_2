package com.example.studentApp.model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    private static final Model _instance = new Model();

    public static Model instance(){
        return _instance;
    }
    private Model(){
        addStudent(new Student("Noam", "1", "0534567383", "hahatzav 9", true));
        addStudent(new Student("Shir", "2", "0523425678", "hakinor 12", true));
        addStudent(new Student("Dani", "3", "0508763582", "hamania 54", false));
        addStudent(new Student("Yossi", "4", "0548763846", "rabi yosef 2", false));
        addStudent(new Student("Or", "5", "0524376524", "calanit 19", true));
    }

    List<Student> data = new LinkedList<>();

    public List<Student> getAllStudents(){
        return data;
    }

    public Student getStudentById(String id) {
        for (Student st : data) {
            if(st.id.equals(id)) {
                return st;
            }
        }

        return null;
    }

    public void addStudent(Student st){
        data.add(st);
    }

    public void updateStudent(Student oldSt, Student newSt){
        int i = data.indexOf(oldSt);
        if(i != -1) {
            data.set(i, newSt);
        }
    }

    public void deleteStudent(Student st){
        if(data.contains(st)) {
            data.remove(st);
        }
    }

}
