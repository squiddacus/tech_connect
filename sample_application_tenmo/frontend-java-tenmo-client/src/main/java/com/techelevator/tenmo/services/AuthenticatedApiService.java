package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class AuthenticatedApiService {

    protected final String baseUrl;
    protected final RestTemplate restTemplate = new RestTemplate();
    protected AuthenticatedUser authUser;

    public AuthenticatedApiService(String baseUrl){
        this.baseUrl = baseUrl;
    }

    protected HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authUser.getToken());
        return new HttpEntity<>(headers);
    }
    protected HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(this.authUser.getToken());
        return headers;
    }

    public void setAuthUser(AuthenticatedUser authUser){
        this.authUser = authUser;
    }
}
