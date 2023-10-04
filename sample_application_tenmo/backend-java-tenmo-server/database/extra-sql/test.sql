select * from account
select
        a.account_id
        ,a.user_id
        ,a.balance
        ,u.username
    from
        account a
        join tenmo_user u
            on u.user_id = a.user_id
    where
        a.account_id = ?;

select
        a.account_id
        ,a.user_id
        ,a.balance
        ,u.username
    from
        account a
        join tenmo_user u
            on u.user_id = a.user_id
    where
            a.user_id = ?;

select
    t.transfer_id
  ,t.transfer_type_id
  ,t.transfer_status_id
  ,t.account_from
  ,t.account_to
  ,t.amount
  ,ts.transfer_status_desc
  ,tt.transfer_type_desc
  ,ato.balance as balance_to
  ,afrom.balance as balance_from
  ,tuto.user_id as user_id_to
  ,tuto.username as username_to
  ,tufrom.user_id as user_id_from
  ,tufrom.username as username_from
from
    transfer t
        join transfer_status ts
            on t.transfer_status_id = ts.transfer_status_id
        join transfer_type tt
            on t.transfer_type_id = tt.transfer_type_id
        join account afrom
            on t.account_from = afrom.account_id
        join account ato
            on t.account_to = ato.account_id
        join tenmo_user tuto
            on ato.user_id = tuto.user_id
        join tenmo_user tufrom
            on afrom.user_id = tufrom.user_id
    where
        (
                tuto.user_id = ?
            or
                tufrom.user_id = ?
        );


update transfer
set
    transfer_status_id = ?
from
  account a
where
    transfer.account_from = a.account_id
    and
    a.user_id = ?;


insert into transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount)
    values (?, ?, ?, ?, ?)
    returning transfer_id;

update account
    set
        balance = balance - ?
    where
        account_id = ?;


select * from log_audit;
select * from log_error;


delete from log_audit;
delete from log_error;




select * from transfer_status;
select * from transfer_type;


select * from tenmo_user
select * from account;

select * from transfer;



select CURRENT_TIMESTAMP;