CREATE OR REPLACE PACKAGE ClientAccountOperations AS
  PROCEDURE CreateAccount(
    account_id IN NUMBER,
    client_id IN NUMBER,
    account_type IN VARCHAR2,
    initial_balance IN NUMBER
  );

  PROCEDURE DeleteAccount(
    account_id IN NUMBER
  );

  FUNCTION RetrieveTotalBalance(
    client_id IN NUMBER
  ) RETURN NUMBER;
END ClientAccountOperations;
/
CREATE OR REPLACE PACKAGE BODY ClientAccountOperations AS

  PROCEDURE CreateAccount(
    account_id IN NUMBER,
    client_id IN NUMBER,
    account_type IN VARCHAR2,
    initial_balance IN NUMBER
  ) IS
  BEGIN
    INSERT INTO Accounts (AccountID, ClientID, AccountType, Balance, LastModified)
    VALUES (account_id, client_id, account_type, initial_balance, SYSDATE);

    DBMS_OUTPUT.PUT_LINE('Account created successfully: ' || account_id);
  END CreateAccount;

  PROCEDURE DeleteAccount(
    account_id IN NUMBER
  ) IS
  BEGIN
    DELETE FROM Transactions
    WHERE AccountID = account_id;

    DELETE FROM Accounts
    WHERE AccountID = account_id;

    DBMS_OUTPUT.PUT_LINE('Account deleted successfully: ' || account_id);
  END DeleteAccount;

  FUNCTION RetrieveTotalBalance(
    client_id IN NUMBER
  ) RETURN NUMBER IS
    total_balance NUMBER;
  BEGIN
    SELECT SUM(Balance)
    INTO total_balance
    FROM Accounts
    WHERE ClientID = client_id;

    RETURN total_balance;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN 0;
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20001, 'Error retrieving total balance.');
  END RetrieveTotalBalance;

END ClientAccountOperations;
/
BEGIN
  -- Test CreateAccount
  ClientAccountOperations.CreateAccount(
    account_id => 3, 
    client_id => 1, 
    account_type => 'Checking', 
    initial_balance => 2000
  );

  -- Test DeleteAccount
  ClientAccountOperations.DeleteAccount(account_id => 2);

  -- Test RetrieveTotalBalance
  DECLARE
    total_balance NUMBER;
  BEGIN
    total_balance := ClientAccountOperations.RetrieveTotalBalance(1);
    DBMS_OUTPUT.PUT_LINE('Total balance for client 1: ' || total_balance);
  END;
END;
/
