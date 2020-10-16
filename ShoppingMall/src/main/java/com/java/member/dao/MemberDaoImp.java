package com.java.member.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberDaoImp implements MemberDao {

	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public String memberLogin(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("memberLogin", map);
	}

	@Override
	public int memberJoin(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int check=0;
		try {
			return sqlSessionTemplate.insert("memberJoin", map);
		}catch(Exception e) {
			return check;
		}
	}

}
