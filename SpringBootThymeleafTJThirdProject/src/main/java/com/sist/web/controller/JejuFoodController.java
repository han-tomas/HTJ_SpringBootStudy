package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sist.web.dao.*;
import com.sist.web.entity.*;
import java.util.*;
@Controller
@RequestMapping("food/")
public class JejuFoodController {
	@Autowired
	private JejuFoodDAO dao;
	@RequestMapping("food_find")
	public String food_find(String page,String fd,Model model)
	{
		if(fd==null)
			fd="제주";
		if(page==null)
	         page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize);
		List<JejuFoodEntity> list=dao.jejuFoodFindData(fd, start);
		for(JejuFoodEntity vo:list)
		{
			String poster = vo.getPoster();
			poster=poster.replace("#", "&");
			if(poster.contains("^"))
				poster=poster.substring(0,poster.indexOf("^"));
			vo.setPoster(poster);
			String addr=vo.getAddress();
			addr=addr.substring(0,addr.indexOf("지번"));
			if(addr.length()>22)
				addr=addr.substring(0,22)+"...";
			vo.setAddress(addr);
			
		}
		int totalpage=dao.jejuFoodFindTotalPage(fd);
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		  
		if(endPage>totalpage)
		   endPage=totalpage;
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
		model.addAttribute("fd",fd);
		model.addAttribute("main_html","food/food_find");
		return "main";
	}
	@GetMapping("food_detail")
	public String food_detail(int fino,Model model)
	{
		JejuFoodEntity vo = dao.findByFino(fino);
		String addr = vo.getAddress();
		addr = addr.substring(0,addr.indexOf("지번"));
		addr=addr.trim();
		vo.setAddress(addr);
		String poster = vo.getPoster();
		String[] temp = poster.split("\\^"); 
		List<String> pList=Arrays.asList(temp);
		String menu = vo.getMenu();
		String[] temp1 = menu.split("원");
		List<String> mList=Arrays.asList(temp1);
		model.addAttribute("mList",mList);
		model.addAttribute("pList",pList);
		model.addAttribute("vo",vo);
		model.addAttribute("addr",addr);
		model.addAttribute("name",vo.getName());
		System.out.println(addr);
		model.addAttribute("main_html","food/food_detail");
		return "main";
	}
}
