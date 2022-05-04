package tn.esprit.TRAVELGO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.TRAVELGO.entities.Planning;
import tn.esprit.TRAVELGO.service.Imp_Planning_Service;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlanningController {
	
	
	
	
	
	
@Autowired
Imp_Planning_Service plannigService ;


@PostMapping("/addPlanning")
@ResponseBody
public void addPlanning(@RequestBody Planning planning) {
	plannigService.addPlanning(planning);
    }


	@DeleteMapping("/remove-planning/{idPlanning}")
	@ResponseBody
	public void removePlanning(@PathVariable("idPlanning") long idPlanning) {
		plannigService.DeletePlanning(idPlanning) ;
	}
	
	@PutMapping("/modify-planning/{idPlanning}")
	@ResponseBody
	public void modifyTravel(@RequestBody Planning pl , @PathVariable("idPlanning") long id ) {
		plannigService.Updatetravel(pl, id)	;
	}
	
	@GetMapping("/retrieve-planning/{idPlanning}")
	@ResponseBody
	public Planning Retrieve(@PathVariable("idPlanning")long idPlanning) {
		Planning planning = plannigService.retrievePlanning(idPlanning);
		return planning ;
	
	}

	@GetMapping("/retrieve-all-planning")
	List<Planning>  planning() {
	    return plannigService.retrieveAllTravels();
	  }
	
	
	@PutMapping("/affecterPlanning/{idTravel}/{idPlanning}")
	@ResponseBody
	public void affecterPlanning(@PathVariable("idTravel")long idTravel,@PathVariable("idPlanning") long idPlanning) {
		plannigService.affecterPlanning(idTravel, idPlanning);
	}
	

}
