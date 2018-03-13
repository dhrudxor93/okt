package total.controller;

import java.util.HashMap;
import java.util.LinkedHashSet;
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

/*
 *  WebSocket
 *  ws 통신을 제어활용도의 컨트롤러 구현방법
 *  - 1. WebSocketHandler (I) 를 implemets 걸어서 목적에 개조해서 사용
 *  - 2. 목적에 맞는 WebSocketHandler를 extends 걸어서 사용
 *  	- TextWebSocketHandler / BinaryWebSocketHandler
 *  
 *  - TextWebSocketHandler 이용시
 *    afterConnectionEstablished / handleTextMessage / afterConnectionClosed 3가지를 갖추고 있어야 함.
 * 
 * 	WebSocket Handler 의 매핑은.. spring 설정파일(app-config)에서 
 */
@Controller("wsController") // 임의로 바꿀 수도 있다
public class WSController extends TextWebSocketHandler {
	
	Set<WebSocketSession> wsSessions;
	@Autowired
	Gson gson;
	@PostConstruct // init메소드 (bean 자동등록시 PostConstruct)
	public void init() {
		wsSessions = new LinkedHashSet<>();
	}
	
	@Override // 클라이언트 측에서 웹소켓 연결이 되었을 때
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterConnectionEstablished.." + session);
		System.out.println(session.getRemoteAddress().getAddress().getHostAddress());// IP주소가 나옴
		wsSessions.add(session);
		Map map = new HashMap();
		map.put("cnt", wsSessions.size() );
		map.put("info",session.getRemoteAddress().getAddress().getHostAddress());
		for(WebSocketSession ws : wsSessions) {
			ws.sendMessage(new TextMessage(gson.toJson(map)));
		}
	}

	@Override // 클라이언트 측에서 메세지가 들어올 때.
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("handleTextMessage.." + session + " / " + message);
	}

	@Override // 클라이언트측과 연결이 해제되었을 떄
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("afterConnectionClosed.." + session);
		wsSessions.remove(session);
		Map map = new HashMap();
		map.put("cnt", wsSessions.size() );
		map.put("info",session.getRemoteAddress().getAddress().getHostAddress());
		for(WebSocketSession ws : wsSessions) {
			ws.sendMessage(new TextMessage(gson.toJson(map)));
		}
		
	}
}
