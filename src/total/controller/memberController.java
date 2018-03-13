package total.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import total.service.GreetService;
import total.service.MemberService;

@Controller
public class memberController {
	@Autowired
	GreetService greetService;
	@Autowired
	MemberService memberService;


	// 등록
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String registPostHandle(Model model, HttpSession session, @RequestParam Map<String, String> param) {
		try {
			boolean rst = memberService.addNewMember(param);
			if (rst) {
				session.setAttribute("logon", param.get("id"));
				return "redirect:/index";
			}
			throw new Exception();
		} catch (Exception e) {
			model.addAttribute("msg", "문제발생");
			return "/register";
		}
	}

	// 로그인
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String loginHandle(Model model, HttpSession session, @RequestParam Map<String, String> param) {
		boolean rst = memberService.loginMember(param);
		if (rst) {
			session.setAttribute("logon", param.get("id"));
			session.setAttribute("sid", "로그인");
			return "redirect:/index";
		} else {
			model.addAttribute("msg", "실패하였습니다.");
			return "/login";
		}
	}

	// 로그아웃
	@RequestMapping("/logout")
	public String logoutHandle(Model model, HttpSession session) {
		session.removeAttribute("logon");
		return "redirect:/index";
	}
}
