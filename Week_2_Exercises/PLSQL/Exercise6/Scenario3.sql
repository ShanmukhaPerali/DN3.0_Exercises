DECLARE
  
  CURSOR UpdateLoanInterest IS 
    SELECT LoanID, InterestRate 
    FROM LoanAccounts;
  
  loanID LoanAccounts.LoanID%TYPE;
  interestRate LoanAccounts.InterestRate%TYPE;
  
BEGIN
  
  OPEN UpdateLoanInterest;
  
  LOOP
      
    FETCH UpdateLoanInterest INTO loanID, interestRate;
      
    EXIT WHEN UpdateLoanInterest%NOTFOUND;
      
    UPDATE LoanAccounts
    SET InterestRate = interestRate - 2
    WHERE LoanID = loanID;
      
    DBMS_OUTPUT.PUT_LINE('LoanID: ' || loanID || ' AND new InterestRate: ' || (interestRate - 2));
  
  END LOOP;
  
  CLOSE UpdateLoanInterest;

END;
/
