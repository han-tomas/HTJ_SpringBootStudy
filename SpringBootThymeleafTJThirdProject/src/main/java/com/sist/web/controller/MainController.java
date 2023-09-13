package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;

@Controller
public class MainController {
	@Autowired
	private JejuTravelDAO dao;
	@Autowired
	private JejuFoodDAO fdao;
	@GetMapping("/")
	public String main_page(Model model)
	{
		
		List<JeJuTravelEntity> list = dao.jejuListData();
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
		List<JejuFoodEntity> flist =fdao.jejuFoodData();
		for(JejuFoodEntity vo:flist)
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
		model.addAttribute("flist",flist);
		model.addAttribute("list",list);
		model.addAttribute("main_html","main/home");
		return "main";
	}
}
