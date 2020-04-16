package com.wqz.alumniBook.dao;


import com.wqz.alumniBook.bean.Classes;
import com.wqz.alumniBook.bean.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesDao {

    List<Classes> findAllClasses();

    void addOneClasses(Classes classes);

    void updateOneClasses(Classes classes);

    void deleteOneClasses(String classesNum);

    Classes getOneClasses(String classesNum);

    List<Student> getStudentsByClassesNum(String classesNum);

}
