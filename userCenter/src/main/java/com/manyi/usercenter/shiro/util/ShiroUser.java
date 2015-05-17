package com.manyi.usercenter.shiro.util;

import java.io.Serializable;
import java.util.Objects;

public class ShiroUser implements Serializable {

	private static final long serialVersionUID = -1373760761780840081L;

	public Long id;
	public String loginName;
	public String nickName;

	public ShiroUser(Long id, String loginName, String nickName) {
		this.id = id;
		this.loginName = loginName;
		this.nickName = nickName;
	}

	public ShiroUser(Long id, String loginName) {
		this.id = id;
		this.loginName = loginName;
	}
	
	public Long getId() {
		return id;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getNickName() {
		return nickName;
	}

	@Override
	public String toString() {
		return loginName;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(loginName);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		ShiroUser other = (ShiroUser) obj;
		if(loginName == null) {
			if(other.loginName != null) {
				return false;
			}
		}
		if(!loginName.equals(other.getLoginName())) {
			return false;
		}
		return true;
	}
}