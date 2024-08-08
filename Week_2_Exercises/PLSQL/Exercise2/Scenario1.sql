CREATE OR REPLACE PROCEDURE TransferFundsSafely(
  srcAccount Accounts.AccountID%TYPE,
  destAccount Accounts.AccountID%TYPE,
  amount NUMBER
)
IS
  NotEnoughFunds EXCEPTION;
  currentBalance NUMBER;
BEGIN
  SELECT Balance INTO currentBalance FROM Accounts WHERE AccountID = srcAccount;

  IF currentBalance < amount THEN
    RAISE NotEnoughFunds;
  END IF;

  DBMS_OUTPUT.PUT_LINE('Funds transferred successfully :)');

EXCEPTION
  WHEN NotEnoughFunds THEN
    DBMS_OUTPUT.PUT_LINE('Insufficient funds in the source account');
END;
/
  
BEGIN
  TransferFundsSafely(srcAccount => 1, destAccount => 2, amount => 1000);
END;
/
