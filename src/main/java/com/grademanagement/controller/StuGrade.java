package com.grademanagement.controller;

import com.grademanagement.pojo.ExcelDto;
import com.grademanagement.pojo.User;
import com.grademanagement.service.StuService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/stu")
public class StuGrade {
    @Autowired
    private StuService stuService;
    @RequestMapping(value="/{path}")
    public  String pathParam(@PathVariable String path){
        return path;
    }
    @RequestMapping("/mygrade")
    @ResponseBody
    public List<ExcelDto> getGrade(HttpSession session){
        //获取当前登陆的用户的信息
        User curUser = (User) session.getAttribute("curUser");
        System.out.println(curUser.getName()+ curUser.getPassword()+"=============");
        List<ExcelDto> courseIno = stuService.getCourseIno(curUser);
//        courseIno.forEach(course->{
//            System.out.println(course);
//        });
        return courseIno;
    }
}
