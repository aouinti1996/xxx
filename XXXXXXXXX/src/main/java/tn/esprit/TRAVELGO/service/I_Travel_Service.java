package tn.esprit.TRAVELGO.service;

import tn.esprit.TRAVELGO.entities.Travel;

import java.sql.Date;
import java.util.List;

public interface I_Travel_Service {

	public Travel addTravel(Travel travel);
	public void DeleteTravel (Long idTravel);
	public void Updatetravel(Travel tr, Long id );
	
	public void affecterOffre(Long idTravel, Long idOffre);
	public List<Travel> retrieveAllTravels();
	public Travel retrieveTravel(Long id );
    //public void alertFinTravel();
	public List<Travel> travelsBetweenTwoDates(Travel t, Date startDate, Date endDate);

	
}
