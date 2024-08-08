CREATE OR REPLACE FUNCTION CheckSufficientFunds (
  accountID NUMBER,
  requiredBalance NUMBER
) RETURN BOOLEAN
IS
  currentBalance NUMBER;
BEGIN
  SELECT Balance INTO currentBalance 
  FROM ClientAccounts 
  WHERE AccountID = accountID
  AND ROWNUM = 1;

  IF currentBalance >= requiredBalance THEN
    RETURN TRUE;
  ELSE
    RETURN FALSE;
  END IF;

END CheckSufficientFunds;
/

BEGIN
   IF CheckSufficientFunds(accountID => 1, requiredBalance => 100) THEN
    DBMS_OUTPUT.PUT_LINE('Funds are sufficient to transfer');
   ELSE
     DBMS_OUTPUT.PUT_LINE('Funds are insufficient');
   END IF;
END;
/
