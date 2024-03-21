package com.erich.dev.userservice.controller;

import com.erich.dev.userservice.dto.request.TransactionRequestDto;
import com.erich.dev.userservice.dto.response.TransactionResponseDto;
import com.erich.dev.userservice.entity.UserTransaction;
import com.erich.dev.userservice.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {


    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public Mono<TransactionResponseDto> transfer(@RequestBody Mono<TransactionRequestDto> requestDto) {
        return requestDto.flatMap(transactionService::createTransaction);
    }

    @GetMapping("/history/{userId}")
    public Flux<UserTransaction> history(@PathVariable Long userId) {
       return  transactionService.fetchTransactionsByUserId(userId);
    }
}
