package com.simplepaymentplatform.repository;

import com.simplepaymentplatform.domain.user.User;
import com.simplepaymentplatform.domain.user.UserType;
import com.simplepaymentplatform.dto.UserDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should get user successfully from db")
    void findByDocumentSuccess() {
        UserDTO newDTO = new UserDTO("Caio", "César", "123.456.789-01", new BigDecimal("785.25"), "caio.cesar@teste.com.br", "@123456", UserType.COMMON);
        User createdUser = createTestUser(newDTO);

        Optional<User> foundedUser =  this.userRepository.findByDocument(createdUser.getDocument());

        assertThat(foundedUser.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get user when the data don't exists")
    void findByDocumentError() {
        String document = "789.456.123-01";
        Optional<User> notFoundedUser =  this.userRepository.findByDocument(document);
        assertThat(notFoundedUser.isEmpty()).isTrue();
    }

    private User createTestUser(UserDTO userDTO) {
        User newUser = new User(userDTO);
        entityManager.persist(newUser);
        return newUser;
    }
}