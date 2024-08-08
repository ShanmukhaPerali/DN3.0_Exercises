CREATE OR REPLACE PROCEDURE ApplyMonthlyInterest IS
  CURSOR eligible_accounts_cursor IS
    SELECT AccountID, ClientID, Balance
    FROM ClientAccounts
    WHERE AccountType = 'Savings'
      AND (SYSDATE - LastUpdated) >= 30
      FOR UPDATE;
  
  accID ClientAccounts.AccountID%TYPE;
  custID ClientAccounts.ClientID%TYPE;
  accBalance ClientAccounts.Balance%TYPE;
BEGIN
  OPEN eligible_accounts_cursor;
  
  LOOP
    FETCH eligible_accounts_cursor INTO accID, custID, accBalance;
    EXIT WHEN eligible_accounts_cursor%NOTFOUND;
    
    UPDATE ClientAccounts
    SET Balance = accBalance * 1.01,
        LastUpdated = SYSDATE
    WHERE AccountID = accID;
    
    DBMS_OUTPUT.PUT_LINE('AccountID: ' || accID || 
                         ', ClientID: ' || custID || 
                         ', New Balance: ' || accBalance * 1.01);
  END LOOP;
  
  CLOSE eligible_accounts_cursor;
  
  COMMIT;
  
  DBMS_OUTPUT.PUT_LINE('Monthly interest of 1% applied to all eligible savings accounts.');
END;
/
BEGIN
  ApplyMonthlyInterest;
END;
/
