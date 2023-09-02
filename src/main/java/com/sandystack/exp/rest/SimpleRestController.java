package com.sandystack.exp.rest;


import com.sandystack.exp.jms.MessageProducer;
import com.sandystack.exp.model.Department;
import com.sandystack.exp.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


@RestController
@AllArgsConstructor
public class SimpleRestController {


    private DepartmentRepository departmentRepository;
    private MessageProducer producer;

    @PostMapping("/test")
    String testEndpoint() {

        Department department = new Department();
        department.setName("MyDepartment-" + new Random().nextInt(0, 5));
        departmentRepository.save(department);

        String message = "Department created in DB" + department.getName();
        producer.sendMessage(message);
        return message;
    }

    public void test(){
        System.out.println("just test");
    }
}
