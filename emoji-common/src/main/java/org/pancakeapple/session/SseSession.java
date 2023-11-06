package org.pancakeapple.session;

import org.pancakeapple.constant.SseConstant;
import org.pancakeapple.enumeration.MessageType;
import org.pancakeapple.exception.SseException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Sse连接
 */
public class SseSession {
    //利用ConcurrentHashMap维护用户连接，key是用户名，value是SseEmitter
    private final Map<String, SseEmitter> sessionMap = new ConcurrentHashMap<>();

    //新增一个Sse连接
    public void add(String username,SseEmitter sseEmitter){
        if(sessionMap.get(username) != null){
            throw new SseException(SseConstant.USER_HAS_CONNECTED);
        }
        sessionMap.put(username, sseEmitter);
    }

    //检查某个用户是否已经连接
    public boolean exists(String username){
        return sessionMap.get(username) != null;
    }

    //断开某个连接
    public void remove(String username){
        SseEmitter sseEmitter = sessionMap.get(username);
        if(sseEmitter == null) {
            throw new SseException(SseConstant.USER_NOT_CONNECTED);
        }
        sseEmitter.complete();
        sessionMap.remove(username);
    }

    //连接出错情况的处理
    public void onError(String username,Throwable throwable){
        SseEmitter sseEmitter = sessionMap.get(username);
        if(sseEmitter != null){
            sseEmitter.completeWithError(throwable);
            sessionMap.remove(username);
        }
    }

    //发送消息
    public void send(String senderName, String receiverName, MessageType messageType) throws IOException {
        if(sessionMap.get(receiverName) == null) {
            //用户不在线时，直接返回即可
            return;
        }
        switch (messageType) {
            case COMMENT -> sessionMap.get(receiverName).send(senderName+SseConstant.COMMENT_TO_YOU);
            case REPLY -> sessionMap.get(receiverName).send(senderName+SseConstant.REPLY_TO_YOU);
            case FAVORITE -> sessionMap.get(receiverName).send(senderName+SseConstant.FAVORITE_YOUR_EMOJI);
            case PRIVATE_LETTER -> sessionMap.get(receiverName).send(senderName+SseConstant.SEND_PRIVATE_LETTER);
            default -> {
            }
        }

    }

}