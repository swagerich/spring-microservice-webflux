package com.erich.dev.userservice.services.impl;

import com.erich.dev.userservice.dto.TransactionStatus;
import com.erich.dev.userservice.dto.UserTransactionDto;
import com.erich.dev.userservice.dto.request.TransactionRequestDto;
import com.erich.dev.userservice.dto.response.TransactionResponseDto;
import com.erich.dev.userservice.entity.UserTransaction;
import com.erich.dev.userservice.repository.UserRepository;
import com.erich.dev.userservice.repository.UserTransactionRepository;
import com.erich.dev.userservice.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final UserTransactionRepository userTransactionRepository;

    private final UserRepository userRepository;

    @Override
    public Mono<TransactionResponseDto> createTransaction(TransactionRequestDto requestDto) {
        return userRepository.updateUserBalance(requestDto.getUserId(), requestDto.getAmount())
                        .filter(Boolean::booleanValue)
                        .map(userT -> UserTransactionDto.toEntity(requestDto))
                        .flatMap(userTransaction -> userTransactionRepository.save(userTransaction))
                        .map(dto -> UserTransactionDto.toResponseDto(requestDto, TransactionStatus.APPROVED))
                        .defaultIfEmpty(UserTransactionDto.toResponseDto(requestDto, TransactionStatus.DECLINED));
    }

    @Override
    public Flux<UserTransaction> fetchTransactionsByUserId(Long userId) {
        return userTransactionRepository.findByUserId(userId);
    }
}
