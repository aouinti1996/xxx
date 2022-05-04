package tn.esprit.TRAVELGO.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String username;
    private String password;
    private String companyName;
    private String email;
    private String localisationCompany;
    private String adressUser;
    private int phoneNumberUser;
    private boolean isEnable;
    Long idCompany;
    @Enumerated(EnumType.STRING)
    private SexeType sexeUser;
    @ManyToMany(fetch = FetchType.EAGER )
    private Collection<Role> roles = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private Set<ImageUser> ImageUser1;

    @OneToOne
    private Travel idTravel ;

    @OneToOne
    private Reservation reservation;


    /******************************************************WISSEM****************************************/


    /******************************************************WISSEM****************************************/


    /******************************************************WAEL****************************************/
    @ManyToMany
    List<Reunion> reunions;
    @OneToMany (mappedBy="user")
    List<Reunion> ReunionsCompany;
    @ManyToOne
    Domain domain;
    @ManyToOne
    Profession profession;
    @OneToMany(mappedBy="user")
    List<Formation> formations;
    private int acceptedReports;
    private int rejectedReports;
    private int submittedReports;
    /******************************************************WAEL******************************************/

    /******************************************************lotfi******************************************/



    /******************************************************lotfi******************************************/
}
