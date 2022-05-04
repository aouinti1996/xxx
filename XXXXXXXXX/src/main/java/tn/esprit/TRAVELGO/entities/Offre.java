package tn.esprit.TRAVELGO.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;






@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Offre {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idOffre; // Clé primaire	
	@Temporal(TemporalType.DATE)
	private Date startDateOffre ;
	@Temporal(TemporalType.DATE)
	private Date endDateOffre ;
	
	@Enumerated(EnumType.STRING)
	private offreType  offreType ;

	@OneToOne(mappedBy = "idOffre")
	  private Travel idTravel ;


	
}