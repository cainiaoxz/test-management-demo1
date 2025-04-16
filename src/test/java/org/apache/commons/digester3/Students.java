package org.apache.commons.digester3;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private List<Student> studentList = new ArrayList<>();

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public List<Student> getStudentList() {
        return studentList;
    }
}
