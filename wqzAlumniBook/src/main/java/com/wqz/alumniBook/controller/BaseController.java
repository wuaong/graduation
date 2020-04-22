package com.wqz.alumniBook.controller;


import com.wqz.alumniBook.bean.MessageBoard;
import com.wqz.alumniBook.bean.Student;
import com.wqz.alumniBook.config.RedisKey;
import com.wqz.alumniBook.service.NoticeService;
import com.wqz.alumniBook.service.StudentService;
import com.wqz.alumniBook.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BaseController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private RedisTemplate<String, Map<String, String>> hashStringRedis;


    /**
     * 403页面
     * @return
     */
    @GetMapping("/403")
    public String is403(){
        System.out.println("------没有权限-------");
        return "403";
    }

    /**
     * 404页面
     * @return
     */
    @GetMapping("/404")
    public String is404(){
        System.out.println("------页面不存在-------");
        return "404";
    }


    /**
     * 首页
     * @param request
     * @return
     */
    @GetMapping("index")
    public ModelAndView index(HttpServletRequest request) {

        ModelAndView indexMV=new ModelAndView("index");

        Map<String, String> userMap = hashStringRedis.opsForValue().get(CookieUtil.getCookie(request, "token"));
        if(userMap==null){
            indexMV=new ModelAndView("error");
            indexMV.addObject("errmsg","主页信息获取异常");
            return indexMV;
        }

        indexMV.addObject("admin",userMap.get(RedisKey.IS_ADMIN));
        return indexMV;
    }

    @GetMapping("/album")
    public ModelAndView album(HttpServletRequest request){
        ModelAndView indexMV=new ModelAndView("album");
        Map<String, String> userMap = hashStringRedis.opsForValue().get(CookieUtil.getCookie(request, "token"));
        indexMV.addObject("admin",userMap.get(RedisKey.IS_ADMIN));
        return indexMV;
    }


    @GetMapping("/notice")
    public ModelAndView notice(HttpServletRequest request){

        List<MessageBoard> notices=noticeService.getAllNotice();
        ModelAndView modelAndView = new ModelAndView("notice");
        modelAndView.addObject("notices",notices);

        Map<MessageBoard, Student[]> map1 = new HashMap<>();
        for (MessageBoard notice:notices){
            Student sender= studentService.getStudentBySID(notice.getSender());
            Student receiver = studentService.getStudentBySID(notice.getReceiver());
            Student[] studentSR = {sender,receiver};
            map1.put(notice,studentSR);
        }
        modelAndView.addObject("map1",map1);

        Map<String, String> userMap = hashStringRedis.opsForValue().get(CookieUtil.getCookie(request, "token"));
        modelAndView.addObject("admin",userMap.get(RedisKey.IS_ADMIN));
      /*  System.out.printf(userMap.get(RedisKey.USER_NAME));
        System.out.println(userMap.get(RedisKey.IS_ADMIN));*/
        return modelAndView;

    }
}
