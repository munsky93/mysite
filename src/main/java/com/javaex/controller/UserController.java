package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/joinform", method = RequestMethod.GET)
	public String joinform() {
		System.out.println("joinform");

		return "user/joinform";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("join");
		System.out.println(userVo.toString());
		userService.join(userVo);
		return "user/joinsuccess";

	}

	@RequestMapping(value = "/loginform", method = RequestMethod.GET)
	public String loginform() {
		System.out.println("loginform");
		return "user/loginform";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam("email") String email, 
						@RequestParam("password") String password,
			HttpSession session, Model model) {

		UserVo authUser = userService.login(email, password);

		if (authUser != null) {
			session.setAttribute("authUser", authUser);
			return "redirect:/main";

		} else {
			model.addAttribute("result", "fail");
			return "redirect:/user/loginform?";

		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println("logout");
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/main";

	}

	@RequestMapping(value = "/modifyform", method = RequestMethod.GET)
	public String modifyform(@RequestParam("no") int no, Model model) {
		System.out.println("modifyform");
		UserVo userInfo = userService.getUserInfo(no);
		model.addAttribute("userInfo", userInfo);
		return "user/modifyform";

	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(UserVo userVo, HttpSession session) {
		System.out.println("modify");
		int result = userService.userModify(userVo);
		if (result != 0) {
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			authUser.setNo(userVo.getNo());
			authUser.setName(userVo.getName());
			System.out.println("수정완료~~~");
		}
		return "redirect:/main";

	}
	
	
	
	
    @ResponseBody
	@RequestMapping(value="/emailcheck", method=RequestMethod.POST)       /* emailcheck를 받는다 */
	public boolean exists(@RequestParam("email") String email) {          /* String email 담는다 */
		
		System.out.println("ajax 이메일체크" + email);
  		/*boolean isExists = true;*/    //isExists존재하니 ? 묻는거임
		
  		return userService.idcheck(email);                                /* email 을 파라미터로 이용해서 userservice에 있는 idcheck 메소드를 호출한다 */
	}                                                                     /* .idcheck == 메소드 */
	
	
}

