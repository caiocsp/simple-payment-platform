package com.simplepaymentplatform.service;

import com.simplepaymentplatform.domain.user.User;
import com.simplepaymentplatform.domain.user.UserType;
import com.simplepaymentplatform.dto.TransactionDTO;
import com.simplepaymentplatform.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UserService userService;

    @Mock
    private AuthorizationService  authorizationService;

    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @Test
    @DisplayName("Should create a transaction successfully when everything is right")
    void createTransactionSuccess() throws Exception {
        User sender = new User(1L, "Ichigo", "Kurosaki", "ichigo.kurosaki@teste.com.br", "12345678901", "@123456", new BigDecimal("10"), UserType.COMMON);
        User receiver = new User(2L, "Naruto", "Uzumaki", "gabriel.luna@teste.com.br", "12345678902", "@78910", new BigDecimal("10"), UserType.COMMON);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);
        when(authorizationService.authorizeTransaction(sender, sender.getBalance())).thenReturn(true);

        TransactionDTO transactionRequest = new TransactionDTO(new BigDecimal("10"), 1L, 2L);
        transactionService.createTransaction(transactionRequest);

        verify(transactionRepository, times(1)).save(any());

        sender.setBalance(BigDecimal.ZERO);
        verify(userService, times(1)).saveUser(sender);

        receiver.setBalance(new BigDecimal("20"));
        verify(userService, times(1)).saveUser(receiver);
    }

    @Test
    @DisplayName("Should throws an exception when transaction is not allowed")
    void createTransactionError() throws Exception {

        User sender = new User(1L, "Ichigo", "Kurosaki", "ichigo.kurosaki@teste.com.br", "12345678901", "@123456", new BigDecimal("10"), UserType.COMMON);
        User receiver = new User(2L, "Naruto", "Uzumaki", "gabriel.luna@teste.com.br", "12345678902", "@78910", new BigDecimal("10"), UserType.COMMON);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);
        when(authorizationService.authorizeTransaction(any(), any())).thenReturn(false);

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            TransactionDTO transactionRequest = new TransactionDTO(new BigDecimal("10"), 1L, 2L);
            transactionService.createTransaction(transactionRequest);
        });

        Assertions.assertEquals("Transação não autorizada!",  thrown.getMessage());
    }
}