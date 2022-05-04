package tn.esprit.TRAVELGO.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.TRAVELGO.entities.Travel;
import tn.esprit.TRAVELGO.service.Imp_Travel_Service;

import java.sql.Date;
import java.util.List;


@RestController
@RequestMapping("/api")
public class TravelController {
	
	
	
@Autowired
Imp_Travel_Service travelService ;


@PostMapping("/addTravel")
@ResponseBody
public void addTravel(@RequestBody Travel travel) {
	travelService.addTravel(travel);
    }


	@DeleteMapping("/remove-travel/{idTravel}")
	@ResponseBody
	public void removeTravel(@PathVariable("idTravel") long idTravel) {
		travelService.DeleteTravel(idTravel) ;
	}
	
	@PutMapping("/modify-travel/{idTravel}")
	@ResponseBody
	public void modifyTravel(@RequestBody Travel tr , @PathVariable("idTravel") long id ) {
		travelService.Updatetravel(tr, id)	;
	}
	
	@GetMapping("/retrieve-travel/{idTravel}")
	@ResponseBody
	public Travel Retrieve(@PathVariable("idTravel")long travelId) {
		Travel travel = travelService.retrieveTravel(travelId);
		return travel ;
	
	}

	@GetMapping("/retrieve-all-travel")
	List<Travel> travel() {
	    return travelService.retrieveAllTravels();
	  }
	
	
	@PutMapping("/affecterOffre/{idTravel}/{idOffre}")
	@ResponseBody
	public void affectOffreTravel(@PathVariable("idTravel")long idTravel,@PathVariable("idOffre") long idOffre) {
		travelService.affecterOffre(idTravel, idOffre);
	}
	
	
	@GetMapping("/SerchBetweenTwoDates/{startDate}/{endDate}")
	@ResponseBody
	public List<Travel>travelsBetweenTwoDates(@PathVariable("startDate") Date dateStartTravel,@PathVariable("endDate")Date dateEndTravel ){
		return travelService.travelsBetweenTwoDates(  null, dateStartTravel, dateEndTravel) ;
		 
	}
	
	
   /* @GetMapping("/alertFinTravel")
    @ResponseBody
    public void alertFinTravel (){
         travelService.alertFinTravel();
    }
*/



}
