package tn.esprit.TRAVELGO.service;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.TRAVELGO.entities.Reservation;
import tn.esprit.TRAVELGO.entities.Travel;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.repository.ReservationRepository;
import tn.esprit.TRAVELGO.repository.TravelRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Properties;

@Service
public class Imp_Reservation_Service  implements I_Reservation_Service {

	@Autowired
	ReservationRepository reservationRepository ;
	@Autowired
	TravelRepository travelRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	EmailService emailService;
	
	
	 @Override
		public void addReservation(Reservation reserv) {
		 reservationRepository.save(reserv) ;
	 }
		 		
	 @Override
	 public void DeleteReservation (Long idReservation) {
		 reservationRepository.deleteById(idReservation);
	 }
		 		
	 @Override
	 public void UpdateReservation(Reservation rv, Long id ) {
		 Reservation r = reservationRepository.findById(id).get();
		 r.setDatereservation(rv.getDatereservation());
		 r.setIdTravel(rv.getIdTravel());		
		 reservationRepository.save(r) ;
	 }
	 	 		
	 @Override
	 public Reservation getReservationById (Long id ) {
		 Reservation reservation = reservationRepository.findById(id).get();
		 return reservation ;
	 }
 	 
	 @Override
	 public List<Reservation> getAllReservations(){
		 List<Reservation> reservation =  (List<Reservation>) reservationRepository.findAll();
		 return reservation ;
	 }
	 
	 
	 
	 @Override
		public void affectReservationTotravel(long idTravel, long idReservation) {
	    Reservation r=	reservationRepository.findById(idReservation).orElse(null);
	    
	    
	    Travel t=travelRepository.findById(idTravel).orElse(null);
			if (r != null) {
				r.setIdTravel(t);
			}
			reservationRepository.save(r);
		}
	 
	 
	 @Override
	 public void affecterUserToReservation(long idUs , long idReservation) { {
		  
		       Reservation r=	reservationRepository.findById(idReservation).get();
		       User u =userRepository.findById(idUs).get();
			         r.setIdUs(u);;
			         reservationRepository.save(r);
		
			        /* SimpleMailMessage mailMessage = new SimpleMailMessage();
						mailMessage.setTo(u.getEmail());
						mailMessage.setSubject("Notification from TRAVELGO");
						mailMessage.setFrom("sami.riahi@esprit.tn");
						mailMessage.setText("Reservation Sent");
						emailService.sendEmail(mailMessage);*/
	 }

	 
	
	// public void email(long idUs , long idReservation)  throws FileNotFoundException, DocumentException, MessagingException{
		  User u =userRepository.findById(idUs).get();;
	        Reservation r=	reservationRepository.findById(idReservation).get();
	        String smtpHost = "smtp.gmail.com"; // host
	        int smtpPort = 587; //port

	        String sender = "sami.riahi@esprit.tn"; //replace this with a valid sender email address
	        String recipient = u.getEmail(); //replace this with a valid recipient email address
	        String content = "BONJOUR "+u.getUsername()+"\n" +
	                "MERCI D'AVOIR EFFECTUÉ VOS RESERVATION SUR TRAVELGO.TN! \n" +
	                "Ce Fichier Contient Les Détails De Votre RESERVATION"; //this will be the text of the email
	        String subject = "[TRAVELGO.tn] Confirmation De RESERVATION"; //this will be the subject of the email

	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", smtpHost);
	        properties.put("mail.smtp.port", smtpPort);
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.auth", "true");
	        
	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(
	                        "sami.riahi@esprit.tn", "amqwbfvacmvxndgb");//  Username and the PassWord
	            }
	        });

	        ByteArrayOutputStream outputStream = null;

	        try {
	            //construct the text body part
	            MimeBodyPart textBodyPart = new MimeBodyPart();
	            textBodyPart.setText(content);

	            //now write the PDF content to the output stream
	            outputStream = new ByteArrayOutputStream();
	            writePdf(outputStream ,idUs , idReservation);
	            byte[] bytes = outputStream.toByteArray();

	            //construct the pdf body part
	            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
	            MimeBodyPart pdfBodyPart = new MimeBodyPart();
	            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
	            pdfBodyPart.setFileName("#"+ r.getIdReservation());

	            //construct the mime multi part
	            MimeMultipart mimeMultipart = new MimeMultipart();
	            mimeMultipart.addBodyPart(textBodyPart);
	            mimeMultipart.addBodyPart(pdfBodyPart);

	            //create the sender/recipient addresses
	            InternetAddress iaSender = new InternetAddress(sender);
	            InternetAddress iaRecipient = new InternetAddress(recipient);

	            //construct the mime message
	            MimeMessage mimeMessage = new MimeMessage(session);
	            mimeMessage.setSender(iaSender);
	            mimeMessage.setSubject(subject);
	            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
	            mimeMessage.setContent(mimeMultipart);

	            //send off the email
	            Transport.send(mimeMessage);

	            System.out.println("sent from " + sender +
	                    ", to " + recipient +
	                    "; server = " + smtpHost + ", port = " + smtpPort);
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            //clean off
	            if(null != outputStream) {
	                try { outputStream.close(); outputStream = null; }
	                catch(Exception ex) { }
	            }
	        }
	        
	       
	            
	           
	        
	    }

	private void writePdf(ByteArrayOutputStream outputStream,  long idUs, long idReservation) {
		
		 User user = userRepository.findById(idUs).get();
         Reservation reservation = reservationRepository.findById(idReservation).get();
         Document  document = new Document();
         PdfWriter.getInstance(document, outputStream);
         document.open();
         Paragraph paragraph = new Paragraph();
         paragraph.add(new Chunk("Réference de Reservataion                                     Date de reservation\n"+
                 reservation.getIdReservation() + "                                                                         "+ reservation.getDatereservation() +"\n \n "+
                 "Nom utilisateur                       Adresse User                                                      Email \n"+
                 user.getUsername() +"                                "+ user.getAdressUser() +"                                       "+ user.getEmail() +"\n \n \n"+
                 "[TRAVELGO.tn] MERCI POUR VOTRE CONFIANCE  \n"
                 ));
       

         document.add(paragraph);
         document.close();
         System.out.println("pdf generee") ;
		
	}
	
	  
	

	 
	 }
	 
	 
	 /*
			@Override
			 public void Matching(long idUs ,long idTravel) {
				 List<Reservation> reservation =  (List<Reservation>) reservationRepository.findAll();
				 Travel travel =  travelRepository.findById(idTravel).get();
				 User user = userRepository.findById(idUs).get();
				 Reservation reservation1 = new Reservation() ;
				 
				  int  nbr =(int) reservation.stream().filter(e->e.getIdUs()==idUs && e.getIdTravel()==idTravel).count();
				 if (reservation.size()==0) {
					 reservation1.setIdUs(user);; 
					 reservation1.setIdTravel(travel);
					 reservationRepository.save(reservation1);
					 
				 }else {
					 for (Reservation r:reservation) {
						 User usertest = userRepository.findById(r.getIdUs()).get();
						 Travel travelrtest = travelRepository.findById(r.getIdTravel()).get();
						 if (nbr==0 && (travel.getIdUs().getIdUs()==usertest.getIdUs()&& travelrtest.getIdUs().getIdTravel())){
							 r.setIdUs(user);
							 r.setIdTravel(travel);
							 reservationRepository.save(r);
						 }else if(nbr==0) {
							 reservation1.setIdUs(user); 
							 reservation1.setIdTravel(travel);
							 reservationRepository.save(reservation1);
						 }
						 
						 
						 
					 }
				 }
				 	 
			 }
			 */
			
			
	

