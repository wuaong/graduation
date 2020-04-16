package com.wqz.alumniBook.controller;

import com.wqz.alumniBook.bean.Classes;
import com.wqz.alumniBook.bean.Student;
import com.wqz.alumniBook.config.RedisKey;
import com.wqz.alumniBook.service.ClassesService;
import com.wqz.alumniBook.service.StudentService;
import com.wqz.alumniBook.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private RedisTemplate<String, Map<String, String>> hashStringRedis;


    /**
     * 班级列表
     * @param request
     * @return
     */
    @GetMapping("classes-list")
    public ModelAndView index(HttpServletRequest request) {

        List<Classes> classess= classesService.findAllClasses();
        ModelAndView indexMV=new ModelAndView("classes/classes-list");
        if(classess.size()==0||classess==null){
            indexMV=new ModelAndView("error");
            indexMV.addObject("errmsg","主页信息获取异常");
            return indexMV;
        }
        Map<String, String> userMap = hashStringRedis.opsForValue().get(CookieUtil.getCookie(request, "token"));
        if(userMap==null){

            indexMV=new ModelAndView("error");
            indexMV.addObject("用户未登陆或登陆已失效","主页信息获取异常");
            return indexMV;
        }
        indexMV.addObject("classess",classess);
        indexMV.addObject("admin",userMap.get(RedisKey.IS_ADMIN));
        return indexMV;
    }

    /**
     * 班级详情页面
     *
     * @param classesNum
     * @param request
     * @return
     */
    @GetMapping("classes-detail/{classesNum}")
    public ModelAndView classesDetail(@PathVariable("classesNum") String classesNum, HttpServletRequest request) {
        List<Student> students = classesService.getStudentsByClassesNum(classesNum);
        Classes classes = classesService.getOneClasses(classesNum);
        if (students == null | students.size() == 0 || classes == null) {
            ModelAndView classesDetailMV = new ModelAndView("error");
            classesDetailMV.addObject("errmsg", "传入的班级信息有误");
            return classesDetailMV;
        }
        Student monitor = studentService.getStudentBySID(classes.getMonitorId());
        ModelAndView classesDetailMV = new ModelAndView("classes/classes-detail");
        if (monitor != null) {
            classesDetailMV.addObject("monitor", monitor);
        }
        classesDetailMV.addObject("students", students);
        classesDetailMV.addObject("classesNum", classesNum);

        Map<String, String> userMap = hashStringRedis.opsForValue().get(CookieUtil.getCookie(request, "token"));
        classesDetailMV.addObject("admin",userMap.get(RedisKey.IS_ADMIN));
        return classesDetailMV;
    }


}
