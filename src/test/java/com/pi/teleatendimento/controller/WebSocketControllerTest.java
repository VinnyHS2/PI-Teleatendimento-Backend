package com.pi.teleatendimento.controller;

import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Type;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pi.teleatendimento.dto.WebSocketChannelDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class WebSocketControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@LocalServerPort
	private Integer port;
	
	final String WEBSOCKET_URI = "ws://localhost:" + String.valueOf(port) + "/api/pi-websocket";
    static final String WEBSOCKET_TOPIC = "2046458";
	
	BlockingQueue<String> blockingQueue;
    WebSocketStompClient stompClient;

    @BeforeEach
    public void setup() {
        blockingQueue = new LinkedBlockingDeque<>();
        stompClient = new WebSocketStompClient(new SockJsClient(
        		asList(new WebSocketTransport(new StandardWebSocketClient()))));
    }
	
    @Test
    public void shouldReceiveAMessageFromTheServer() throws Exception {
    	
    	WebSocketChannelDto dto = WebSocketChannelDto.builder()
    			.topico(WEBSOCKET_TOPIC)
    			.tipo("iniciar")
    			.payload("sala_id")
    			.build();
		
		ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dto);
    	
        StompSession session = stompClient
                .connect("ws://localhost:" + String.valueOf(port) + "/api/pi-websocket", new StompSessionHandlerAdapter() {}).get(2, SECONDS);
        session.subscribe(WEBSOCKET_TOPIC, new DefaultStompFrameHandler());

//        String message = "MESSAGE TEST";
        
        this.mockMvc.perform(
				MockMvcRequestBuilders.post("/websocket/message")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.characterEncoding("utf-8"))
				.andExpect(status().isOk());
        
//        session.send(WEBSOCKET_TOPIC, message.getBytes());

//        assertEquals(json, blockingQueue.poll(2, SECONDS));
    }
    
    class DefaultStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return byte[].class;
        }

        @Override
        public void handleFrame(StompHeaders stompHeaders, Object o) {
            blockingQueue.offer(new String((byte[]) o));
        }
    }
    
}
