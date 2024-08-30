package com.ashakir.dev.rest.webservices.restful_web_services.users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepositery extends JpaRepository<User,Integer> {
    User findByName(String name);
}
