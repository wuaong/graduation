package com.wqz.alumniBook.service.Impl;

import com.wqz.alumniBook.bean.Classes;
import com.wqz.alumniBook.bean.Student;
import com.wqz.alumniBook.dao.ClassesDao;
import com.wqz.alumniBook.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesDao classesDao;

    public List<Classes> findAllClasses(){
        return classesDao.findAllClasses();
    }

    @Override
    public void addOneClasses(Classes classes) {
        classesDao.addOneClasses(classes);
    }

    @Override
    public void updateOneClasses(Classes classes) {
        classesDao.updateOneClasses(classes);
    }

    @Override
    public void deleteOneClasses(String classesNum) {
        classesDao.deleteOneClasses(classesNum);
    }

    @Override
    public Classes getOneClasses(String classesNum) {
        return classesDao.getOneClasses(classesNum);
    }

    @Override
    public List<Student> getStudentsByClassesNum(String classesNum) {
        return classesDao.getStudentsByClassesNum(classesNum);
    }
}
