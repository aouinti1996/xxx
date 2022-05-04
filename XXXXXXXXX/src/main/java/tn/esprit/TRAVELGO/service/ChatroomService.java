package tn.esprit.TRAVELGO.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.TRAVELGO.entities.Chatroom;
import tn.esprit.TRAVELGO.repository.ChatroomRepository;

import java.util.Optional;

@Service
public class ChatroomService {
    @Autowired
    ChatroomRepository chatroomRepository;


    public Chatroom findChatroomBySenderIdAndRecipientId(Long senderId, Long recipientId){
        Optional<Chatroom> found = chatroomRepository.findChatroomBySenderIdAndRecipientId(senderId, recipientId);
        if(found.isPresent()){
            return found.get();
        }
        return null;
    }
}
