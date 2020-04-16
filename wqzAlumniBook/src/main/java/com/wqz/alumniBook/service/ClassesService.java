package com.wqz.alumniBook.service;


import com.wqz.alumniBook.bean.Classes;
import com.wqz.alumniBook.bean.Student;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClassesService {

    List<Classes> findAllClasses();

    void addOneClasses(Classes classes);

    void updateOneClasses(Classes classes);

    void deleteOneClasses(String classesNum);

    Classes getOneClasses(String classesNum);

    List<Student> getStudentsByClassesNum(String classesNum);
}
