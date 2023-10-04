package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.techelevator.tenmo.dao.Mappers.*;

@Repository
public class TransferDaoJdbc implements TransferDao{
    private final JdbcTemplate jdbcTemplate;

    public TransferDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Transfer> getTransfersByUser(long userId, boolean isPending) {
        if (userId < 0) throw new IllegalArgumentException("UserId must be positive");
        String sql = " select " +
                "             t.transfer_id " +
                "            ,t.transfer_type_id " +
                "            ,t.transfer_status_id " +
                "            ,t.account_from " +
                "            ,t.account_to                              " +
                "            ,t.amount " +
                "            ,ts.transfer_status_desc " +
                "            ,tt.transfer_type_desc " +
                "            ,ato.balance as balance_to " +
                "            ,afrom.balance as balance_from " +
                "            ,tuto.user_id as user_id_to " +
                "            ,tuto.username as username_to" +
                "            ,tufrom.user_id as user_id_from " +
                "            ,tufrom.username as username_from " +
                "            ,t.created_date " +
                "            ,t.balance_to as transfer_balance_to " +
                "            ,t.balance_from as transfer_balance_from " +
                "            ,t.transfer_message " +
                "            ,t.fee " +
                " from transfer t " +
                " join transfer_status ts " +
                "   on t.transfer_status_id = ts.transfer_status_id " +
                " join transfer_type tt " +
                "   on t.transfer_type_id = tt.transfer_type_id " +
                " join account afrom " +
                "   on t.account_from = afrom.account_id " +
                " join account ato " +
                "   on t.account_to = ato.account_id " +
                " join tenmo_user tuto " +
                "   on ato.user_id = tuto.user_id " +
                "  join tenmo_user tufrom  " +
                "   on afrom.user_id = tufrom.user_id " +
                " where ";

        if (isPending) {
            sql += " tufrom.user_id = ? and t.transfer_status_id = ? ";
        } else {
            sql += " (tuto.user_id = ?  or tufrom.user_id = ?) " +
                    " and t.transfer_status_id != ? ";
        }
        SqlRowSet rs = (isPending) ? jdbcTemplate.queryForRowSet(sql, userId, 1) : jdbcTemplate.queryForRowSet(sql, userId, userId, 1);
        return mapRowToTransfers(rs);
    }

    @Override
    public Transfer getTransferById(int id, boolean includeBalance) {
        if (id < 0) throw new IllegalArgumentException("id must be positive");
        String sql = " select " +
                "             t.transfer_id " +
                "            ,t.transfer_type_id " +
                "            ,t.transfer_status_id " +
                "            ,t.account_from " +
                "            ,t.account_to " +
                "            ,t.amount " +
                "            ,ts.transfer_status_desc " +
                "            ,tt.transfer_type_desc " +
                "            ,ato.balance as balance_to " +
                "            ,afrom.balance as balance_from " +
                "            ,tuto.user_id as user_id_to " +
                "            ,tuto.username as username_to " +
                "            ,tufrom.user_id as user_id_from " +
                "            ,tufrom.username as username_from " +
                "            ,t.created_date " +
                "            ,t.balance_to as transfer_balance_to " +
                "            ,t.balance_from as transfer_balance_from " +
                "            ,t.transfer_message " +
                "            ,t.fee " +
                "  from transfer t " +
                "  join transfer_status ts " +
                "    on t.transfer_status_id = ts.transfer_status_id " +
                "  join transfer_type tt " +
                "    on t.transfer_type_id = tt.transfer_type_id " +
                "  join account afrom " +
                "    on t.account_from = afrom.account_id " +
                "  join account ato " +
                "    on t.account_to = ato.account_id " +
                "  join tenmo_user tuto " +
                "    on ato.user_id = tuto.user_id " +
                "  join tenmo_user tufrom " +
                "    on afrom.user_id = tufrom.user_id " +
                " where transfer_id = ? ";


        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);
        if (rs.next()) {
            return mapRowToTransfer(rs,includeBalance);
        }
        return null;
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        if (transfer==null) throw new IllegalArgumentException("Transfer cannot be null");
        String sql = " insert into transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount, created_date " +
                "                            ,balance_to,balance_from,transfer_message,fee) " +
                "                  values (?, ?, ?, ?, ?, current_timestamp, ?, ?, ?, ?) " +
                "      returning transfer_id;";
        int transferId = jdbcTemplate.queryForObject(sql,int.class,
                transfer.getTransferType().getId(),
                transfer.getTransferStatus().getId(),
                transfer.getAccountFrom().getId(),
                transfer.getAccountTo().getId(),
                transfer.getAmount(),
                transfer.getAccountBalanceTo(),
                transfer.getAccountBalanceFrom(),
                transfer.getMessage(),
                transfer.getFee()
        );
        return getTransferById(transferId, false);
    }

    @Override
    public Transfer updateTransfer(Transfer transfer) {
        if (transfer==null) throw new IllegalArgumentException("Transfer cannot be null");
        String sql = "update transfer "                   +
                     "       set transfer_status_id = ? " +
                     "           from account a "         +
                     " where transfer.account_from = a.account_id " +
                    "    and transfer.transfer_id = ?;";
        jdbcTemplate.update(sql,
                transfer.getTransferStatus().getId(),
                transfer.getId());
        return getTransferById(transfer.getId(), false);
    }

    @Override
    public List<TransferStatus> getTransferStatuses(User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null.");
        String sql = " select " +
                     "       ts.transfer_status_id " +
                     "      ,ts.transfer_status_desc " +
                     "  from transfer_status ts;";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
        return mapRowsToTransferStatuses(rs);
    }
}
