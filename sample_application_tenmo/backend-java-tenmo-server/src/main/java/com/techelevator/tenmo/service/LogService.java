package com.techelevator.tenmo.service;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;

public interface LogService {
    void logError(User user, Account account, String errorMessage, Exception ex);
    void logError(User user, Account account, String errorMessage);
    void logInfo(User user, Account account, String message);
    void logAudit(User user, Account account, String auditCode, String message);
}
