/*
==========================================================
                    SQL JOINS
==========================================================

A JOIN is used to combine rows from two or more tables
based on a related column.

Main JOINs:
1. INNER JOIN
2. LEFT JOIN
3. RIGHT JOIN
4. FULL OUTER JOIN
5. SELF JOIN
6. CROSS JOIN


==========================================================
                SAMPLE TABLES
==========================================================

Employees
----------------------------------------------------------
employee_id | name  | department_id
------------|-------|--------------
1           | Rahul | 10
2           | Priya | 20
3           | Amit  | 30
4           | Neha  | 40


Departments
----------------------------------------------------------
department_id | department_name
--------------|----------------
10            | Engineering
20            | QA
30            | HR
50            | Finance

Important:
- Employee 4 (Neha) has department_id = 40,
  but department 40 does not exist.
- Department 50 (Finance) has no employee.


==========================================================
                    1. INNER JOIN
==========================================================

Returns ONLY rows that have a matching record
in BOTH tables.

Syntax:

SELECT columns
FROM table1
INNER JOIN table2
    ON table1.column = table2.column;


Example:

SELECT e.name, d.department_name
FROM Employees e
INNER JOIN Departments d
    ON e.department_id = d.department_id;


Result:

Rahul  -> Engineering
Priya  -> QA
Amit   -> HR

Neha is excluded because department 40 doesn't exist.
Finance is excluded because no employee belongs to it.


MEMORY:
INNER JOIN = MATCHING ROWS ONLY

          Employees
              *
             / \
            /   \
           /     \
          *-------*
        Matching rows


==========================================================
                    2. LEFT JOIN
==========================================================

Returns:
- ALL rows from the LEFT table
- Matching rows from the RIGHT table
- NULL when there is no match


Syntax:

SELECT columns
FROM table1
LEFT JOIN table2
    ON table1.column = table2.column;


Example:

SELECT e.name, d.department_name
FROM Employees e
LEFT JOIN Departments d
    ON e.department_id = d.department_id;


Result:

Rahul  -> Engineering
Priya  -> QA
Amit   -> HR
Neha   -> NULL


Why?

Because Employees is the LEFT table.

LEFT JOIN says:
"Give me EVERY employee, even if they don't
have a matching department."


MEMORY:
LEFT JOIN = EVERYTHING FROM LEFT + MATCHING RIGHT


==========================================================
                    3. RIGHT JOIN
==========================================================

Returns:
- ALL rows from the RIGHT table
- Matching rows from the LEFT table
- NULL when there is no match


Syntax:

SELECT columns
FROM table1
RIGHT JOIN table2
    ON table1.column = table2.column;


Example:

SELECT e.name, d.department_name
FROM Employees e
RIGHT JOIN Departments d
    ON e.department_id = d.department_id;


Result:

Rahul  -> Engineering
Priya  -> QA
Amit   -> HR
NULL   -> Finance


Why?

Because Departments is the RIGHT table.

RIGHT JOIN says:
"Give me EVERY department, even if it doesn't
have an employee."


MEMORY:
RIGHT JOIN = EVERYTHING FROM RIGHT + MATCHING LEFT


IMPORTANT:
RIGHT JOIN can usually be rewritten as LEFT JOIN
by swapping the table positions.

Example:

-- RIGHT JOIN
FROM Employees e
RIGHT JOIN Departments d
ON e.department_id = d.department_id;

-- Equivalent LEFT JOIN
FROM Departments d
LEFT JOIN Employees e
ON d.department_id = e.department_id;


==========================================================
                    4. FULL OUTER JOIN
==========================================================

Returns:
- ALL rows from LEFT table
- ALL rows from RIGHT table
- Matching rows are combined
- Non-matching columns become NULL


Syntax:

SELECT columns
FROM table1
FULL OUTER JOIN table2
    ON table1.column = table2.column;


Example:

SELECT e.name, d.department_name
FROM Employees e
FULL OUTER JOIN Departments d
    ON e.department_id = d.department_id;


Result:

Rahul  -> Engineering
Priya  -> QA
Amit   -> HR
Neha   -> NULL
NULL   -> Finance


MEMORY:
FULL OUTER JOIN = EVERYTHING FROM BOTH TABLES


NOTE:
MySQL does NOT directly support FULL OUTER JOIN.
It can be simulated using UNION of LEFT JOIN and
RIGHT JOIN.


==========================================================
                    5. SELF JOIN
==========================================================

A table is joined with ITSELF.

Useful when rows in the same table are related.

Example:

Employees
----------------------------------------------------------
employee_id | name  | manager_id
------------|-------|-----------
1           | Rahul | NULL
2           | Priya | 1
3           | Amit  | 1
4           | Neha  | 2


Query:

SELECT
    e.name AS employee,
    m.name AS manager
FROM Employees e
LEFT JOIN Employees m
    ON e.manager_id = m.employee_id;


Result:

Rahul -> NULL
Priya -> Rahul
Amit  -> Rahul
Neha  -> Priya


MEMORY:
SELF JOIN = TABLE JOINED WITH ITSELF


Common use cases:
- Employee -> Manager
- Parent -> Child
- Category -> Parent Category
- Hierarchical data


==========================================================
                    6. CROSS JOIN
==========================================================

Returns EVERY possible combination of rows
from both tables.

Also called a CARTESIAN PRODUCT.

If:
Table A = 3 rows
Table B = 4 rows

Result = 3 × 4 = 12 rows


Syntax:

SELECT *
FROM table1
CROSS JOIN table2;


Example:

SELECT e.name, d.department_name
FROM Employees e
CROSS JOIN Departments d;


MEMORY:
CROSS JOIN = EVERY POSSIBLE COMBINATION


WARNING:
CROSS JOIN can produce a VERY large number of rows.


==========================================================
              JOIN COMPARISON CHEAT SHEET
==========================================================

INNER JOIN
-> Only matching rows

LEFT JOIN
-> Everything from LEFT + matching RIGHT

RIGHT JOIN
-> Everything from RIGHT + matching LEFT

FULL OUTER JOIN
-> Everything from BOTH tables

SELF JOIN
-> Table joined with ITSELF

CROSS JOIN
-> Every possible combination


==========================================================
                 VISUAL MEMORY TRICK
==========================================================

Think of:

LEFT TABLE        RIGHT TABLE

     A                B
    (   )            (   )
   (     )          (     )
    (   )            (   )

INNER JOIN
-> Intersection only

LEFT JOIN
-> Entire LEFT + intersection

RIGHT JOIN
-> Entire RIGHT + intersection

FULL OUTER JOIN
-> Entire LEFT + Entire RIGHT


==========================================================
              MOST COMMON INTERVIEW QUESTION
==========================================================

Q: Find ALL users, including users who have
   never placed an order.

Answer:

SELECT
    u.user_id,
    u.name,
    o.order_id
FROM users u
LEFT JOIN orders o
    ON u.user_id = o.user_id;


WHY LEFT JOIN?

Because we want ALL users.

Users without orders will have:

order_id = NULL


==========================================================
             FIND RECORDS WITH NO MATCH
==========================================================

Very important interview pattern.

Find employees who don't have a department:

SELECT e.*
FROM Employees e
LEFT JOIN Departments d
    ON e.department_id = d.department_id
WHERE d.department_id IS NULL;


Pattern:

LEFT JOIN
+
WHERE right_table.id IS NULL

=
Find records in LEFT table
that have NO matching record
in RIGHT table.


==========================================================
                  JOIN + WHERE
==========================================================

Example:

SELECT e.name, d.department_name
FROM Employees e
LEFT JOIN Departments d
    ON e.department_id = d.department_id
WHERE d.department_name = 'QA';


IMPORTANT:

A condition on the RIGHT table in WHERE can effectively
remove NULL rows and make a LEFT JOIN behave like
an INNER JOIN for that condition.


==========================================================
              ON vs WHERE IN JOIN
==========================================================

ON:
-> Defines HOW tables are joined.

WHERE:
-> Filters the final result.


Example:

SELECT e.name, d.department_name
FROM Employees e
LEFT JOIN Departments d
    ON e.department_id = d.department_id
    AND d.department_name = 'QA';


Here, ALL employees remain.

Only QA departments are matched.


Compare with:

SELECT e.name, d.department_name
FROM Employees e
LEFT JOIN Departments d
    ON e.department_id = d.department_id
WHERE d.department_name = 'QA';


Here, employees without a QA match are removed.


==========================================================
                 JOIN PERFORMANCE
==========================================================

JOIN performance depends on:
- Indexes
- Number of rows
- Join condition
- Database engine
- Query execution plan


Good practice:

JOIN using indexed / key columns when appropriate.

Example:

ON users.user_id = orders.user_id


Primary keys and foreign keys are commonly used
for JOIN conditions.


==========================================================
                 INTERVIEW QUICK REVISION
==========================================================

INNER JOIN
    -> Matching rows only
    -> Most restrictive

LEFT JOIN
    -> All LEFT rows
    -> Most commonly used

RIGHT JOIN
    -> All RIGHT rows
    -> Can usually be rewritten as LEFT JOIN

FULL OUTER JOIN
    -> All rows from both tables

SELF JOIN
    -> Same table joined with itself

CROSS JOIN
    -> Every combination


==========================================================
                 GOLDEN MEMORY RULE
==========================================================

INNER = MATCH

LEFT  = KEEP LEFT

RIGHT = KEEP RIGHT

FULL  = KEEP BOTH

SELF  = SAME TABLE

CROSS = ALL COMBINATIONS


==========================================================
                  SDET INTERVIEW TIP
==========================================================

Most important JOIN patterns to practice:

1. INNER JOIN + WHERE
2. LEFT JOIN + IS NULL
3. JOIN + GROUP BY
4. JOIN + HAVING
5. Multiple JOINs
6. JOIN + ORDER BY
7. JOIN + COUNT()
8. Finding users with NO orders
9. Finding duplicate / unmatched records
10. Employee -> Manager SELF JOIN


==========================================================
*/ 