package com.grademanagement.service.serviceImpl;

import com.grademanagement.controller.util.MD5;
import com.grademanagement.mapper.UserMapper;
import com.grademanagement.pojo.ExcelDto;
import com.grademanagement.pojo.User;
import com.grademanagement.pojo.UserExample;
import com.grademanagement.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StuServiceImpl implements StuService {
    @Autowired
    private UserMapper userMapper;


    /**
     * 获取当前用户的所有的课程及授课教师信息
     *
     * @param user 当前登陆系统的用户
     * @return
     */
    @Override
    @Transactional
    public List<ExcelDto> getCourseIno(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(user.getName());
        criteria.andPasswordEqualTo(MD5.toPassWord(user.getPassword()));
        List<User> users = userMapper.selectByExample(userExample);
//        users.forEach(u->{
//            System.out.println(u.getName()+u.getPassword()+u.getId());
//        });
                User user1 = users.get(0);
        return userMapper.getCourseInfo(user1.getId());
    }
}
