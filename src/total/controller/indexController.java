package total.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import total.model.User;
import total.service.GreetService;
import total.service.MemberService;

@Controller
public class indexController {
	@Autowired
	GreetService greetService;
	@Autowired
	MemberService memberService;
	// 인덱스
	@RequestMapping({"/index","/"})
	public String indexHandler(HttpSession session) {
		session.setAttribute("ment", greetService.make());
		return "index";
	}
	// 등록페이지로 가기 
	@RequestMapping(path="/regist")
	public String registHandle(Model model) {
		return "regist";
	}
	// 등록페이지로 가기 
	@RequestMapping(path="/log")
	public String loginHandle(Model model) {
		return "login";
	}
	

		
	
	
}
