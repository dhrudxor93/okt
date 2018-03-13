package total.controller;
/*
 * 같은 브라우저상에서의 로그인되었을 시 자동 로그인 되게끔..
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

@Controller("AlertController")
public class AlertController extends TextWebSocketHandler{
	// 웹소켓 커넥션이 열릴 때, 세션을 키로 해서 묶어서 관리.
	Map<String,List<WebSocketSession>> sessions;
	@Autowired
	Gson gson;
	
	@PostConstruct
	public void init() {
		sessions = new HashMap<>();
	}
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	//	System.out.println("작동함,afterConnectionEstablished");
		Map<String,Object> map = session.getAttributes();
		// HttpSession에 담아뒀던 모든 데이터가 뽑힌다.
		// 반드시 Spring 설정파일에 HttpSessionHandshakeInterceptor가 등록되어 있어야 한다.
		// 추가로 "HTTP.SESSION.ID"라는 키로 사용중인 session id도 넣어주고,
		System.out.println(map);
		String key = (String)session.getAttributes().get("HTTP.SESSION.ID"); // HttpSession을 접근해야된다.
		if(!sessions.containsKey(key)) {
			sessions.put(key, new ArrayList<>());
		}
		sessions.get(key).add(session);
		System.out.println(sessions.keySet());
		for(String k : sessions.keySet()) {
			if(sessions.get(k).size()>1) {
				if(map.containsKey("sid")) {
					
				}
			}
			System.out.println("size= " + sessions.get(k).size());
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String key = (String)session.getAttributes().get("HTTP.SESSION.ID");
		sessions.get(key).remove(session);
		if(sessions.get(key).isEmpty()) {
			sessions.remove(key);
		}
	}
}
