package com.sandystack.exp;

import com.sandystack.exp.model.entities.Department;
import com.sandystack.exp.model.entities.Employee;
import com.sandystack.exp.model.entities.Product;
import com.sandystack.exp.model.entities.Sale;
import com.sandystack.exp.repository.DepartmentRepository;
import com.sandystack.exp.repository.EmployeeRepository;
import com.sandystack.exp.repository.ProductRepository;
import com.sandystack.exp.repository.SaleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.time.LocalDateTime;

/**
 * Just simple repository operations test
 * TODO: remove
 */
@DataJpaTest
@ActiveProfiles("test")
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/sql/initDB.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/sql/dataDB.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/sql/clearDB.sql")
})
class SpringLabApplicationTests {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Test
    void contextLoads() {
        departmentRepository.findAll();
        System.out.println("context loaded!");
    }

    @Test
    void testEntities() {

        Department department = new Department();
        department.setName("Enjoyment Dep.");

        Employee employee = new Employee();
        employee.setFirstName("Dave");
        employee.setLastName("Clark");
        employee.setPhone("6465465464");
        employee.setEmail("dave.clarc@example.com");
        employee.setAddress("Nice street 48");
        employee.setTitle("boss");
        employee.setSalary(125000);
        employee.setHiredate(LocalDateTime.now());
        employee.setDepartment(department);
        Employee fetched = employeeRepository.save(employee);

        Product product = new Product();
        product.setProductName("XD-P-gadget");
        product.setCategory("Car items");
        product.setStockQuantity(20L);
        product.setUnitPrice(1500L);
        product.setSupplier("Super Supplier");

        productRepository.save(product);

        Sale sale = new Sale();
        sale.setProduct(product);
        sale.setEmployee(employee);
        sale.setQuantitySold(15L);
        sale.setCustomerName("Our Best customer");
        sale.setCustomerEmail("best@example.com");
        sale.setSaleDate(LocalDateTime.now());
        sale.setTotalAmount(10L);
        saleRepository.save(sale);

        Long employeeCount = employeeRepository.count();
        Long departmentCount = departmentRepository.count();
        Long productCount = productRepository.count();
        Long saleCount = saleRepository.count();

        System.out.println("number Employees in db: " + employeeCount);
        System.out.println("number Departments in db: " + departmentCount);
        System.out.println("number Products in db: " + productCount);
        System.out.println("number sales in db: " + saleCount);
        System.out.println("fetched Employee id:" + fetched.getId() + " " + fetched.getFirstName());
    }

}
