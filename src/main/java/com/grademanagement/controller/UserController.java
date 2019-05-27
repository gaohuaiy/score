package com.grademanagement.controller;

import com.grademanagement.controller.util.MD5;
import com.grademanagement.pojo.User;
import com.grademanagement.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value="/{path}")
    public  String pathParam(@PathVariable String path){
        return path;
    }
    @RequestMapping("/doAdd")
    public String doAdd(User user) {
        ModelAndView modelAndView = new ModelAndView();
        boolean isSuccess=userService.addUser(user);
        return "redirect:info";
    }
    @RequestMapping("/login")
    public String login(User user, HttpSession session){
        String error;
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getName().trim(), user.getPassword().trim());
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (AuthenticationException e) {
            //其他错误，比如锁定，如果想单独处理请单独catch处理
            error = "其他错误：" ;
            System.out.println(error);
        }catch (Exception e) {
            System.out.println("密码或用户名错误");
        }
        boolean authenticated = subject.isAuthenticated();
        System.out.println(authenticated+"====================");
        if (authenticated) {
            session.setAttribute("curUser",user);
            return "main";
        }

        return "redirect:/";

    }

    @RequiresRoles("admin")
//    @RequiresAuthentication
    @RequestMapping("/info")
    @ResponseBody
    public ModelAndView getInfo(){
        ModelAndView modelAndView=new ModelAndView();
        List<User> users = userService.getUserList();
        modelAndView.addObject("users",users);
        modelAndView.setViewName("info");
        return modelAndView;
    }
    @RequestMapping("/del")
    public  String delById(String id, HttpServletRequest request){
        userService.delUser(id);
        return "redirect:info";
    }


    /**
     * 添加用户与其角色信息
     */
    @ResponseBody
    @RequestMapping(value="/addUser" )
    public Boolean addUser(User user){
        //将从前端页面的用户的密码进行加密，存放进入数据库中
        String password = user.getPassword();
        user.setPassword(MD5.toPassWord(password));
        boolean b = userService.addUser(user);
        return b;
    }
    @ResponseBody
    @RequestMapping(value="/modify" )
    public String modifyUser(User user){
        String password = user.getPassword();
        user.setPassword(MD5.toPassWord(password));
        userService.modifyUser(user);
        return "Modify Succeed!";
    }
    @ResponseBody
    @RequestMapping(value="/findById" )
    public User findById(User user){
        User users = userService.findUserById(user);
        return users;
    }

}
