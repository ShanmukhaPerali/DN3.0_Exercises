DECLARE
  v_client_age NUMBER;
  v_updated_rate Loans.InterestRate%type;
BEGIN
  FOR r_client_loan IN (
    SELECT c.ClientID AS Client_ID, c.DateOfBirth, l.LoanID, l.InterestRate
    FROM Clients c
    JOIN Loans l ON c.ClientID = l.ClientID
  ) LOOP
    v_client_age := FLOOR(MONTHS_BETWEEN(SYSDATE, r_client_loan.DateOfBirth) / 12);
    IF v_client_age > 60 THEN
      UPDATE Loans
      SET InterestRate = InterestRate - 1
      WHERE LoanID = r_client_loan.LoanID;

      SELECT InterestRate INTO v_updated_rate
      FROM Loans l
      WHERE l.ClientID = r_client_loan.Client_ID;

      DBMS_OUTPUT.PUT_LINE('Applied 1% discount to Client ID: ' || r_client_loan.Client_ID || ', Original Interest Rate: ' || (r_client_loan.InterestRate) || ' NEW Interest Rate: ' || v_updated_rate);
    END IF;
  END LOOP;
END;
/
