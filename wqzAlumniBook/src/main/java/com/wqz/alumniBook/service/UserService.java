package com.wqz.alumniBook.service;


import com.wqz.alumniBook.bean.User;

public interface UserService {

    User getUserByUserName(String username);

    boolean checkUser(String username, String password);

    void addUser(User user);

    void updateUser(User user);
}
