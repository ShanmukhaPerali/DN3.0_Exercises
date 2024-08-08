CREATE OR REPLACE FUNCTION ComputeAge(
  birthDate IN DATE
)
RETURN NUMBER
IS 
  calculatedAge NUMBER;
BEGIN
  calculatedAge := FLOOR(MONTHS_BETWEEN(SYSDATE, birthDate) / 12);
  RETURN calculatedAge;
END;
/

BEGIN
  DBMS_OUTPUT.PUT_LINE(ComputeAge(TO_DATE('1975-05-15', 'YYYY-MM-DD')));
END;
/
