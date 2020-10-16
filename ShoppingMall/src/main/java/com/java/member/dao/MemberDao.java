package com.java.member.dao;

import java.util.Map;

public interface MemberDao {
	
	public String memberLogin(Map<String,String> map);
	public int memberJoin(Map<String, Object> map);
}
