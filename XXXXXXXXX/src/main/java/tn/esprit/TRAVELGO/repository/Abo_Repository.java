package tn.esprit.TRAVELGO.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.TRAVELGO.entities.Abonnement;
import tn.esprit.TRAVELGO.entities.User;

import java.util.List;
import java.util.Optional;



@Repository
public interface Abo_Repository extends CrudRepository<Abonnement,Long>  {
	
	Optional<Abonnement> findById(Long id);
	
	
	@Query("SELECT followed FROM Abonnement")
    List<User> findAllFollowedUsers();
	
	@Query("SELECT a.folowers FROM Abonnement a WHERE a.followed = :user")
    List<User> findFollowers(User user);
}
