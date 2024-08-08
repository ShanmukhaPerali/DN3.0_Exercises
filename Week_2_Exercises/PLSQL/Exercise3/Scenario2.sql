CREATE OR REPLACE PROCEDURE AdjustEmployeeBonus(
    bonusPercentage IN INT,
    department IN VARCHAR2
) 
AS
BEGIN
    UPDATE Staff
    SET Salary = Salary + (Salary * (bonusPercentage / 100))
    WHERE Dept = department;

    COMMIT;
END;
/

BEGIN
    AdjustEmployeeBonus(10, 'IT');
END;
/

DECLARE
    CURSOR staff_cursor IS
        SELECT EmpID, FullName, JobTitle, Salary, Dept, DateOfHire
        FROM Staff
        WHERE Dept = 'IT'; -- Replace 'IT' with the department you want to check

    staff_rec staff_cursor%ROWTYPE;
BEGIN
    -- Call the procedure
    AdjustEmployeeBonus(10, 'IT');

    -- Open the cursor
    OPEN staff_cursor;
    
    -- Loop through the cursor and display the updated data
    LOOP
        FETCH staff_cursor INTO staff_rec;
        EXIT WHEN staff_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('EmpID: ' || staff_rec.EmpID || 
                             ', FullName: ' || staff_rec.FullName || 
                             ', JobTitle: ' || staff_rec.JobTitle || 
                             ', Salary: ' || staff_rec.Salary || 
                             ', Dept: ' || staff_rec.Dept || 
                             ', DateOfHire: ' || staff_rec.DateOfHire);
    END LOOP;
    
    -- Close the cursor
    CLOSE staff_cursor;
END;
/
