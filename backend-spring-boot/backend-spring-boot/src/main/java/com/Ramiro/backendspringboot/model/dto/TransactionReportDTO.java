package com.Ramiro.backendspringboot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionReportDTO {
    private LocalDate date;
    private Long transactionCount;
}

/* Hagarra la fecha y la cantidad de transacciones */
