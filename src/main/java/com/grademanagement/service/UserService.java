package com.grademanagement.service;


import com.grademanagement.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
public interface UserService {
    /**
     * 用户登陆方法
     */
        User login(User user);

    List<User> getUserList();
    @Transactional
    boolean delUser(String id);

    Boolean addUser(User user);

    Boolean modifyUser(User user);

    User findUserById(User user);


    User getUserByName(String username);
}
