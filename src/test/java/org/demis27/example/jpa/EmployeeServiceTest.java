package org.demis27.example.jpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
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
    @DisplayName("We should have one employee create by the data.sql script")
    void test_init() {
        List<Employee> all = service.getAll();

        Assertions.assertThat(all).hasSize(1);
        Employee johnByrne = all.getFirst();
        Assertions.assertThat(johnByrne.getFirstname()).isEqualTo("John");
        Assertions.assertThat(johnByrne.getLastname()).isEqualTo("Byrne");
    }

    @Test
    @Sql(statements = "INSERT INTO employee (id, firstname, lastname) VALUES (random_uuid(), 'Chris', 'Claremont');")
    @DisplayName("We should have two employees, one create by the data.sql and one by the statement")
    void test_statement() {
        List<Employee> all = service.getAll();

        Assertions.assertThat(all).hasSize(2);
    }

    @Test
    @Sql(scripts = "data-test.sql")
    @DisplayName("We should have three employees, one create by the data.sql and two by the specific script")
    void test_script() {
        List<Employee> all = service.getAll();

        Assertions.assertThat(all).hasSize(3);
    }

}