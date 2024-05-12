package com.Ramiro.backendspringboot.service;

import com.Ramiro.backendspringboot.exception.BadRequestException;
import com.Ramiro.backendspringboot.exception.ResourceNotFoundException;
import com.Ramiro.backendspringboot.mapper.TransactionMapper;
import com.Ramiro.backendspringboot.model.dto.TransactionReportDTO;
import com.Ramiro.backendspringboot.model.dto.TransactionRequestDTO;
import com.Ramiro.backendspringboot.model.dto.TransactionResponseDTO;
import com.Ramiro.backendspringboot.model.entity.Account;
import com.Ramiro.backendspringboot.model.entity.Transaction;
import com.Ramiro.backendspringboot.repository.AccountRepository;
import com.Ramiro.backendspringboot.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;

    @Transactional(readOnly = true)
    public List<TransactionResponseDTO> getTransactionsByAccountNumber(String accountNumber){
        List<Transaction> transactions = transactionRepository.findBySourceOrTargetAccountNumber(accountNumber);
        return transactionMapper.convertToListDTO(transactions);
    }

    @Transactional
    public TransactionResponseDTO createTransaction(TransactionRequestDTO transactionDTO){
        // Obtener las cuentas involucradas en la transaccion y verificar si las cuentas existen
        Account sourceAccount = accountRepository.findByAccountNumber(transactionDTO.getSourceAccountNumber())
                .orElseThrow(() -> new ResourceNotFoundException("La cuenta de destino no existe"));

        Account targetAccount = accountRepository.findByAccountNumber(transactionDTO.getSourceAccountNumber())
                .orElseThrow(()-> new ResourceNotFoundException("La cuenta de destino no existe"));

        // Verificar si el saldo de la cuenta de origen es suficiente para realizar la transaccion
        BigDecimal amount = transactionDTO.getAmount();
        BigDecimal sourceAccountBalance = sourceAccount.getBalance();
        if(sourceAccountBalance.compareTo(amount) < 0){
            throw new BadRequestException("Saldo insuficiente en la cuenta de origen");
        }

        // Realizar la transaccion
        Transaction transaction = transactionMapper.convertToEntity(transactionDTO);
        transaction.setTransaction_date(LocalDate.now());
        transaction.setSourceAccount(sourceAccount);
        transaction.setTargetAccount(targetAccount);
        transaction = transactionRepository.save(transaction);

        // Actualizar los saldos de las cuentas
        BigDecimal newSourceAccountBalance = sourceAccountBalance.subtract(amount);
        BigDecimal targetAccountBalance = targetAccount.getBalance().add(amount);

        sourceAccount.setBalance(newSourceAccountBalance);
        targetAccount.setBalance(targetAccountBalance);

        // Guardar los cambios en las cuentas
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        return transactionMapper.convertToDTO(transaction);
    }

    /*@Transactional(readOnly = true)
    public List<TransactionReportDTO> generateTransactionReport(String startDateStr, String endDateStr, String accountNumber){
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        List<Object[]> transactionCounts = transactionRepository.getTransactionCountByDateRangeAndAccountNumber(startDate, endDate, accountNumber);
        return transactionCounts.stream().map(transactionMapper::convertTransactionReportDTO).toList();
    }*/
}
