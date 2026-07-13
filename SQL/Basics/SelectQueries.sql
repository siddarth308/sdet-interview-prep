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

-- The AND operator displays a record if all the conditions are TRUE. The OR operator displays a record if any of the conditions are TRUE.

SELECT * FROM Customers
WHERE country = 'USA' AND City = 'California'
AND CustomerName LIKE 'A%'
OR City = 'LA';


SELECT * FROM Customers
WHERE NOT country = 'INDIA';







-- IN - 

-- The IN operator functions as a shorthand for multiple OR conditions, making queries shorter and more readable.

SELECT * FROM Customers
WHERE Country IN ('Germany', 'France', 'UK');

SELECT * FROM Customers
WHERE Country = 'Germany' OR Country = 'France' OR Country = 'UK';







-- BETWEEN - 

SELECT * FROM Products
WHERE Price BETWEEN 10 AND 20;

SELECT * FROM Products
WHERE Price NOT BETWEEN 10 AND 20;







-- LIKE-

-- The MySQL LIKE Operator The LIKE operator is used in a WHERE clause to search for a specified pattern within a column's text data.There are two wildcards often used in conjunction with the LIKE operator:

-- A percent sign % - represents zero, one, or multiple characters
-- A underscore sign _ - represents a single character
-- The percent sign and the underscore can also be used in combinations!

-- The following SQL selects all customers with a CustomerName starting with "a":

SELECT * FROM Customers
WHERE CustomerName LIKE 'a%';

-- The following SQL selects all customers with a CustomerName ending with "a":


SELECT * FROM Customers
WHERE CustomerName LIKE '%a';



The following SQL selects all customers with a CustomerName that have "or" in any position:


SELECT * FROM Customers
WHERE CustomerName LIKE '%or%';




The following SQL selects all customers with a CustomerName that have "r" in the second position:


SELECT * FROM Customers
WHERE CustomerName LIKE '_r%';




The following SQL selects all customers with a CustomerName that starts with "a" and are at least 3 characters in length:


SELECT * FROM Customers
WHERE CustomerName LIKE 'a__%';




The following SQL selects all customers with a ContactName that starts with "a" and ends with "o":


SELECT * FROM Customers
WHERE ContactName LIKE 'a%o';





-- IS NULL- 


SELECT CustomerName, ContactName, Address
FROM Customers
WHERE Address IS NULL;

SELECT CustomerName, ContactName, Address
FROM Customers
WHERE Address IS NOT NULL;
