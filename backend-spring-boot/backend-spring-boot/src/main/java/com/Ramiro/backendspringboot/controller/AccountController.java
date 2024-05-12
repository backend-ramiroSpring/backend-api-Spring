package com.Ramiro.backendspringboot.controller;

import com.Ramiro.backendspringboot.model.dto.AccountRequestDTO;
import com.Ramiro.backendspringboot.model.dto.AccountResponseDTO;
import com.Ramiro.backendspringboot.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// http://localhost:8080/api/v1/accounts
@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAllAccount(){
        List<AccountResponseDTO> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    // http://localhost:8080/api/v1/accounts/4
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id){
        AccountResponseDTO account = accountService.getAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@Validated @RequestBody AccountRequestDTO accountDTO){
        AccountResponseDTO createdAccount = accountService.createAccount(accountDTO);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> updateAccount(@PathVariable Long id, @Validated @RequestBody AccountRequestDTO accountDTO){
        AccountResponseDTO updatedAccount = accountService.updateAccount(id, accountDTO);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
