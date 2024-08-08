BEGIN
  FOR rec IN (
    SELECT c.ClientID AS clientID, l.ExpiryDate, c.FullName
    FROM Clients c
    JOIN Loans l ON l.ClientID = c.ClientID
  ) LOOP
    IF rec.ExpiryDate BETWEEN SYSDATE AND SYSDATE + 30 THEN
        DBMS_OUTPUT.PUT_LINE('Loan due in the next 30 days for Client ID: ' || rec.clientID || ', Name: ' || rec.FullName);
    END IF;
  END LOOP;
END;
/
