package com.erich.dev.userservice.repository;

import com.erich.dev.userservice.entity.User;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long>{

    @Modifying
    @Query("UPDATE users SET balance = balance - :amount WHERE id = :id AND balance >=:amount")
    Mono<Boolean> updateUserBalance(Long id, Integer amount);
}
