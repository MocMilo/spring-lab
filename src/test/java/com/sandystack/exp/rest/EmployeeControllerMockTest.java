package com.sandystack.exp.rest;

import com.sandystack.exp.model.dto.EmployeeConverter;
import com.sandystack.exp.model.dto.EmployeeDTO;
import com.sandystack.exp.model.entities.Employee;
import com.sandystack.exp.repository.EmployeeRepository;
import com.sandystack.exp.services.EmployeeService;
import com.sandystack.exp.services.concurrent.ProxyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Example test of rest api controller with
 * MockMvc and fully mocked data in repository
 */
@WebMvcTest(EmployeeController.class)
@ActiveProfiles("test")
public class EmployeeControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeConverter employeeConverter;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testGetEmployeeById_found() throws Exception {

        // given
        prepareMocks();

        // when
        mockMvc.perform(get("/secured/employee/{id}", "someId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        // Here, you can also add more assertions to check the returned Employee content
        // using andExpect(content()....)

        // then
        verify(employeeService, times(1)).fetchEmployeeById(anyString());
    }

    @BeforeEach
    public void prepareMocks() {
        Employee employee = new Employee();
        EmployeeDTO employeeDTO = new EmployeeDTO();

        when(employeeConverter.toDTO(employee)).thenReturn(employeeDTO);
        when(employeeService.fetchEmployeeById(anyString())).thenReturn(employee);
    }

    @TestConfiguration
    static class SecurityBypassConfig {

        // TODO: implement separate security tests

        @MockBean
        private SecurityFilterChain securityFilterChain;
    }


    @MockBean
    private ProxyService proxyService;
}
