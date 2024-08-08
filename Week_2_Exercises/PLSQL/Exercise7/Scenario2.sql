CREATE OR REPLACE PACKAGE StaffManagement AS
  PROCEDURE RecruitStaff(
    staff_id IN NUMBER,
    full_name IN VARCHAR2,
    job_title IN VARCHAR2,
    monthly_salary IN NUMBER,
    team IN VARCHAR2,
    start_date IN DATE
  );

  PROCEDURE ModifyStaffDetails(
    staff_id IN NUMBER,
    full_name IN VARCHAR2,
    job_title IN VARCHAR2,
    monthly_salary IN NUMBER,
    team IN VARCHAR2
  );

  FUNCTION ComputeAnnualSalary(
    staff_id IN NUMBER
  ) RETURN NUMBER;
END StaffManagement;
/
CREATE OR REPLACE PACKAGE BODY StaffManagement AS

  PROCEDURE RecruitStaff(
    staff_id IN NUMBER,
    full_name IN VARCHAR2,
    job_title IN VARCHAR2,
    monthly_salary IN NUMBER,
    team IN VARCHAR2,
    start_date IN DATE
  ) IS
  BEGIN
    INSERT INTO Staff (StaffID, FullName, JobTitle, MonthlySalary, Team, StartDate)
    VALUES (staff_id, full_name, job_title, monthly_salary, team, start_date);

    DBMS_OUTPUT.PUT_LINE('Staff recruited successfully: ' || staff_id);
  END RecruitStaff;

  PROCEDURE ModifyStaffDetails(
    staff_id IN NUMBER,
    full_name IN VARCHAR2,
    job_title IN VARCHAR2,
    monthly_salary IN NUMBER,
    team IN VARCHAR2
  ) IS
  BEGIN
    UPDATE Staff
    SET FullName = full_name,
        JobTitle = job_title,
        MonthlySalary = monthly_salary,
        Team = team
    WHERE StaffID = staff_id;

    DBMS_OUTPUT.PUT_LINE('Staff details updated successfully: ' || staff_id);
  END ModifyStaffDetails;

  FUNCTION ComputeAnnualSalary(
    staff_id IN NUMBER
  ) RETURN NUMBER IS
    staff_salary NUMBER;
  BEGIN
    SELECT MonthlySalary
    INTO staff_salary
    FROM Staff
    WHERE StaffID = staff_id;

    RETURN staff_salary * 12;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20001, 'Error computing annual salary.');
  END ComputeAnnualSalary;

END StaffManagement;
/
BEGIN
  -- Test RecruitStaff
  StaffManagement.RecruitStaff(
    staff_id => 3, 
    full_name => 'Sarah Connor', 
    job_title => 'Analyst', 
    monthly_salary => 5000, 
    team => 'Finance', 
    start_date => TO_DATE('2023-01-15', 'YYYY-MM-DD')
  );

  -- Test ModifyStaffDetails
  StaffManagement.ModifyStaffDetails(
    staff_id => 1, 
    full_name => 'Alice Johnson', 
    job_title => 'Senior Manager', 
    monthly_salary => 8000, 
    team => 'HR'
  );

  -- Test ComputeAnnualSalary
  DECLARE
    annual_salary NUMBER;
  BEGIN
    annual_salary := StaffManagement.ComputeAnnualSalary(1);
    DBMS_OUTPUT.PUT_LINE('Annual salary: ' || annual_salary);
  END;
END;
/
