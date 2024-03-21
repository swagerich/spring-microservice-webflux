package com.erich.dev.userservice.dto.request;

import lombok.Data;

@Data
public class TransactionRequestDto {

    private Long userId;

    private Integer amount;
}
