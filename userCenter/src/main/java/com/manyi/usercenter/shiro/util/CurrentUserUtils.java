package com.manyi.usercenter.shiro.util;

import org.apache.shiro.SecurityUtils;

public class CurrentUserUtils {

	public static Long getCurrentUserId() {
		ShiroUser user = null;
		try {
			user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		} catch (Exception e) {
			user = new ShiroUser(1l, "" , "");
		}
		return user.getId();
	}

	public static String getCurrentUserLoginName() {
		ShiroUser user = null;
		try {
			user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		} catch (Exception e) {
			user = new ShiroUser(1l, "" , "");
		}
		return user.getLoginName();
	}

	public static String getCurrentUserNickName() {
		ShiroUser user = null;
		try {
			user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		} catch (Exception e) {
			user = new ShiroUser(1l, "" , "");
		}
		return user.getNickName();
	}
}
