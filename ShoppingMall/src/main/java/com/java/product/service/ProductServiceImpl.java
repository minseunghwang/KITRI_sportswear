package com.java.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.common.ProductImageVO;
import com.java.common.ProductSizeVO;
import com.java.pagenation.dto.PaginationDto;
import com.java.product.dao.ProductDao;
import com.java.product.dto.ProductDto;
import com.java.review.dao.ReviewDao;
import com.java.review.dto.ReviewDto;


@Component
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ReviewDao reviewDao;
	
	
	@Override
	public void getProductAll(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		List<ProductDto> mainpageItem = productDao.selectAll();
		System.out.println(mainpageItem);
		System.out.println(mainpageItem.size());
		mav.addObject("mainpageItem",mainpageItem);
		mav.setViewName("main/main");
	}
	
	@Override
	public void getNewProducts(ModelAndView mav, int numberItems) {
		Map<String, Object> map = mav.getModelMap();
		List<ProductDto> newProducts = productDao.selectNewProducts(numberItems);
		mav.addObject("newProducts", newProducts);
		mav.setViewName("main/main");
	}
	
	@Override
	public ArrayList<ProductSizeVO> getProductsSizeAll(int p_num) {
		return productDao.selectSizeAll(p_num);
	}
	
	@Override
	public ArrayList<ProductDto> getProductManagementByPageNum(int page) {
		return productDao.selectAllByPageNum(page);
	}

	@Override
	public void getBestProducts(ModelAndView mav, int numberItems) {
	}

	@Override
	public void getCategoryProducts(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String category = request.getParameter("category");
		int page = Integer.parseInt(request.getParameter("page"));
		
		String orderBy = request.getParameter("orderBy");
		if(request.getParameter("orderBy") == "") {
			orderBy = null;
		}
		List<ProductDto> categoryProducts = productDao.selectCategoryProducts(category);
		List<ProductDto> products = new ArrayList<ProductDto>();
		
		if(orderBy == null) {
			products = productDao.selectCategoryProductsByPageNum(category,page);
		} else {
			products = productDao.selectCategoryProductsSort(category,page,orderBy);
		}
		
		for(ProductDto product:products) {
			ArrayList<ReviewDto> reviews = (ArrayList<ReviewDto>) reviewDao.selectByP_Num(product.getNum());
			product.setReviews(reviews);
		}
		
		request.setAttribute("products", products);
		
		PaginationDto pn = new PaginationDto();
		
		pn.setPage(page);
		pn.setCountList(8);
		pn.setCountPage(3);
		
		pn.setTotalCount(categoryProducts.size());
		
		pn.setTotalPage(pn.getTotalCount() / pn.getCountList());
		if(pn.getTotalCount() % pn.getCountList() > 0) {	// ex) 총 상품 35개, 한 페이지에 8개 표시 :: 4개의 페이지(8개 상품) + 1페이지(3개 상품)
			pn.setTotalPage(pn.getTotalPage() + 1);
		}
		
		if(pn.getTotalPage() < pn.getPage()) {
			pn.setPage(pn.getTotalPage());
		}
		
		pn.setStartPage((pn.getPage() - 1) / pn.getCountPage() * pn.getCountPage() + 1);
		pn.setEndPage(pn.getStartPage() + pn.getCountPage() - 1);
		
		if(pn.getEndPage() > pn.getTotalPage()) {
			pn.setEndPage(pn.getTotalPage());
		}
		
//		request.setAttribute("pn", pn);
		mav.addObject("pn", pn);
		mav.setViewName("product/categoryList");
	}
	
	@Override
	public ArrayList<ProductDto> getCategoryProductsByPageNum(String category, int page) {
		return (ArrayList<ProductDto>) productDao.selectCategoryProductsByPageNum(category, page);
	}
	
	@Override
	public ArrayList<ProductDto> getCategoryProductsSort(String category, int page, String orderBy) {
		return (ArrayList<ProductDto>) productDao.selectCategoryProductsSort(category, page, orderBy);
	}

	@Override
	public ArrayList<ProductDto> getKeywordProductsByPageNum(String keyword, int page) {
		return productDao.selectKeywordProductsByPageNum(keyword, page);
	}

	@Override
	public ArrayList<ProductDto> getKeywordProductsSort(String keyword, int page, String orderBy) {
		return productDao.selectKeywordProductsSort(keyword, page, orderBy);
	}
	
	@Override
	public ArrayList<ProductImageVO> getDetailImgAll(int p_num) {
		return productDao.selectDetailImages(p_num);
	}

	@Override
	public int checkQuantity(int productNum, String size) {
		return productDao.selectQuantity(productNum, size);
	}
	
	@Override
	public int makeProductNum() {
		return productDao.selectProductNum();
	}

	@Override
	public int makeProductImgNum() {
		return productDao.selectProductImgNum();
	}

	@Override
	public int makeProductSizeNum() {
		return productDao.selectProductSizeNum();
	}
	
	@Override
	public void add(ProductDto p) {
		productDao.insert(p);
	}	
	
	@Override
	public void add(ProductImageVO pi) {
		productDao.insert(pi);
	}
	
	@Override
	public void add(ProductSizeVO ps) {
		productDao.insert(ps);
	}

	@Override
	public ProductDto getProduct(int num) {
		return productDao.select(num);
	}

	@Override
	public void addQuantity(ProductSizeVO ps) {
		productDao.update(ps);
	}

	@Override
	public void delProduct(int num) {
		productDao.delete(num);
	}

	@Override
	public void recordup(ProductDto productvo) {
		productDao.recordCount(productvo);
	}

}
