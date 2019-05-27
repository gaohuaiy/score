package com.grademanagement.service;

import com.grademanagement.pojo.Role;
import com.grademanagement.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService {
    public List<Role> getRoleInfo();
    List<Role> selectRolesByUID(int id);
}
