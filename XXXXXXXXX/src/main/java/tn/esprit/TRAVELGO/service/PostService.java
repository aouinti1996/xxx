package tn.esprit.TRAVELGO.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tn.esprit.TRAVELGO.entities.Post;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.model.FileDB;
import tn.esprit.TRAVELGO.repository.FileDBRepository;
import tn.esprit.TRAVELGO.repository.PostRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;

import javax.transaction.Transactional;


@Service
@Transactional
@Slf4j
public class PostService implements IPostService{

    @Autowired
    PostRepository pr;
    @Autowired
    UserRepository userrepository;
    @Autowired
    private FileDBRepository fileDBRepository;
    @Override
    public Post addPost(Post p, User user, String id) {
        User existingUser = userrepository.findByUsername(user.getUsername());
        User user11 = new User();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            user11= userrepository.findByUsername(username);
        } else {
            String username = principal.toString();
            user11= userrepository.findByUsername(username);
        }
        if(existingUser != null)
        {
            log.info("This email already exists!");
        }else {
            p.setIdUser(user11.getId());
            FileDB file = fileDBRepository.findById(id).orElse(null);
            p.setFile(file);
            pr.save(p);
            log.info("saving new news");
        }
        return p;
    }
}
