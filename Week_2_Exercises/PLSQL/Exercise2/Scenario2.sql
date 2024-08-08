CREATE OR REPLACE PROCEDURE AdjustSalary(
  staffID Employees.EmployeeID%TYPE,
  increasePercent NUMBER
)
IS
  EmployeeNotFound EXCEPTION;
  employeeCount NUMBER;
  newSalary NUMBER;
  
BEGIN
  SELECT COUNT(*) INTO employeeCount FROM Employees WHERE EmployeeID = staffID;
  IF employeeCount < 1 THEN
    RAISE EmployeeNotFound;
  END IF;
  
  UPDATE Employees
  SET Salary = Salary + Salary * (0.01 * increasePercent)
  WHERE EmployeeID = staffID;
  
  SELECT Salary INTO newSalary FROM Employees WHERE EmployeeID = staffID;
  DBMS_OUTPUT.PUT_LINE('Successfully updated salary to ' || newSalary);
  
EXCEPTION
  WHEN EmployeeNotFound THEN
    DBMS_OUTPUT.PUT_LINE('Employee does not exist');
     
END;
/

BEGIN
  AdjustSalary(staffID => 5, increasePercent => 5);
END;
/
