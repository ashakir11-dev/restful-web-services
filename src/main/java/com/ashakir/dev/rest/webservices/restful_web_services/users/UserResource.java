package com.ashakir.dev.rest.webservices.restful_web_services.users;

import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResource {
    private UserDaoService userDaoService;
    private MessageSource messageSource;

    public UserResource(UserDaoService userService, MessageSource messageSource) {
        this.userDaoService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    private EntityModel<User> getUserById(@PathVariable int id){
        User user = userDaoService.findById(id);
        if(user == null)
            throw new UserNotFoundException("ID: " + id);

        EntityModel<User> entity = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entity.add(link.withRel("all-users"));

        return entity;
    }

    @GetMapping("/users/name/{name}")
    private User getUserByName(@PathVariable String name){
        return userDaoService.findByName(name);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        if(userDaoService.save(user)){
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }


        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userDaoService.deleteById(id);
    }

    @GetMapping("/users/header")
    public String userHeader() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("user.message",null,locale);
    }

    
}
