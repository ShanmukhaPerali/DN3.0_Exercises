DECLARE 
  CURSOR ApplyYearlyCharge IS 
    SELECT AccountID, Balance 
    FROM ClientAccounts;
  
  accountID ClientAccounts.AccountID%TYPE;
  currentBalance ClientAccounts.Balance%TYPE;
  
BEGIN
  OPEN ApplyYearlyCharge;
  
  LOOP
    FETCH ApplyYearlyCharge INTO accountID, currentBalance;
    EXIT WHEN ApplyYearlyCharge%NOTFOUND;
    
    UPDATE ClientAccounts
    SET Balance = currentBalance - 250 
    WHERE AccountID = accountID;
    
    DBMS_OUTPUT.PUT_LINE('Account ID: ' || accountID || ' || Balance: ' || (currentBalance - 250));
  END LOOP;
  
  CLOSE ApplyYearlyCharge;
END;
/
