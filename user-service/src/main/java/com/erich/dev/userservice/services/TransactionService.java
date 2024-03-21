package com.erich.dev.userservice.services;

import com.erich.dev.userservice.dto.request.TransactionRequestDto;
import com.erich.dev.userservice.dto.response.TransactionResponseDto;
import com.erich.dev.userservice.entity.UserTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Mono<TransactionResponseDto> createTransaction(TransactionRequestDto requestDto);

    Flux<UserTransaction> fetchTransactionsByUserId(Long userId);
}
