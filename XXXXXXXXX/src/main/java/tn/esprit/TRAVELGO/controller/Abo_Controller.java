package tn.esprit.TRAVELGO.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.TRAVELGO.entities.Abonnement;
import tn.esprit.TRAVELGO.entities.ResourceNotFoundException;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.repository.Abo_Repository;
import tn.esprit.TRAVELGO.repository.UserRepository;
import tn.esprit.TRAVELGO.service.AbonnementService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class Abo_Controller {


    @Autowired
    private UserRepository userRepository;
    

    @Autowired
    private Abo_Repository AboRepository;
    @Autowired
    AbonnementService as;
    

    @GetMapping("/Follow")
    public Page<Abonnement> getAllPosts(Pageable pageable) {
        return (Page<Abonnement>) AboRepository.findAll();
    }
    



   

    @DeleteMapping("/Follow/{id-Abo}")
    public ResponseEntity<?> deleteSub(@PathVariable(value = "id-Abo") Long id) {
        return AboRepository.findById(id).map(abonnement -> {
        	AboRepository.delete(abonnement);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Abonnemt id " + id + " not found"));
    }

    @GetMapping("/Follow/User/{id-user}")
    public List<User> abonnementByUser(@PathVariable(value = "id-user") Long id) {
    	User user = userRepository.findById(id).orElse(null);
        return  (List<User>) AboRepository.findFollowers(user);
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/users/follow/{id-user}/{id-user2}")
    public Abonnement Subscribe(@Valid @RequestBody Abonnement abonnement, @PathVariable(value = "id-user") Long id1,@PathVariable(value = "id-user2") Long id2) {

        User user = userRepository.findById(id1).orElse(null);
        User user2 = userRepository.findById(id2).orElse(null);
        abonnement.setFollowed(user);
        abonnement.setFolowers(user2);
        return AboRepository.save(abonnement);
    }
   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/users/follow/{id-user}")
    public ResponseEntity<Abonnement> saveNews(@Valid  Abonnement a, User user ,@PathVariable(value = "id-user") Long id){
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/news/save").toUriString());
        return ResponseEntity.created(uri).body(as.addAbonnement(a,user,id));
    }





}