package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.TransferException;
import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.techelevator.tenmo.dao.Mappers.mapRowToAccount;
import static com.techelevator.tenmo.dao.Mappers.mapRowsToAccounts;

@Repository
public class AccountDaoJdbc implements AccountDao{

    private final JdbcTemplate jdbcTemplate;

    public AccountDaoJdbc(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Account getAccount(long userId) {
        if (userId <= 0) throw new IllegalArgumentException("UserId must be a positive integer");
        String sql = " select a.account_id " +
                     "       ,a.user_id "    +
                     "       ,a.balance "    +
                     "       ,u.username "   +
                     "   from account a "    +
                     "        join tenmo_user u "          +
                     "          on u.user_id = a.user_id " +
                     "  where a.user_id = ?; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId);
        if (results.next()) {
            return mapRowToAccount(results, true);
        }
        return null;
    }

    @Override
    public Account getAccountByAccount(int accountId) {

        if (accountId <= 0) throw new IllegalArgumentException("accountId must be a positive integer");
        String sql = " select a.account_id " +
                     "       ,a.user_id "    +
                     "       ,a.balance "    +
                     "       ,u.username  "  +
                     "   from account a "    +
                     "   join tenmo_user u " +
                     "     on u.user_id = a.user_id " +
                     "  where a.account_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,accountId);
        if (results.next()) {
            return mapRowToAccount(results, true);
        }
        return null;
    }

    @Override
    public List<Account> getAccountsByUser(long userId) {

        if (userId <= 0) throw new IllegalArgumentException("userId must be a positive integer");
        String sql = " select a.account_id " +
                     "       ,a.user_id "    +
                     "       ,a.balance " +
                     "       ,u.username " +
                     "   from account a " +
                     "   join tenmo_user u " +
                     "     on u.user_id = a.user_id " +
                     " where a.user_id != ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId);
        return mapRowsToAccounts(results);
    }

    @Override
    public Account updateAccount(Account account) {
        if (account == null) throw new IllegalArgumentException("account cannot be null");
        String sql = " update account " +
                     "    set balance = ? " +
                     " where account_id = ?;";
        int rowsUpdated = jdbcTemplate.update(sql,account.getBalance(), account.getId());
        if (rowsUpdated==1) return getAccountByAccount(account.getId());
        throw new RuntimeException("Account not updated in database");

    }
}
