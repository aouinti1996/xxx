package tn.esprit.TRAVELGO.service;

import tn.esprit.TRAVELGO.entities.Offre;

import java.util.List;

public interface I_Offre_Service  {
	public void addOffre(Offre offre);
	public void DeleteOffre (Long idOffre) ;
	public void Updateoffre(Offre of, Long id ) ;
	public Offre retrieveOffre(Long id );
	public List<Offre> retrieveAllOffres() ;

}
