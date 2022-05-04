package tn.esprit.TRAVELGO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.TRAVELGO.entities.Reservation;
import tn.esprit.TRAVELGO.service.Imp_Reservation_Service;
import tn.esprit.TRAVELGO.service.Imp_Travel_Service;
import tn.esprit.TRAVELGO.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationController {


	@Autowired
	Imp_Reservation_Service reservationService; 
	@Autowired
	Imp_Travel_Service travelService;
	@Autowired
	UserService userService;
	
	@PostMapping("/addReservation")
	@ResponseBody
	public void addReservation(@RequestBody Reservation reservation) {
		reservationService.addReservation(reservation);
	    }

		@DeleteMapping("/remove-reservation/{idReservation}")
		@ResponseBody
		public void removeReservation(@PathVariable("idReservation") long idReservation) {
			reservationService.DeleteReservation(idReservation) ;
		}
		
		@PutMapping("/modify-reservation/{idReservation}")
		@ResponseBody
		public void modifyReservation(@RequestBody Reservation rv , @PathVariable("idReservation") long id ) {
			reservationService.UpdateReservation(rv, id)	;
		}
		
		@GetMapping("/get-reservation/{idReservation}")
		@ResponseBody
		public Reservation Get(@PathVariable("idReservation")long idReservation) {
			Reservation reserv = reservationService.getReservationById(idReservation);
			return reserv ;
		
		}

		@GetMapping("/get-all-reservation")
		List<Reservation> reservation() {
		    return reservationService.getAllReservations();
		  }
		
	
	  @PutMapping("/affectReservationTotravel/{idReservation}/{idTravel}")
      @ResponseBody
      public void affectReservationTotravel(@RequestBody  @PathVariable("idReservation") long idReservation,@PathVariable("idTravel")long idTravel) {
		reservationService.affectReservationTotravel(idTravel, idReservation);
 
	  }
	  
	  
	    @PutMapping("/affecterUserToReservation/{idUs}/{idReservation}")
		@ResponseBody
		public void affecterUserToReservation(@PathVariable("idUs")long idUs,@PathVariable("idReservation") long idReservation) {
		  reservationService.affecterUserToReservation(idUs, idReservation);
	  
	  }
	  
	
}
