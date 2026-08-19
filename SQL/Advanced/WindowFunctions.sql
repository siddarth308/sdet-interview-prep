-- ============================================================
-- SQL WINDOW FUNCTIONS - SDET INTERVIEW REVISION
-- ============================================================

-- Sample table:
--
-- Employee
-- -----------------------------------------
-- id
-- name
-- department_id
-- salary
--
-- ============================================================
-- 1. WHAT IS A WINDOW FUNCTION?
-- ============================================================

-- A window function performs a calculation across
-- related rows WITHOUT combining/removing those rows.


-- ============================================================
-- 2. ROW_NUMBER()
-- ============================================================

-- Assign a unique sequential number to every row.

SELECT
    name,
    department_id,
    salary,
    ROW_NUMBER() OVER (
        ORDER BY salary DESC
    ) AS row_num
FROM Employee;


-- ============================================================
-- 3. ROW_NUMBER() WITH PARTITION BY
-- ============================================================

-- Rank employees separately within each department.

SELECT
    name,
    department_id,
    salary,
    ROW_NUMBER() OVER (
        PARTITION BY department_id
        ORDER BY salary DESC
    ) AS row_num
FROM Employee;


-- Example:
--
-- Department 10:
-- salary 90000 -> 1
-- salary 80000 -> 2
-- salary 70000 -> 3
--
-- Department 20:
-- salary 95000 -> 1
-- salary 85000 -> 2


-- ============================================================
-- 4. RANK()
-- ============================================================

-- RANK gives the same rank to tied values.
-- It leaves gaps after ties.

SELECT
    name,
    department_id,
    salary,
    RANK() OVER (
        ORDER BY salary DESC
    ) AS salary_rank
FROM Employee;


-- With PARTITION BY

SELECT
    name,
    department_id,
    salary,
    RANK() OVER (
        PARTITION BY department_id
        ORDER BY salary DESC
    ) AS salary_rank
FROM Employee;


-- Example:
--
-- Salary:
-- 100000 -> rank 1
-- 90000  -> rank 2
-- 90000  -> rank 2
-- 80000  -> rank 4
--
-- Notice the gap.


-- ============================================================
-- 5. DENSE_RANK()
-- ============================================================

-- DENSE_RANK gives the same rank to ties
-- but does NOT leave gaps.

SELECT
    name,
    department_id,
    salary,
    DENSE_RANK() OVER (
        ORDER BY salary DESC
    ) AS salary_rank
FROM Employee;


-- Example:
--
-- Salary:
-- 100000 -> rank 1
-- 90000  -> rank 2
-- 90000  -> rank 2
-- 80000  -> rank 3
--
-- No gap.


-- ============================================================
-- 6. ROW_NUMBER vs RANK vs DENSE_RANK
-- ============================================================

-- Example salaries:
--
-- 100000
-- 90000
-- 90000
-- 80000
--
-- ROW_NUMBER:
-- 1
-- 2
-- 3
-- 4
--
-- RANK:
-- 1
-- 2
-- 2
-- 4
--
-- DENSE_RANK:
-- 1
-- 2
-- 2
-- 3


-- ============================================================
-- 7. SECOND HIGHEST SALARY
-- ============================================================

-- Using DENSE_RANK

SELECT name,
       salary
FROM (
    SELECT
        name,
        salary,
        DENSE_RANK() OVER (
            ORDER BY salary DESC
        ) AS salary_rank
    FROM Employee
) ranked
WHERE salary_rank = 2;


-- ============================================================
-- 8. THIRD HIGHEST SALARY
-- ============================================================

SELECT name,
       salary
FROM (
    SELECT
        name,
        salary,
        DENSE_RANK() OVER (
            ORDER BY salary DESC
        ) AS salary_rank
    FROM Employee
) ranked
WHERE salary_rank = 3;


-- ============================================================
-- 9. HIGHEST SALARY PER DEPARTMENT
-- ============================================================

SELECT name,
       department_id,
       salary
FROM (
    SELECT
        name,
        department_id,
        salary,
        RANK() OVER (
            PARTITION BY department_id
            ORDER BY salary DESC
        ) AS salary_rank
    FROM Employee
) ranked
WHERE salary_rank = 1;


-- ============================================================
-- 10. TOP 2 EMPLOYEES PER DEPARTMENT
-- ============================================================

SELECT name,
       department_id,
       salary
FROM (
    SELECT
        name,
        department_id,
        salary,
        ROW_NUMBER() OVER (
            PARTITION BY department_id
            ORDER BY salary DESC
        ) AS row_num
    FROM Employee
) ranked
WHERE row_num <= 2;


