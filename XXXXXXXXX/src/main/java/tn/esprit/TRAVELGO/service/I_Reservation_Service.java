package tn.esprit.TRAVELGO.service;

import tn.esprit.TRAVELGO.entities.Reservation;

import java.util.List;

public interface I_Reservation_Service {
	 
	 public void addReservation(Reservation reserv) ;
	 public void DeleteReservation (Long idReservation) ;
	 public void UpdateReservation(Reservation rv, Long id );
	 public Reservation getReservationById (Long id );
	 public List<Reservation> getAllReservations();
	 public void affecterUserToReservation(long idUs ,long idReservation);
	 public void affectReservationTotravel(long idTravel, long idReservation);
	// public void email(long idUs , long idReservation)  throws FileNotFoundException, DocumentException, MessagingException;
	 //public void Matching(long idUs ,long idTravel) ;

}
