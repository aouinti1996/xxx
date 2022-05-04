package tn.esprit.TRAVELGO.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.TRAVELGO.entities.Comment;
import tn.esprit.TRAVELGO.entities.Post;
import tn.esprit.TRAVELGO.entities.ResourceNotFoundException;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.repository.CommentRepository;
import tn.esprit.TRAVELGO.repository.PostRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;
import tn.esprit.TRAVELGO.service.BadWordFilter;
import tn.esprit.TRAVELGO.service.CommentService;
import tn.esprit.TRAVELGO.service.PostService;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    CommentService cs;

    @GetMapping("/posts/{postId}/comments")
    public Page<Comment> getAllCommentsByPostId(@PathVariable (value = "postId") Long postId,
                                                Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }


    @PostMapping("/users/comment/{post_id}")
    public ResponseEntity<Comment> savecomment(@Valid @RequestBody Comment c, User user, @PathVariable(value = "post_id") Long id){
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/news/save").toUriString());
        return ResponseEntity.created(uri).body(cs.addCommentt(c,user,id));
    }



    @PutMapping("/posts/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable (value = "postId") Long postId,
                                 @PathVariable (value = "commentId") Long commentId,
                                 @Valid @RequestBody Comment commentRequest) {
        if(!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

        return commentRepository.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
           comment.setUpdatedAt(commentRequest.getUpdatedAt()) ;
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
    }

    @DeleteMapping("/users/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId,
                              @PathVariable (value = "commentId") Long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId + " and postId " + postId));
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   /*@PostMapping("/users/posts/{postId}/comments/{parentId}/{id-user}")
    public Comment addCommentToParentId(@PathVariable (value = "postId") Long postId, @Valid @RequestBody Comment comment,@PathVariable(value = "id-user") Long id,@PathVariable (value = "parentId") Long commentId) {
        Post post = postRepository.findById(postId)
                .orElse(null);
        Comment parentComment = commentRepository.findById(commentId)
                .orElse(null);
        User user = userRepository.findById(id)
                .orElse(null);
        comment.addComment(comment.getText());
        comment.setParentComment(parentComment);
        comment.setPost(post);
        comment.setUsers(user);
        return commentRepository.save(comment);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }*/


    @PostMapping("/users/posts/{post_Id}/comments/{parent_Id}")
    public ResponseEntity<Comment> savecomment2(@Valid @RequestBody Comment c, User user,@PathVariable(value = "post_Id") Long id, @PathVariable(value = "parent_Id")
            Long idc){
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/news/save").toUriString());
        return ResponseEntity.created(uri).body(cs.addCommentt2(c,user,id,idc));
    }
    
    

    
    
    
    
    
    
    
    
    
    
}