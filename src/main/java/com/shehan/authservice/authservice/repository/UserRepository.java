package com.shehan.authservice.authservice.repository;

import com.shehan.authservice.authservice.models.SystemUser;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<SystemUser, String> {
    @Query("{'username' : ?0, 'password' : ?1}")
    public Mono<SystemUser> findByUser(String username, String password);
    @Query("{'username' : ?0'}")
    public Mono<SystemUser>findByUsername(String username);
}
