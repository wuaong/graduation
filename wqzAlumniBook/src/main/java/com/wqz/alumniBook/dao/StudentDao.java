package com.wqz.alumniBook.dao;


import com.wqz.alumniBook.bean.Student;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface StudentDao {
    Student getStudentBySID(String SID);

    void updateStudent(Student student);

    void deleteStudentBySID(String SID);

    void addStudent(Student student);

    List<Student> findAllStudent();
}
