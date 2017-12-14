package com.summit.common.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
//@EnableWebMvc//这个标注可以不加，如果有加，要extends WebMvcConfigurerAdapter
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatMessageHandler(),"/ws.do").addInterceptors(new HandshakeInterceptor());
        registry.addHandler(chatMessageHandler(), "/ws.do").addInterceptors(new HandshakeInterceptor()).withSockJS();
    }

    @Bean
    public DemoWSHandler chatMessageHandler(){
        return new DemoWSHandler();
    }

}