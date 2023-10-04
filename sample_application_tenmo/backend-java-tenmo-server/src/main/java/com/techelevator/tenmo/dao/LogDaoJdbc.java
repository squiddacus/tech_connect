package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.PrintWriter;
import java.io.StringWriter;

@Repository
public class LogDaoJdbc implements LogDao{
    private final JdbcTemplate jdbcTemplate;

    public LogDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void logError(User user, Account account, String logType, String message, Exception ex) {
        String stackTrace = null;
        long userId = 0;
        long accountId = 0;
        if (user != null){
            userId = user.getId();
        }
        if (account != null){
            accountId = account.getId();
        }

        if (ex != null){
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            stackTrace = sw.toString();
        }
        String sql = """                
                insert into log_error (user_id, account_id, log_type, log_message, stack_trace)
                    values
                        (?,?,?,?,?)
                            """;
        jdbcTemplate.update(sql, userId, accountId, logType, message, stackTrace);
    }

    @Override
    public void logAudit(User user, Account account, String auditCode, String message) {
        long userId = 0;
        long accountId = 0;
        if (user != null){
            userId = user.getId();
        }
        if (account != null){
            accountId = account.getId();
        }
        String sql = " insert into log_audit (user_id, account_id, log_audit, log_message) " +
                     "  values (?,?,?,?) ";
        jdbcTemplate.update(sql,userId, accountId, auditCode,message);
    }
}
