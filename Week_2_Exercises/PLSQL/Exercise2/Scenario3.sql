CREATE OR REPLACE PROCEDURE RegisterCustomer(
    clientID NUMBER,
    clientName VARCHAR2,
    clientDOB DATE,
    initialBalance NUMBER,
    lastUpdated DATE
)
IS
  CustomerAlreadyExists EXCEPTION;
  existingCustomerCount NUMBER;
BEGIN
  -- Check if the customer already exists
  SELECT COUNT(*) INTO existingCustomerCount FROM Clients WHERE ClientID = clientID;
  
  IF existingCustomerCount > 0 THEN
    RAISE CustomerAlreadyExists;
  END IF;
  
  -- Insert the new customer
  INSERT INTO Clients (ClientID, Name, DOB, Balance, LastUpdated)
  VALUES (clientID, clientName, clientDOB, initialBalance, lastUpdated);
  
  DBMS_OUTPUT.PUT_LINE('Successfully registered a new client');
  
EXCEPTION
  WHEN CustomerAlreadyExists THEN
    DBMS_OUTPUT.PUT_LINE('Client already exists');
END;
/


BEGIN
  RegisterCustomer(
    clientID => 60,
    clientName => 'John Doe',
    clientDOB => TO_DATE('1947-05-15', 'YYYY-MM-DD'),
    initialBalance => 1000,
    lastUpdated => SYSDATE
  );
END;
/ 
