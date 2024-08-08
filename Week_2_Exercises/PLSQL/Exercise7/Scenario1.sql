CREATE OR REPLACE PACKAGE ClientManagement AS
  PROCEDURE AddClient(
    client_id IN NUMBER,
    full_name IN VARCHAR2,
    birth_date IN DATE,
    initial_balance IN NUMBER
  );

  PROCEDURE UpdateClientDetails(
    client_id IN NUMBER,
    full_name IN VARCHAR2,
    birth_date IN DATE
  );

  FUNCTION GetClientBalance(
    client_id IN NUMBER
  ) RETURN NUMBER;
END ClientManagement;
/
CREATE OR REPLACE PACKAGE BODY ClientManagement AS

  PROCEDURE AddClient(
    client_id IN NUMBER,
    full_name IN VARCHAR2,
    birth_date IN DATE,
    initial_balance IN NUMBER
  ) IS
  BEGIN
    INSERT INTO Clients (ClientID, FullName, BirthDate, Balance, LastUpdated)
    VALUES (client_id, full_name, birth_date, initial_balance, SYSDATE);

    DBMS_OUTPUT.PUT_LINE('Client added successfully: ' || client_id);
  END AddClient;

  PROCEDURE UpdateClientDetails(
    client_id IN NUMBER,
    full_name IN VARCHAR2,
    birth_date IN DATE
  ) IS
  BEGIN
    UPDATE Clients
    SET FullName = full_name,
        BirthDate = birth_date,
        LastUpdated = SYSDATE
    WHERE ClientID = client_id;

    DBMS_OUTPUT.PUT_LINE('Client details updated successfully: ' || client_id);
  END UpdateClientDetails;

  FUNCTION GetClientBalance(
    client_id IN NUMBER
  ) RETURN NUMBER IS
    balance NUMBER;
  BEGIN
    SELECT Balance
    INTO balance
    FROM Clients
    WHERE ClientID = client_id;

    RETURN balance;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20001, 'Error retrieving client balance.');
  END GetClientBalance;

END ClientManagement;
/
BEGIN
  ClientManagement.AddClient(3, 'Michael Johnson', TO_DATE('1978-11-25', 'YYYY-MM-DD'), 2000);
  ClientManagement.UpdateClientDetails(1, 'John Doe Jr.', TO_DATE('1985-05-15', 'YYYY-MM-DD'));
  DECLARE
    client_balance NUMBER;
  BEGIN
    client_balance := ClientManagement.GetClientBalance(1);
    DBMS_OUTPUT.PUT_LINE('Client balance: ' || client_balance);
  END;
END;
/
