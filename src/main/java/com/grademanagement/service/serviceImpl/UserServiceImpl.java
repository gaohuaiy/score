package com.grademanagement.service.serviceImpl;

import com.fasterxml.jackson.databind.node.BooleanNode;
import com.grademanagement.mapper.UserMapper;
import com.grademanagement.mapper.UserRoleMapper;
import com.grademanagement.pojo.User;
import com.grademanagement.pojo.UserExample;
import com.grademanagement.pojo.UserRoleExample;
import com.grademanagement.pojo.UserRoleKey;
import com.grademanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    /*
    根据用户名，密码查询用户
     */
    @Override
    public User login(User user) {

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(user.getName());
        criteria.andPasswordEqualTo(user.getPassword());
        List<User> userList = userMapper.selectByExample(userExample);
        return userList.get(0);
    }
    /*
    查询所有用户
     */
    @Override
    public List<User> getUserList() {
        return userMapper.selectByExample(new UserExample());
    }
/*
根据用户id进行删除用户
 */
    @Override
    public boolean delUser(String id) {
        UserRoleExample userRoleExample=new UserRoleExample();
        UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
        criteria.andUidEqualTo(Long.valueOf(id));
        userRoleMapper.deleteByExample(userRoleExample);
        return userMapper.deleteByPrimaryKey(Long.valueOf(id))>0?true:false;
    }

    @Override
    public Boolean addUser(User user) {
        int insert = userMapper.insert(user);
        return insert>0?true:false;
    }

    @Override
    public Boolean modifyUser(User user) {

        int i = userMapper.updateByPrimaryKey(user);
        System.out.println(i+"========="+user);
        return i>0?true:false;
    }

    @Override
    public User findUserById(User user) {
        User user1 = userMapper.selectByPrimaryKey(user.getId());
        return user1;
    }

    @Override
    public User getUserByName(String username) {

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        System.out.println(userList.get(0));
        return userList.get(0);
    }
}
