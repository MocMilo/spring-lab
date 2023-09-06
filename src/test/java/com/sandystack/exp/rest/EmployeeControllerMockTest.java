package com.sandystack.exp.rest;

import com.sandystack.exp.model.entities.Employee;
import com.sandystack.exp.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
@ActiveProfiles("test")
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetEmployeeById_found() throws Exception {

        // given
        Employee employee = new Employee();

        // when
        when(employeeRepository.findById(anyString())).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/employee/{id}", "someId")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        // Here, you can also add more assertions to check the returned Employee content using andExpect(content()....)

        // then
        verify(employeeRepository, times(1)).findById(anyString());
    }
}
