package com.wqz.alumniBook.controller;

import com.google.common.base.Strings;
import com.wqz.alumniBook.annotation.CheckLogin;
import com.wqz.alumniBook.bean.MessageBoard;
import com.wqz.alumniBook.bean.Student;
import com.wqz.alumniBook.bean.User;
import com.wqz.alumniBook.config.RedisKey;
import com.wqz.alumniBook.service.NoticeService;
import com.wqz.alumniBook.service.StudentService;
import com.wqz.alumniBook.service.UserService;
import com.wqz.alumniBook.util.CookieUtil;
import com.wqz.alumniBook.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 用于用户修改个人资料
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private NoticeService noticeService;

    @Resource
    private RedisTemplate<String, Map<String, String>> hashStringRedis;



    /**
     * 检查用户名和密码
     * @param username
     * @param password
     * @param response
     * @return
     */
    @PostMapping(value = "/check-user")
    @ResponseBody
    public String checkUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpServletResponse response) throws IOException {
        User user = userService.getUserByUserName(username);
        if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
            return "用户名或密码格式不正确";
        }
        if (user == null) {
            return "该用户不存在";
        }
        if (user.getPassword().equals(MD5Util.getMD5(password))) {
//            System.out.println(MD5Util.getMD5(password));
            String token = UUID.randomUUID().toString();
            Map<String, String> userMap = new HashMap<>();
            userMap.put(RedisKey.USER_NAME, username);
            userMap.put(RedisKey.IS_ADMIN, String.valueOf(user.getIsAdmin()));
            //放进cookie域
            CookieUtil.addCookie(response, "token", token, 7 * 24 * 3600);
            //放进redis缓存
            hashStringRedis.opsForValue().set(token, userMap, 24 * 7, TimeUnit.HOURS);

            response.sendRedirect("index");
            return null;
        }

        return "用户名或密码错误";
    }


    /**
     * 注册用户请求
     * @param username
     * @param password
     */
    @PostMapping("register-user")
    @ResponseBody
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpServletResponse response,
                               @RequestParam("password1") String password1) throws IOException {
        if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password) || Strings.isNullOrEmpty(password1)) {
            return "请将所有需要填的内容补全";
        }
        if (!password.equals(password1)) {
            return "两次输入密码不一致";
        }

        User user = userService.getUserByUserName(username);
        if (user != null) {
            return "该用户名已经存在";
        }
        User user1  = new User();
        user1.setUsername(username);
        user1.setPassword(MD5Util.getMD5(password.trim()));
        userService.addUser(user1);
        response.sendRedirect("login");
        return "注册成功";
    }

    /**
     * 注册页面
     * @return
     */
    @GetMapping("register")
    public String register() {
        return "register";
    }

    /**
     * 登录页面
     * @return
     */
    @GetMapping("login")
    public String login() {
        return "login";
    }

    /**
     * 注销
     * @return
     */
    @GetMapping("logout")
    public String logout() {

        return "login";
    }


    @GetMapping("user-detail")
    public ModelAndView userPage(HttpServletRequest request){

        ModelAndView indexMV=new ModelAndView("user/user-detail");

        Map<String, String> userMap = hashStringRedis.opsForValue().get(CookieUtil.getCookie(request, "token"));
        //登录用户的学号，或===用户名
        String userSid = userMap.get(RedisKey.USER_NAME);

        User user = userService.getUserByUserName(userSid);
        Student student=studentService.getStudentBySID(userSid);
        indexMV.addObject("student",student);
        indexMV.addObject("user",user);
        indexMV.addObject("admin",userMap.get(RedisKey.IS_ADMIN));

        /*留言*/
        List<MessageBoard> noticeBysender = noticeService.getNoticeBySender(user.getUsername());
        List<MessageBoard> noticeByReceiver = noticeService.getNoticeByReceiver(user.getUsername());
        indexMV.addObject("noticeBysender",noticeBysender);
        indexMV.addObject("noticeByReceiver",noticeByReceiver);

        return indexMV;
    }

    //来到用户修改页面
    @GetMapping("/user/{username}")
    public String toEditPage(@PathVariable("username")String username,Model model){
        //回显
        User user = userService.getUserByUserName(username);
        model.addAttribute("user",user);
        return "user/updateUser";
    }


    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PutMapping("/user")
    @CheckLogin
    public void updateUser(User user,HttpServletResponse response) throws IOException {

        userService.updateUser(user);

        response.sendRedirect("user-detail");

    }


    /**
     * 公告添加
     */
    @GetMapping("addNotice/{username}")
    public String  addNoticPage(@PathVariable("username") String username,Model model) {
        model.addAttribute("sender",username);
        return "notice/add";
    }

    @PostMapping("addNotice")
    public String addNotice(MessageBoard notice){
        System.out.println(notice.getCreateTime().getTime());

        noticeService.addNotice(notice);

        return "redirect:user-detail";

    }

    /**
     * 公告删除
     */
    @DeleteMapping("delNotic/{nid}")
    public String delNotic(@PathVariable("nid") Integer nid) throws IOException {

        noticeService.deleteNoticeById(nid);

        return "redirect:/user-detail";


    }

}
