package com.grademanagement.realm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import com.grademanagement.pojo.Role;
import com.grademanagement.pojo.User;
import com.grademanagement.service.RoleService;
import com.grademanagement.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DELL
 *doGetAuthenticationInfo
 */
public class UserRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Override
	protected void clearCache(PrincipalCollection principals) {
		//获取到认证之后的信息
		PrincipalCollection collection = SecurityUtils.getSubject().getPrincipals();
		//手动调用clearCache方法进行 清空缓存操作
		super.clearCache(collection);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取登录时输入的用户名
		String username = principals.getPrimaryPrincipal().toString();
		System.out.println(username+"  has been AuthorizationInfo");
		if(username!=null){
			//获取用户信息
			  User user=userService.getUserByName(username);

			List<Role> roles = roleService.selectRolesByUID(user.getId().intValue());

			SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

//			for(Role role:roles){
//				info.addRole(role.getName());//加入角色
//				System.out.println(role.getName());
//			}
            if(roles!=null&&!roles.isEmpty()){
                for(Role role:roles){
                    info.addRole(role.getName());//加入角色
					System.out.println(role.getName());
                }
            }
			return info;
		}
		return null;
	}

	@Override
	public String getName() {
		return "userRealm";
	}
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String  username = (String) token.getPrincipal();//获取身份信息
		System.out.println(username);
		System.out.println("获取用户输入的用户名======================="+username);
		//通过用户名获取到用户的密码信息
		//根据用户名到数据库查询密码信息

		User user1 = userService.getUserByName(username);
		System.out.println(user1.getPassword());
//		//将从数据库中查询到的信息封装到SimpleAuthenticationInfo
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(username,user1.getPassword(),getName());
//
//		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(username, user1.getPassword(), ByteSource.Util.bytes(user1.getPassword_salt().getBytes()), getName());
		return info;
	}

}
