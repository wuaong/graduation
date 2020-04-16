package com.wqz.alumniBook.service.Impl;


import com.wqz.alumniBook.bean.User;
import com.wqz.alumniBook.dao.UserDao;
import com.wqz.alumniBook.service.UserService;
import com.wqz.alumniBook.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public  boolean checkUser(String username, String password){
        User user=userDao.getUserByUserName(username);
        password= MD5Util.getMD5(password);
        System.out.println(password);
        if(user!=null&&password.equals(user.getPassword())){
            return  true;
        }else {
            return  false;
        }
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User getUserByUserName(String username) {
        return userDao.getUserByUserName(username);
    }
}
