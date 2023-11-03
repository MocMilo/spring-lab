CREATE TABLE public.department(
    departmentid   varchar(255) NOT NULL,
    departmentname varchar      NOT NULL,
    managerid      varchar(255) NULL,
    CONSTRAINT department_managerid_key UNIQUE (managerid),
    CONSTRAINT department_pkey PRIMARY KEY (departmentid)
);

CREATE TABLE public.product(
    productid     varchar(255) NOT NULL,
    productname   varchar      NOT NULL,
    category      varchar      NOT NULL,
    unitprice     numeric      NOT NULL,
    stockquantity int4         NOT NULL,
    supplier      varchar      NOT NULL,
    description   varchar NULL,
    CONSTRAINT product_pkey PRIMARY KEY (productid)
);

CREATE TABLE public.employee(
    employeeid   varchar(255) NOT NULL,
    firstname    varchar      NOT NULL,
    lastname     varchar      NOT NULL,
    departmentid varchar(255) NULL,
    title        varchar      NOT NULL,
    hiredate     date         NOT NULL,
    salary       numeric      NOT NULL,
    address      varchar NULL,
    phone        varchar NULL,
    email        varchar      NOT NULL,
    version      numeric      NOT NULL,
    CONSTRAINT employee_pkey  PRIMARY KEY (employeeid)
);

CREATE TABLE public.sales(
    salesid       varchar(255) NOT NULL,
    employeeid    varchar(255) NULL,
    productid     varchar(255) NULL,
    dateofsale    date         NOT NULL,
    quantitysold  int4         NOT NULL,
    totalamount   numeric      NOT NULL,
    customername  varchar      NOT NULL,
    customeremail varchar      NOT NULL,
    CONSTRAINT sales_pkey PRIMARY KEY (salesid)
);

ALTER TABLE public.department
    ADD CONSTRAINT department_managerid_fkey FOREIGN KEY (managerid) REFERENCES public.employee (employeeid);
ALTER TABLE public.employee
    ADD CONSTRAINT employee_departmentid_fkey FOREIGN KEY (departmentid) REFERENCES public.department (departmentid);
ALTER TABLE public.sales
    ADD CONSTRAINT sales_employeeid_fkey FOREIGN KEY (employeeid) REFERENCES public.employee (employeeid);
ALTER TABLE public.sales
    ADD CONSTRAINT sales_productid_fkey FOREIGN KEY (productid) REFERENCES public.product (productid);
