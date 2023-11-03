package com.sandystack.exp.rest;

import com.sandystack.exp.model.entities.Employee;
import com.sandystack.exp.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Example test of rest api controller with
 * MockMvc and scripts sql data in repository.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/sql/initDB.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/sql/dataDB.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/sql/clearDB.sql")
})
class EmployeeControllerSqlDataTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetEmployeeById_found() throws Exception {

        // given
        Employee employee = employeeRepository.findById("uuid_e0001").get();

        // when
        // then
        mockMvc.perform(get("/secured/employee/{id}", "uuid_e0001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(employee.getId()))
                .andExpect(jsonPath("$.firstName").value(employee.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(employee.getLastName()))
                .andExpect(jsonPath("$.salary").value(employee.getSalary()))
                .andExpect(jsonPath("$.phone").value(employee.getPhone()))
                .andExpect(jsonPath("$.email").value(employee.getEmail()));
    }

    @TestConfiguration
    static class SecurityBypassConfig {

        @MockBean
        private SecurityFilterChain securityFilterChain;
    }
}

