package tn.esprit.TRAVELGO.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.TRAVELGO.entities.Message;
import tn.esprit.TRAVELGO.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public int countNewMessagesFromOnlineUser(Long currentUserId, Long userId){
        return messageRepository.countNewMessagesFromOnlineUser(currentUserId, userId);
    }

    public List<Message>findChatMessagesFromSelectedUser(Long senderId, Long recipientId){
        return messageRepository.findChatMessagesFromSelectedUser(senderId, recipientId);
    }

    public void updateMessagesStatusToDelivered(List<Message>msgs){
        msgs.stream().filter(m->m.getStatus().equals("RECEIVED")).forEach(m->{
            m.setStatus("DELIVERED");
            messageRepository.save(m);
        });
    }

    public List<Message> findChatMessagesByChatroomId(String string){
        return messageRepository.findChatMessagesByChatroomId(string);
    }
}
