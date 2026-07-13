-- SQL and MySQL are related, but they are not the same thing.

-- SQL	MySQL
-- SQL (Structured Query Language) is a language used to communicate with databases.	MySQL is a relational database management system (RDBMS) that uses SQL.
-- It is a standard language for creating, reading, updating, and deleting data.	It is software that stores and manages data.
-- You cannot install SQL because it's a language.	You install MySQL on your computer or server.
-- Used by many database systems like MySQL, Oracle Database, Microsoft SQL Server, PostgreSQL, and SQLite.	One specific database system among many.





-- SELECT - 

SELECT * FROM Customers
SELECT CustomerName, City FROM Customers;

-- The SELECT DISTINCT statement is used to return only distinct (unique) values.

-- In a table, a column may contain several duplicate values - and sometimes you want to list only the unique values.



-- WHERE -

SELECT * FROM Customers WHERE country = 'Japan';

-- The following operators can be used in the WHERE clause:

-- Operator	Description	
-- =	Equal	
-- >	Greater than	
-- <	Less than	
-- >=	Greater than or equal	
-- <=	Less than or equal	
-- <>	Not equal. Note: In some versions of SQL this operator may be written as !=	
-- BETWEEN	Between a certain range	
-- LIKE	Search for a pattern	
-- IN	To specify multiple possible values for a column





-- ORDER BY-

-- The SQL ORDER BY The ORDER BY keyword is used to sort the result-set in ascending or descending order.The ORDER BY keyword sorts the result-set in ascending order (ASC) by default.

SELECT * FROM Customers
ORDER BY country ASC, CustomerName DESC; 





-- LIMIT - 

SELECT * FROM Customers
LIMIT 3;

-- What if we want to select records 4 - 6 (inclusive)? MySQL provides a way to handle this: by using OFFSET. The following SQL says "return only 3 records, start on record 4 (OFFSET 3)":

SELECT * FROM Customers
LIMIT 3 OFFSET 3;







-- AND, OR, NOT-

