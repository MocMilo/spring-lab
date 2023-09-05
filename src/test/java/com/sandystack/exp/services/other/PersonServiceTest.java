package com.sandystack.exp.services.other;

import com.sandystack.exp.model.dto.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Stream;

@SpringBootTest
@ActiveProfiles("test")
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    // PARAMETRIZED TESTS
    @ParameterizedTest
    @MethodSource("providePersons")
    void isEligibleForTheProgram(Person person, boolean expectedEligibility) {

        // given
        // when
        boolean actual = personService.isEligibleForProgram(person);
        // then
        Assertions.assertEquals(expectedEligibility, actual);
    }

    static Stream<Arguments> providePersons() {
        return Stream.of(
                Arguments.of(new Person("Alice", 21), true),
                Arguments.of(new Person("Tom", 17), false),
                Arguments.of(new Person("Andrea", 68), false));
    }
}