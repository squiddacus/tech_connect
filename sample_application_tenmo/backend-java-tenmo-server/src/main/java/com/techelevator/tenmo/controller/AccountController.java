package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.service.AccountService;
import com.techelevator.tenmo.service.LogService;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
@RequestMapping("/api")
public class AccountController extends BaseController {

    private final AccountService accountService;

    public AccountController(AccountService accountService, LogService logService){
        super(logService);
        this.accountService = accountService;
    }
    //o	Account (balance) (GET /account/detail)
    @RequestMapping(path="/account/detail", method = RequestMethod.GET)
    public Account getAccountInfo(Principal principal) {
        logAPICall("HTTP GET request received for user " + principal.getName() + " with URL /account/detail");
        User user = super.getUserFromPrincipal(principal);
        return accountService.getByUser(user);
    }
    //o	Account (GET /account)
    @RequestMapping(path="/account", method = RequestMethod.GET)
    public List<Account> getAccountList(Principal principal){
        logAPICall("HTTP GET request received for user " + principal.getName() + " with URL /account");
        return accountService.getAccountListByUser(super.getUserFromPrincipal(principal));
    }
    /**
     * Helper method to log API calls made to the server
     *
     * @param message - message to be included in the server log
     */
    public void logAPICall(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.A");
        String timeNow = now.format(formatter);
        System.out.println(timeNow + "-" + message);

    }
}
