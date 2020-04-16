package com.wqz.alumniBook.service.Impl;

import com.wqz.alumniBook.bean.Student;
import com.wqz.alumniBook.dao.ClassesDao;
import com.wqz.alumniBook.dao.StudentDao;
import com.wqz.alumniBook.dto.StudentPosition;
import com.wqz.alumniBook.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ClassesDao classesDao;

    @Override
    public Student getStudentBySID(String SID) {
        return studentDao.getStudentBySID(SID);
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    @Override
    public void deleteStudentBySID(String SID) {
        studentDao.deleteStudentBySID(SID);
    }

    @Override
    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    @Override
    public List<Student> findAllStudent() {
        return studentDao.findAllStudent();
    }



    /**
     * 判断根据学生学号，判断下一个学号，上一个学号，是否有下个学号，是否有上个学号
     * @param student
     * @return
     */
    @Override
    public StudentPosition getStudentPosition(Student student) {
        StudentPosition studentPosition=new StudentPosition();
        List<Student> students=classesDao.getStudentsByClassesNum(student.getClassesNum());
        if(students.size()<=1){
            studentPosition.setPosition(-2);
            return studentPosition;
        }
        for(int i=0;i<students.size();i++){
            if(student.getSid().equals(students.get(i).getSid())){
                if(i==0){
                    studentPosition.setPosition(-1);
                    studentPosition.setNextStudentSid(students.get(i+1).getSid());
                }else if(i<students.size()-1){
                    studentPosition.setPosition(0);
                    studentPosition.setNextStudentSid(students.get(i+1).getSid());
                    studentPosition.setPerStudentSid(students.get(i-1).getSid());
                }else {
                    studentPosition.setPosition(1);
                    studentPosition.setPerStudentSid(students.get(i-1).getSid());
                }

            }
        }
        return studentPosition;
    }


}
