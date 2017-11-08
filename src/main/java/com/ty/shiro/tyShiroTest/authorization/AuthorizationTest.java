package com.ty.shiro.tyShiroTest.authorization;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * 
 * @author DesFirefly
 * @date 2017年11月8日
 */
public class AuthorizationTest {

	@Test
	public void testPermission() {

		// 从ini文件中创建SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");

		// 创建SecurityManager
		SecurityManager securityManager = factory.getInstance();

		// 将securityManager设置到运行环境
		SecurityUtils.setSecurityManager(securityManager);

		// 创建主体对象
		Subject subject = SecurityUtils.getSubject();

		// 对主体对象进行认证
		// 用户登陆
		// 设置用户认证的身份(principals)和凭证(credentials)
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 用户认证状态
		Boolean isAuthenticated = subject.isAuthenticated();

		System.out.println("用户认证状态：" + isAuthenticated);

		// 用户授权检测 基于角色授权
		// 是否有某一个角色
		System.out.println("用户是否拥有某一个角色：" + subject.hasRole("role1"));
		// 是否有多个角色
		System.out.println("用户是否拥有多个角色：" + subject.hasAllRoles(Arrays.asList("role1", "role2")));
		
//		subject.checkRole("role1");
//		subject.checkRoles(Arrays.asList("role1", "role2"));

		// 授权检测，失败则抛出异常
		// subject.checkRole("role22");

		// 基于资源授权
		System.out.println("是否拥有某一个权限：" + subject.isPermitted("user:delete"));
		System.out.println("是否拥有多个权限：" + subject.isPermittedAll("user:create:1",	"user:delete"));
		
		//检查权限
		subject.checkPermission("user:delete:1");
		subject.checkPermissions("user:create:1","user:delete");
		

	}

}
