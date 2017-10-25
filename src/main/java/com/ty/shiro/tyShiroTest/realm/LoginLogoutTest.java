package com.ty.shiro.tyShiroTest.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author DesFirefly
 * @date 2017年10月25日
 * 再次测试登录认证
 */

public class LoginLogoutTest {

	@Test
	public void testHelloworld(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		 SecurityManager securityManager =factory.getInstance();
		 SecurityUtils.setSecurityManager(securityManager);
		 
		 Subject subject = SecurityUtils.getSubject();
		 UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
		 
		 try{
			 subject.login(token);
			 System.out.println("yanzhengchenggong");
		 } catch (AuthenticationException e){
			 System.out.println("yanzhengshibai");
		 }
		 
		 Assert.assertEquals(true, subject.isAuthenticated());
		 
		 subject.logout();
	}
}
