package com.erich.dev.userservice.repository;

import com.erich.dev.userservice.entity.UserTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction, Long>{

    Flux<UserTransaction> findByUserId(Long userId);

}
