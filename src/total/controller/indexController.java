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
	@RequestMapping({"/index","/"})
	public String indexHandler(Model model) {
		model.addAttribute("ment", greetService.make());
		return "index";
	}
	@RequestMapping("/regist")
	public String regitHandle(Model model) {
		model.addAttribute("ment", greetService.make());
		return "register";
	}
	@RequestMapping("/result")
	public String resultHandle(@ModelAttribute(name="user") User user) {
		return "result";
	}
}
