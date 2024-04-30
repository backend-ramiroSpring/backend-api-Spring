package com.Ramiro.backendspringboot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private String ownerName;
    private String ownerEmail;
}

/* Aqui ya no hay las validaciones al mostrar */
