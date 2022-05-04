package tn.esprit.TRAVELGO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.TRAVELGO.entities.Offre;
import tn.esprit.TRAVELGO.repository.OffreRepository;

import java.util.List;

@Service
public class Imp_Offre_Service implements I_Offre_Service {
	
	
	

	@Autowired
	OffreRepository offreRepository ;
	
	
	@Override
	public void addOffre(Offre offre) {
		 offreRepository.save(offre)  ;
		 }
	
	 @Override
	 public void DeleteOffre (Long idOffre) {
		 offreRepository.deleteById(idOffre);
	 }
	
	 @Override
	 public void Updateoffre(Offre of, Long id ) {
		 Offre o = offreRepository.findById(id).get();
		 o.setEndDateOffre(of.getEndDateOffre());
		 o.setStartDateOffre(of.getStartDateOffre());
		 o.setOffreType(of.getOffreType());
		 o.setIdTravel(of.getIdTravel());
		 offreRepository.save(o) ;
	 }
	
	 @Override
	 public Offre retrieveOffre(Long id ) {
		 Offre offre = offreRepository.findById(id).get();
		 return offre ;
	 }
 	 
	 @Override
	 public List<Offre> retrieveAllOffres(){
		 List<Offre> offre =  (List<Offre>) offreRepository.findAll();
		 return offre ;
	 }

}
