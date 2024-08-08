ALTER TABLE Clients ADD IsPremium BOOLEAN;

DECLARE
  CURSOR premium_client_cursor IS
    SELECT ClientID
    FROM Clients
    WHERE AccountBalance > 10000;
  
  client_ID Clients.ClientID%TYPE;
BEGIN
  OPEN premium_client_cursor;
  
  LOOP
    FETCH premium_client_cursor INTO client_ID;
    EXIT WHEN premium_client_cursor%NOTFOUND;
    
    UPDATE Clients
    SET IsPremium = TRUE
    WHERE ClientID = client_ID;
  END LOOP;
  
  CLOSE premium_client_cursor;
END;
/
