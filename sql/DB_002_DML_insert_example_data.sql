-- Inserting Departments
INSERT INTO Department (DepartmentID, DepartmentName) VALUES (uuid_generate_v4(), 'Sales');
INSERT INTO Department (DepartmentID, DepartmentName) VALUES (uuid_generate_v4(), 'Administration');
INSERT INTO Department (DepartmentID, DepartmentName) VALUES (uuid_generate_v4(), 'Production');

-- Inserting Employees (Sales, Administration, Production)
-- Sales
INSERT INTO Employee (FirstName, LastName, DepartmentID, Title, HireDate, Salary, Phone, Email) VALUES ('John', 'Doe', (SELECT DepartmentID FROM Department WHERE DepartmentName = 'Sales'), 'Sales Rep', '2020-01-15', 50000, '123-456-7890', 'john.doe@example.com');
INSERT INTO Employee (FirstName, LastName, DepartmentID, Title, HireDate, Salary, Phone, Email) VALUES ('Jane', 'Smith', (SELECT DepartmentID FROM Department WHERE DepartmentName = 'Sales'), 'Sales Rep', '2019-03-10', 52000, '123-456-7891', 'jane.smith@example.com');
INSERT INTO Employee (FirstName, LastName, DepartmentID, Title, HireDate, Salary, Phone, Email) VALUES ('Alice', 'Johnson', (SELECT DepartmentID FROM Department WHERE DepartmentName = 'Sales'), 'Sales Rep', '2018-06-05', 53000, '123-456-7892', 'alice.johnson@example.com');
INSERT INTO Employee (FirstName, LastName, DepartmentID, Title, HireDate, Salary, Phone, Email) VALUES ('Bob', 'Williams', (SELECT DepartmentID FROM Department WHERE DepartmentName = 'Sales'), 'Sales Manager', '2017-09-20', 54000, '123-456-7893', 'bob.williams@example.com');
INSERT INTO Employee (FirstName, LastName, DepartmentID, Title, HireDate, Salary, Phone, Email) VALUES ('Charlie', 'Brown', (SELECT DepartmentID FROM Department WHERE DepartmentName = 'Sales'), 'Sales Rep', '2021-11-10', 55000, '123-456-7894', 'charlie.brown@example.com');

-- Administration
INSERT INTO Employee (FirstName, LastName, DepartmentID, Title, HireDate, Salary, Phone, Email) VALUES ('David', 'Lee', (SELECT DepartmentID FROM Department WHERE DepartmentName = 'Administration'), 'Admin Manager', '2016-04-30', 60000, '123-456-7895', 'david.lee@example.com');
INSERT INTO Employee (FirstName, LastName, DepartmentID, Title, HireDate, Salary, Phone, Email) VALUES ('Emily', 'Clark', (SELECT DepartmentID FROM Department WHERE DepartmentName = 'Administration'), 'Admin', '2017-08-15', 61000, '123-456-7896', 'emily.clark@example.com');

-- Production
INSERT INTO Employee (FirstName, LastName, DepartmentID, Title, HireDate, Salary, Phone, Email) VALUES ('Frank', 'Lewis', (SELECT DepartmentID FROM Department WHERE DepartmentName = 'Production'), 'Production Manager', '2018-12-25', 48000, '123-456-7897', 'frank.lewis@example.com');
INSERT INTO Employee (FirstName, LastName, DepartmentID, Title, HireDate, Salary, Phone, Email) VALUES ('Grace', 'Adams', (SELECT DepartmentID FROM Department WHERE DepartmentName = 'Production'), 'Worker', '2019-05-10', 42000, '123-456-7898', 'grace.adams@example.com');
INSERT INTO Employee (FirstName, LastName, DepartmentID, Title, HireDate, Salary, Phone, Email) VALUES ('Harry', 'Nelson', (SELECT DepartmentID FROM Department WHERE DepartmentName = 'Production'), 'Worker', '2020-03-20', 43000, '123-456-7899', 'harry.nelson@example.com');

-- Inserting Managers
UPDATE Department SET ManagerID = (SELECT EmployeeID FROM Employee WHERE FirstName = 'David' AND LastName = 'Lee') WHERE DepartmentName = 'Administration';
UPDATE Department SET ManagerID = (SELECT EmployeeID FROM Employee WHERE FirstName = 'Frank' AND LastName = 'Lewis') WHERE DepartmentName = 'Production';
UPDATE Department SET ManagerID = (SELECT EmployeeID FROM Employee WHERE FirstName = 'Bob' AND LastName = 'Williams') WHERE DepartmentName = 'Sales';

