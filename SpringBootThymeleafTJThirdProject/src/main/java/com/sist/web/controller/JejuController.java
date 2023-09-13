package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.web.dao.*;
import java.util.*;
import com.sist.web.entity.*;

@Controller
@RequestMapping("jeju/")
public class JejuController {
	@Autowired
	private JejuTravelDAO dao;
	@GetMapping("travel_list")
	public String jeju_travel_list(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize);
		List<JeJuTravelEntity> list = dao.jejuTravelListData(start);
		for(JeJuTravelEntity vo :list)
		{
			String title=vo.getTitle();
			if(title.length()>11)
				title=title.substring(0,11)+"...";
			vo.setTitle(title);
			String tag=vo.getTag();
			if(tag.length()>20)
				tag=tag.substring(0,20)+"...";
			vo.setTag(tag);
			String addr=vo.getAddr();
			if(addr.length()>23)
				addr=addr.substring(0,23)+"...";
			vo.setAddr(addr);
		}
		int totalpage = dao.jejuTravelTotalPage();
		
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
		model.addAttribute("main_html", "jeju/travel_list");
		return "main";
		
	}
	@GetMapping("travel_detail")
	public String jeju_travel_detail(int no,Model model)
	{
		JeJuTravelEntity vo = dao.findByNo(no);
		String content = vo.getInfo();
		content=content.replace("?", "");
		vo.setInfo(content);
		model.addAttribute("vo",vo);
		model.addAttribute("addr",vo.getAddr());
		model.addAttribute("name",vo.getTitle());
		model.addAttribute("main_html","jeju/travel_detail");
		return "main";
	}
}
