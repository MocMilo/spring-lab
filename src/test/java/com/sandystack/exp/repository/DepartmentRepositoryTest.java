package com.sandystack.exp.repository;

import com.sandystack.exp.model.entities.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;

/**
 * Example test with Repository with data inserted from sql scripts
 */
@SpringBootTest
@ActiveProfiles("test")
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/sql/initDB.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/sql/dataDB.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/sql/clearDB.sql")
})
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void createTest() {
        // given
        // when
        List<Department> departments = departmentRepository.findAll();
        departments.forEach(x -> System.out.println("department" + x.getName()));

        // then
        Assertions.assertFalse(departments.isEmpty());
    }
}