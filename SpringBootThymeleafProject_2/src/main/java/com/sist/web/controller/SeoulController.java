package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.web.dao.SeoulLocationDAO;
import com.sist.web.entity.FoodEntity;
import com.sist.web.entity.SeoulLocationEntity;
@Controller
public class SeoulController {
	@Autowired
	private SeoulLocationDAO dao;
	@RequestMapping("seoul/location")
	public String seoul_find(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize);
		List<SeoulLocationEntity> list = dao.seoulListData(start);
		int totalpage=dao.seoulListTotalPage();
		final int BLOCK = 10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("list",list);
		return "seoul/location";
	}
}
