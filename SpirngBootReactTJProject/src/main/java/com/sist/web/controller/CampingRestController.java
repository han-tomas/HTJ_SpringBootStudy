package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.CampingDAO;
import com.sist.web.entity.CampingEntity;
import com.sist.web.entity.PageVO;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("camp/")
public class CampingRestController {
	@Autowired
	private CampingDAO dao;
	@GetMapping("home_react")
	public List<CampingEntity> camping_list()
	{
		List<CampingEntity> list = dao.campingHomeListData();
		for(CampingEntity vo:list)
		{
			if(vo.getImage().contains("^"))
			{	
				String image = vo.getImage();
				image=image.substring(0,image.indexOf("^"));
				vo.setImage(image);
			}
		}
		return list;
	}
	@RequestMapping("camp_find_react")
	public List<CampingEntity> camp_find(String address, String page)
	{
		if(address==null)
			address="";
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-rowSize;
		
		List<CampingEntity> list = dao.campFindData(address, start);
		for(CampingEntity vo:list)
		{
			String image=vo.getImage();
			if(image.contains("^"))
			{
				image=image.substring(0,image.indexOf("^"));
			}
			vo.setImage(image);
		}
		return list;
	}
	@GetMapping("camp_page_react")
	public PageVO camp_page(String address, int page) {
		int totalpage=dao.campFindTotalPage(address);
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		PageVO vo=new PageVO();
		vo.setCurpage(page);
		vo.setTotalpage(totalpage);
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		return vo;
	}
	@GetMapping("camp_detail_react")
	public CampingEntity camp_detail(int cno)
	{
		CampingEntity vo = dao.findByCno(cno);
		return vo;
	}
	
	// redux 페이지 네이션
	// 총페이지 수
   @GetMapping("camp_totalpage_react")
   public int food_page(String address)
   {
	   int totalpage=dao.campFindTotalPage(address);
	   
	   return totalpage;
   }
   @GetMapping("camp_count_react")
   public int food_count(String address)
   {
	   return dao.campFindCount(address);
   }
}
