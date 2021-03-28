package com.shehan.authservice.authservice.repository;

import com.shehan.authservice.authservice.models.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    @Query("{'username' : ?0, 'password' : ?1}")
    public Mono<User> findByUser(String username, String password);
    
    @Query("{'username' : ?0'}")
    public Mono<User>findByUsername(String username);
}
