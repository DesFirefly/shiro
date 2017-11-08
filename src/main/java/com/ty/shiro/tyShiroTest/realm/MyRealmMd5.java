package com.ty.shiro.tyShiroTest.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealmMd5 extends AuthorizingRealm {

	// 设置realm的名称
		@Override
		public void setName(String name) {
			super.setName("customRealmMd5");
		}

		// 用于认证
		@Override
		protected AuthenticationInfo doGetAuthenticationInfo(
				AuthenticationToken token) throws AuthenticationException {

			// token是用户输入的
			// 第一步从token中取出身份信息
			String userCode = (String) token.getPrincipal();

			// 第二步：根据用户输入的userCode从数据库查询
			// ....

			// 模拟从数据库查询到密码,散列值
			String password = "c66fc24fa70e190479d6c09df6fb3914";
			// 从数据库获取salt
			String salt = "happy";
			//上边散列值和盐对应的明文：123

			// 如果查询到返回认证信息AuthenticationInfo
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
					userCode, password, ByteSource.Util.bytes(salt), this.getName());

			return simpleAuthenticationInfo;
		}

		// 用于授权
		@Override
		protected AuthorizationInfo doGetAuthorizationInfo(
				PrincipalCollection principals) {
			// TODO Auto-generated method stub
			return null;
		}

}
