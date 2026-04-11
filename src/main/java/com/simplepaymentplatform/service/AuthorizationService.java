package com.simplepaymentplatform.service;

import com.simplepaymentplatform.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class AuthorizationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    public boolean authorizeTransaction(User sender, BigDecimal value) throws Exception {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(env.getProperty("external.auth.tool.url", ""), Map.class);
        if (authorizationResponse.getStatusCode().equals(HttpStatus.OK)
                && authorizationResponse.getBody() != null) {
            String message = authorizationResponse.getBody().get("status").toString();
            return message.equalsIgnoreCase("Success");
        }
        return false;
    }
}
