package de.home.vs.rest.model;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private static DataSource instance = null;

    private ArrayList<Student> students = new ArrayList<>();
    private DataSource() {
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }


    public void prefillData() {
        students.add(new Student(123, "John", "Miller"));
        students.add(new Student(444, "Peter", "Schmidt"));

    }


    public ArrayList<Student> getAllStudents() {
        return (ArrayList<Student>) this.students;
    }

    public int getNewStudentId() {
        return this.students.size() + 1;
    }

    public void addStudent(Student s) {
        students.add(s);
    }
}