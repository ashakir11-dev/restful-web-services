package com.ashakir.dev.rest.webservices.restful_web_services.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    private UserDaoService userDaoService;

    public UserResource(UserDaoService userService) {
        this.userDaoService = userService;
    }

    @GetMapping("/users")
    private List<User> getAllUsers(){
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    private User getAllUsers(@PathVariable int id){
        User user = userDaoService.findById(id);
        if(user == null)
            throw new UserNotFoundException("ID: " + id);
        return user;
    }

    @GetMapping("/users/name/{name}")
    private User getAllUsers(@PathVariable String name){
        return userDaoService.findByName(name);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
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
}
