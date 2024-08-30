package com.ashakir.dev.rest.webservices.restful_web_services.users;

import com.ashakir.dev.rest.webservices.restful_web_services.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;


import java.time.LocalDate;
import java.util.List;
@JsonIgnoreProperties("posts")
@Entity(name = "User_details")
public class User {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    //@Min(value = 2,message = "Name too short")
    @JsonProperty("user_name")
    private String name;

    @JsonProperty("birth_date")
    @Past(message = "Birthdate must be in the past")
    private LocalDate birthDate;

    public User(){

    }

    public User(int id, List<Post> posts, String name, LocalDate birthDate) {
        this.id = id;
        this.posts = posts;
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", posts=" + posts +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
