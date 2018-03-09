package total.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import total.model.User;
import total.service.GreetService;

@Controller
public class indexController {
	@Autowired
	GreetService greetService;
	// 인덱스
	@RequestMapping({"/index","/"})
	public String indexHandler(Model model) {
		model.addAttribute("ment", greetService.make());
		return "index";
	}
	// 등록
	@RequestMapping("/regist")
	public String registHandle(Model model) {
		model.addAttribute("ment", greetService.make());
		return "register";
	}
	// 로그인
	@RequestMapping("/login")
	public String loginHandle(Model model) {
		model.addAttribute("ment", greetService.make());
		return "login";
	}
	// 결과
	@RequestMapping("/result")
	public String resultHandle(@ModelAttribute(name="user") User user) {
		return "result";
	}
}
