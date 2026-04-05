package com.simplepaymentplatform.controller;

import com.simplepaymentplatform.domain.user.User;
import com.simplepaymentplatform.dto.UserDTO;
import com.simplepaymentplatform.response.UserResponseBody;
import com.simplepaymentplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseBody> createUser(@RequestBody UserDTO user) throws Exception {
        UserResponseBody newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
