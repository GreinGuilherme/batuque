package com.api.batuque.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Habilita um broker simples em memória.
        // O Flutter vai se inscrever nestes tópicos (ex: /topic/pontos, /topic/giras)
        config.enableSimpleBroker("/topic");

        // Prefixo para mensagens enviadas do Flutter para o Spring (caso precise)
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Endpoint que o Flutter vai usar para conectar: wss://batuque.duckdns.org/ws
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*"); // Permite conexões de qualquer origem (útil para o app mobile)
    }
}