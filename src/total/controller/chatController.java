package total.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import total.service.MemberService;

@Controller
public class chatController {
	@Autowired
	MemberService memberService;
	@RequestMapping("/chat")
	public String chatHandle(HttpSession session, Model model) {
		String s = (String)session.getAttribute("logon");
		String lv = memberService.Idinfo(s);
		if(lv.equals("0")) {
			// model.addAttribute("lv", "당신의 레벨로서는 불가합니다.");
			// return "/index";
			return "/loginp";
		}
		return "/chatroom";
	}
}
