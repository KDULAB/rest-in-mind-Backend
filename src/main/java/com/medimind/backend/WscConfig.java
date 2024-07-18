package com.medimind.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WscConfig implements WebSocketConfigurer {
	
	private final WscComponent wscComponent;
	
	public WscConfig(WscComponent wscComponent) {
		this.wscComponent = wscComponent;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(wscComponent, "/wscMedimind").setAllowedOrigins("*");
	}
	
}
