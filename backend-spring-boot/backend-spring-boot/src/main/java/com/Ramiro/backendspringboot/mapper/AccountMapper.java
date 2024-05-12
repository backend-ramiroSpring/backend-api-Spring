package com.Ramiro.backendspringboot.mapper;

import com.Ramiro.backendspringboot.model.dto.AccountRequestDTO;
import com.Ramiro.backendspringboot.model.dto.AccountResponseDTO;
import com.Ramiro.backendspringboot.model.entity.Account;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/* No es una entidad, repository, servicio, configuracion */
@Component  /* Cuando no cumple */
@AllArgsConstructor
public class AccountMapper {

    private final ModelMapper modelMapper;

    public Account converToEntity(AccountRequestDTO accountRequestDTO){
        return modelMapper.map(accountRequestDTO, Account.class);
    }

    public AccountResponseDTO converToDTO(Account account){
        return modelMapper.map(account, AccountResponseDTO.class);
    }

    public List<AccountResponseDTO> converToListDTO(List<Account> accounts){

        return accounts.stream()
                .map(this::converToDTO)
                .toList();
    }
}

/* Aqui existe una clase y los objetos yo me encargo de gestionarlos */
/* El metodo convierte el DTO en una entidad cuenta */
/* El accountRequestDTO lo convierte a Account.class */