package com.Ramiro.backendspringboot.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data   /* Genera todos los metodos GET, SET, etc */
@NoArgsConstructor  /* Constructor sin argumentos */
@AllArgsConstructor /* Constructor con argumentos */

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="account_number", nullable=false, unique=true)
    private String accountNumber;
    @Column(name="balance", nullable=false)
    private BigDecimal balance;
    @Column(name="ownerName", nullable=false)
    private String ownerName;
    @Column(name="ownerEmail", nullable=false)
    private String ownerEmail;
    @Column(name="createdAt")
    private LocalDate createdAt;
    @Column(name="updatedAt")
    private LocalDate updatedAt;
}
