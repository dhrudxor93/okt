package total.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import total.model.WebSocketMap;
import total.service.GreetService;
import total.service.MemberService;

@Controller
public class memberController {
	@Autowired
	GreetService greetService;

	@Autowired
	MemberService memberService;

	@Autowired
	WebSocketMap sessions;

	// 등록
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String registPostHandle(Model model, HttpSession session, @RequestParam Map<String, String> param) {
		try {
			boolean rst = memberService.addNewMember(param);
			if (rst) {
				session.setAttribute("logon", param.get("id"));
				return "redirect:/index";
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			model.addAttribute("msg", "문제발생");
			return "/register";
		}
	}

	// 로그인
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String loginHandle(Model model, HttpSession session, @RequestParam Map<String, String> param) {
		boolean rst = memberService.loginMember(param);
		try {
			if (rst) {
				session.setAttribute("logon", param.get("id"));
				List<WebSocketSession> s = sessions.get(session.getId());
				if (s != null) {
					for (WebSocketSession ws : s) {
						ws.sendMessage(new TextMessage("로그인"));
					}
				}
				return "redirect:/index";
			}
			throw new Exception();
		} catch (Exception e) {
			System.out.println("에러" + e.toString());
			model.addAttribute("main","login.jsp");
			model.addAttribute("msg", "실패하였습니다.");
			return "el";
		}

	}

	// 로그아웃
	@RequestMapping("/logout")
	public String logoutHandle(Model model, HttpSession session) {
		try {
			session.removeAttribute("logon");
			List<WebSocketSession> s = sessions.get(session.getId());
			if (s != null) {
				for (WebSocketSession ws : s) {
					ws.sendMessage(new TextMessage("로그아웃"));
				}
			}
			return "redirect:/index";
		} catch (Exception e) {
			return "redirect:/";
		}
	}

	// 친구목록
	@RequestMapping("/friend")
	public String findHandle(Model model, HttpSession session) {
		String id = (String) session.getAttribute("logon");
		System.out.println(id);
		List<Map> list = memberService.find(id);
		model.addAttribute("friend", list);
		return "/friendsearch";
	}
}
