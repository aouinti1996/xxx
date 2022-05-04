package tn.esprit.TRAVELGO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.TRAVELGO.entities.Offre;
import tn.esprit.TRAVELGO.service.Imp_Offre_Service;

import java.util.List;
 
@RestController
@RequestMapping("/api")
public class OffreController {
	
	
	@Autowired
	Imp_Offre_Service offreService ;
	
	
	@PostMapping("/addOffre")
	@ResponseBody
	public void addOffre(@RequestBody Offre offre) {
		offreService.addOffre(offre);
		}

     
	@DeleteMapping("/remove-offre/{idOffre}")
	@ResponseBody
	public void removeOffre(@PathVariable("idOffre") Long idOffre) {
		offreService.DeleteOffre(idOffre) ;
	}

	@PutMapping("/modify-offre/{idOffre}")
	@ResponseBody
	public void modifyOffre(@RequestBody Offre of , @PathVariable("idOffre") Long id ) {
		offreService.Updateoffre(of, id);
	}

	@GetMapping("/retrieve-offre/{idOffre}")
	@ResponseBody
	public Offre Retrieve(@PathVariable("idOffre") Long offreId) {
		Offre offre = offreService.retrieveOffre(offreId);
		return offre ;
	}
	
	@GetMapping("/retrieve-all-offre")
	List<Offre> offre() {
	    return offreService.retrieveAllOffres();
	  }


}
