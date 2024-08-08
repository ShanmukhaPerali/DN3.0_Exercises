CREATE OR REPLACE TRIGGER ValidateTransactionRules
BEFORE INSERT ON AccountTransactions
FOR EACH ROW
DECLARE
    currentBalance ClientAccounts.Balance%TYPE;
BEGIN
    SELECT Balance
    INTO currentBalance
    FROM ClientAccounts
    WHERE AccountID = :NEW.AccountID;

    IF :NEW.TransactionType = 'Withdrawal' THEN
        IF :NEW.Amount > currentBalance THEN
            RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance for withdrawal.');
        END IF;
    ELSIF :NEW.TransactionType = 'Deposit' THEN
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be positive.');
        END IF;
    ELSE
        RAISE_APPLICATION_ERROR(-20003, 'Invalid transaction type. Must be "Deposit" or "Withdrawal".');
    END IF;
END;
/

BEGIN
  DBMS_OUTPUT.ENABLE;

  INSERT INTO AccountTransactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
  VALUES (3, 1, SYSDATE, 200, 'Deposit');
  DBMS_OUTPUT.PUT_LINE('Insert deposit successful for AccountID 1');

  INSERT INTO AccountTransactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
  VALUES (4, 2, SYSDATE, 1000, 'Withdrawal');
  DBMS_OUTPUT.PUT_LINE('Insert withdrawal successful for AccountID 2');

  BEGIN
    INSERT INTO AccountTransactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (5, 1, SYSDATE, -100, 'Deposit');
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
  END;

  BEGIN
    INSERT INTO AccountTransactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (6, 1, SYSDATE, 600, 'Withdrawal');
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
  END;

END;
/
