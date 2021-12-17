package com.blog.yataverse.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ChatHandler extends TextWebSocketHandler {

    // (<"bang_id", 방ID>, <"session", 세션>) - (<"bang_id", 방ID>, <"session", 세션>) - (<"bang_id", 방ID>, <"session", 세션>) 형태
    private List<Map<String, Object>> sessionList = new ArrayList<Map<String, Object>>();
    String fname;
    // 클라이언트가 서버로 메세지 전송 처리
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        super.handleTextMessage(session, message);

        // JSON --> Map으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> mapReceive = objectMapper.readValue(message.getPayload(), Map.class);
        System.out.println("맵리시브:" + mapReceive);
        fname = mapReceive.get("uname");
        switch (mapReceive.get("cmd")) {

            // CLIENT 입장
            case "CMD_ENTER":
                // 세션 리스트에 저장
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("bang_id", mapReceive.get("bang_id"));
                map.put("uname", mapReceive.get("uname"));
                map.put("session", session);
                sessionList.add(map);

                // 같은 채팅방에 입장 메세지 전송
                for (int i = 0; i < sessionList.size(); i++) {
                    Map<String, Object> mapSessionList = sessionList.get(i);
                    String bang_id = (String) mapSessionList.get("bang_id");
                    String uname = (String) mapSessionList.get("uname");
                    WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");

                    if(bang_id.equals(mapReceive.get("bang_id"))) {
                        Map<String, String> mapToSend = new HashMap<String, String>();
                        mapToSend.put("bang_id", bang_id);
                        mapToSend.put("cmd", "CMD_ENTER");
                        mapToSend.put("msg", mapReceive.get("uname") +  "has Entered.");

                        String jsonStr = objectMapper.writeValueAsString(mapToSend);
                        sess.sendMessage(new TextMessage(jsonStr));
                    }
                }
                break;

            // CLIENT 메세지
            case "CMD_MSG_SEND":
                // 같은 채팅방에 메세지 전송
                for (int i = 0; i < sessionList.size(); i++) {
                    Map<String, Object> mapSessionList = sessionList.get(i);
                    String bang_id = (String) mapSessionList.get("bang_id");
                    String uname = (String) mapSessionList.get("uname");
                    WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");

                    if (bang_id.equals(mapReceive.get("bang_id"))) {
                        Map<String, String> mapToSend = new HashMap<String, String>();
                        mapToSend.put("bang_id", bang_id);
                        mapToSend.put("uname", mapReceive.get("uname"));
                        mapToSend.put("cmd", "CMD_MSG_SEND");
                        mapToSend.put("msg", mapReceive.get("uname") + " : " + mapReceive.get("msg"));

                        String jsonStr = objectMapper.writeValueAsString(mapToSend);
                        sess.sendMessage(new TextMessage(jsonStr));
                    }
                }
                break;
        }
    }

    // 클라이언트가 연결을 끊음 처리
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        super.afterConnectionClosed(session, status);

        ObjectMapper objectMapper = new ObjectMapper();
        String now_bang_id = "";

        // 사용자 세션을 리스트에서 제거
        for (int i = 0; i < sessionList.size(); i++) {
            Map<String, Object> map = sessionList.get(i);
            String bang_id = (String) map.get("bang_id");
            WebSocketSession sess = (WebSocketSession) map.get("session");

            if(session.equals(sess)) {
                now_bang_id = bang_id;
                sessionList.remove(map);
                break;
            }
        }

        // 같은 채팅방에 퇴장 메세지 전송
        for (int i = 0; i < sessionList.size(); i++) {
            Map<String, Object> mapSessionList = sessionList.get(i);
            String bang_id = (String) mapSessionList.get("bang_id");
            String uname = (String) mapSessionList.get("uname");
            WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");

            if (bang_id.equals(now_bang_id)) {
                Map<String, String> mapToSend = new HashMap<String, String>();
                mapToSend.put("bang_id", bang_id);
                mapToSend.put("cmd", "CMD_EXIT");
                mapToSend.put("msg", fname + "had Left.");

                String jsonStr = objectMapper.writeValueAsString(mapToSend);
                sess.sendMessage(new TextMessage(jsonStr));
            }
        }
    }
}
