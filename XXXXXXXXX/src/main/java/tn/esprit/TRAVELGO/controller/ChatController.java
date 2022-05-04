package tn.esprit.TRAVELGO.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import tn.esprit.TRAVELGO.config.WebSocketEventListener;
import tn.esprit.TRAVELGO.entities.Chatroom;
import tn.esprit.TRAVELGO.entities.Message;
import tn.esprit.TRAVELGO.repository.ChatroomRepository;
import tn.esprit.TRAVELGO.repository.MessageRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;
import tn.esprit.TRAVELGO.service.dto.NotificationDto;

import java.util.Optional;

@Controller
public class ChatController {
    @Autowired
    WebSocketEventListener auth;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatroomRepository chatroomRepository;

    @MessageMapping("/chat")
    public void sendMessage(Message chatMessage
            , SimpMessageHeaderAccessor headerAccessor) {
        String sessionId = headerAccessor.getSessionId();
        headerAccessor.setSessionId(sessionId);

        Optional<Chatroom> chatroom = chatroomRepository.findChatroomBySenderIdAndRecipientId(chatMessage.getSenderId(),chatMessage.getRecipientId());
        String chatroomId = "";
        if(chatroom.isEmpty()){
             chatroomId= String.format("%i_%i", chatMessage.getSenderId(), chatMessage.getRecipientId());

            Chatroom senderRecipient = Chatroom
                    .builder()
                    .chatroomId(chatroomId)
                    .senderId(chatMessage.getSenderId())
                    .recipientId(chatMessage.getRecipientId())
                    .build();

            Chatroom recipientSender = Chatroom
                    .builder()
                    .chatroomId(chatroomId)
                    .senderId(chatMessage.getRecipientId())
                    .recipientId(chatMessage.getSenderId())
                    .build();
            try{
                chatroomRepository.save(senderRecipient);
                chatroomRepository.save(recipientSender);
            }
            catch(Exception ex){
                ex.printStackTrace();
               }

        }
        else{
            chatroomId = chatroom.get().getChatroomId();
        }
        chatMessage.setChatroomId(chatroomId);
        Message saved = null;
        try{
            saved = messageRepository.save(chatMessage);
        }
        catch(Exception ex){
            
        }

        NotificationDto noti = MapperUtils.mapperObject(saved, NotificationDto.class);

       messagingTemplate.convertAndSendToUser(chatMessage.getRecipientName(),"/queue/messages",noti);
    }



}
