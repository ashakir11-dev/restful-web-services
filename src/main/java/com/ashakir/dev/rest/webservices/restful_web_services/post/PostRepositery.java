package com.ashakir.dev.rest.webservices.restful_web_services.post;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface PostRepositery extends JpaRepository<Post,Integer> {
    List<Post> findByUserId(int userId);
}
