package com.simplepaymentplatform.service;

import com.simplepaymentplatform.domain.user.User;
import com.simplepaymentplatform.domain.user.UserType;
import com.simplepaymentplatform.dto.UserDTO;
import com.simplepaymentplatform.repository.UserRepository;
import com.simplepaymentplatform.response.UserResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType().equals(UserType.MERCHANT)) {
            throw new IllegalArgumentException("Usuário do tipo lojista não está autorizado a realizar transações.");
        }

        if(sender.getBalance().compareTo(amount) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception { //TODO: Use a DTO return
        return this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
    }

    public User saveUser(User user) throws Exception {
        try {
            return this.userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falha ao salvar informações do usuário!");
        }
    }

    public UserResponseBody createUser(UserDTO userData) throws Exception {
        try {
            User user = new User(userData);
            return new UserResponseBody(saveUser(user));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falha ao criar usuário!");
        }
    }

    public List<UserResponseBody> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseBody> usersResponseList = new ArrayList<>();
        for (User user : users) {
            usersResponseList.add(new UserResponseBody(user));
        }
        return usersResponseList;
    }
}