-- Inserting Products
INSERT INTO Product (ProductName, Category, UnitPrice, StockQuantity, Supplier, Description) VALUES ('Widget A', 'Tools', 100.50, 500, 'Supplier A', 'High-quality widget');
INSERT INTO Product (ProductName, Category, UnitPrice, StockQuantity, Supplier, Description) VALUES ('Widget B', 'Tools', 150.75, 300, 'Supplier B', 'Medium-quality widget');
INSERT INTO Product (ProductName, Category, UnitPrice, StockQuantity, Supplier, Description) VALUES ('Gadget X', 'Electronics', 200.25, 100, 'Supplier C', 'Advanced gadget');
INSERT INTO Product (ProductName, Category, UnitPrice, StockQuantity, Supplier, Description) VALUES ('Gadget Y', 'Electronics', 250.99, 50, 'Supplier D', 'Basic gadget');
INSERT INTO Product (ProductName, Category, UnitPrice, StockQuantity, Supplier, Description) VALUES ('Part Z', 'Components', 50.10, 1000, 'Supplier E', 'Essential component');
INSERT INTO Product (ProductName, Category, UnitPrice, StockQuantity, Supplier, Description) VALUES ('Toolset Q', 'Hardware', 75.30, 250, 'Supplier Q', 'Quality toolset for various tasks');
INSERT INTO Product (ProductName, Category, UnitPrice, StockQuantity, Supplier, Description) VALUES ('Device R', 'Gadgets', 300.40, 150, 'Supplier R', 'Innovative device for modern needs');

-- Inserting Sales Operations
INSERT INTO Sales (EmployeeID, ProductID, DateOfSale, QuantitySold, TotalAmount, CustomerName, CustomerEmail) VALUES ((SELECT EmployeeID FROM Employee LIMIT 1), (SELECT ProductID FROM Product LIMIT 1), '2022-01-15', 5, 502.50, 'Customer A', 'customer.a@example.com');
INSERT INTO Sales (EmployeeID, ProductID, DateOfSale, QuantitySold, TotalAmount, CustomerName, CustomerEmail) VALUES ((SELECT EmployeeID FROM Employee OFFSET 1 LIMIT 1), (SELECT ProductID FROM Product OFFSET 1 LIMIT 1), '2022-02-10', 3, 452.25, 'Customer B', 'customer.b@example.com');
INSERT INTO Sales (EmployeeID, ProductID, DateOfSale, QuantitySold, TotalAmount, CustomerName, CustomerEmail) VALUES ((SELECT EmployeeID FROM Employee OFFSET 2 LIMIT 1), (SELECT ProductID FROM Product OFFSET 2 LIMIT 1), '2022-03-15', 10, 2002.50, 'Customer C', 'customer.c@example.com');
INSERT INTO Sales (EmployeeID, ProductID, DateOfSale, QuantitySold, TotalAmount, CustomerName, CustomerEmail) VALUES ((SELECT EmployeeID FROM Employee OFFSET 2 LIMIT 1), (SELECT ProductID FROM Product OFFSET 3 LIMIT 1), '2022-04-10', 4, 1003.96, 'Customer D', 'customer.d@example.com');
INSERT INTO Sales (EmployeeID, ProductID, DateOfSale, QuantitySold, TotalAmount, CustomerName, CustomerEmail) VALUES ((SELECT EmployeeID FROM Employee OFFSET 2 LIMIT 1), (SELECT ProductID FROM Product OFFSET 4 LIMIT 1), '2022-05-20', 8, 401.60, 'Customer E', 'customer.e@example.com');
INSERT INTO Sales (EmployeeID, ProductID, DateOfSale, QuantitySold, TotalAmount, CustomerName, CustomerEmail) VALUES ((SELECT EmployeeID FROM Employee OFFSET 0 LIMIT 1), (SELECT ProductID FROM Product OFFSET 5 LIMIT 1), '2022-06-15', 6, 451.80, 'Customer F', 'customer.f@example.com');
INSERT INTO Sales (EmployeeID, ProductID, DateOfSale, QuantitySold, TotalAmount, CustomerName, CustomerEmail) VALUES ((SELECT EmployeeID FROM Employee OFFSET 1 LIMIT 1), (SELECT ProductID FROM Product OFFSET 6 LIMIT 1), '2022-07-25', 2, 150.60, 'Customer G', 'customer.g@example.com');
INSERT INTO Sales (EmployeeID, ProductID, DateOfSale, QuantitySold, TotalAmount, CustomerName, CustomerEmail) VALUES ((SELECT EmployeeID FROM Employee OFFSET 0 LIMIT 1), (SELECT ProductID FROM Product OFFSET 5 LIMIT 1), '2022-06-17', 4, 350.80, 'Customer F', 'customer.f@example.com');
INSERT INTO Sales (EmployeeID, ProductID, DateOfSale, QuantitySold, TotalAmount, CustomerName, CustomerEmail) VALUES ((SELECT EmployeeID FROM Employee OFFSET 1 LIMIT 1), (SELECT ProductID FROM Product OFFSET 6 LIMIT 1), '2022-07-26', 2, 70.50, 'Customer G', 'customer.g@example.com');
INSERT INTO Sales (EmployeeID, ProductID, DateOfSale, QuantitySold, TotalAmount, CustomerName, CustomerEmail) VALUES ((SELECT EmployeeID FROM Employee OFFSET 0 LIMIT 1), (SELECT ProductID FROM Product OFFSET 2 LIMIT 1), '2022-04-10', 4, 1003.96, 'Customer D', 'customer.d@example.com');
