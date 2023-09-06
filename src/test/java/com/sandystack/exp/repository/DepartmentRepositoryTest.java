package com.sandystack.exp.repository;

import com.sandystack.exp.model.entities.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "/sql/initDB.sql")
@Sql(scripts = "/sql/dataDB.sql")
@Sql(scripts = "/sql/clearDB.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void createTest() {
        List<Department> departments = departmentRepository.findAll();
        departments.forEach(x -> System.out.println("department" + x.getName()));
    }


}