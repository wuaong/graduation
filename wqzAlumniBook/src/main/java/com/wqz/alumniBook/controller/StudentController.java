package com.wqz.alumniBook.controller;


import com.google.common.base.Strings;
import com.wqz.alumniBook.bean.Classes;
import com.wqz.alumniBook.bean.Student;
import com.wqz.alumniBook.config.RedisKey;
import com.wqz.alumniBook.dto.StudentPosition;
import com.wqz.alumniBook.service.ClassesService;
import com.wqz.alumniBook.service.StudentService;
import com.wqz.alumniBook.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private RedisTemplate<String, Map<String,Integer>> hashIntegerRedis;

    @Autowired
    private ClassesService classesService;


    /**
     * 学生详情页面
     * @param sid
     * @return
     */
    @GetMapping("student-detail/{sid}")
    public String studentDetail(@PathVariable("sid") String sid, Model model, HttpServletRequest request){
        if(Strings.isNullOrEmpty(sid)){
            model.addAttribute("errmsg","请求参数有误");
            return "error";
        }
        Student student=studentService.getStudentBySID(sid);
        if(student==null){
            model.addAttribute("errmsg","该学生不存在");
            return "error";
        }
        //判断是不是本人，是本人才可以进行修改
        String userSid= String.valueOf(hashIntegerRedis.opsForValue().
                get(HttpUtils.getCookieByName(request,"token").getValue()).get(RedisKey.USER_NAME));
        if(sid.equals(userSid)){
            model.addAttribute("isSelf","1");
        }

        StudentPosition studentPosition= studentService.getStudentPosition(student);
        model.addAttribute("studentPosition",studentPosition);
        model.addAttribute("student",student);
        System.out.println(student);
        return "student/student-detail";
    }


    /**
     * 更新学生信息
     *
     * @return
     */
    @GetMapping("update-info/{sid}")
    public String toUpdatePage(@PathVariable("sid") String sid,Map map,Model model) {

        Student stu=studentService.getStudentBySID(sid);
        List<Classes> allClasses = classesService.findAllClasses();
        //用于回显
        model.addAttribute("student",stu);
        model.addAttribute("classess",allClasses);
        if(stu==null){
            map.put("errmsg","用户出错");
            return "error";
        }

        return "student/student";
    }

    @PutMapping("update-info")
    @ResponseBody
    public void updateInfo(Student student, HttpServletResponse response) throws IOException {
        studentService.updateStudent(student);
        System.out.println(student);
        String url = "student-detail/"+student.getSid();
        response.sendRedirect(url);
        System.out.println(url);
    }




}
