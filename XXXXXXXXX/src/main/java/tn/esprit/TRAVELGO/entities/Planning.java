package tn.esprit.TRAVELGO.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Planning {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Long  idPlanning;
	@Temporal(TemporalType.DATE)
	 private Date dateFlight;
	 private String logement ;


	 @OneToOne
	    private Travel idTravel ;

	
	 
	 
	

}
