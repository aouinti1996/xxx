package tn.esprit.TRAVELGO.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "chatroom")
@Builder
public class Chatroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chatroom_id")
    private String chatroomId;

    @Column(name="sender_id")
    private Long senderId;

    @Column(name="recipient_id")
    private Long recipientId;

	private String Biulder;


	public Chatroom(Long id, String chatroomId, Long senderId, Long recipientId) {
		super();
		this.id = id;
		this.chatroomId = chatroomId;
		this.senderId = senderId;
		this.recipientId = recipientId;
	}

	public Chatroom() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChatroomId() {
		return chatroomId;
	}

	public void setChatroomId(String chatroomId) {
		this.chatroomId = chatroomId;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
	}

    
    
    
    
    
    
    
}
