CREATE OR REPLACE PROCEDURE ExecuteTransfer(
    from_account_id IN ClientAccounts.AccountID%TYPE,
    to_account_id IN ClientAccounts.AccountID%TYPE,
    amount IN NUMBER
) IS
    from_balance ClientAccounts.Balance%TYPE;
BEGIN
    SELECT Balance
    INTO from_balance
    FROM ClientAccounts
    WHERE AccountID = from_account_id
    FOR UPDATE;

    IF from_balance < amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance in source account.');
    END IF;

    UPDATE ClientAccounts
    SET Balance = Balance - amount
    WHERE AccountID = from_account_id;

    UPDATE ClientAccounts
    SET Balance = Balance + amount
    WHERE AccountID = to_account_id;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Funds transferred successfully: ' ||
                         'From AccountID ' || from_account_id || 
                         ' To AccountID ' || to_account_id ||
                         ' Amount ' || amount);
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('One or both account IDs do not exist.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
BEGIN
  ExecuteTransfer(from_account_id => 1, 
                  to_account_id => 2, 
                  amount => 500);
END;
/
