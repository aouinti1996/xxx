package tn.esprit.TRAVELGO.entities;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name="chatroom_id")
    private String chatroomId;

    @Column(name="sender_id")
    private Long senderId;

    @Column(name="recipient_id")
    private Long recipientId;

    @Column(name="sender_name")
    private String senderName;

    @Column(name="recipient_name")
    private String recipientName;

    @Column
    private String content;

    @Column(name = "created_on",nullable = false)
    private Date createdOn;

    @Column(name="status")
    private String status;

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
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

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Message(Long messageId, String chatroomId, Long senderId, Long recipientId, String senderName,
			String recipientName, String content, Date createdOn, String status) {
		super();
		this.messageId = messageId;
		this.chatroomId = chatroomId;
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.senderName = senderName;
		this.recipientName = recipientName;
		this.content = content;
		this.createdOn = createdOn;
		this.status = status;
	}

	public Message() {
		super();
	}
    
    
    
    
    
    
    
    
    
    
}
