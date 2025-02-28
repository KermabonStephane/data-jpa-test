package org.demis27.example.jpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest(properties = {"spring.datasource.url=jdbc:h2:mem:testdb", "spring.hibernate.ddl-auto:none"})
class EmployeeServiceTest {

    @Autowired
    private EmployeeRepository repository;

    private EmployeeService service;

    @BeforeEach
    void setup() {
        service = new EmployeeService(repository);
    }

    @Test
    void test_init() {
        List<Employee> all = service.getAll();

        Assertions.assertThat(all).hasSize(1);
        Assertions.assertThat(all.getFirst().getFirstname()).isEqualTo("John");
    }

}