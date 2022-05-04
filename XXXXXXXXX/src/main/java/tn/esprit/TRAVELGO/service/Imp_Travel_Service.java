package tn.esprit.TRAVELGO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.TRAVELGO.entities.Offre;
import tn.esprit.TRAVELGO.entities.Travel;
import tn.esprit.TRAVELGO.repository.OffreRepository;
import tn.esprit.TRAVELGO.repository.TravelRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Service
public class Imp_Travel_Service implements I_Travel_Service{
	
	
	
	
	@Autowired
	TravelRepository travelrepository ;
	@Autowired
	OffreRepository  offreRepository;
	
	@Autowired
	EmailService emailService;;
	
	
	
	 @Override
		public Travel addTravel(Travel travel) {
		 		return travelrepository.save(travel);
		 }
		 
		 @Override
		 public void DeleteTravel (Long idTravel) {
			 travelrepository.deleteById(idTravel);
		 }
		 
		 @Override
		 public void Updatetravel(Travel tr, Long id ) {
			 Travel t = travelrepository.findById(id).get();
			 t.setDescreption(tr.getDescreption());
			 t.setDestination(tr.getDestination());
			 t.setTitle(tr.getTitle());
			 t.setDateEndTravel(tr.getDateEndTravel());
			 t.setDateStartTravel(tr.getDateStartTravel());
			 travelrepository.save(t) ;
		 }
		 
		 @Override
		 public Travel retrieveTravel(Long id ) {
			 Travel travel = travelrepository.findById(id).get();
			 return travel ;
		 }
	 	 
		 @Override
		 public List<Travel> retrieveAllTravels(){
			 List<Travel> travel =  (List<Travel>) travelrepository.findAll();
			 return travel ;
		 }
		 
		 
		 @Override
			public void affecterOffre( Long idTravel, Long idOffre) {
				
				Travel t=travelrepository.findById(idTravel).get();
				Offre o =offreRepository.findById(idOffre).get();
				t.setIdOffre(o);
				travelrepository.save(t);
			
			}
		 
		 
		 
		 
		 @Override
		public List<Travel> travelsBetweenTwoDates(Travel t, Date startDate, Date endDate) {
			List<Travel> listTravel= (List<Travel>) travelrepository.findAll();
			List<Travel> listTravelBetweenDates = new ArrayList<>();
			for(int i=0;i<listTravel.size();i++)
			{
				if (listTravel.get(i).getDateStartTravel().getTime() >= startDate.getTime() && listTravel.get(i).getDateEndTravel().getTime() <= endDate.getTime()) {
				listTravelBetweenDates.add(listTravel.get(i));	
				}
			}
			
			return listTravelBetweenDates;
			
			   
		  }
		 
		 
	/* 
	 @Override
     public void alertFinTravel (){

        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date (0);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int monthToDay = localDate.getMonthValue();
        int dayToDay = localDate.getDayOfMonth();

        List<Travel> listTravel= (List<Travel>) travelrepository.findAll() ;
       for(Travel tr : listTravel  ){
             Date dateTravel = (Date) tr.getDateEndTravel();
             User  userTravel = (User) tr.getIdUs();

               Calendar cal = Calendar.getInstance();
               cal.setTime(dateTravel);
               int monthTravel = cal.get(Calendar.MONTH);
               int dayTravel = cal.get(Calendar.DAY_OF_MONTH);
               
              if ((monthToDay == monthTravel) && ( dayToDay-1 == dayTravel) ){
                  System.out.println("ce Travel apres un jour va termine "+ tr.getIdTravel());
                  System.out.println(((Travel) userTravel).getIdUs());
                  
                   SimpleMailMessage mailMessage = new SimpleMailMessage();
					mailMessage.setTo(userTravel.getEmail());
					mailMessage.setSubject("Notification from TRAVELGO");
					mailMessage.setFrom("sami.riahi@esprit.tn");
					mailMessage.setText( " votre Travel  "+tr.getDestination() +" ca sera  termine apres un jour Monsieur/Madame :  ", " allert de site Travel_GO ");
					emailService.sendEmail(mailMessage);
                  
                  
                  
                  //service.sendSimpleEmail("sami.riahi@esprit.tn", " votre Travel  "+tr.getDestination() +" ca sera  termine apres un jour Monsieur/Madame :  ", " allert de site Travel_GO ");

              }
        }
    }
*/
		 

}
