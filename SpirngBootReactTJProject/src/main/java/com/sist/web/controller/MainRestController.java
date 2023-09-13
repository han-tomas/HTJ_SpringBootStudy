package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.web.dao.*;
import com.sist.web.entity.*;
@RestController
@CrossOrigin("http://localhost:3000")
public class MainRestController {
	@Autowired
	private CampingDAO dao;
	@GetMapping("/")
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
}
