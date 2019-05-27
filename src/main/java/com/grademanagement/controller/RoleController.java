package com.grademanagement.controller;

import com.grademanagement.mapper.RoleMapper;
import com.grademanagement.pojo.Role;
import com.grademanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("/rolesInfo")
    @ResponseBody
    /**
     * 获取角色的信息
     */
    public List<Role> getRoleInfo(){
        return roleService.getRoleInfo();
    }
}
