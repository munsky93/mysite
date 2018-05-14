package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	/*
	 * @RequestMapping(value="/list", method=RequestMethod.GET) public String
	 * list(Model model) { System.out.println("list"); List<BoardVo> list =
	 * boardService.getList(); model.addAttribute("list", list); return
	 * "board/list"; }
	 */

	@RequestMapping(value = "/list", method = RequestMethod.GET)   //crtPage 밑에 번호 //
	public String list(@RequestParam(value = "crtPage", required = false, defaultValue = "1") Integer crtPage,
		@RequestParam(value = "kwd", required = false, defaultValue = "") String kwd, Model model) {
		Map<String, Object> bMap = boardService.getList(crtPage, kwd);
		model.addAttribute("bMap", bMap);
		return "board/list";
	}

	@RequestMapping(value = "/writeform", method = RequestMethod.GET)
	public String writeform() {
		System.out.println("writeform");
		return "board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("write");
		boardService.write(boardVo);
		/*for(int i=1;i<=100;i++) {
			BoardVo Vo = new BoardVo(i+"번째 제목", i+"번째 글", 0, null, 2);   //100개 한번에 글 적기 !
			boardService.write(Vo);
		}*/
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(@RequestParam("no") int no, Model model) {
		System.out.println("view");
		BoardVo boardVo = boardService.view(no);
		model.addAttribute("BoardVo", boardVo);
		return "board/view";
	}

	@RequestMapping(value = "/modifyform", method = RequestMethod.GET)
	public String modifyform(@RequestParam("no") int no, Model model) {
		System.out.println("modifyform");
		BoardVo boardVo = boardService.getVo(no);
		model.addAttribute("vo", boardVo);
		return "board/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyform(@ModelAttribute BoardVo boardVo, 
		                     @RequestParam("crtPage") int crtPage,
		                     @RequestParam("kwd") String kwd) {
		System.out.println("modify");
		boardService.modify(boardVo);
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("no") int no) {
		System.out.println("delete");
		boardService.delete(no);
		return "redirect:/board/list?crtPage=\"+ crtPage + \"&kwd=\" + kwd;";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@RequestParam(value = "kwd", required = false, defaultValue = "%") String kwd, Model model) {
		System.out.println("search");
		List<BoardVo> list = boardService.search(kwd);
		model.addAttribute("list", list);
		return "board/list";
	}

}