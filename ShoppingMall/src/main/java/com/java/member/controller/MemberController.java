package com.java.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.member.service.MemberService;

@Component
@Controller
public class MemberController {

	
	@Autowired
	private MemberService memberService;
	
	
//	@RequestMapping(value="/member/delete.do")
//	public ModelAndView deleteMember(HttpServletRequest request, HttpServletResponse response) {
//		
//		System.out.println("OK.");
//		ModelAndView mav=new ModelAndView();
//		
//		return mav;
//	}
	
	
//	public ModelAndView IdCheck(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav=new ModelAndView();
//		
//		return mav;
//	}
	
//	
//	public ModelAndView joinMember(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav=new ModelAndView();
//		
//		return mav;
//	}
//	
	
	@RequestMapping(value="/member/login.do")
	public ModelAndView memberLogin(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("member/login");
	}
	
	@RequestMapping(value="/member/loginOk.do")
	public ModelAndView memberLoginOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);

		memberService.memberLoginOk(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/member/logout.do")
	public ModelAndView memberLogout(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);
		memberService.memberLogout(mav);
		return mav;
	}
	
	@RequestMapping(value="/member/join.do")
	public ModelAndView memberJoin(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/signup");
	}
	
	@RequestMapping(value="/member/joinOk.do")
	public ModelAndView memberJoinOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);
		memberService.memberJoinOk(mav);
		return mav;
	}
	
	
//	
//	public ModelAndView mypage(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav=new ModelAndView();
//		
//		return mav;
//	}
//	
//	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav=new ModelAndView();
//		
//		return mav;
//	}
//	
//	public ModelAndView searchId(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav=new ModelAndView();
//		
//		return mav;
//	}
//	
//	public ModelAndView searchPwd(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav=new ModelAndView();
//		
//		return mav;
//	}
//	


}
