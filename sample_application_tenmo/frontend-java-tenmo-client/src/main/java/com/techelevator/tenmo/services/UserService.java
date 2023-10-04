package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

public class UserService extends AuthenticatedApiService  {
    public UserService(String baseUrl){
        super(baseUrl);
    }

    public User[] getAllUsers() {

        try {
            ResponseEntity<User[]> response =
                    restTemplate.exchange(baseUrl + "user", HttpMethod.GET,
                            makeAuthEntity(), User[].class);
            return response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return null;
    }
}
