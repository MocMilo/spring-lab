package com.sandystack.exp.services.concurrent;

import com.sandystack.exp.model.entities.Employee;
import com.sandystack.exp.services.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
@AllArgsConstructor
public class ProxyService {


    EmployeeService employeeService;

    static final String employeeId = "7e604d66-6a9f-4f48-9b5d-01e2e67cfc1a";


    /**
     * This experiment demonstrates optimistic lock exception
     *
     */
    public void saveWithOptimisticLockException() {

        CountDownLatch latch = new CountDownLatch(1);

        Thread thread1 = new Thread(() -> {
            holdThreadWithLatch(latch);
            Employee employee = employeeService.fetchEmployeeById(employeeId);
            log.info("thread 1 red entity:" + employee.getId());

            waitForMilliseconds(5000);
            employee.setTitle("Thread 1 version");

            log.info("Thread 1 try update after while");
            employeeService.save(employee);

        });

        Thread thread2 = new Thread(() -> {

            holdThreadWithLatch(latch);

            waitForMilliseconds(2000);
            Employee employee = employeeService.fetchEmployeeById(employeeId);
            employee.setTitle("title form thread 2");
            employeeService.save(employee);

            log.info("thread 2 updated");
        });

        thread1.start();
        thread2.start();

        latch.countDown();
    }

    private static void holdThreadWithLatch(CountDownLatch latch) {
        try {
            latch.await();  // This will make the thread wait until the latch is counted down
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void waitForMilliseconds(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
