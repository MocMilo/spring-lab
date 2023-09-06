-- CLEAR DATABASE:
ALTER TABLE employee DROP CONSTRAINT employee_departmentid_fkey;
DROP TABLE department;
ALTER TABLE public.sales DROP CONSTRAINT sales_productid_fkey;
DROP TABLE public.product;
ALTER TABLE public.sales DROP CONSTRAINT sales_employeeid_fkey;
DROP TABLE public.employee;
DROP TABLE public.sales;