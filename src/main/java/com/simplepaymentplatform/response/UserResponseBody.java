package com.simplepaymentplatform.response;

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
}
