package com.erich.dev.userservice.dto;

import com.erich.dev.userservice.dto.request.TransactionRequestDto;
import com.erich.dev.userservice.dto.response.TransactionResponseDto;
import com.erich.dev.userservice.entity.UserTransaction;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserTransactionDto {

    private Long userId;

    private Integer amount;

    private LocalDateTime localdatetime;

    public static UserTransactionDto fromEntity(UserTransaction userTransaction) {
        if (userTransaction == null) {
            return null;
        }
        return UserTransactionDto.builder()
                .userId(userTransaction.getUserId())
                .amount(userTransaction.getAmount())
                .localdatetime(LocalDateTime.now())
                .build();
    }

    public static UserTransaction toEntity(TransactionRequestDto userTransactionDto) {
        if (userTransactionDto == null) {
            return null;
        }
        return UserTransaction.builder()
                .userId(userTransactionDto.getUserId())
                .amount(userTransactionDto.getAmount())
                .localdatetime(LocalDateTime.now())
                .build();
    }

    public static TransactionResponseDto toResponseDto(TransactionRequestDto transactionRequestDto, TransactionStatus status) {
        if (transactionRequestDto == null) {
            return null;
        }
        return TransactionResponseDto.builder()
                .userId(transactionRequestDto.getUserId())
                .amount(transactionRequestDto.getAmount())
                .status(status)
                .build();
    }
}
