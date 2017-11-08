package com.ty.shiro.tyShiroTest.strategy;

import org.apache.shiro.authc.credential.HashingPasswordService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.omg.CORBA.StringHolder;

/**
 * 
 * @author DesFirefly
 * @date 2017年11月8日
 * +salt MD5散列加密
 */

public class MD5Test {
	
	public static void main(String[] args) {
	    String source = "123";
	    String salt = "happy";
		int hashIterations = 2;
		
		Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations); 
		//hashIterations参数是hash次数：例如hash两次是hash(hash());
		String password_md5 = md5Hash.toString();
		System.out.println(password_md5);
		
		SimpleHash simpleHash = new SimpleHash("md5", source, salt, hashIterations);
		System.out.println(simpleHash.toString());
	}
}
