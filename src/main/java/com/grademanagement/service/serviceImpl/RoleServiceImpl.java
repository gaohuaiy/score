package com.grademanagement.service.serviceImpl;

import com.grademanagement.mapper.RoleMapper;
import com.grademanagement.pojo.Role;
import com.grademanagement.pojo.RoleExample;
import com.grademanagement.pojo.User;
import com.grademanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService{
   @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> getRoleInfo() {
        return roleMapper.selectByExample(new RoleExample());
    }

    @Override
    public List<Role> selectRolesByUID(int id) {
        return roleMapper.selectRolesByUID(id);

    }
}
