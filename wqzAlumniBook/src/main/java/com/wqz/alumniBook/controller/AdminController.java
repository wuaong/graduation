package com.wqz.alumniBook.controller;

import com.wqz.alumniBook.annotation.CheckLogin;
import com.wqz.alumniBook.bean.Classes;
import com.wqz.alumniBook.bean.Student;
import com.wqz.alumniBook.config.RedisKey;
import com.wqz.alumniBook.service.ClassesService;
import com.wqz.alumniBook.service.NoticeService;
import com.wqz.alumniBook.service.StudentService;
import com.wqz.alumniBook.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private RedisTemplate<String, Map<String,String>> hashStringRedis;


    private String isAdminToken(HttpServletRequest request){
        Map<String,String> userMap = hashStringRedis.opsForValue().get(CookieUtil.getCookie(request, "token"));
        return userMap.get(RedisKey.IS_ADMIN);
    }


    /**
     * 公告删除
     */
    @DeleteMapping("delNotic/{nid}")
    public String  delNotic(@PathVariable("nid") Integer nid){

        noticeService.deleteNoticeById(nid);
        return "redirect:/notice";
    }

    /**
     *学生管理页面
     * @param classesNum
     * @return
     */
    @GetMapping("classes-student-manage/{classesNum}")
    @CheckLogin
    public ModelAndView adminDetail(@PathVariable("classesNum") String classesNum,HttpServletRequest request) {

        //获取本班所有学生
        classesNum = classesNum.trim();
        List<Student> students = classesService.getStudentsByClassesNum(classesNum);
        ModelAndView studentDetailMV = new ModelAndView("classes/classes-student-manage");
        studentDetailMV.addObject("students", students);
        studentDetailMV.addObject("classesNum", classesNum);
        studentDetailMV.addObject("admin",isAdminToken(request));
        return studentDetailMV;
    }

    //来到学生添加页面
    @GetMapping("student")
    public String toAddStudentPage(HttpServletRequest request,Model model){

        model.addAttribute("admin",isAdminToken(request));

        return "student/add";
    }

    /**
     * 添加学生
     *SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
     */
    @PostMapping("student")
    @CheckLogin
    public String addStudent(Student student,Model model,HttpServletResponse response,HttpServletRequest request) throws IOException {
        System.out.println(student);
        model.addAttribute("errMsg","该学生信息已存在");

        if (studentService.getStudentBySID(student.getSid()) != null) {
            model.addAttribute("admin",isAdminToken(request));
            return "error";
        }
        studentService.addStudent(student);

        String jumpUrl = "/admin/classes-student-manage/"+student.getClassesNum();
        response.sendRedirect(jumpUrl);
        return null;
    }

    //来到修改页面，查出当前学生，在页面回显
    @GetMapping("/student/{sid}")
    public String toEditStudentPage(@PathVariable("sid") String sid, Model model,HttpServletRequest request){
        Student student = studentService.getStudentBySID(sid);
        model.addAttribute("student",student);
        model.addAttribute("admin",isAdminToken(request));
        //回到修改页面(add是一个修改添加二合一的页面);
        return "student/add";
    }

    /**
     *更新学生信息
     * @param student
     * @return
     */
    @PutMapping("student")
    @CheckLogin
    public String updateStudent(Student student,HttpServletResponse response,Model model,HttpServletRequest request) throws IOException {
        //更新学生信息
        Student s = studentService.getStudentBySID(student.getSid());

        if(s==null){
            model.addAttribute("errMsg","该学生信息不存在");
            return "error";
        }
        System.out.println(student+"//////");
        studentService.updateStudent(student);

        String jumpUrl = "/admin/classes-student-manage/"+student.getClassesNum();
        response.sendRedirect(jumpUrl);

        model.addAttribute("admin",isAdminToken(request));
        return null;
    }

    /**
     * 删除学生信息
     */
    @CheckLogin
    @DeleteMapping("student/{sid}")
    public void deleteStudent(@PathVariable("sid") String sid,HttpServletResponse response) throws IOException {
        Student student = studentService.getStudentBySID(sid);
        System.out.println(student);

        studentService.deleteStudentBySID(sid);
        String jumpUrl = "/admin/classes-student-manage/"+student.getClassesNum();
        response.sendRedirect(jumpUrl);


    }



    /**
     * 班级管理页面
     *
     * @return
     */
    @GetMapping("classes-manage")
    @CheckLogin
    public ModelAndView getAllClasses(HttpServletRequest request) {
        List<Classes> classes = classesService.findAllClasses();
        Map<String,String> userMap = hashStringRedis.opsForValue().get(CookieUtil.getCookie(request, "token"));
        ModelAndView classesMV = new ModelAndView("classes/classes-manage");
        classesMV.addObject("classess", classes);
        classesMV.addObject("admin",userMap.get(RedisKey.IS_ADMIN));
        return classesMV;
    }


    //来到班级添加页面
    @GetMapping("/classes")
    public String toAddPage(){
        return "classes/add";
    }

    /**
     * 添加班级
     *SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
     * @param classes
     * @return
     */
    @PostMapping("classes")
    @CheckLogin
    public String addClasses(Classes classes) {
            System.out.println(classes);
            classesService.addOneClasses(classes);
            return "redirect:/admin/classes-manage";
    }


    //来到修改页面，查出当前班级，在页面回显
    @GetMapping("/classes/{classesNum}")
    public String toEditPage(@PathVariable("classesNum") String classesNum, Model model){
        Classes classes1= classesService.getOneClasses(classesNum);

        model.addAttribute("classes",classes1);
        //回到修改页面(add是一个修改添加二合一的页面);
        return "classes/add";
    }


    /**
     * 更新班级信息
     *
     * @param classes
     * @return
     */
    @PutMapping("/classes")
    @CheckLogin
    public void updateClasses(Classes classes,HttpServletResponse response) throws IOException {

            Classes classes1= classesService.getOneClasses(classes.getClassesNum());
            classes1.setClassesName(classes.getClassesName());
            classes1.setMonitorId(classes.getMonitorId());
            classesService.updateOneClasses(classes1);
            response.sendRedirect("/admin/classes-manage");

    }

    /**
     * 删除班级信息
     */
    @CheckLogin
    @DeleteMapping("classes/{classesNum}")
    public String deleteClasses(@PathVariable("classesNum") String classesNum) throws IOException {

        classesService.deleteOneClasses(classesNum);
        return "redirect:/admin/classes-manage";

    }



}


