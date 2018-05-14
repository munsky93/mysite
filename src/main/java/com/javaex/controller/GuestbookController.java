package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired // 이건 new를 안쓰기 위한 것 ~
	private GuestbookService guestbookService;

	@RequestMapping(value = "/deleteform", method = RequestMethod.GET) // 결과에서 삭제창으로 넘어가는 과정
	public String add(@RequestParam("no") int no, Model model) { // 물어볼꺼 !
		System.out.println("deleteform");
		model.addAttribute("no");

		return "guestbook/deleteform";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST) // 결과에서 삭제하는 과정
	public String delete(GuestbookVo guestbookVo) { // 물어볼꺼 !
		System.out.println("delete");
		guestbookService.delete(guestbookVo);

		return "redirect:/guestbook/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestParam("name") String name, @RequestParam("password") String password,
			@RequestParam("content") String content, HttpSession session, Model model) {

		GuestbookVo vo = new GuestbookVo(name, password, content);

		guestbookService.add(vo);
		return "redirect:/guestbook/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		System.out.println("list");
		List<GuestbookVo> list = guestbookService.getList();
		model.addAttribute("list", list);

		return "guestbook/list";
	}
	
	@RequestMapping(value = "/ajax-list", method = RequestMethod.GET)
	public String ajaxList() {
		System.out.println("ajax-list");
		return "guestbook/ajax-list";
	}
}
