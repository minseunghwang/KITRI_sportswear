package com.java.product.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.notice.dto.NoticeDto;
import com.java.notice.service.NoticeService;
import com.java.product.service.ProductService;

@Component
@Controller
public class ProductController {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	ServletContext servletContext;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value="/product/main.do")
	public ModelAndView productMain(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		productService.getNewProducts(mav,3);
		List<NoticeDto> listnotices = noticeService.getNoticeheader(mav);
		servletContext.setAttribute("listnotices", listnotices);
		return mav;
	}
	
	@RequestMapping(value="/product/category.do")
	public ModelAndView proudctCategory(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		productService.getCategoryProducts(mav);
		return mav;
	}
	
	@RequestMapping(value="/product/productDetail.do")
	public ModelAndView productDetail(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		productService.getProductDetail(mav);
		return mav;
	}
}
