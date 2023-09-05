package com.sandystack.exp.rest;


import com.sandystack.exp.jms.MessageProducer;
import com.sandystack.exp.model.entities.Department;
import com.sandystack.exp.repository.DepartmentRepository;
import com.sandystack.exp.services.experiments.ExperimentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


@RestController
@AllArgsConstructor
public class ExperimentRestController {


    private DepartmentRepository departmentRepository;
    private MessageProducer producer;
    private ExperimentService experimentService;

    @PostMapping("/experiment/{param}")
    String testEndpoint(@PathVariable String param) throws Exception {

        if (param.equals("0")) {

            experiment1();
            return HttpStatus.OK.name();
        } else if (param.equals("1")) {

            experimentService.memoryAllocationAndGC();
            return HttpStatus.OK.name() + "experiment finished";
        } else {
            return HttpStatus.NOT_FOUND.name() + " - wrong url param value:" + param + ", eg. should be: ../experiment/1";
        }
    }

    /**
     * Just create some Department object, save to database and run async job.
     */
    public void experiment1() {
        Department department = new Department();
        department.setName("MyDepartment-" + new Random().nextInt(0, 5));
        departmentRepository.save(department);

        String message = "Department created in DB" + department.getName();
        producer.sendMessage(message);
    }
}
