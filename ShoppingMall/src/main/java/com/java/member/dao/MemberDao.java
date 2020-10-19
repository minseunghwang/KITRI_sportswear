package com.java.member.dao;

import java.util.Map;

public interface MemberDao {
	
	public String memberLogin(Map<String,String> map);
	public int memberJoin(Map<String, Object> map);
	public String memberGetId(Map<String,String> map);
	public String memberGetPwd(Map<String,String> map);
	public int memberIdCheck(Map<String, String> map);
	
	public String memberGetPoint(Map<String,String> map);
}
