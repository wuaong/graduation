package com.wqz.alumniBook.dao;


import com.wqz.alumniBook.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserDao {
        User getUserByUserName(@Param("username") String username);

        void updateUser(User user);

        void deteleUserById(String userId);

        void addUser(User user);

        List<User> findAllUser();

}
