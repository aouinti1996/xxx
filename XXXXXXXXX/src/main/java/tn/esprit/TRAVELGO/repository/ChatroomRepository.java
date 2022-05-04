package tn.esprit.TRAVELGO.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.TRAVELGO.entities.Chatroom;

import java.util.Optional;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
    @Query("FROM Chatroom c WHERE c.senderId = :senderId AND c.recipientId = :recipientId")
    Optional<Chatroom> findChatroomBySenderIdAndRecipientId(Long senderId, Long recipientId);
}
