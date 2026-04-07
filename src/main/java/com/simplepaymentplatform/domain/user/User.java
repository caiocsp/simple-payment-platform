package com.simplepaymentplatform.domain.user;

import com.simplepaymentplatform.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String document;
    @Column
    private String password; //TODO: auth features
    @Column
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDTO userData) {
        this.firstName = userData.firstName();
        this.lastName = userData.lastName();
        this.email = userData.email();
        this.password = userData.password();
        this.document = userData.document();
        this.userType = userData.userType();
        this.balance = userData.balance();
    }
}
