package com.erich.dev.userservice.dto.response;


import com.erich.dev.userservice.dto.TransactionStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponseDto {

    private Long userId;

    private Integer amount;

    private TransactionStatus status;
}
