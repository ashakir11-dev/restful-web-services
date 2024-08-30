package com.ashakir.dev.rest.webservices.restful_web_services.users;

import com.ashakir.dev.rest.webservices.restful_web_services.post.Post;
import com.ashakir.dev.rest.webservices.restful_web_services.post.PostRepositery;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResourceJPA {
    private MessageSource messageSource;
    private UserRepositery userRepositery;
    private PostRepositery postRepositery;
    public UserResourceJPA(UserRepositery userRepositery, MessageSource messageSource, PostRepositery postRepositery) {
        this.userRepositery = userRepositery;
        this.messageSource = messageSource;
        this.postRepositery = postRepositery;
    }

    @GetMapping("/jpa/users")
    List<User> getAllUsers(){
        return userRepositery.findAll();
    }

    @GetMapping("/jpa/users/id/{id}")
    private EntityModel<Optional<User>> getUserById(@PathVariable int id){
        Optional<User> user = userRepositery.findById(id);
        if(user == null)
            throw new UserNotFoundException("ID: " + id);

        EntityModel<Optional<User>> entity = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entity.add(link.withRel("all-users"));

        return entity;
    }

    @GetMapping("/jpa/users/{name}")
    private User getUserByName(@PathVariable String name){
        return userRepositery.findByName(name);
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        userRepositery.save(user);
        URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepositery.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getUserPosts(@PathVariable int id){
        Optional<User> user = userRepositery.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("User does not exist");

        return user.get().getPosts();
    }
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createUserPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> user = userRepositery.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("User does not exist");
        post.setUser(user.get());

        postRepositery.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
//
//    @GetMapping("/users/header")
//    public String userHeader() {
//        Locale locale = LocaleContextHolder.getLocale();
//        return messageSource.getMessage("user.message",null,locale);
//    }

    
}
