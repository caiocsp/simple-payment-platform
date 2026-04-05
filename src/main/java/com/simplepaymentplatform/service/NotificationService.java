package com.simplepaymentplatform.service;

import com.simplepaymentplatform.domain.user.User;
import com.simplepaymentplatform.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    public void sendNotification(User user, String message) {
     String email  = user.getEmail();
     NotificationDTO notificationRequest = new NotificationDTO(email, message);

     ResponseEntity<String> notificationResponse = restTemplate.postForEntity(env.getProperty("external.notification.tool.url", ""), notificationRequest, String.class);

     if (!notificationResponse.getStatusCode().equals(HttpStatus.OK)
        &&  notificationResponse.getBody() == null) {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Serviço de notificação indisponível!");
     }
    }
}
