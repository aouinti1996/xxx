package tn.esprit.TRAVELGO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.TRAVELGO.entities.Planning;
import tn.esprit.TRAVELGO.entities.Travel;
import tn.esprit.TRAVELGO.repository.PlanningRepository;
import tn.esprit.TRAVELGO.repository.TravelRepository;

import java.util.List;

@Service
public class Imp_Planning_Service implements I_Planning_Service {
	
	@Autowired
	TravelRepository travelrepository ;
	@Autowired
	PlanningRepository  planningRepository;
	
	 @Override
		public void addPlanning(Planning planning) {
		 planningRepository.save(planning);
		 }
		 
		 @Override
		 public void DeletePlanning (Long idPlanning) {
			 planningRepository.deleteById(idPlanning);
		 }
		 
		 @Override
		 public void Updatetravel(Planning pl, Long id ) {
			 Planning p = planningRepository.findById(id).get();
			 p.setDateFlight(pl.getDateFlight());
			 p.setLogement(pl.getLogement());
			 planningRepository.save(p) ;
		 }
		 
		 @Override
		 public Planning retrievePlanning(Long id ) {
			 Planning planning = planningRepository.findById(id).get();
			 return planning ;
		 }
	 	 
		 @Override
		 public List<Planning> retrieveAllTravels(){
			 List<Planning> planning =  (List<Planning>) planningRepository.findAll();
			 return planning ;
		 }
		 
		 
		 @Override
			public void affecterPlanning( Long idTravel, Long idPlanning) {
				Travel t=travelrepository.findById(idTravel).get();
				Planning p =planningRepository.findById(idPlanning).get();
				p.setIdTravel(t);;
				planningRepository.save(p);
			
			}

}
