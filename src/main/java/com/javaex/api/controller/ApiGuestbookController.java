package com.javaex.api.controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;



@Controller
@RequestMapping(value="/api/guestbook")
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<GuestbookVo> list() {
		System.out.println("ajax-list : guestbook");
		List<GuestbookVo> list=guestbookService.getList();
		System.out.println(list.toString());
		
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public GuestbookVo add(@ModelAttribute GuestbookVo guestbookVo) {
		 System.out.println("add");
		 System.out.println(guestbookVo.toString());
		 
		 GuestbookVo vo = guestbookService.write(guestbookVo);
		 System.out.println("controller:" + vo.toString());
		 
		 return vo;
	}
	
	@ResponseBody
	@RequestMapping(value="/delete2", method=RequestMethod.POST)
	public int delete(@RequestParam("no") int no,
			             @RequestParam("password") String password) {
	    int delno = guestbookService.delete2(no, password);    //delno에 delete2를 넣어준다
	    if(delno != 0 ) {                                      //delno가 0이 아닐때 no로 돌아간다.
	    	return no;
	    } else {                                               //그게아니라면 0
	    	return 0;
	    }
	    
	    
			
	}
}
