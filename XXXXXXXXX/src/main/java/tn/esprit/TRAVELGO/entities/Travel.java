package tn.esprit.TRAVELGO.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Date;
import java.util.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Travel  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Long  idTravel;
	
	 private String title ;
	 private String descreption ;
	@Temporal(TemporalType.DATE)
	 private Date dateStartTravel ;
	@Temporal(TemporalType.DATE)
	 private Date dateEndTravel ;
	 private String destination ; 
	 
	@Enumerated(EnumType.STRING)
		private travelType  travelType ;


	@OneToOne
    private Offre idOffre;



	@OneToOne(mappedBy = "idTravel")
	  private Planning idPlanning ;
	
	// m3aa e reservation

	@OneToOne(mappedBy = "idTravel")
	private   Reservation reservation;
	
	// m3aa l User


	@OneToOne(mappedBy = "idTravel")
	private User idUs;




	 
	
	
}