package com.simplepaymentplatform.service;

import com.simplepaymentplatform.domain.transaction.Transaction;
import com.simplepaymentplatform.domain.user.User;
import com.simplepaymentplatform.dto.TransactionDTO;
import com.simplepaymentplatform.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationService  authorizationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        if (transaction == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transação inválida!");
        }
        User sender = userService.findUserById(transaction.senderId());
        User receiver = userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        if (!authorizationService.authorizeTransaction(sender, transaction.value())) {
            throw new Exception("Transação não autorizada!");
        }

        Transaction newTransaction = Transaction.builder()
                .id(null).amount(transaction.value()).sender(sender).receiver(receiver)
                .dateCreated(LocalDateTime.now()).build();

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));
        try {
            transactionRepository.save(newTransaction);
            userService.saveUser(sender);
            userService.saveUser(receiver);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falha ao atualizar informações do usuário!");
        }
        return newTransaction;
    }
}
