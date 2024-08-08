DECLARE
  -- Define a record type to hold the transaction details
  TYPE StatementRecType IS RECORD (
    ClientID Clients.ClientID%TYPE,
    ClientName Clients.FullName%TYPE,
    AccountID ClientAccounts.AccountID%TYPE,
    StatementDate AccountTransactions.TransactionDate%TYPE,
    Amount AccountTransactions.Amount%TYPE,
    TransactionType AccountTransactions.TransactionType%TYPE
  );

  CURSOR MonthlyStatementCursor IS
    SELECT c.ClientID, c.FullName, a.AccountID, t.TransactionDate, t.Amount, t.TransactionType
    FROM Clients c
    JOIN ClientAccounts a ON c.ClientID = a.ClientID
    JOIN AccountTransactions t ON a.AccountID = t.AccountID
    WHERE EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE)
      AND EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
    ORDER BY c.ClientID, t.TransactionDate;

  StatementRec StatementRecType;

BEGIN
  OPEN MonthlyStatementCursor;
  LOOP
    FETCH MonthlyStatementCursor INTO StatementRec;
    EXIT WHEN MonthlyStatementCursor%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('Client ID: ' || StatementRec.ClientID || ', Name: ' || StatementRec.ClientName);
    DBMS_OUTPUT.PUT_LINE('Account ID: ' || StatementRec.AccountID);
    DBMS_OUTPUT.PUT_LINE('Transaction Date: ' || TO_CHAR(StatementRec.StatementDate, 'YYYY-MM-DD'));
    DBMS_OUTPUT.PUT_LINE('Amount: ' || StatementRec.Amount || ', Type: ' || StatementRec.TransactionType);
    DBMS_OUTPUT.PUT_LINE('----------------------------------------');
  END LOOP;
  CLOSE MonthlyStatementCursor;
END;
/
