CREATE OR REPLACE FUNCTION ComputeMonthlyPayment(
  principalAmount IN NUMBER,
  annualRate IN NUMBER,
  durationYears IN NUMBER
)
RETURN NUMBER
IS 
  monthlyRate NUMBER;  
  totalPayments NUMBER;  
  monthlyPayment NUMBER;  
BEGIN
  monthlyRate := (annualRate / 100) / 12;
  totalPayments := durationYears * 12;
  monthlyPayment := (principalAmount * monthlyRate * POWER(1 + monthlyRate, totalPayments)) / (POWER(1 + monthlyRate, totalPayments) - 1); 
  RETURN monthlyPayment;
END;
/

BEGIN
  DBMS_OUTPUT.PUT_LINE(ComputeMonthlyPayment(principalAmount => 100000, annualRate => 2, durationYears => 2));
END;
/
