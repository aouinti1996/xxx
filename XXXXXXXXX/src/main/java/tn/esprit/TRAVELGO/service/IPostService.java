package tn.esprit.TRAVELGO.service;

import tn.esprit.TRAVELGO.entities.Post;
import tn.esprit.TRAVELGO.entities.User;

public interface IPostService {
    Post addPost(Post p, User user, String id);
}
