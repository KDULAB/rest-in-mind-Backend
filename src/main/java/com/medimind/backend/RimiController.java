package com.medimind.backend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

@Controller
public class RimiController {
	
	@Value("${api.url}")
	private String apiurl;

	@ResponseBody
	@PostMapping(value="/getChatData")
	public String getChatData(String id) {
		String result = "";
		
		return result;
	}
	
	@ResponseBody
	@PostMapping(value="/sendChat")
	public String sendChat(@RequestBody HashMap<String, Object> param) {
		String result = "";
		
		
		String id = (String)param.get("id");
		String chat = (String)param.get("chat");
		String chat_ind = (String)param.get("thread_id");
		
		result = requestChatbot(chat, chat_ind);
		
		return result;
	}
	
	private String requestChatbot(String chat, String thread_id) {
		String result = "";
		
		// API header, body
		HashMap<String, String> header = new HashMap<String, String>();
		String body = "";
		
		header.put("Content-Type", "application/json");
		JsonObject o = new JsonObject();
		o.addProperty("user", chat);
		o.addProperty("thread_id", thread_id);
		body = o.toString();
		
		// API request
		result = httpApiRequest(apiurl, "POST", header, body);
		
		return result;
	}
	
	private String httpApiRequest(String requestUrl, String method, HashMap<String, String> header, String body) {
		String result = "";
		
		try {
			URL url = new URI(requestUrl).toURL();
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(method);
			con.setDoOutput(true);
			con.setDoInput(true);
			
			// make header
			for(String key : header.keySet()) {
				con.setRequestProperty(key, header.get(key));
			}
			
			// make body
			if(method.equals("POST") || method.equals("post")) {
				OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
				osw.write(body);
				osw.flush();
			}
			
			// response
			StringBuilder sb = new StringBuilder();
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				String line;
				while((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				result = sb.toString();
			} else {
				con.getResponseMessage();
				result = "ERROR : (HTTP)" + con.getResponseMessage();
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "ERROR : " + e.getMessage();
		}
		
		return result;
	}
}
