package com.java.member.service;

import org.springframework.web.servlet.ModelAndView;

public interface MemberService {

	
	public void memberLoginOk(ModelAndView mav);
	public void memberLogout(ModelAndView mav);
	public void memberJoinOk(ModelAndView mav);
	public void memberGetId(ModelAndView mav);
	public void memberGetPwd(ModelAndView mav);
}
