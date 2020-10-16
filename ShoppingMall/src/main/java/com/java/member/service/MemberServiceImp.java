package com.java.member.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.member.dao.MemberDao;


@Component
public class MemberServiceImp implements MemberService {

	
	@Autowired
	private MemberDao memberDao;

	@Override
	public void memberLoginOk(ModelAndView mav) {
		// TODO Auto-generated method stub
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request =(HttpServletRequest)map.get("request");
		Map<String, String> hmap=new HashMap<String, String>();
		HttpSession session=request.getSession();
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		hmap.put("id",id);
		hmap.put("pwd",pwd);
		
		
		String value=memberDao.memberLogin(hmap);
		//���� ���� 
		session.setAttribute("id", id);
		session.setAttribute("pwd", pwd);
		session.setAttribute("memberType", value);
				
		mav.addObject("type",value);
		mav.addObject("id",hmap.get("id"));
		System.out.println(value);
		mav.setViewName("member/loginOk");
		
	}

	@Override
	public void memberLogout(ModelAndView mav) {
		// TODO Auto-generated method stub
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request =(HttpServletRequest)map.get("request");
		HttpSession session=request.getSession(false);
		session.invalidate();
		mav.setViewName("member/logout");

	}

	@Override
	public void memberJoinOk(ModelAndView mav) {
		// TODO Auto-generated method stub
		Map<String, Object> map =mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		Map<String, Object> hmap=new HashMap<String, Object>();
		
		int check=0;
		String id = request.getParameter("id"); //기본키
        String pwd=request.getParameter("pwd");
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String addr=request.getParameter("addr");
        int type=1;//일반 회원
        int point=2000;
        
        
        if(id==null|| id.equals("")|| id.equals("null")) {
        	check=0;
        }else if(pwd==null|| pwd.equals("")||pwd.equals("null")) {
        	check=0;
        }else if(name==null|| name.equals("")|| name.equals("null")) {
        	check=0;
        }else if(email==null|| email.equals("")|| email.equals("null")) {
        	check=0;
        }else if(addr==null|| addr.equals("")|| addr.equals("null")) {
        	check=0;
        }else {
	        hmap.put("id",id);
	        hmap.put("pwd",pwd);
		    hmap.put("name",name);
		    hmap.put("email",email);
		    hmap.put("addr",addr);
		    hmap.put("type",type);
		    hmap.put("point",point);    
		    check=memberDao.memberJoin(hmap);
        }
        mav.addObject("check",check);
        mav.setViewName("member/signupOk");
         
        
        
	}
    
	


}
