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
public class Reservation  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idReservation ;
	@Temporal(TemporalType.DATE)
	private Date datereservation ;


	@OneToOne
	private Travel idTravel ;


	 @OneToOne
     private User idUs;


}
	