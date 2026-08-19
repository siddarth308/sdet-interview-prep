-- ============================================================
-- SQL SUBQUERIES - SDET INTERVIEW REVISION
-- ============================================================

-- Sample Tables
--
-- Employee
-- -----------------------------------------
-- id | name | department_id | salary | manager_id
--
-- Department
-- -----------------------------------------
-- department_id | department_name
--
-- ============================================================
-- 1. SINGLE-ROW SUBQUERY
-- ============================================================

-- Find employees earning more than the average salary.

SELECT name, salary
FROM Employee
WHERE salary > (
    SELECT AVG(salary)
    FROM Employee
);


-- Find employee(s) with the highest salary.

SELECT name, salary
FROM Employee
WHERE salary = (
    SELECT MAX(salary)
    FROM Employee
);


-- Find employee(s) with the lowest salary.

SELECT name, salary
FROM Employee
WHERE salary = (
    SELECT MIN(salary)
    FROM Employee
);


-- Find employees earning less than the average salary.

SELECT name, salary
FROM Employee
WHERE salary < (
    SELECT AVG(salary)
    FROM Employee
);


-- ============================================================
-- 2. MULTI-ROW SUBQUERY
-- ============================================================

-- A multi-row subquery returns multiple values.
-- Use operators such as:
--
-- IN
-- ANY
-- ALL


-- Find employees who belong to departments located in Delhi.

SELECT name, department_id
FROM Employee
WHERE department_id IN (
    SELECT department_id
    FROM Department
    WHERE location = 'Delhi'
);


-- ============================================================
-- 3. IN
-- ============================================================

-- Find employees belonging to IT or HR departments.

SELECT name, department_id
FROM Employee
WHERE department_id IN (
    SELECT department_id
    FROM Department
    WHERE department_name IN ('IT', 'HR')
);


-- Find employees whose salary matches any salary
-- in the Management department.

SELECT name, salary
FROM Employee
WHERE salary IN (
    SELECT salary
    FROM Employee
    WHERE department_id = (
        SELECT department_id
        FROM Department
        WHERE department_name = 'Management'
    )
);


-- ============================================================
-- 4. EXISTS
-- ============================================================

-- EXISTS checks whether the subquery returns
-- at least one row.


-- Find employees whose department exists.

SELECT e.name
FROM Employee e
WHERE EXISTS (
    SELECT 1
    FROM Department d
    WHERE d.department_id = e.department_id
);


-- Find departments that have at least one employee.

SELECT d.department_name
FROM Department d
WHERE EXISTS (
    SELECT 1
    FROM Employee e
    WHERE e.department_id = d.department_id
);


-- ============================================================
-- 5. NOT EXISTS
-- ============================================================

-- Find departments that have no employees.

SELECT d.department_name
FROM Department d
WHERE NOT EXISTS (
    SELECT 1
    FROM Employee e
    WHERE e.department_id = d.department_id
);


-- ============================================================
-- 6. CORRELATED SUBQUERY
-- ============================================================

-- A correlated subquery depends on the outer query.


-- Find employees earning more than their department average.

SELECT e1.name,
       e1.department_id,
       e1.salary
FROM Employee e1
WHERE e1.salary > (
    SELECT AVG(e2.salary)
    FROM Employee e2
    WHERE e2.department_id = e1.department_id
);


-- Find employees earning the highest salary
-- within their department.

SELECT e1.name,
    e1.department_id,
    e1.salary
FROM Employee e1
WHERE e1.salary = (
    SELECT MAX(e2.salary)
    FROM Employee e2
    WHERE e2.department_id = e1.department_id
);


-- ============================================================
-- 7. SECOND HIGHEST SALARY
-- ============================================================

-- Method 1: MAX + subquery

SELECT MAX(salary) AS second_highest_salary
FROM Employee
WHERE salary < (
    SELECT MAX(salary)
    FROM Employee
);


-- Find employee(s) having the second-highest salary.

SELECT name, salary
FROM Employee
WHERE salary = (
    SELECT MAX(salary)
    FROM Employee
    WHERE salary < (
        SELECT MAX(salary)
        FROM Employee
    )
);


-- ============================================================
-- 8. ANY
-- ============================================================

-- Find employees earning more than ANY employee
-- in department 10.

SELECT name, salary
FROM Employee
WHERE salary > ANY (
    SELECT salary
    FROM Employee
    WHERE department_id = 10
);


-- ============================================================
-- 9. ALL
-- ============================================================

-- Find employees earning more than ALL employees
-- in department 10.

SELECT name, salary
FROM Employee
WHERE salary > ALL (
    SELECT salary
    FROM Employee
    WHERE department_id = 10
);


-- ============================================================
-- 10. IN vs EXISTS
-- ============================================================

-- IN:
-- Compare a value against a set of values.

SELECT name
FROM Employee
WHERE department_id IN (
    SELECT department_id
    FROM Department
);


-- EXISTS:
-- Check whether a matching row exists.

SELECT e.name
FROM Employee e
WHERE EXISTS (
    SELECT 1
    FROM Department d
    WHERE d.department_id = e.department_id
);


-- ============================================================
-- INTERVIEW QUESTIONS
-- ============================================================

-- Q1. Find employees earning above average salary.


-- Q2. Find employee(s) with the highest salary.


-- Q3. Find employees belonging to the IT department using IN.


-- Q4. Find employees whose department exists using EXISTS.


-- Q5. Find departments having no employees.


-- Q6. Find employees earning more than their department average.


-- Q7. Find the second-highest salary.


-- Q8. Find employees earning more than their manager.


-- Q9. Find departments where at least one employee earns
-- more than 100000.


-- Q10. Find employees who earn the maximum salary
-- in their department.


-- ============================================================
-- QUICK REVISION
-- ============================================================

-- Single-row subquery
-- -> Returns one value/row
-- -> Use =, >, <, >=, <=, <>

-- Multi-row subquery
-- -> Returns multiple values
-- -> Use IN, ANY, ALL

-- IN
-- -> Is this value present in the result?

-- EXISTS
-- -> Does at least one matching row exist?

-- Correlated subquery
-- -> Inner query depends on outer query

-- Key interview distinction:
--
-- IN     -> compare VALUE
-- EXISTS -> check EXISTENCE
--
-- ============================================================