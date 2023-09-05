package com.sandystack.exp.services.other;

import com.sandystack.exp.model.dto.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    public boolean isEligibleForProgram(Person person) {

        return person.age() >= 18 && person.age() < 65;
    }
}
