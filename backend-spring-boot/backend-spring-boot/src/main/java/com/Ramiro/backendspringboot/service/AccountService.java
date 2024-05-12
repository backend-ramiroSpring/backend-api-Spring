package com.Ramiro.backendspringboot.service;

import com.Ramiro.backendspringboot.exception.ResourceNotFoundException;
import com.Ramiro.backendspringboot.mapper.AccountMapper;
import com.Ramiro.backendspringboot.model.dto.AccountRequestDTO;
import com.Ramiro.backendspringboot.model.dto.AccountResponseDTO;
import com.Ramiro.backendspringboot.model.entity.Account;
import com.Ramiro.backendspringboot.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Transactional(readOnly = true)

    /* Metodos */
    /* Para recuperar la informacion de las cuentas */
    public List<AccountResponseDTO> getAllAccounts(){
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.converToListDTO(accounts);
    }

    @Transactional(readOnly = true)
    /* Metodo -> Informacion de una cuenta pero por su ID */
    public AccountResponseDTO getAccountById(Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Cuenta no encontrada con el numero: "+id));     /* Si no se encontrara se usa ello */
        return accountMapper.converToDTO(account);
    }

    @Transactional
    /* Metodo -> para crear una cuenta */
    public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO){
        Account account = accountMapper.converToEntity(accountRequestDTO);
        account.setCreatedAt(LocalDate.now());
        accountRepository.save(account);
        return accountMapper.converToDTO(account);
    }

    @Transactional
    /* Actualizar */
    public AccountResponseDTO updateAccount(Long id, AccountRequestDTO accountRequestDTO){
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Cuenta no encontrada con el numero: "+id));
        account.setAccountNumber(accountRequestDTO.getAccountNumber());
        account.setBalance(accountRequestDTO.getBalance());
        account.setOwnerName(accountRequestDTO.getOwnerName());
        account.setOwnerEmail(accountRequestDTO.getOwnerEmail());
        account.setUpdatedAt(LocalDate.now());

        account = accountRepository.save(account);
        return accountMapper.converToDTO(account);
    }

    @Transactional
    /* Eliminar una cuenta */
    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }
}
