package com.simplepaymentplatform.response;

import com.simplepaymentplatform.domain.user.User;
import com.simplepaymentplatform.domain.user.UserType;
import com.simplepaymentplatform.dto.UserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UserResponseBody {

    private String firstName;
    private String lastName;
    private String email;
    private String document;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public UserResponseBody(UserDTO userData) {
        this.firstName = userData.firstName();
        this.lastName = userData.lastName();
        this.email = userData.email();
        this.document = userData.document();
        this.userType = userData.userType();
        this.balance = userData.balance();
    }

    public UserResponseBody(User userData) {
        this.firstName = userData.getFirstName();
        this.lastName = userData.getLastName();
        this.email = userData.getEmail();
        this.document = userData.getDocument();
        this.userType = userData.getUserType();
        this.balance = userData.getBalance();
    }
}
