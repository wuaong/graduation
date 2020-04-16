package com.wqz.alumniBook.service;

import com.wqz.alumniBook.bean.Student;
import com.wqz.alumniBook.dto.StudentPosition;
import java.util.List;

public interface  StudentService {

    Student getStudentBySID(String SID);

    void updateStudent(Student student);

    void deleteStudentBySID( String SID);

    void addStudent(Student student);

    List<Student> findAllStudent();

    StudentPosition getStudentPosition(Student student);
}
