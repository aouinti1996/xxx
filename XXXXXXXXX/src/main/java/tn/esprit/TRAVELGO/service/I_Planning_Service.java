package tn.esprit.TRAVELGO.service;

import tn.esprit.TRAVELGO.entities.Planning;

import java.util.List;

public interface I_Planning_Service {

	 public void addPlanning(Planning planning);
	 public void DeletePlanning (Long idPlanning);
	 public List<Planning> retrieveAllTravels();
	 public Planning retrievePlanning(Long id ) ;
	 public void affecterPlanning( Long idTravel, Long idPlanning);
	 public void Updatetravel(Planning pl, Long id );
}