-- ============================================================
-- 11. TOP 3 SALARIES PER DEPARTMENT
-- ============================================================

SELECT name,
       department_id,
       salary
FROM (
    SELECT
        name,
        department_id,
        salary,
        DENSE_RANK() OVER (
            PARTITION BY department_id
            ORDER BY salary DESC
        ) AS salary_rank
    FROM Employee
) ranked
WHERE salary_rank <= 3;


-- ============================================================
-- 12. RUNNING TOTAL
-- ============================================================

SELECT
    name,
    salary,
    SUM(salary) OVER (
        ORDER BY id
    ) AS running_total
FROM Employee;


-- ============================================================
-- 13. DEPARTMENT TOTAL SALARY
-- ============================================================

SELECT
    name,
    department_id,
    salary,
    SUM(salary) OVER (
        PARTITION BY department_id
    ) AS department_total_salary
FROM Employee;


-- ============================================================
-- 14. DEPARTMENT AVERAGE SALARY
-- ============================================================

SELECT
    name,
    department_id,
    salary,
    AVG(salary) OVER (
        PARTITION BY department_id
    ) AS department_avg_salary
FROM Employee;


-- ============================================================
-- 15. COMPARE EMPLOYEE SALARY WITH DEPARTMENT AVERAGE
-- ============================================================

SELECT
    name,
    department_id,
    salary,
    AVG(salary) OVER (
        PARTITION BY department_id
    ) AS department_avg_salary,
    salary -
    AVG(salary) OVER (
        PARTITION BY department_id
    ) AS difference_from_average
FROM Employee;


-- ============================================================
-- 16. LAG()
-- ============================================================

-- LAG accesses a previous row.

SELECT
    name,
    salary,
    LAG(salary) OVER (
        ORDER BY salary DESC
    ) AS previous_salary
FROM Employee;


-- ============================================================
-- 17. LEAD()
-- ============================================================

-- LEAD accesses the next row.

SELECT
    name,
    salary,
    LEAD(salary) OVER (
        ORDER BY salary DESC
    ) AS next_salary
FROM Employee;


-- ============================================================
-- 18. GROUP BY vs WINDOW FUNCTIONS
-- ============================================================

-- GROUP BY combines rows.

SELECT
    department_id,
    AVG(salary) AS average_salary
FROM Employee
GROUP BY department_id;


-- Window function keeps every employee row.

SELECT
    name,
    department_id,
    salary,
    AVG(salary) OVER (
        PARTITION BY department_id
    ) AS average_salary
FROM Employee;


-- KEY DIFFERENCE:
--
-- GROUP BY
-- -> Reduces number of rows
-- -> Produces one result row per group
--
-- WINDOW FUNCTION
-- -> Keeps original rows
-- -> Adds calculated information to each row


-- ============================================================
-- 19. PARTITION BY vs GROUP BY
-- ============================================================

-- GROUP BY:
--
-- department 10 -> one row
-- department 20 -> one row


-- PARTITION BY:
--
-- department 10 -> all employees remain
-- department 20 -> all employees remain


-- ============================================================
-- 20. WINDOW FUNCTION SYNTAX
-- ============================================================

-- function() OVER (
--     PARTITION BY column
--     ORDER BY column
-- )


-- ============================================================
-- 21. INTERVIEW QUESTIONS
-- ============================================================

-- Q1. Find the second-highest salary.


-- Q2. Find the third-highest salary.


-- Q3. Find the highest-paid employee in every department.


-- Q4. Find the top 2 employees from every department.


-- Q5. Find the top 3 salaries from every department.


-- Q6. Find employees whose salary is above
-- their department average.


-- Q7. Find employees with the same salary rank.


-- Q8. Assign a unique number to employees
-- within each department.


-- Q9. Find the running total of salaries.


-- Q10. Find the previous employee salary using LAG.


-- ============================================================
-- QUICK REVISION
-- ============================================================

-- ROW_NUMBER()
-- -> Unique sequential number
-- -> Ties get different numbers
--
-- RANK()
-- -> Ties get same rank
-- -> Gaps after ties
--
-- DENSE_RANK()
-- -> Ties get same rank
-- -> No gaps
--
-- PARTITION BY
-- -> Creates independent windows/groups
--
-- ORDER BY inside OVER()
-- -> Determines calculation/ranking order
--
-- GROUP BY
-- -> Reduces rows
--
-- WINDOW FUNCTION
-- -> Keeps rows
--
-- ============================================================