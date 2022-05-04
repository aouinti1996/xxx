package tn.esprit.TRAVELGO.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.TRAVELGO.entities.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation ,Long>{

}
