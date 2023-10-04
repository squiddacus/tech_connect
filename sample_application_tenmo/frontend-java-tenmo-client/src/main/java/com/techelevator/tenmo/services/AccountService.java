package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

import java.math.BigDecimal;

public class AccountService extends AuthenticatedApiService {
    public AccountService(String baseUrl){
        super(baseUrl);
    }
    public Account getCurrentAccount(){
        Account account = null;
        try {
            ResponseEntity<Account> response =
                    restTemplate.exchange(baseUrl + "account/detail", HttpMethod.GET,
                            makeAuthEntity(), Account.class);
            account = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return account;
    }


    public Account[] getAccounts() {
        try {
            ResponseEntity<Account[]> response =
                    restTemplate.exchange(baseUrl + "account", HttpMethod.GET,
                            makeAuthEntity(), Account[].class);
            return response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return null;
    }
}
