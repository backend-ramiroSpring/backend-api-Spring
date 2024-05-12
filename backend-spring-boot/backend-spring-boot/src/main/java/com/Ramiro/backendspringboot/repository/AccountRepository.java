package com.Ramiro.backendspringboot.repository;

import com.Ramiro.backendspringboot.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);    /* Optional -> al manejar recurso se ve una opcion alternativa */
}

/* Heredamos el jpa<NombreEntidad. TipoDeDatoDeID> */