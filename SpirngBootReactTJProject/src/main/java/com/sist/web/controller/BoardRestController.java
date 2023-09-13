package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("board/")
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	@GetMapping("board_list_react")
	public List<BoardEntity> board_list(String page)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-rowSize;
		List<BoardEntity> list = dao.boardListData(start);
		for(BoardEntity vo:list)
		{
			String s = vo.getRegdate();
			String[] ss = s.split(" ");//2023-08-31 11:02:04 시간 자르기
			vo.setRegdate(ss[0].trim());
		}
		return list;
	}
	@GetMapping("board_page_react")
	public PageVO board_page(int page) 
	{
		int totalpage=dao.boardTotalPage();
		final int BLOCK=5;
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
	@GetMapping("board_detail_react")
	public BoardEntity board_detail(int no)
	{
		BoardEntity vo = dao.findByNo(no);
		vo.setHit(vo.getHit()+1);
		dao.save(vo);
		vo=dao.findByNo(no);
		return vo;
	}
	@GetMapping("board_insert_react")
	public void board_insert(BoardEntity vo)
	{
		dao.save(vo);
	}
	@PostMapping("board_update_react")
	public void board_update_ok(int no,BoardEntity vo)
	{
		BoardEntity tvo=dao.findByNo(no);
		tvo=dao.save(vo);
		dao.save(tvo);
	}
	@PostMapping("board_delete_react")
	public void board_delete_ok(String pwd,int no)
	{
		BoardEntity vo = dao.findByNo(no);
		dao.delete(vo);
	}
}
