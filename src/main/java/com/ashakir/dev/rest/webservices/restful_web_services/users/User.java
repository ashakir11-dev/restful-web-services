package com.ashakir.dev.rest.webservices.restful_web_services.users;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.List;

public class User {
    private int id;
    private List<String> posts;
    @Min(value = 2,message = "Name too short")
    private String name;
    @Past(message = "Birthdate must be in the past")
    private LocalDate birthDate;

    public User(int id, List<String> posts, String name, LocalDate birthDate) {
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

    public List<String> getPosts() {
        return posts;
    }

    public void setPosts(List<String> posts) {
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
