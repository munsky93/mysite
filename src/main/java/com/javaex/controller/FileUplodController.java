package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileUploadService;
import com.javaex.vo.FileVo;


@Controller
@RequestMapping(value="/gallery")
public class FileUplodController {
	
	@Autowired
	private FileUploadService fileUploadService;

	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		System.out.println("file form");
		List<FileVo> list = fileUploadService.getFile();
		model.addAttribute("fileList", list);
		
		return "gallery/list";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
	
		FileVo fileVo = fileUploadService.restore(file);
		model.addAttribute("fileVo", fileVo);
		
		return "redirect:/gallery/list";
		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET) // 결과에서 삭제하는 과정
	public String delete(@RequestParam("no") int no) { // 물어볼꺼 !
		System.out.println(no);
		System.out.println("delete");
		fileUploadService.delete(no);

		return "redirect:/gallery/list";
	}

}
