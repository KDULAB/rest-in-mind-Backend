package com.medimind.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MediMindController {

	@ResponseBody
	@PostMapping(value="/getChatData")
	public String getChatData(String id) {
		String result = "";
		
		return result;
	}
	
	@ResponseBody
	@PostMapping(value="/sendChat")
	public String sendChat(String id, String chat) {
		String result = "";
		
		result = requestChatbot(chat);
		
		return result;
	}
	
	private String requestChatbot(String chat) {
		String result = "";
		
		result = "챗봇의 답변";
		
		return result;
	}
}
